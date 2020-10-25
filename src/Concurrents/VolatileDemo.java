package Concurrents;

import java.util.concurrent.TimeUnit;

/**
 * Author:Young
 * Date:2020/10/18-16:58
 */

//验证volatile的可见性
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t" + "come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + "\t" + "update number value:" + myData.number);

        },"AAA").start();
        while (myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName() + "\t" + "mission is over" + myData.number);

    }

}
