package TestCase1.Test1;

public class Calculator {
    private long n = 0;
    public long add(long x) {
        n = n + x;
        return n;
    }
    public long sub(long x) {
        n = n - x;
        return n;
    }
}