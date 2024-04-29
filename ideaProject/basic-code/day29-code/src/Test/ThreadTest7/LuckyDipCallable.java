package Test.ThreadTest7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class LuckyDipCallable implements Callable<HashMap<String,Integer>> {
    //初始化数据
    static ArrayList<Integer> pot = PotUtil.initPot();

    //创建集合记录最大值
    static HashMap<String,Integer> max = new HashMap<>();

    @Override
    public HashMap<String,Integer> call() throws Exception {

        //定义在方法内，循环外
        ArrayList<Integer> values = new ArrayList<>();

        while (true) {

            Thread.sleep(10);

            synchronized (Thread.class) {
                if (pot.size() != 0) {
                    //打乱集合
                    Collections.shuffle(pot);
                    Integer prize = pot.remove(0);
                    //System.out.println(prize);
                    values.add(prize);
                } else {
                    //ArrayList<Integer> values = tm.get(getName());
                    System.out.print("在此次抽奖中" + Thread.currentThread().getName() + "总共产生了"
                            + values.size() + "个奖项，分别为");
                    //计算总和
                    int sum = 0;
                    for (int i = 0; i < values.size(); i++) {
                        Integer value = values.get(i);
                        sum += value;
                        System.out.print(i != values.size() - 1 ? value + "," : value);
                    }
                    //记录最大值
                    Integer m = Collections.max(values);
                    //将最大值添加到集合中
                    max.put(Thread.currentThread().getName(),m);
                    System.out.println("\n最大奖项为" + m + "，总计额：" + sum);

                    System.out.println();
                    /*tm.forEach((k, v) -> {
                        System.out.println();
                    });*/
                    break;
                }
            }
        }

        return max;
    }
}
