package com.fmi.cleancode.demoracecondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class RaceCondition {
    private static int counter = 0;
    static Lock  mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                System.out.println("T1:" + Thread.currentThread().getName());
                int i = 0;
                while (i < 2_000_000) {
                    mutex.lock();
                    counter++;
                    i++;
                    mutex.unlock();
                }
                notify();
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                System.out.println("T2:" + Thread.currentThread().getName());
                int i = 0;
                while (i < 2_000_000) {
                    mutex.lock();
                    counter++;
                    i++;
                    mutex.unlock();
                }
            }
        });
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter:" + counter);
        System.out.println("time:" + (System.currentTimeMillis() - startTime));
    }
}