public static Fibonacci(BrobInt n) {
    BrobInt zero = new BrobInt("0");
    BrobInt one = new BrobInt("1");
    BrobInt two = new BrobInt("2");

    if (n.equals(zero))
        return zeros;
    if (n.equals(one) || n.equals(two))
        return one;

    BrobInt previous = new BrobInt(-1);
    BrobInt result = new BrobInt(1);

    for (int i = 0; i <= n; i++) {

        BrobInt sum = result.add(previous);
        previous = result;
        result = sum;
    }

    return result;
    }
