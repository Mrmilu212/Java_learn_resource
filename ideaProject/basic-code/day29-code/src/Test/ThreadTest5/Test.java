package Test.ThreadTest5;

public class Test {
    public static void main(String[] args) {
        /*
        有一个抽奖池，该抽奖池中存放了奖励的金额，该抽奖池中的奖项为
        {10,5,20,50,100,200,500,800,2,80,300,700};
        创建两个抽奖箱（线程）设置该线程名称分别为“抽奖箱1”，“抽奖箱2”
        随机从奖池中获取奖项元素并打印在控制台上，格式如下：
                每次抽出一个奖项就随机打印一个
                抽奖箱1又产生了一个10元大奖
                抽奖箱1又产生了一个100元大奖
                抽奖箱1又产生了一个200元大奖
                抽奖箱1又产生了一个700元大奖
                抽奖箱1又产生了一个800元大奖
         */
        //创建对象
        LuckyDipThread ld1 = new LuckyDipThread();
        LuckyDipThread ld2 = new LuckyDipThread();

        //设置名称
        ld1.setName("抽奖箱1");
        ld2.setName("抽奖箱2");

        //开启线程
        ld1.start();
        ld2.start();
    }
}
