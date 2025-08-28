package MultiThreading.ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        BBlockingQueue blockingQueue = new BBlockingQueue();
        for (int i = 0; i < 1; i++) {
            executor.submit(() -> {
                try {
                    blockingQueue.producer();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (int i = 0; i < 1; i++) {
            executor.submit(() -> {
                try {
                    blockingQueue.consumer();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
    }
}

class BBlockingQueue{

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public void producer() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " producing " + i);
            queue.put(i); // blocks if queue is full
            System.out.println("✅ " + Thread.currentThread().getName() + " added: " + i);
            Thread.sleep(200); // simulate production time

        }
    }

    public void consumer() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " waiting to consume...");
            int value = queue.take(); // blocks if queue is empty
            System.out.println("🛒 " + Thread.currentThread().getName() + " consumed: " + value);
            Thread.sleep(500); // simulate consumption time
        }
    }
}
