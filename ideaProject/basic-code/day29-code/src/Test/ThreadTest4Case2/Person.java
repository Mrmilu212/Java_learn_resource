package Test.ThreadTest4Case2;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Person extends Thread {

    static ArrayList<BigDecimal> redPockets = RedPocketUtil.redPockets(BigDecimal.valueOf(100), 3);

    @Override
    public void run() {
        synchronized (Person.class) { // 使用当前类作为锁对象
            if (!redPockets.isEmpty()) {
                System.out.println(getName() + "抢到了" + redPockets.remove(0).setScale(2, BigDecimal.ROUND_HALF_UP) + "元");
            } else {
                System.out.println(getName() + "没抢到");
            }
        }
    }
}
