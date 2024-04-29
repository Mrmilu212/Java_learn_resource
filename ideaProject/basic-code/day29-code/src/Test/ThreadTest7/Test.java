package Test.ThreadTest7;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        有一个抽奖池，该抽奖池中存放了奖励的金额，该抽奖池中的奖项为
        {10,5,20,50,100,200,500,800,2,80,300,700};
        创建两个抽奖箱（线程）设置该线程名称分别为“抽奖箱1”，“抽奖箱2”
        随机从奖池中获取奖项元素并打印在控制台上，格式如下：
                抽奖时不输出，抽完后再一次性输出
                格式如下
                在此次抽奖中抽奖箱2总共产生了6个奖项，分别为800,100,2,10,300,5
                最大奖项为800，总计额：1217

                附加要求：
                输出两个抽奖箱间的最大的一个奖项


         */

        //创建Callable对象
        LuckyDipCallable ld = new LuckyDipCallable();
        //创建FutureTask对象
        FutureTask<HashMap<String,Integer>> ft = new FutureTask<>(ld);

        //创建Thread对象
        Thread t1 = new Thread(ft);
        Thread t2 = new Thread(ft);

        t1.setName("抽奖箱1");
        t2.setName("抽奖箱2");

        //启动线程
        t1.start();
        t2.start();

        //获取记录最大值的Map集合
        HashMap<String, Integer> hm = ft.get();
        //输出最大值
        String maxKey = null;
        int max = 0;
        Set<Map.Entry<String, Integer>> entries = hm.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            Integer value = entry.getValue();
            if (value > max){
                maxKey = entry.getKey();
            }
        }

        System.out.println("在此次抽奖过程中，" + maxKey + "中产生了最大奖项，该奖项金额为" + hm.get(maxKey) + "元");

    }
}
