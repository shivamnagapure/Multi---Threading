package MultiThreading.ExplictLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessExample {

    private final Lock lock = new ReentrantLock(true);

    public void accessResources(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " acquire the lock");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName() + " release the lock");
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        FairnessExample fairnessExample = new FairnessExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                fairnessExample.accessResources();
            }
        };

        Thread t1 = new Thread(task ,"Thread 1");
        Thread t2 = new Thread(task ,"Thread 2");
        Thread t3 = new Thread(task ,"Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
