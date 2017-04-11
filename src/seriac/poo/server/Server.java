/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server;

import java.util.ArrayList;
import java.net.*;
import seriac.poo.structs.Message;
import seriac.poo.structs.PrivateMessage;

/**
 *
 * @author student
 */
public class Server {

    int clients;
    int tcp_port;
    int max_clients;
    ServerSocket serverSocket;
    ArrayList<ServerPeer> svp = new ArrayList<ServerPeer>();
    public Server(ServerConfig svc) {
        try {
            clients = 0;
            tcp_port = svc.getConfig(2);
            max_clients = svc.getConfig(1);
            serverSocket = new ServerSocket(tcp_port);

        } catch (Exception e) {
            System.out.println(e.toString() + "--block 1");
        }
    }

    public static void main(String[] args) {
        try {
            ServerConfig svc = new ServerConfig();
            Server server1 = new Server(svc);
            
            server1.listen(server1);
        } catch (Exception e) {
            System.out.println(e.toString() + "--block 2");
        }
    }

    public void listen(Server server) {
        System.out.println("Astept conexiuni...");
            while(clients<max_clients)
            {
            try {
                svp.add(new ServerPeer(server, serverSocket.accept(), clients));
                svp.get(clients).start();
                clients++;
                System.out.println("S-a conectat clientul " + clients + " !");
            } catch (Exception e) {
                System.out.println(e.toString() + "--block 3");
                break;
            }
        }
    }

    void dispatch(Message msg) {
        if (msg instanceof PrivateMessage) {
            for (int i = 0; i < clients; i++) {
                if (((PrivateMessage) msg).getRecipient().equals(svp.get(i).Username)) {
                    svp.get(i).sendMessage(msg);
                }
            }
        } else if (msg instanceof Message) {
            for (int i = 0; i < clients; i++) {
                svp.get(i).sendMessage(msg);
            }
        }
    }

    void removeClient(ArrayList<ServerPeer> svp, int id) {
        Message anunt = new Message(svp.get(id).Username, ": *Disconnected from Server* ");
        dispatch(anunt);
        svp.remove(id);
        clients--;
        for (int i = id; i < clients; i++) {
            svp.get(i).id--;           
        }
    }
}
