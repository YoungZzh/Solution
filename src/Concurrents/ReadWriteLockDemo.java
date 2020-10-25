package Concurrents;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:Young
 * Date:2020/10/22-16:13
 */
class MyCache{//资源类
    private volatile Map<String,Object> map = new HashMap<>();
    //ReentrantLock lock = new ReentrantLock();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public void put(String key,Object value){
        lock.writeLock().lock();
       try {
           System.out.println(Thread.currentThread().getName() + "\t正在写入：" + key);
           TimeUnit.MILLISECONDS.sleep(300);
           map.put(key,value);
           System.out.println(Thread.currentThread().getName() + "\t 写入完成");
       } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
           lock.writeLock().unlock();
       }
    }

    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取：");
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

}


public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
               myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        //TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
