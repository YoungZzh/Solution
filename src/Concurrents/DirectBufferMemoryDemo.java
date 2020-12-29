package Concurrents;

import java.nio.ByteBuffer;

/**
 * Author:Young
 * Date:2020/11/21-22:42
 * <p>
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式
 * 它可以使用Native函数直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作
 * 这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据
 * <p>
 * ByteBuffer.allocate（capability）第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢
 * <p>
 * ByteBuffer.allocateDirect（capability）第二种方式分配的是OS本地内存，不属于GC范围，由于不需要内存拷贝所以速度相对较快
 * <p>
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收，
 * 这时候堆内存充足，但是本地内存可能已经用光了，再次尝试分配本地内存就会出现OutOfMemoryError，那么程序就会直接崩溃
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        //System.out.println("配置的maxDirectMemory：" + (sun.misc.VM.maxDirectMemory() / (double)1024/1024) + "MB");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);

    }
}
