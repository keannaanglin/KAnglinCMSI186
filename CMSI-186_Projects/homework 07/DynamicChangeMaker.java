/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    : KeAnna Anglin
 * Date       :  05-02-2018
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;


public class DynamicChangeMaker{

    private static int[] denoms = null;
    private static Tuple denomsTuple = null;
    private static Tuple[][] Table = null;


    public DynamicChangeMaker(){
        super();
    }

    public static Tuple makeChangeWithDynamicProgramming(int [] denom, int change){
        int size = denom.length;
        if(change <= 0){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size; i++){
            for (int j = i + 1; j < size; j++){
                if (denom[i] == denom[j] || denom[i] <= 0 || denom[j] <= 0 || denom.length < 1){
                    throw new IllegalArgumentException();
                }
            }
        }

        Table = new Tuple[size][change + 1];
        for (int row = 0; row < size; row++){
            for (int col = 0; col < change + 1; col++){
                if (col == 0){
                    Table[row][col] = new Tuple(size);
                } else {
                    if (col < denom[row]){
                        Table[row][col] = new Tuple(new int[0]);
                        if (col - denom[row] >= 0){
                            if (!(Table[row][col-denom[row]].isImpossible())){
                                Table[row][col].add(Table[row][col - denom[row]]);
                            }
                        }
                        if (row != 0){
                            if (!(Table[row - 1][col].isImpossible())){
                                if(Table[row][col].isImpossible() || Table[row - 1][col].total() < Table[row][col].total()){
                                    Table[row][col] = Table[row - 1][col];
                                }
                            }
                        }
                    } else {
                        Table[row][col] = new Tuple(size);
                        Table[row][col].setElement(row, 1);
                        if (col - denom[row] >= 0){
                            if(Table[row][col - denom[row]].isImpossible()){
                                Table[row][col] = new Tuple(new int[0]);
                            } else {
                                Table[row][col] = Table[row][col].add(Table[row][col - denom[row]]);
                            }
                        }
                        if (row != 0){
                            if(!(Table[row - 1][col].isImpossible())){
                                if(Table[row][col].isImpossible() || Table[row - 1][col].total() < Table[row][col].total()){
                                    Table[row][col] = Table[row - 1][col];
                                }
                            }
                        }
                    }
                }
            }
        }
        return Table[size -1][change];
    }


    public static void main(String args[]){
        if ( args.length >= 2 ){
         denoms = new int[args.length - 1];
         for ( int i = 0; i < args.length - 1; i++ ){
            denoms[i] = Integer.parseInt(args[i]);
         }
         System.out.println( "Minimum coins required is " + makeChangeWithDynamicProgramming(denoms, Integer.parseInt(args[args.length - 1]) ));
      } else {
         System.out.println( "\n   Sorry, the arguments you entered are invalid." );
         System.out.println( "   The first argument must be an integer array of arguments with a value greater than or equal to 0\n" );
         System.out.println( "   For the second argument enter the change you want to make (must be a positive number)");
      }
   }
}
