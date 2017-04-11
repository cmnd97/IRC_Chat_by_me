/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import seriac.poo.structs.Message;
import seriac.poo.structs.PrivateMessage;

/**
 *
 * @author student
 */
public class ServerPeer extends Thread implements Serializable {

    Server server;
    Socket connsocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    String Username;
    int id;

    public ServerPeer(Server _server, Socket socket, int _id) {
        connsocket = socket;
        server = _server;
        id = _id;
        try {
            this.ois = new ObjectInputStream(connsocket.getInputStream());
            this.oos = new ObjectOutputStream(connsocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private String getUsername(Message msg) {
        this.Username = msg.getSender();
        return Username;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Message msg = (Message) ois.readObject();
                getUsername(msg);
                server.dispatch(msg);
            } catch (Exception ex) {
                try {
                    PrivateMessage pmsg = (PrivateMessage) ois.readObject();
                    pmsg.getRecipient();
                    server.dispatch(pmsg);
                } catch (Exception e) {
                    System.out.println(e.toString());
                    server.removeClient(server.svp, id);
                    break;
                }
            }
        }
    }

}
