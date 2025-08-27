package MultiThreading.RettrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Buffer{

    private boolean hasValue = false ;

    ReentrantLock lock = new ReentrantLock(false);

    Condition notFull = lock.newCondition();

    Condition notEmpty = lock.newCondition();

    public void producer() throws InterruptedException {

        lock.lock();
        try{
            while(hasValue){
                notFull.await();
            }
            hasValue = true ;
            System.out.println(Thread.currentThread().getName() + " Produced");
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }

    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try{
            while(!hasValue){
                notEmpty.await();
            }
            hasValue = false ;
            System.out.println(Thread.currentThread().getName() + " Consumed");
            notFull.signal();
        }
        finally {
            lock.unlock();
        }
    }

}

class Producer extends Thread{

    Buffer buffer ;

    public Producer(Buffer buffer){
        this.buffer = buffer ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                buffer.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer extends Thread{
    Buffer buffer ;

    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                buffer.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ConditionExample {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        Thread t5 = new Thread(producer);
        Thread t3 = new Thread(consumer);
        Thread t4 = new Thread(consumer);
        Thread t6 = new Thread(consumer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

