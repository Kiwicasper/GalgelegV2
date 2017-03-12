package dk.due;

import javax.xml.ws.Endpoint;

/**
 * Created by mortenduechristiansen on 01/02/2017.
 */
public class HangmanServer {

    public static void main(String[] args) throws Exception
    {
        HangmanLogicImpl hl = new HangmanLogicImpl();
        Endpoint.publish("http://[::]:8511/hangman", hl);
        System.out.println("Hangman server publiceret over SOAP");
    }
}
