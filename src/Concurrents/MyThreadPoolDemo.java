package Concurrents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:Young
 * Date:2020/10/18-15:48
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定线程数
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//只存在一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//带有缓存的线程池，如果一个线程能够应对，那么就不会多创建线程
                                                                    //如果不能，那么会创建多个线程

        try{
            //模拟10个用户来办理业务，每个用户就是一个外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
                });
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
