package MultiThreading.RettrantLock;

import java.util.concurrent.locks.ReentrantLock;

class Count{

    private int count = 0 ;

    //ReentrantLock lock = new ReentrantLock(false);//starvation
    //Thread-0 count
    //Thread-0 count ...
    //then
    //Thread-1 count
    //Thread-1 count

    ReentrantLock lock = new ReentrantLock(true);//FIFO

    public void increment(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " count");
            count++ ;
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

class CounterIncrement extends Thread{

    Count count ;

    public CounterIncrement(Count count){
        this.count = count ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            count.increment();
        }
    }
}

public class Counter {
    public static void main(String[] args) {
        Count count = new Count();
        CounterIncrement t1 = new CounterIncrement(count);
        CounterIncrement t2 = new CounterIncrement(count);
        t1.start();
        t2.start();
    }
}
