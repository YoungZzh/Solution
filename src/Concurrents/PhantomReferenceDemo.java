package Concurrents;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Author:Young
 * Date:2020/11/15-21:09
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o,referenceQueue);


        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("========================");
        o = null;
        System.gc();
        Thread.sleep(3000);
        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
