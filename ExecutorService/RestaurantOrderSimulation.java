package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantOrderSimulation {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        ProcessOrder order = new ProcessOrder();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executors.submit(() -> order.prepareOrder(finalI));
        }
        executors.shutdown();
    }
}

class ProcessOrder{

    public void prepareOrder(int orderId){
        try{
            System.out.println(Thread.currentThread().getName() + " started preparing Order " + orderId);
            Thread.sleep(1000 + (int)(Math.random() * 2000));
            System.out.println(Thread.currentThread().getName() + " completed Order " + orderId);
        }catch(InterruptedException e){
            System.out.println("Order " + orderId + " preparation interrupted!");
        }
    }
}
