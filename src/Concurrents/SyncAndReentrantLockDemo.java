package Concurrents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:Young
 * Date:2020/10/18-15:15
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            executorService.execute(()->{
                for (int i = 0; i < 10; i++) {
                    shareResource.print5();
                }
            });
            executorService.execute(()->{
                for (int i = 0; i < 10; i++) {
                    shareResource.print10();
                }
            });
            executorService.execute(()->{
                for (int i = 0; i < 10; i++) {
                    shareResource.print15();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
