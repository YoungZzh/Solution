package Concurrents;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Young
 * Date:2020/11/21-22:27
 */
public class GCOverheadLimitDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try{
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("****************i:" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
