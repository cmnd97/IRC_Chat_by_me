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
public class PrivateMessage extends Message implements Serializable
{
    private final String NumeDestinatar;
    
    public PrivateMessage(String _NumeDestinatar, String _NumeExpeditor, String _Mesaj)
    {
     super(_NumeExpeditor, _Mesaj);
     NumeDestinatar=_NumeDestinatar;
    }
    public String getRecipient()
    {
     return NumeDestinatar;   
    }
    @Override
    public String toString()
            {
                return "(priv)" + super.toString();
            }
}
