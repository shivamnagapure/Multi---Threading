package MultiThreading.ExplictLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteReentrant {

    private int count = 0 ;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();


    public void increment() throws InterruptedException {
        writeLock.lock();
        try{
            count++ ;
            Thread.sleep(100); // make acquiring of lock a little bit slower , due to which thread 1 and thread 2 can use readLock
        }finally {
          writeLock.unlock();
        }

    }

    public int getCount(){
        readLock.lock();
        try{
            return count ;
        }finally {
            readLock.unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteReentrant counter = new ReadWriteReentrant();

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        counter.increment();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "incremented");
                }

            }
        };

        Runnable readTask = new Runnable(){
            public void run(){
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "read : " + counter.getCount());
                }
            }
        };

        Thread t1 = new Thread(writeTask);
        Thread t2 = new Thread(readTask );
        Thread t3 = new Thread(readTask );
        t1.start();
        t2.start();
        t3.start();
    }
}
