package dk.due;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class HangmanClient {

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("http://localhost:3000/hangman?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        QName qname = new QName("http://due.dk/", "HangmanLogicImplService");
        Service service = Service.create(url, qname);
        HangmanLogic logic = service.getPort(HangmanLogic.class);


        String connectionId = null;
        Scanner reader = new Scanner(System.in);
        // Login
        System.out.print("Skriv brugernavn: ");
        String username = reader.nextLine();
        System.out.print("Skriv password: ");
        String password = reader.nextLine();
        try {
            connectionId = logic.connect(username, password);
        } catch(Exception e){

            System.out.println("Fejl i login, programmet lukker");
            return;
        }
        System.out.println("*******************************************************");
        System.out.println("* Velkommen til Hangman! Du er forbundet til serveren *");
        System.out.println("*******************************************************");
        System.out.print("\n\n");

        while(true){
            System.out.print("\n\n\n\n");
            System.out.println("*******************************************************\n");
            printHangman(logic.getAntalForkerteBogstaver(connectionId));
            System.out.println();
            System.out.println("Ordet der skal gættes: " + logic.getSynligtOrd(connectionId) + "\n");

            List<String> bogstaver = logic.getBrugteBogstaver(connectionId);
            System.out.print("Forkerte Bogstaver: ");
            for(String s: bogstaver)
                System.out.print(s + ", ");
            String guess;
            boolean correct;
            do {
                correct = true;
                System.out.println();
                System.out.print("Gæt! skriv et bogstav: ");
                guess = reader.nextLine();
                for (String s : bogstaver) {
                    if (s.equals(guess)) {
                        System.out.println("Bogstav allerede brugt!");
                        correct = false;
                    }
                }
            } while (!correct);

            logic.guessLetter(connectionId, guess);

            if(logic.isGameWon(connectionId)){

                if(logic.isGameWon(connectionId)){
                    System.out.println("************************************");
                    System.out.println("* Du har vundet!!! Spillet er slut *");
                    System.out.println("************************************");
                }else{
                    System.out.println("**********************************");
                    System.out.println("* Du har tabt!!! Spillet er slut *");
                    System.out.println("**********************************");
                }
                logic.logout(connectionId);
                return;

            }
        }

    }

    private static void printHangman(int imageNo){
        switch(imageNo){
            case 0:
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("############");
                break;
            case 1:
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 2:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 3:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |     #");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 4:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |     #");
                System.out.println("  | #########");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 5:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |     #");
                System.out.println("  | #########");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 6:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |     #");
                System.out.println("  | #########");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |    #");
                System.out.println("  |    #");
                System.out.println("  |   ##");
                System.out.println("  |");
                System.out.println("############");
                break;
            case 7:
                System.out.println("  |------");
                System.out.println("  |     |");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |     #");
                System.out.println("  | #########");
                System.out.println("  |    ###");
                System.out.println("  |    ###");
                System.out.println("  |    # #");
                System.out.println("  |    # #");
                System.out.println("  |   ## ##");
                System.out.println("  |");
                System.out.println("############");
                break;

        }

    }
}
