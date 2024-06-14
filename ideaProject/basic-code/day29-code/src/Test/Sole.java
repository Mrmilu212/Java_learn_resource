package Test;

public class Sole extends Thread {
    //static修饰的变量表示该类所有对象共有这个变量
    static int ticket = 0;

    //定义锁对象，并保证唯一
    static Object obj = new Object();


    //多线程书写套路
    //1.循环
    //2.同步代码块
    //3.判断是否到了末尾（否，执行核心逻辑）
    //4.判断是否到了末尾（是）


    @Override
    public void run() {
        while (true) {//1.循环
            //同步代码块
            synchronized (obj){//2.同步代码块
                if (ticket < 100) {//3.判断是否到了末尾（否，执行核心逻辑）
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket++;
                    System.out.println(getName() + "买了第" + ticket + "张票");
                } else {//4.判断是否到了末尾（是）
                    break;
                }
            }
        }

    }
}
