/**
 * Author:Young
 * Date:2020/6/20-20:38
 */
public class TestSort {

    public static void main(String[] args) {
       int[] arr = new int[]{8,9,1,7,2,3,5,4,6,0};
        Solution.Rapid(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
