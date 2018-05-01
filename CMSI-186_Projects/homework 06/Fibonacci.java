public class Fibonacci {
    public static BrobInt Fibonacci(BrobInt n) {
        BrobInt zero = new BrobInt("0");
        BrobInt one = new BrobInt("1");
        BrobInt two = new BrobInt("2");

        if (n.equals(zero))
            return zero;
        if (n.equals(one) || n.equals(two))
            return one;

        BrobInt previous = new BrobInt("-1");
        BrobInt result = new BrobInt("1");
        BrobInt i = new BrobInt("0");

        while ( !(i.equals(n)) ) {
            BrobInt sum = result.add(previous);
            previous = result;
            result = sum;
            i.add(new BrobInt("1"));
        }
        return result;
    }
    public static void main (String[] args) {
        Fibonacci(new BrobInt(args[0]));
    }

}
