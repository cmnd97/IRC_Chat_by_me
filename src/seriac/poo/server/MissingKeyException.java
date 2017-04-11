/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server;

/**
 *
 * @author cristi-mnd
 */
public class MissingKeyException extends Exception {

    public MissingKeyException() {
        super("Missing Key!");
    }
}
