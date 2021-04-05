package edu.dlnu.Vod.utils;

//懒汉式单例,用的时候才加载
public class lazyMan {
    private lazyMan(){ //外部无法new,无法创建新的实例
        System.out.println(Thread.currentThread().getName()+"ok");
    }
    //双重检测锁模式 懒汉单例 DCL懒汉式
    //双重检测锁加原子性操作
    private volatile static lazyMan l;
    //实例对象是第一次调用的时候才构建的，而不是程序一启动就构建然后等你调用
    public static lazyMan getInstance(){ //获取对象
        //在getInstance时，不需要同步锁，所有线程都可以进入，只有创建的时候才加锁

        if(l==null){//可能会有多个不同线程同时进入
            //加锁
            synchronized (lazyMan.class) {
                if (l == null) { //A执行完，已经创建了,B获得锁，如果不做判断也会创建对象
                    l = new lazyMan(); //不是原子性操作,
                    /**
                     * 1、分配内存
                     * 2、执行构造方法,初始化对象
                     * 3、把这个对象指向这个空间
                     *真正执行，虚拟机肯能会因为效率而进行指令重拍
                     * 可能会132 A
                     *          B 此时lazyman还没有完成构造
                     */
                }
            }
        }
        return l;

    }












    //单线程下单例ok,多线程并发
    public static void main(String[] args) {
        for(int i=0;i<10;++i){
            new Thread(()->{
                lazyMan.getInstance();
            }).start();
        }
    }
}
