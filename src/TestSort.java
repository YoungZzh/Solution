/**
 * Author:Young
 * Date:2020/6/20-20:38
 */
public class TestSort {

    public static void main(String[] args) {

        System.out.println(reverse(-2147483648));
    }
    public static int reverse(int x) {
        long temp = 0;

        while(x != 0){
            int pop = x % 10;
            temp = temp * 10 + pop;

            if(temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE){
                return 0;
            }
            x /= 10;
        }
        return (int)temp;
    }

}
