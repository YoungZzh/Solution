package Concurrents;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Author:Young
 * Date:2020/10/20-21:30
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        Map<Object,Object> map = new HashMap<>();

        for (Map.Entry<Object,Object> i : map.entrySet()){

        }

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t *************班长最后关门离开教室");
    }
}
