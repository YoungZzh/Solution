/**
 * Author:Young
 * Date:2020/6/20-20:38
 */
public class TestSort {

    public static void main(String[] args) {
        int[] ints = {4, 5, 0, 6, 7, 0, 1};
        int[] ints1 = new Solution().sorted0(ints);
        for (int i = 0; i < ints1.length; i++) {
            System.out.println(ints1[i]);
        }
    }
}
