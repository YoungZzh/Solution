package Concurrents;

import java.util.concurrent.*;

/**
 * Author:Young
 * Date:2020/10/18-15:48
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService threadPool1 = Executors.newFixedThreadPool(5);//固定线程数
        //ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//只存在一个线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();//带有缓存的线程池，如果一个线程能够应对，那么就不会多创建线程
                                                                    //如果不能，那么会创建多个线程
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try{
            //模拟10个用户来办理业务，每个用户就是一个外部的请求线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
