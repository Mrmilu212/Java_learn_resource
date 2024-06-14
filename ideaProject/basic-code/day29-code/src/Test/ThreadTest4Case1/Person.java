package Test.ThreadTest4Case1;

import java.util.ArrayList;

public class Person extends Thread {

    static ArrayList<Double> redPockets = RedPocketUtil.redPockets(100, 3);;

    @Override
    public void run() {
        synchronized (Thread.class) {
            if (redPockets.size() != 0) {
                System.out.println(getName() + "抢到了" + redPockets.remove(0) + "元");
            } else System.out.println(getName() + "没抢到");
        }
    }

}
