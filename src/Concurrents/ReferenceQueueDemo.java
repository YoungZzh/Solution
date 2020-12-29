package Concurrents;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Author:Young
 * Date:2020/11/15-20:58
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o,referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("========================");
        o = null;
        System.gc();
        Thread.sleep(3000);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
