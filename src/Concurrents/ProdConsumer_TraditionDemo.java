package Concurrents;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:Young
 * Date:2020/10/25-22:08
 * 传统的生产者—消费者问题，使用同步锁synchronized或者ReeentrantLock来实现
 */
//资源类
class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try{
            //1.判断
            while (number != 0){
                //等待，不能生产
                condition.await();
            }
            //2.生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try{
            //1.判断
            while (number == 0){
                //等待，不能消费
                condition.await();
            }
            //2.消费
            number--;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加一，一个减一
 *
 * 1.线程         操作（方法）          资源类
 * 2.判断         干活                  通知
 * 3.防止虚假唤醒
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try{
                    shareDate.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try{
                    shareDate.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try{
                    shareDate.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try{
                    shareDate.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
