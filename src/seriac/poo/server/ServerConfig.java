/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author cristi-mnd
 */
public class ServerConfig {

    public String NumeFisier;
    public int MAX_CLIENTS;
    public int TCP_PORT;

    public int getConfig(int id) {
        int valoare = 0;
        try {
            FileReader citeste = new FileReader(NumeFisier);
            BufferedReader citire = new BufferedReader(citeste);
            String line = null;

            while ((line = citire.readLine()) != null) {
                if (!(line.contains("#"))) {
                    if (line.contains("MAX_CLIENTS=") && id == 1) {
                        valoare = Integer.parseInt(line.replaceAll("[\\D]", ""));
                        break;
                    }
                    if (line.contains("TCP_PORT=") && id == 2) {
                        valoare = Integer.parseInt(line.replaceAll("[\\D]", ""));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("fisier negasit!");
        }
        return valoare;
    }

    public ServerConfig(String _NumeFisier) {
        NumeFisier = _NumeFisier;
        MAX_CLIENTS = getConfig(1);
        TCP_PORT = getConfig(2);
    }

    public ServerConfig() {
        NumeFisier = "Server.conf";

    }

    public void afisare() {
        System.out.println("NumeleFisierului= " + NumeFisier);
        System.out.println("MAX_CLIENTS= " + MAX_CLIENTS);
        System.out.println("TCP_PORT= " + TCP_PORT);
    }

}
