package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.submit(counter::increment);
        }

        executor.shutdown();

        Thread.sleep(100);
        System.out.println(counter.getCount());
    }
}

class Counter{

    AtomicInteger count = new AtomicInteger(0);

    public void increment(){
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get() ;
    }
}
