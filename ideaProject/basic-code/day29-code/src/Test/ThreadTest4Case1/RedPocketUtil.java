package Test.ThreadTest4Case1;

import java.util.ArrayList;
import java.util.Random;

public  class RedPocketUtil {

    private RedPocketUtil(){};
    //创建红包集合
    static ArrayList<Double> redPockets = new ArrayList<>();

    //设定最小金额
    static final double MIN = 0.01;


    public static ArrayList<Double> redPockets(double money , int amount){
        Random r = new Random();
        double pocketMoney = r.nextDouble(money - MIN * (amount - 1));

        //保证不小于最小金额
        if (pocketMoney < MIN)
            pocketMoney = MIN;

        redPockets.add(pocketMoney);
        amount--;
        if (amount > 1){
            redPockets(money - pocketMoney , amount);
        }else {
            redPockets.add(money - pocketMoney);
        }
        return redPockets;
    }
}
