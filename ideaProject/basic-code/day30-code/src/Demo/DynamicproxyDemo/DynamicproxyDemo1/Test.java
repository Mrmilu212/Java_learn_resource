package Demo.DynamicproxyDemo.DynamicproxyDemo1;

public class Test {
    public static void main(String[] args) {
        /*
        需求：
            外面的人想要大明星唱一首歌
            1.获取代理对象
                代理对象 = ProxyUtil.createProxy(大明星的对象);
            2.在调用代理的唱歌方法
                代理对象.唱歌的方法();
         */
        BigStar bigStar = new BigStar("大古");
        Star proxy = ProxyUtil.createProxy(bigStar);

        String str = proxy.sing("奇迹再现");
        System.out.println(str);
        proxy.dance();
    }
}
