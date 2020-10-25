import java.util.*;

/**
 * Author:Young
 * Date:2020/10/10-23:26
 */
public class FindsNodePunish {

    static int beautifulPath(int n, int[][] edges, int start, int end) {
        Map<Integer, Map<Integer, Integer>> edgesMap = getEdgesMap(n, edges);
        int result = minPath(edgesMap, start, end);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    //获得点到其它的距离集合
    private static Map<Integer, Map<Integer, Integer>> getEdgesMap(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(2 * n);
        List<int[]> list = new ArrayList<>(n);
        Collections.addAll(list, edges);
        list.forEach(e -> {
            int start = e[0];
            int end = e[1];
            int distance = e[2];
            map.computeIfAbsent(start, k -> new HashMap<Integer, Integer>(n));
            //当map中不存在指定的key时，便将传入的value设置为key的值，相当于map.put(key, value)；当key存在值时，
            // 执行一个方法该方法接收key的旧值和传入的value，执行自定义的方法返回最终结果设置为key的值。
            map.get(start).merge(end, distance, (a, b) -> b < a ? b : a);
        });
        return map;
    }

    //深度优先，获取所有可能的路径及惩罚
    private static int minPath(Map<Integer, Map<Integer, Integer>> edgesMap, int start, int end) {
        Map<Integer, Integer> startMap = edgesMap.get(start);
        int result = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> e : startMap.entrySet()) {
            int next = e.getKey();
            int path = e.getValue();
            if (next != end) {
                edgesMap.remove(start);
                path = path | minPath(edgesMap, next, end);
                edgesMap.put(start, startMap);
            }
            if (path < result) {
                result = path;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2, 1}, {2, 3, 3}, {1, 3, 100}};
        System.out.println(beautifulPath(3, edges, 1, 2));
    }
}
