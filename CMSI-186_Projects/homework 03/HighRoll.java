import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

    static DiceSet dieGame = null;
    static int highscore = 0;

    public static void main (String args[] ) {
        if (0 == args.length) {
            System.out.println("USAGE: java HighRoll <#dice> <#sides>");
            System.exit(-1);
        }
        dieGame = new DiceSet (Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        while(true) {
            System.out.println(" 1. select to roll all the dice\n" +
            "2. select to roll one die\n" +
            "3. select to calculate the sum\n" +
            "4. select to save your sum as a high score\n" +
            "5. select to display the high score\n" +
            "6. select to quit");
            System.out.print(">>");


            BufferedReader input = new BufferedReader( new InputStreamReader (System.in));


            String inputLine = null;
            try {
                inputLine = input.readLine();
                if (0 == inputLine.length()) {
                    System.out.println(" text por favor !! ");
                }
                if ('1' == inputLine.charAt(0)) {
                    dieGame.roll();
                    System.out.println(dieGame.toString());
                } else if ('2' == inputLine.charAt(0)) {
                    System.out.println("Which dice do you want to roll?");
                    inputLine = input.readLine();
                    System.out.println(dieGame.rollIndividual(Integer.parseInt(inputLine) - 1));
                    System.out.println(dieGame.toString());
                }else if ('3' == inputLine.charAt(0)) {
                    System.out.println(dieGame.sum());
                    System.out.println(dieGame.toString());
                }else if ('4' == inputLine.charAt(0)) {
                    highscore = dieGame.sum();
                    System.out.println(dieGame.toString());
                }else if ('5' == inputLine.charAt(0)) {
                    System.out.println(" Your highscore is: " + highscore);
                    System.out.println(dieGame.toString());
                }else if('6' == inputLine.charAt(0)) {
                    System.exit(0);
                }
            } catch(IOException ioe) {
                System.out.println("Caught IOException");

            }
        }
    }
}
