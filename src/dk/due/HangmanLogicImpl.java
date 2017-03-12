package dk.due;

import soap.Brugeradmin;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by mortenduechristiansen on 01/02/2017.
 */
@WebService(endpointInterface = "dk.due.HangmanLogic")
public class HangmanLogicImpl implements HangmanLogic{

    Service brugerService;
    Brugeradmin ba;

    HashMap<String, Galgelogik> logik;

    public HangmanLogicImpl()
    {
        logik = new HashMap<>();
        try {
            URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
            QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
            brugerService = Service.create(url, qname);
            ba = brugerService.getPort(Brugeradmin.class);

        }catch(MalformedURLException e){
            e.printStackTrace();
            System.err.println("Url ukorrekt");
        }
    }

    public boolean validateUser(String username, String password){

        Bruger b = ba.hentBruger(username, password);
        if(b.brugernavn.equals(username)) {
            return true;
        }
        return false;
    }


    // connectiong to galgeleg, getting a unique id, this way more clients can use the server at the same time
    // because each gets their own logik
    public String connect(String username, String password)
    {
        if(!validateUser(username, password)){
            return "Error";
        }

        String uId = UUID.randomUUID().toString();
        logik.put(uId, new Galgelogik());
        return uId;
    }

    public String getSynligtOrd(String id){
        return logik.get(id).getSynligtOrd();
    }
    public int getAntalForkerteBogstaver(String id){
        return logik.get(id).getAntalForkerteBogstaver();
    }
    public ArrayList<String> getBrugteBogstaver(String id){
        return logik.get(id).getBrugteBogstaver();
    }

    public void guessLetter(String id,String letter){
        logik.get(id).gætBogstav(letter);
    }

    public boolean isGameOver(String id){
        return logik.get(id).erSpilletSlut();
    }

    public boolean isGameWon(String id){
        return logik.get(id).erSpilletVundet();
    }

    public void logout(String id){
        logik.remove(id);
    }

    public void nulstil(String id) {logik.get(id).nulstil();}

    public String getOrdet(String id){return logik.get(id).getOrdet();}




}
