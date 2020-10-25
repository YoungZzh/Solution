package Concurrents;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:Young
 * Date:2020/10/19-21:51
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2019) + "\t current data:" + atomicInteger.get() );
        System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data:" + atomicInteger.get() );
    }
}
