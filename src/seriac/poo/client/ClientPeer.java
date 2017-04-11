/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.client;

import java.io.*;
import java.net.*;
import seriac.poo.structs.Message;
import seriac.poo.structs.PrivateMessage;

/**
 *
 * @author student
 */
public class ClientPeer implements Serializable {

    public final Socket clientSocket;
    public final String NumeUser;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;

    public ClientPeer(String _NumeUser, Socket _clientSocket) {
        clientSocket = _clientSocket;
        NumeUser = _NumeUser;
        try {
            this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
            this.ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    void sendMessage(String message) throws IOException {
        Message msg1 = new Message(NumeUser, message);
        oos.writeObject(msg1);
    }

    void sendMessage(String message, String recipient) throws IOException {
        PrivateMessage pmsg1 = new PrivateMessage(recipient, NumeUser, message);
        oos.writeObject(pmsg1);

    }

    void receive() throws IOException {
        while (true) {

            try {
                Message msg = (Message) ois.readObject();
                System.out.println(msg.toString());
            } catch (Exception ex) {
                try {
                    PrivateMessage pmsg = (PrivateMessage) ois.readObject();
                    System.out.println(pmsg.toString());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    break;
                }
            }
        }
    }
}
