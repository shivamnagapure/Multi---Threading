package MultiThreading.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTaskExecution {

    static long startTime = System.currentTimeMillis();

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        int[] arr = {1 , 3 , 5} ;

        for(int i : arr){
            scheduler.schedule(DelayedTaskExecution::print , i , TimeUnit.SECONDS);
        }

        scheduler.shutdown();

    }

    public static void print(){
        System.out.println(Thread.currentThread().getName() + " " + "Hello " + (System.currentTimeMillis() - startTime ) / 1000);
    }
}
