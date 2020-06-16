/**
 * Author:Young
 * Date:2020/6/9-11:08
 */
public class transRadix {

    public static void main(String[] args) {

        System.out.println(binaryToDecimal("1111"));
        System.out.println(decimalToBinary(15));
    }

    /**
     * 二进制转换为十进制
     * @param n
     * @return
     */
    public static double binaryToDecimal(String n){
        char[] x;//存放每一位数
        double result = 0.0;//最后返回的结果
        x = n.toCharArray();
        for(int i = x.length-1,j = 0; i >= 0 && j < x.length; i--,j++){
            result = result + ((x[i]-'0')*(Math.pow(2,j)));
        }
        return result;
    }

    public static int decimalToBinary(int m){
        int t = 0;//记录位数
        int y = 0;
        int s = 0;
        while(m != 0){
            s = m % 2;
            m = m / 2;
            y += s*Math.pow(10,t);
            t++;
        }
        return y;

    }
}
