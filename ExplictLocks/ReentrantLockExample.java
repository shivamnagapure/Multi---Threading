package MultiThreading.ExplictLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try{
            System.out.println("Outer Method");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    private void innerMethod() {
        lock.lock();
        try{
            System.out.println("Inner Method");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.outerMethod();
    }

}
