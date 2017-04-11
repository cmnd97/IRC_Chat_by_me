/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.structs;
import seriac.poo.server.ServerConfig;
/**
 *
 * @author cristi-mnd
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Message mesaj1 = new Message("OP", "salut!");
        Message mesaj2 = new Message("Andrei", "ce mai faci?");
        Message mesaj3 = new Message("Sergiu", "esti ko?");
        Message mesaj4 = new Message("Mesaj de la unknown");
        System.out.println(mesaj1.toString());
        System.out.println(mesaj2.toString());
        System.out.println(mesaj3.toString());
        System.out.println(mesaj4.toString());

        PrivateMessage pm1 = new PrivateMessage("Andrei", "OP", "ce are Sergiu?");
        System.out.println(pm1.toString());
        System.out.println(pm1.getRecipient());
        PrivateMessage pm2 = new PrivateMessage("OP", "Andrei", "a picat la POO");
        System.out.println(pm2.toString());
        System.out.println(pm2.getRecipient());
        PrivateMessage pm3 = new PrivateMessage("Sergiu", "OP", "ce praf esti");
        System.out.println(pm3.toString());
        System.out.println(pm3.getRecipient());
        
        ServerConfig svc1= new ServerConfig("Server.conf");
        svc1.afisare();
        
    }

}
