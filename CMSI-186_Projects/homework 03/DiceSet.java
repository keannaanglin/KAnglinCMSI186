public class DiceSet {

	   private int count;
	   private int sides;
	   private Die[] ds = null;

	   // public constructor:
	  /**
	   * constructor
	   * @param  count int value containing total dice count
	   * @param  sides int value containing the number of pips on each die
	   * @throws IllegalArgumentException if one or both arguments don't make sense
	   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
	   */
	   public DiceSet( int count, int sides ) {
		   this.count = count;
           this.sides = sides;
		   ds = new Die[count];
		   if (sides < 4) {
			   throw new IllegalArgumentException("You need more sides!");
		   }
		   for (int i = 0; i < count; i++) {
			   ds[i] = new Die(sides);
		   }
	   }

	  /**
	   * @return the sum of all the dice values in the set
	   */
	   public int sum() {
		  int sum = 0;
	      for (int i = 0; i < count; i++) {
	          sum += ds[i].getValue();
	      }
	      return sum;
	   }

	  /**
	   * Randomly rolls all of the dice in this set
	   *  NOTE: you will need to use one of the "toString()" methods to obtain
	   *  the values of the dice in the set
	   */
	   public void roll() {
		   for( int i = 0; i < count; i++) {
			   ds[i].roll();
		   }
	   }

	  /**
	   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
	   * @param  dieIndex int of which die to roll
	   * @return the integer value of the newly rolled die
	   * @trhows IllegalArgumentException if the index is out of range
	   */
	   public int rollIndividual( int dieIndex ) {
	      return ds[dieIndex].roll();
	   }

	  /**
	   * Gets the value of the die in this set indexed by 'dieIndex'
	   * @param  dieIndex int of which die to roll
	   * @trhows IllegalArgumentException if the index is out of range
	   */
	   public int getIndividual( int dieIndex ) {
	      return ds[dieIndex].getValue();
	   }

	  /**
	   * @return Public Instance method that returns a String representation of the DiceSet instance
	   */
	   public String toString() {
	      String result = "";
	      for(int i = 0; i < count; i++) {
	    	      result += ds[i].toString();
	      }
	      return result;
	   }

	  /**
	   * @return Class-wide version of the preceding instance method
	   */
	   public static String toString( DiceSet ds ) {
	      return ds.toString();
	   }

	  /**
	   * @return  tru iff this set is identical to the set passed as an argument
	   */
	   public boolean isIdentical( DiceSet ds ) {
		   if (count == ds.count && sides == ds.sides) {
			   return true;
	      } else {
	    	       return false;
	      }
	   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
       DiceSet a = null;
       try { a = new DiceSet(1, 1);}
       catch (IllegalArgumentException iae ) {System.out.println(" Not enough sides!");}
       try { a = new DiceSet(1,2);}
       catch(IllegalArgumentException iae) {System.out.println(" Not enough sides!!");}
       try {a = new DiceSet(1,3);}
       catch(IllegalArgumentException iae) {System.out.println(" Not enough sides!!");}
       try {a = new DiceSet(0,1);}
       catch(IllegalArgumentException iae) {System.out.println(" Not enough die and not enough sides!");}

       try {
           a = new DiceSet(2,4);
           System.out.println("Sum of the die" + a.sum());
           a.roll();
           System.out.println(" Rolled all die");
           System.out.println(" Rolling die at index 0:" + a.rollIndividual(0));
           System.out.println(" Getting value of the die at index 0:" + a.getIndividual(0));
           System.out.println("String of die: " + a.toString());
       } catch (IllegalArgumentException iae) {System.out.println(" IllegalArgumentException");}
   }

}
