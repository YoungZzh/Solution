package Concurrents;

import java.lang.ref.WeakReference;

/**
 * Author:Young
 * Date:2020/11/15-16:43
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("=================");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
