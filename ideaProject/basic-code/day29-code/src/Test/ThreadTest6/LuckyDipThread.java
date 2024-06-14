package Test.ThreadTest6;

import java.util.ArrayList;
import java.util.Collections;

public class LuckyDipThread extends Thread {
    static ArrayList<Integer> pot = PotUtil.initPot();

    //创建Map集合，用于保存不同抽奖箱的出奖情况
    /*static TreeMap<String, ArrayList<Integer>> tm = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int value1 = o1.charAt(o1.length() - 1);
            int value2 = o2.charAt(o2.length() - 1);
            return value1 - value2;
        }
    });*/

    //创建集合各抽奖箱里的最大值记录最大值
    static ArrayList<Integer> max = new ArrayList<>();

    @Override
    public void run() {
        //定义在方法内，循环外
        ArrayList<Integer> values = new ArrayList<>();

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Thread.class) {
                if (pot.size() != 0) {
                    //打乱集合
                    Collections.shuffle(pot);
                    Integer prize = pot.remove(0);
                    //System.out.println(prize);
                    values.add(prize);
                } else {
                    //ArrayList<Integer> values = tm.get(getName());
                    System.out.print("在此次抽奖中" + getName() + "总共产生了"
                            + values.size() + "个奖项，分别为");
                    //计算总和
                    int sum = 0;
                    for (int i = 0; i < values.size(); i++) {
                        Integer value = values.get(i);
                        sum += value;
                        System.out.print(i != values.size() - 1 ? value + "," : value  );
                    }
                    //记录最大值
                    Integer m = Collections.max(values);
                    //将最大值添加到集合中
                    max.add(m);
                    System.out.println("\n最大奖项为" + m + "，总计额：" + sum );

                    System.out.println();
                    /*tm.forEach((k, v) -> {
                        System.out.println();
                    });*/
                    break;
                }
            }
        }
    }
}
