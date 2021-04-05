package edu.dlnu.Vod;

import java.util.concurrent.atomic.AtomicInteger;

//饿汉式单例
public class Hungry {

    //可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];
    private byte[] data5 = new byte[1024*1024];
    //单例，构造器私有，私有的话别人无法new这个对象了，保证内存中只有这一个对象
    private Hungry(){

    }

    //一上来就把对象加载了,饿汉式有可能浪费内存
    private final static Hungry h = new Hungry();
    public static Hungry getInstance(){
        return h;
    }
}
