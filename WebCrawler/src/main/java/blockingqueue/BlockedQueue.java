package blockingqueue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class BlockedQueue<T> extends AbstractQueue<T> {
    private LinkedList<T> l = new LinkedList<T>();
    private int maxSize;
    public BlockedQueue(){
        this.maxSize=100;
    }
    public BlockedQueue(int mSize){
        this.maxSize=mSize;
    }
    public void setMaxSize(int maxSize){
        this.maxSize=maxSize;
    }
    @Override
    public synchronized boolean offer(T e) {
        while (l.size() >= maxSize)
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        notifyAll();
        return l.offer(e);
    }

    @Override
    public T peek() {
        return l.peek();
    }

    @Override
    public synchronized T poll() {

        while (l.size() == 0)
            try {
//                System.out.println("trying to poll from an empty queue..");
                wait();
//                System.out.println("still waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if (l.size() <= maxSize)
            notifyAll();
        return l.poll();
    }

    @Override
    public Iterator<T> iterator() {
        return l.iterator();
    }

    @Override
    public int size() {
        return l.size();
    }

    public static void main(String[] args) throws InterruptedException {
        final BlockedQueue<Integer> bq = new BlockedQueue<Integer>();
        // Thread t1 = new Thread(new Runnable() {
        // @Override
        // public void run() {
        //
        // try {
        // for (int i = 1; i <= 10; i++) {
        // Thread.sleep(5000);
        // bq.offer(i);
        // System.out.println(Thread.currentThread().getName() + ": " + i +
        // " added to queue.");
        // }
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        //
        // }
        //
        // });
        // Thread t2 = new Thread(new Runnable() {
        // @Override
        // public void run() {
        //
        // for (int i = 1; i <= 10; i++) {
        // System.out.println(Thread.currentThread().getName() +
        // ": Trying to pop from queue...");
        // System.out.println(Thread.currentThread().getName() + ": " +
        // bq.poll() + " is removed from queue.");
        // }
        // }
        //
        // });

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    bq.offer(i);
                    System.out.println("element " + i + " offered to queue..");
                }
            }

        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 22; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("element " + bq.poll() + " polled from queue..");
                }
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
