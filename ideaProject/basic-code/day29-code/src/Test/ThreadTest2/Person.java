package Test.ThreadTest2;

public class Person extends Thread{
    //创建静态变量表示该类所有对象共用次变量
    static int gift = 100;

    @Override
    public void run() {
        while (true){
            synchronized (Thread.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (gift > 10){
                    gift--;
                    System.out.println(getName() + "送出了一份礼物，剩下" + gift + "份");
                }else break;
            }
        }
    }
}
