package Concurrents;

import java.util.WeakHashMap;

/**
 * Author:Young
 * Date:2020/11/15-17:08
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myWeakHashMap();
    }

    private static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = 2;
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
