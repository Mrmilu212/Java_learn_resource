package Test.ThreadTest5;

import java.util.ArrayList;
import java.util.Collections;

public class LuckyDipThread extends Thread {
    static ArrayList<Integer> pot = PotUtil.initPot();

    @Override
    public void run() {
        while (true) {
            synchronized (Thread.class) {

                if (pot.size() != 0) {
                    //打乱集合，用以随机
                    Collections.shuffle(pot);
                    System.out.println(getName() + "又产生了一个" + pot.remove(0) + "元大奖");
                } else break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
