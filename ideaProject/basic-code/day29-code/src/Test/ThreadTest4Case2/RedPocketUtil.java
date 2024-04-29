package Test.ThreadTest4Case2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class RedPocketUtil {

    private RedPocketUtil() {
    }

    // 创建红包集合
    static ArrayList<BigDecimal> redPockets = new ArrayList<>();

    // 设定最小金额
    static final BigDecimal MIN = new BigDecimal("0.01");

    public static ArrayList<BigDecimal> redPockets(BigDecimal money, int amount) {
        Random r = new Random();
        double min = MIN.doubleValue();
        double max = money.subtract(MIN.multiply(new BigDecimal(amount - 1))).doubleValue();
        double pocketMoney = min + (max - min) * r.nextDouble();

        // 使用 BigDecimal 进行保留两位小数
        BigDecimal pocketMoneyBD = new BigDecimal(String.format("%.2f", pocketMoney));

        // 保证不小于最小金额
        if (pocketMoneyBD.compareTo(MIN) < 0)
            pocketMoneyBD = MIN;

        redPockets.add(pocketMoneyBD);
        amount--;
        if (amount > 1) {
            redPockets(money.subtract(pocketMoneyBD), amount);
        } else {
            redPockets.add(money.subtract(pocketMoneyBD));
        }
        return redPockets;
    }
}
