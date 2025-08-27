package MultiThreading.Assingment.WaitNotify;

class Buffer {

    private int buffer;

    private boolean hasData = false;

    private int count = 0;

    public synchronized void producer() throws InterruptedException {
        while (hasData) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " Produced value : " + count);
        hasData = true;
        notifyAll();
    }

    public synchronized void consumer() throws InterruptedException {
        while (!hasData){
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " Consumed value : " + count++);
        hasData = false ;
        notifyAll();
    }
}

class Producer1 implements Runnable{

    private final Buffer buffer ;

    public Producer1(Buffer buffer){
        this.buffer = buffer ;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 5 ; i++){
            try {
                buffer.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer1 implements Runnable{

    private final Buffer buffer ;

    public Consumer1(Buffer buffer){
        this.buffer = buffer ;
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

public class MultipleProducersAndConsumers {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer1 producer1 = new Producer1(buffer);
        Consumer1 consumer1 = new Consumer1(buffer);
        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer1);
        Thread t3 = new Thread(consumer1);
        Thread t4 = new Thread(consumer1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}