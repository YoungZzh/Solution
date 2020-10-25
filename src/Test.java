import java.util.Scanner;

/**
 * Author:Young
 * Date:2020/9/17-15:59
 */
public class Test {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int a = in.nextInt();
        searchNum(s,a);
    }

    public static void searchNum(String s, int a){
        char[] str = s.toCharArray();
        for(int i=0;i<str.length;i++){
            int temp = 0;
            for(int j=0;j<str.length;j++){
                if(str[i] == str[j]){
                    temp++;
                }
            }
            if(temp == a){
                System.out.println(str[i]);
            }
        }
    }
}
