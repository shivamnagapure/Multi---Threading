package MultiThreading.RettrantLock;

import java.util.concurrent.locks.ReentrantLock;

class AcquireLock{

    ReentrantLock lock = new ReentrantLock(false);

    public void isLockAcq(){
        boolean acquired = lock.tryLock();
        try{
            if(acquired){
                System.out.println(Thread.currentThread().getName() + " Lock acquired");
            }else {
                System.out.println(Thread.currentThread().getName() + " Lock is not acquired");
            }
        }finally {
            if(acquired){
                lock.unlock();
            }
        }

    }

}

public class TryLock{


    public static void main(String[] args) {
        AcquireLock acquireLock = new AcquireLock();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                acquireLock.isLockAcq();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                acquireLock.isLockAcq();
            }
        });

        t1.start();
        t2.start();
    }
}
