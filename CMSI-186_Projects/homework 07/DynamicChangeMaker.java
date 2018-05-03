public class DynamicChangeMaker {

  public int[] OPT = null;
  public String[][] optimalChange = null;
  //public ArrayList<String> allPossibleChanges = new ArrayList<String>();
  public int targetValue = 0;
  public int[] denoms = null;
  public String denominations = "";

  public DynamicChangeMaker( String args[]  ) {
      /*Tuple [] [] t = new Tuple [rows] [columns]
      rows is # of denoms
      columns is one plus target
      i is going down
      j is going across across the columns
      t[i][j] = new Tuple (denoms.length)
      need denoms[i] < j AND  if denoms[i] > j then impossible (so Tuple.IMPOSSIBLE)
      need to see if you can go back, so if j < i then we cannot go back so impossible
      if i is equal to j then make new Tuple t[i][j] = new Tuple(denoms.length); then t[i][j].setElement(i, 1);
      now need t[i][j - denoms[i]] or if t[i][j - denoms[i]] is impossible then make it impossible, if not impossible then bring it over and add so t[i][j] += t[i][j - denoms[i]];
      */
    denominations = String.parseString(args[0]);
    String[] denominations = denominations.split(",");
    denoms = denominations;
    targetValue = Integer.parseInt(args[1]);
    Tuple[][] t = new Tuple[denoms.length][targetValue];
  }

  public int makeChangeWithDynamicProgramming( int denoms[], int targetValue) {

    if (Tuple.isIMPOSSIBLE()) {
      throw new NumberFormatException("Bad arguments provided \n ");
    }
    t[i][j-denoms[i]];
    int i = 0;
    for (int j = 0; j < targetValue; j++) {
      if () {

      }
    }
    return 1;
  }

  public static void main ( String args[] ) {
      if (args.length != 2) {
           printUsage();
           return;
       }

       try {
           int amount = Integer.parseInt(args[1]);
           if (amount < 0) {
               System.out.println("Change cannot be made for negative amounts.");
               System.out.println();
               printUsage();
               return;
           }

           String[] denominationStrings = args[0].split(",");
           int[] denominations = new int[denominationStrings.length];

           for (int i = 0; i < denominations.length; i++) {
               denominations[i] = Integer.parseInt(denominationStrings[i]);
               if (denominations[i] <= 0) {
                   System.out.println("Denominations must all be greater than zero.");
                   System.out.println();
                   printUsage();
                   return;
               }

               for (int j = 0; j < i; j++) {
                   if (denominations[j] == denominations[i]) {
                       System.out.println("Duplicate denominations are not allowed.");
                       System.out.println();
                       printUsage();
                       return;
                   }
               }
           }
           Tally change = makeOptimalChange(denominations, amount);
           if (change.isImpossible()) {
               System.out.println("It is impossible to make " + amount + " cents with those denominations.");
           } else {
               int coinTotal = change.total();
               System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                       getSimplePluralSuffix(coinTotal) + " as follows:");

               for (int i = 0; i < denominations.length; i++) {
                   int coinCount = change.getElement(i);
                   System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                           getSimplePluralSuffix(coinCount));
               }
           }

  }





}
