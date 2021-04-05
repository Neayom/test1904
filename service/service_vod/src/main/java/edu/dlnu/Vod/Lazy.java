package edu.dlnu.Vod;

public class Lazy {
   private Lazy(){

   }
   public static volatile Lazy lazy;//保证指令重拍
   public static  Lazy getInstance(){
            if(lazy==null){
                synchronized (Lazy.class){
                    if(lazy==null){
                        lazy = new Lazy();
                    }
                }
            }
            return lazy;
   }
}
