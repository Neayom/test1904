package edu.dlnu.Vod.utils;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class 动态规划 {
    //打家劫舍
    public static int rob(int[] nums) {
        int i=nums.length;
        if(i==1){
            return nums[0];
        }
        if(i==0){
            return 0;
        }
        int dp[]=new int[i];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int j=2;j<i;++j){
            dp[j]=Math.max(nums[j]+dp[j-2],dp[j-1]);
        }
        return dp[i-1];
    }
    //斐波那契
    public int fib(int n) {
        if(n==0||n==1){
            return n;
        }
       int dp[] = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;++i){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    //股票
  /*  public int maxProfit(int[] prices) {
        if(prices.length<=1){
            return 0;
        }
        int max=0;
        int min=prices[0];
        for(int i=0;i<prices.length;++i){
            max = Math.max(max,prices[i]-min);
            min=Math.min(min,prices[i]);
        }
        return max;
    }*/
    public int removeElement(int[] nums, int val) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;

    }
    /**
     * 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i)
            for (int j = i+1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;

                }
            }
        return res;
    }

    /**
     * 学习Lock相关
     * condition lock， await，signal
     */
    static final Object room=new Object();
    static boolean hasCigarette=false;
    static boolean hasTakeout=false;

    static ReentrantLock ROOM=new ReentrantLock();
    static Condition condition1=ROOM.newCondition();
    static Condition condition2=ROOM.newCondition();
    static volatile   boolean  run=true;
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            while(run){

            }
        }).start();
        Thread.sleep(1000);
        System.out.println("停止");
        run=false;
    }

    /**
     * 两阶段终止模式
     */
    class Solution {
        public int[] plusOne(int[] digits) {
              for(int i=digits.length-1;i>=0;--i){
                  if(digits[i]==9){
                      digits[i]=0;
                  }else{
                      digits[i]++;
                      return digits;
                  }
              }
              int[] temp = new int[digits.length+1];
              temp[0]=1;
              return temp;
        }
    }
}
