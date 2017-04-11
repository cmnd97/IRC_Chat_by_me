/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.structs;

import java.io.Serializable;

/**
 *
 * @author cristi-mnd
 */
public class Message implements Serializable{
    private String NumeExpeditor;
    private final String Mesaj;

public Message(String _NumeExpeditor, String _Mesaj)
{
NumeExpeditor = _NumeExpeditor;
Mesaj= _Mesaj;
}
public Message (String _Mesaj)
{
    NumeExpeditor = "Unknown";
    Mesaj = _Mesaj;
}

public String getSender()
{
    return NumeExpeditor;
}
@Override
public String toString()
{
return NumeExpeditor + ": " + Mesaj;
}
}