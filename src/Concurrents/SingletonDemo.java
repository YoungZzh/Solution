package Concurrents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:Young
 * Date:2020/10/19-21:12
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t" + "我是构造方法SingletonDemo");
    }


    public static SingletonDemo getInstance(){
        //双端检锁机制（Double Check Lock）DCL
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonDemo.getInstance();
            });
        }
    }
}
