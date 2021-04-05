package edu.dlnu.demo;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranLock {
   private static ReentrantLock lock = new ReentrantLock();

    Thread t1 = new Thread(){
      @Override
      public void run(){
          
      }
    };


    Thread t2 = Runnable(t1)
    public static void m1(){
        lock.lock();
        try {
            System.out.println("m1");
            m2();
        }finally {
            lock.unlock();
        }
    }

    public static void m2(){
        lock.lock();
        try {
            System.out.println("m2");
        }finally {
            lock.unlock();
        }
    }
}
