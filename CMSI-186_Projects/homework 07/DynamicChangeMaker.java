/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    : KeAnna Anglin
 * Date       :  05-02-2018
 Description  : My program takes in two parameters and uses dynamic programming to make a target amount of change.
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

/* Program employs dynamic programming to find the most optimal solution for a given coin denomination*/

public class DynamicChangeMaker{

    private static int[] denoms = null;
    private static Tuple denomsTuple = null;
    private static Tuple[][] Table = null;

    public DynamicChangeMaker(){
        super();
    }

    /* boolean method to invalidate denom that are less than or equal to zero*/

    public static boolean validateChange(int [] denom) {
        for (int i = 0; i < denom.length; i++) {
            if(denom[i] <= 0) {
                return true;
            }
        }
        return false;
    }
    /* boolean method to make sure change that is less than zero is not allowed*/
    public static boolean checkTarget(int change) {
        return change < 0;
    }

    private static void fillColumnsWithZeros(Tuple[][] Table, int[] coins) {
      for (int i = 0; i < coins.length; i++) {
          Table[i][0] = new Tuple(coins.length);
      }
    }
    /* the following code returns the optimal way for making change!!
     @param denom is the integer array of denom you need an optimal solution for
     @param change is for the # of change one desires to make
     a tuple is returned with number of each coin needed to make the target amount*/

     public static Tuple makeChangeWithDynamicProgramming(int[] denom, int change) {
      if (validateChange(denom)) {return Tuple.IMPOSSIBLE;}
      if (checkTarget(change)) { return Tuple.IMPOSSIBLE; }

      int row = denom.length;
      int column = change + 1;
      Tuple[][] Table = new Tuple[row][column];

      fillColumnsWithZeros(Table, denom);

      for (int i = 0; i < row; i++) {
          for (int j = 1; j < column; j++) {
              if (denom[i] > j) {
                  Table[i][j] = Tuple.IMPOSSIBLE;
              } else {
                  Table[i][j] = new Tuple(denom.length);
                  Table[i][j].setElement(i, 1);
                  if (Table[i][j - denom[i]].isImpossible()){
                      Table[i][j] = Tuple.IMPOSSIBLE;
                  } else {
                      Table[i][j] = Table[i][j].add(Table[i][j - denom[i]]);
                  }
              }
              if (i != 0) {
                  if (!(Table[i-1][j].isImpossible())) {
                      if(Table[i][j].isImpossible()) {
                          Table[i][j] = Table[i-1][j];
                      }
                      if (Table[i-1][j].total() < Table[i][j].total()) {
                          Table[i][j] = Table[i-1][j];
                      }
                  }
              }
          }
      }
      Tuple result = Table[row-1][column-1];
      return result;
  }
/*tests and stuff*/

    public static void main(String args[]){
        if ( args.length >= 2 ){
         denoms = new int[args.length - 1];
         for ( int i = 0; i < args.length - 1; i++ ){
            denoms[i] = Integer.parseInt(args[i]);
         }
         System.out.println( "Minimum coins required is " + makeChangeWithDynamicProgramming(denoms, Integer.parseInt(args[args.length - 1]) ));
      } else {
         System.out.println( "\n   These are not valid arguments silly goose !!! " );
         System.out.println( "   Gimme an integer array of arguments, all must have a value greater than zero !! The first argument must be an integer array of arguments with a value greater than or equal to 0\n" );
         System.out.println( "   The second argument is the change you wish to make!! ");
      }
   }
}
