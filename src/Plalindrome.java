import java.util.Scanner;

/**
 * Author:Young
 * Date:2020/6/8-12:21
 */
public class Plalindrome {

    static char[] str = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int temp;
        int max = 0;
        String s = in.nextLine();
        str = s.toCharArray();
        for(int i = 0; i < str.length; i++){
            temp = maxpla(i,str.length);
            if (temp > max){
                max = temp;
            }
        }
        System.out.println("此次输入最大回文长度为：" + max);
    }

    public static int maxpla(int index, int length){

        int result = 1;
        int left = index;
        int right = length-1-index;
        int doNum = 0;
        if (left < right){
            doNum = left;
        }else {
            doNum = right;
        }
        for (int i = 1; i <= doNum; i++){
            if(str[index-i] == str[doNum + i]){
                result += 2;
            }else {
                break;
            }
        }
        return result;
    }

}
