package Test.ThreadTest3;

public class OddPrinter extends Thread {
    static int number = 1;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Thread.class) {
                if (number <= 100) {
                    if (isPrime(number)) {
                        System.out.println(getName() + ":" + number);
                    }
                    number++;
                } else break;
            }
        }
    }

    public boolean isPrime(int number) {
        for (int i = 2; i < (number / 2); i++) {
            //判断是否可以被除尽
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
