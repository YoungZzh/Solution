package Concurrents;

/**
 * Author:Young
 * Date:2020/10/18-16:58
 */
public class MyData {
    volatile int number = 0;
    public void addT060(){
        this.number = 60;
    }
}
