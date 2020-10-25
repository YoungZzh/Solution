package Concurrents;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Author:Young
 * Date:2020/10/20-17:13
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString());
                System.out.println(set);
            },String.valueOf(i)).start();
        }

        /**
         * 1.故障现象
         *      java.util.ConcurrentModificationException
         *  2.导致原因
         *      并发争抢修改导致
         *  3.解决方案
         *      List<String> list = new Vector<>();
         *      List<String> list = Collections.synchronizedList(new ArrayList<>());
         *      CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
         *  4.优化建议
         *      CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
         */
    }
}
