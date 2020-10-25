package Concurrents;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Author:Young
 * Date:2020/10/20-16:19
 */
public class ABADemo {//ABA问题的解决
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        /*new Thread(()->{
           atomicReference.compareAndSet(100,101);
           atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
           //暂停1秒钟t2线程，保证商密的t1线程完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100,2019) + "\t" + atomicReference.get());
        },"t2").start();

        //暂停一会儿线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("============以下是ABA问题的解决===============");

        //ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            //暂停1秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号：" + atomicStampedReference.getStamp());

        },"t3").start();

        //ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
            //暂停1秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改成功与否：" + result + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新值：" + atomicStampedReference.getReference());
        },"t4").start();
    }
}
