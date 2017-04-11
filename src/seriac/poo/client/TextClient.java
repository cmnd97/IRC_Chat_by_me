/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.client;

import java.io.*;
import java.net.*;

/**
 *
 * @author student
 */
public class TextClient {

    public static void main(String[] args) {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Cum te cheama?");
            String NumeUser = in.readLine();
            if (NumeUser.equals("")) {
                NumeUser = "unknown";
            }
            Socket clientSocket = new Socket("192.168.1.19", 9000);
            ClientPeer cp = new ClientPeer(NumeUser, clientSocket);
            System.out.println("Te-ai conectat cu succes la server! Poti incepe sa trimiti mesaje...");

            Thread CPThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cp.receive();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            });
            CPThread.start();
            cp.sendMessage("* Joined the Server* ");
            String UserInput = in.readLine();
            while (!(UserInput.startsWith("/q"))) {
                if (UserInput.startsWith("/q")) {
                    break;
                } else {
                    if (UserInput.startsWith("/w")) {
                        String[] parts = UserInput.split(" ", 3);
                        String part1 = parts[0];
                        String part2 = parts[1];
                        String part3 = parts[2];
                        cp.sendMessage(part3, part2);

                    } else {
                        cp.sendMessage(UserInput);
                    }
                }
                UserInput = in.readLine();
            }
            clientSocket.close();
            CPThread.join();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
