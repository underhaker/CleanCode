package myatomicinteger;

public class TestInteger {
    private static MyAtomicInteger counter = new MyAtomicInteger();
    private static final int COUNT = 2_000_000;
    private static void incrementInteger(){
        int i = 0;
        while (i < COUNT) {
            counter.increment();
            i++;
        }
    }
    
    public static void Run(){
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                incrementInteger();
            }
        });
        
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                incrementInteger();
            }
        });
        
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("counter:" + counter.toString());
        System.out.println("duration:" + (System.currentTimeMillis() - startTime)/1000.0 + " seconds");
    }
    public static void main(String[] args)  {
        Run();
    }
}