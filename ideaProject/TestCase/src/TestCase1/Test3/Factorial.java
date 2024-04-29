package TestCase1.Test3;

public class Factorial {
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r = r * i;
        }
        System.out.println(r);
        return r;
    }
}