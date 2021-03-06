/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;
//import java.math.BigInteger;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   /*public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );*/

  /// These are the internal fields
   private String binaryValue = "";        // internal String representation of this GinormousInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string
   private String inputValue = "";

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a GinormousInt
   */
   public BrobInt( String value ) {
       inputValue = value;  // HARDCODED FOR NOW. SHOULD BE  = value;
       validateDigits();

       String quotient = inputValue;

       // need to set sign
        if(quotient.charAt(0) == '-') {
            sign = 1;
            quotient = quotient.substring(1, quotient.length());
        }
        ArrayList <String> parts = new ArrayList<String>();
        for(int i = 0; i < quotient.length(); i += 8) {
            parts.add(quotient.substring(i, Math.min(i + 8, quotient.length())));
        }
        binaryValue = "";
        reversed = "";
        for(int i=0; i < parts.size(); i++) {
            int temp = Integer.parseInt(parts.get(i));
            if(i == 0 && temp == 0) {
                reversed += "0";
            }
            while(temp != 0) {
                int lastNum = temp % 10;
                //char lastChar = temp.charAt(temp.size() - 1);
                if(lastNum % 2 == 0){
                    // remainder is zero so quotient is even
                    reversed = reversed + "0";
                } else {
                    //  remainder is ONE
                    reversed = reversed + "1";
                }
                temp = Integer.parseInt(divideByTwo(temp + ""));
            }
       }
       binaryValue = reverser((reversed));
   }
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to divide a String by two
    *  @return  result of the long division
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String divideByTwo(String dividend) {
       String result = "";
       // LONG DIVISION
       //look at first number of dividend, that number = current
       int current = Character.getNumericValue(dividend.charAt(0));
       int nextIndex = 1;
       Boolean hasNext = dividend.length() > nextIndex;
       //See how many times divisor goes into current
       int quotient = current / 2;
       result = result + Integer.toString(quotient);

       while (hasNext){
           int next = Character.getNumericValue(dividend.charAt(nextIndex));
           // multiply the divisor by the number added to result and subtract from current
           current = current - (2 * quotient);
           //pull down next number in dividend and add to end of current and repeat
           current = ((current * 10) + next);
           quotient = current / 2;
           // add that number to result
           result = result + Integer.toString(quotient);
           nextIndex += 1;
           hasNext = dividend.length() > nextIndex;
       }
       return result;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
       String inputValue = this.inputValue;
       for (int i = 0; i < inputValue.length(); i++) {
           char Digit = inputValue.charAt(i);
            if (i == 0 && !(Character.isDigit(Digit) || Digit == '-')) {
                throw new UnsupportedOperationException("This is not valid !!");
            }else if (i != 0 && !Character.isDigit(Digit)) {
               throw new UnsupportedOperationException("This is not valid!!");
           }
       }
       return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this GinormousInt
   *  @return GinormousInt that is the reverse of the value of this GinormousInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt WrongReverser() {
       //convert internal value to base 10 and then reverse the digits //
       BrobInt obj = new BrobInt("0");
       for (int i = this.reversed.length() - 1; i >= 0; i--) { //need to incorporate throw UnsupportedOperationException//
           this.binaryValue = this.binaryValue + this.reversed.charAt(i);
       }
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a GinormousIntk passed as argument
   *  Note: static method
   *  @param  gint         GinormousInt to reverse its value
   *  @return GinormousInt that is the reverse of the value of the GinormousInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static String reverser( String stringstring ) {
      String inputValue = stringstring;
      String reversedValue = "";
       for (int i = stringstring.length() - 1; i >= 0; i--){
           reversedValue = reversedValue + stringstring.charAt(i);
       }
      return reversedValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a GinormousIntk passed as argument to this GinormousInt
   *  @param  gint         GinormousInt to add to this
   *  @return GinormousInt that is the sum of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
       String input1 = binaryValue; // e.g. "11100001"
       String input2 = bint.toBinary();
       System.out.println("We are adding " + input1 + " and " + input2);
       while (input1.length() > input2.length()){
           input2 = "0" + input2;
       }
       while (input2.length() > input1.length()) {
           input1 = "0" + input1;
       }
       String sum = "";
       int carry = 0;

       for(int i= input1.length()-1; i >= 0; i--) {
           int almostSum = Character.getNumericValue(input1.charAt(i)) + Character.getNumericValue(input2.charAt(i)) + carry;
           if(almostSum == 0) {
               sum = "0" + sum;
               carry = 0;
           }else if (almostSum == 1) {
               sum = "1" + sum;
               carry = 0;
           }else if (almostSum == 2) {
               sum = "0" + sum;
               carry = 1;
           }else if (almostSum == 3) {
              sum = "1" + sum;
               carry = 1;
           }

       }
       if(carry == 1) {
           sum = "1" + sum;
       }

       //System.out.println("The binary answer is " + sum);
       sum = binaryToDecimal(sum);
       return new BrobInt(sum);

    }
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to convert decimal number to its binary equivalent
     *  @return decimal number that is in its binary equivalent
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
     public String toBinary() {
      return this.binaryValue;
    }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return decimal version of binary value
   *  @return BigInteger that is the decimal version of the answer
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public String toDecimal() {
    return this.inputValue;
     }
     /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to convert binary back to decimal
     *  @return number in decimal form
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

   public static String binaryToDecimal( String args ) {
      String result = Integer.toString( Integer.parseInt( args, 2 ) );
      return result;
   }
     public static String binaryToDecimal2(String binary) {
             int[] intArray = new int[0];
             String result = "";
             // int chunks = (binary.length() / 26) + 1;
             System.out.println("did i get 8 chunks?");
             // System.out.println(chunks);
             int j = 0;
             for( int i = 0; i < binary.length(); i+=26 ) {
                 intArray[j] = Integer.parseInt( binary.substring( i, i+26 ), 2 );
                 j++;
             }

             for ( int k = 0; k < intArray.length; k++ ) {
                 result += Integer.toString(intArray[k]);
             }
             System.out.println("result?");
             System.out.println(result);
             return result;

            // return Integer.parseInt(binary.substring(i, Math.min(i + 26, binary.length())), 2);
        //      } j++;
        //  }
        //  for(int k = 0; k < subChunk.length(); k++) {
        //      subChunk[k] = Integer.parseInt(subChunk[k], 2);
        //  }
        //
        // for(int i = 0; i < chunks; i++) {
        //     stringChunk[i] = Arrays.toString(subChunk[i]);
        // }
        // while (stringChunk.length < 8) {
        //     for ( int i = stringChunk.length; i < 8; i++ ) {
        //         stringChunk[i] = "0";
        //     }
        // }
        // for(int i = 0; i < chunks; i++) {
        //     result = result + Arrays.toString(stringChunk[i]);
        // }
        // return result;
        //  //I know that i need to account for multiple binary string inputs, but i am not sure how to implement that.//
        // for(int i = 0; i < binary.length(); i++) {
        //     result += new String(Integer.valueOf(Integer.parseInt(binary, 2)).toString());
        // }
        // System.out.println("The decimal version is " + result);

         /*System.out.println(new BigInteger(binary, 2).toString());*/

         /*for(int i = binaryValue) {
             System.out.println("binary string: " + binary + "in decimal is " + Integer.parseInt(binary, 2));
         }
         System.out.println("concatenated value is: " +
         new String (Integer.valueOf(Integer.parseInt(binary, 2).toString())));
         System.exit( 0 );
        /* BrobInt sum = new BrobInt("0");
         for(int i = binary.length() - 1; i >= 0; i--) {
             if (binary.charAt(i) == '1') {
                 BrobInt partialSum = new BrobInt("" + (int)Math.pow(2, ((binary.length() - i) -1)));
                 System.out.println("Partial sum is " + partialSum);
                 sum = sum.add(partialSum);
                 System.out.println("Sum is " + sum);
             }
         }*/
         //return sum.toString();
         // String number = "0";
         // int i = 0;
         // while (i < binary.length()) {
         //     // multiply number by two
         //     number = multByTwo(number);
         //     // add next digit
         //     if (binary.charAt(i) == '1') {
         //        number = addOne(number);
         //     }
         //     i++;
         // }
         // return number;*/
         // throw new UnsupportedOperationException("  this has not been implemented ");
     }
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to add one (part of converting back to decimal)
     *  @param  String decimal
     *  @return sum
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
     public static String addOne(String decimal) {
         String sum = "";
         int carry = 0;
         for(int i= decimal.length()-1; i >= 0; i--) {
             int lastDigit = Character.getNumericValue(decimal.charAt(i));
             if(lastDigit != 9) {
                 int newDigit = 1 + lastDigit;
                 sum = decimal.substring(0,i) + newDigit + decimal.substring((i + 1), decimal.length());
                 return sum;
             } else {
                carry = 1;
                sum = decimal.substring(0, i) + "0" + decimal.substring((i + 1), decimal.length());
             }
         }
         if(carry == 1) {
             sum = "1" + sum;
         }
         return sum;
     }
     /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      *  Method to multiply by 2 (getting number from binary to decimal)
      *  @param String s
      *  @return sum
      *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
      public static String multByTwo(String s) {
          String sum = "";
        int partialSum = 0;

        int currentDigit = Integer.parseInt(s.substring(s.length() - 1, s.length()));
        partialSum = currentDigit + currentDigit;

        int i = s.length() - 1;

        int carriedVal = 0;
        String strPartialSum = "";
        int pSumC = 0;

        while(i >= 0) {
            currentDigit = Integer.parseInt(s.substring(i, i + 1));
            partialSum = currentDigit + currentDigit;
            pSumC = currentDigit + currentDigit;
            if(partialSum < 10) {
                partialSum += carriedVal;
                sum += partialSum;
                carriedVal = 0;
            } else {
                partialSum += carriedVal;
                strPartialSum = Integer.toString(partialSum);
                carriedVal = 1;
                partialSum = Integer.parseInt(strPartialSum.substring(1,2));
                sum += partialSum;
            }

            if (i == 0 && pSumC > 9) {
                sum += "1";
            }

            i--;
        }

        sum = new StringBuilder(sum).reverse().toString();
        return sum;
    }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a GinormousIntk passed as argument to this GinormousInt
   *  @param  gint         GinormousInt to subtract from this
   *  @return GinormousInt that is the difference of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint) {
       throw new UnsupportedOperationException( " this is not done!!");
       /*String top = binaryValue;
       String bottom = bint.toBinary();
       String result = "";
       int borrow = 0;
       boolean negative = false;
       while (top.length() > bottom.length()){
           bottom = "0" + bottom;
       }
       if(top.length() < bottom.length()) {
           negative = true;
       }
       while (bottom.length() > top.length()) {
           top = "0" + top;
       }
       for(int i = 0; i >= top.length(); i++) {
           if(top.charAt(i) == '1' && bottom.charAt(i) == '1') {
               result = "0" + result;
           }
           else if(top.charAt(i) == '1' && bottom.charAt(i) == '0') {
               result = "1" + result;
           }
           else if(top.charAt(i) == '0' && bottom.charAt(i) == '1') {
               int j = i;
               while(top.charAt(j) != '1') {
                   top = top.substring(0, j) + "1" + top.substring((j + 1), top.length());
                   j--;
               }
               result = top.charAt(i-1) + result;
           }
           else if(top.charAt(i) == '0' && bottom.charAt(i) == '0') {
               result = "0" + result;
           }
       }
       if(negative) {
           sign = 1;
       }
       return new BrobInt(binaryToDecimal(result));*/
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a GinormousIntk passed as argument to this GinormousInt
   *  @param  gint         GinormousInt to multiply by this
   *  @return GinormousInt that is the product of the value of this GinormousInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {

// take in two strings of numbers
// convert those numbers to binary
// multiply them in binary
// get result in binary
// convert binary number back to decimal version


      /*String firstValue = this.binaryValue;
      String secondValue = bint.toBinary();
      String sum = "";

       if (firstValue == 1){
           System.out.println(firstValue + " : " + secondValue);
           return sum + secondValue;
       }
       if (firstValue % 2 == 0) {
           System.out.println( firstValue + " : " + secondValue);
       } else {
           System.out.println(firstValue + " : " + secondValue);
           sum = sum + secondValue;
       }
       return multiply(firstValue/2, secondValue * 2).toDecimal();*/
       throw new UnsupportedOperationException ("\n Sorry this has not been implemented");
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this GinormousIntk by the GinormousInt passed as argument
   *  @param  gint         GinormousInt to divide this by
   *  @return GinormousInt that is the dividend of this GinormousInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this GinormousInt by the one passed as argument
   *  @param  gint         GinormousInt to divide this one by
   *  @return GinormousInt that is the remainder of division of this GinormousInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a GinormousInt passed as argument to this GinormousInt
   *  @param  gint  GinormousInt to add to this
   *  @return int   that is one of neg/0/pos if this GinormousInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo(BrobInt gint ) {
      return (binaryValue.compareTo( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a GinormousInt passed as argument is equal to this GinormousInt
   *  @param  gint     GinormousInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (inputValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a GinormousInt given a long value passed as argument
   *  @param  value         long type number to make into a GinormousInt
   *  @return GinormousInt  which is the GinormousInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt bi = null;
      try {
         bi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return bi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this GinormousInt
   *  @return String  which is the String representation of this GinormousInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
       return inputValue;

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this GinormousInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   /*public void toArray( byte[] d ) {
     /* System.out.println( ArrayList.toString( d ) );
 }*/

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the GinormousInt program!!\n" );
      System.out.println( "\n   You should run your tests from the GinormousIntTester...\n" );
      binaryToDecimal(args[0]);
      BrobInt a = new BrobInt("10");
      BrobInt b = new BrobInt("20");

      //System.out.println("The answer is " + a.add(b));
      System.exit( 0 );

   }
}
