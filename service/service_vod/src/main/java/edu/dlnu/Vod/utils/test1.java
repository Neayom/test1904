package edu.dlnu.Vod.utils;

/**
 * 数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [[表情]231,  231 [表情] 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *  
 * Java 函数原型
 * public int reverse(int x) {
 *  }
 */
public class test1 {
   public static int reverse(int x){
       if(x>Integer.MAX_VALUE){
           return 0;
       }
       if(x<Integer.MIN_VALUE){
           return 0;
       }
       if(x==0){
           return 0;
       }
       int sum=0;
       while(x!=0){
           sum=sum*10+x%10;
           x/=10;
       }
       return sum;
   }

    public static void main(String[] args) {

        int x=0;
        System.out.println(reverse(x));
    }
}
