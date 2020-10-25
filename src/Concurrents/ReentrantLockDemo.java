package Concurrents;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:Young
 * Date:2020/10/22-14:34
 */
class Phone{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t invoke sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t ########invoke sendEmail()");
    }

    ReentrantLock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ########invoke get()");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ########invoke set()");
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()-> phone.get(),"t1").start();
        new Thread(()-> phone.get(),"t2").start();
    }
}
