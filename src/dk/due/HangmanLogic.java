package dk.due;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

/**
 * Created by mortenduechristiansen on 01/02/2017.
 */
@WebService
public interface HangmanLogic extends java.rmi.Remote{


    @WebMethod String connect(String username, String password);
    @WebMethod String getSynligtOrd(String id);
    @WebMethod ArrayList<String> getBrugteBogstaver(String id);
    @WebMethod int getAntalForkerteBogstaver(String id);
    @WebMethod void guessLetter(String id, String letter);
    @WebMethod boolean isGameOver(String id);
    @WebMethod boolean isGameWon(String id);
    @WebMethod void logout(String id);
    @WebMethod void nulstil(String id);
    @WebMethod String getOrdet(String id);
}
