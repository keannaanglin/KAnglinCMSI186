
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  MainProgLoopDemo.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-14
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

   static DiceSet dieGame = null;
   static int highscore = 0;

   public static void main( String args[] ) {
      if(0 == args.length) {
          System.out.println("USAGE: java HighRoll <#dice> <#sides>");
          System.exit(-1);
      }
      dieGame = new DiceSet(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      while(true){
         System.out.println( "1. roll all the dice\n" +
                             "2. roll one die\n" +
                             "3. calculate the sum\n" +
                             "4. save the sum as high score\n" +
                             "5. display the high score\n" +
                             "6. Quit" );
         System.out.print(">>");


     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );


           String inputLine = null;
           try {
              inputLine = input.readLine();
              if( 0 == inputLine.length()) {
                 System.out.println("enter some text!:");
              }
              if('1' == inputLine.charAt(0)) {
                  dieGame.roll();
                  System.out.println(dieGame.toString());
              } else if('2' == inputLine.charAt(0)) {
                  System.out.println("Which dice do you want to roll?");
                  inputLine = input.readLine();
                  System.out.println(dieGame.rollIndividual(Integer.parseInt(inputLine) - 1));
                  System.out.println(dieGame.toString());
              } else if('3' == inputLine.charAt(0)) {
                  System.out.println(dieGame.sum());
                  System.out.println(dieGame.toString());
              } else if('4' == inputLine.charAt(0)) {
                  highscore = dieGame.sum();
                  System.out.println(dieGame.toString());
              } else if('5' == inputLine.charAt(0)) {
                  System.out.println("The highscore is:" + highscore);
                  System.out.println(dieGame.toString());
              } else if('6' == inputLine.charAt(0)) {
                  System.exit(0);
              }


         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
