package edu.dlnu.Vod;

import edu.dlnu.Vod.utils.lazyMan;

public class Hungry1 {
    private Hungry1(){

    }
    private final static  Hungry1 h = new Hungry1();
    public static Hungry1 getInstance(){
        return h;
    }

}
