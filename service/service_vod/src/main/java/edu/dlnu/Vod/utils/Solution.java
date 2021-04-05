package edu.dlnu.Vod.utils;

class Solution {
    public static  boolean rotate(int num) {
        if(num<0){
            return false;
        }
        int sum=0;
       while(num!=0){
           sum=sum*10+num%10;
           num/=10;
       }
       if(sum==num)
           return true;
       else
           return false;
    }
}
