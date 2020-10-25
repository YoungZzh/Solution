package Concurrents;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Author:Young
 * Date:2020/10/25-16:36
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //add,element,remove方法，如果队列满或空，再添加或删除元素会抛出异常
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));*/
        //System.out.println(blockingQueue.add("c"));

        //检查队首元素是什么
        /*System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        /*====================================*/

        //offer（），peak（），poll（）不抛出异常，失败返回false
        /*System.out.println(blockingQueue.offer("q"));
        System.out.println(blockingQueue.offer("w"));
        System.out.println(blockingQueue.offer("e"));
        System.out.println(blockingQueue.offer("r"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        //put（）会在空间不足时，阻塞队列；take会在队列不存在元素时阻塞队列
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("x");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}
