package edu.dlnu.Vod.utils;


public class Main {
    public static void main(String[] args) {
        String s = "abcdefg";
        String s1 = reverseStr(s);
        System.out.println(s1);
    }

    public static String reverseWords(String str) {
        str.trim();
        String[] split = str.split(" ");
        String res="";
        for(String s:split){
            String sTr = reverseStr(s);
            res+=sTr+" ";
        }
        res.trim();
        return res;

    }
    static String reverseStr(String strArray){

        StringBuffer res = new StringBuffer();
        for(int i=strArray.length()-1;i>=0;--i){
            res.append(strArray.charAt(i));
        }
        return res.toString();
    }


}
