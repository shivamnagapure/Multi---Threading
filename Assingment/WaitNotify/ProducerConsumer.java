package MultiThreading.Assingment.WaitNotify;

class BoundedBuffer {

    private final int[] buffer = new int[5];

    private boolean hasData = false ;

    private int count = 1 ;

    public synchronized void producer() throws InterruptedException {
        while (hasData){
            wait();
        }
        for(int i = 0 ; i < buffer.length ; i++){
            System.out.println(Thread.currentThread().getName() + " Produced value : " + count);
            buffer[i] = count++;
        }
        hasData = true ;
        notify();
    }

    public synchronized void consumer() throws InterruptedException {
        while (!hasData){
            wait();
        }
        for(int i = 0 ; i < buffer.length ; i++){
            System.out.println(Thread.currentThread().getName() + " Consumed Value : " + buffer[i] );
        }
        hasData = false ;
        notify();
    }
}

class Producer implements Runnable{

    private final BoundedBuffer boundedBuffer ;

    public Producer(BoundedBuffer boundedBuffer){
        this.boundedBuffer = boundedBuffer ;
    }

    @Override
    public void run() {
        try {
            boundedBuffer.producer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable{

    private final BoundedBuffer boundedBuffer ;

    public Consumer(BoundedBuffer boundedBuffer){
        this.boundedBuffer = boundedBuffer ;
    }
    @Override
    public void run() {
        try {
            boundedBuffer.consumer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}


