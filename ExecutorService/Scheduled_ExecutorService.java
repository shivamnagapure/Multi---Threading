package MultiThreading.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled_ExecutorService {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(Scheduled_ExecutorService::print, 0 , 2 ,TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("Shutting Down Scheduler ...");
            scheduler.shutdown();
            } , 9 , TimeUnit.SECONDS
        );
    }

    public static void print(){
        System.out.println(Thread.currentThread().getName() + " " + "Hello");
    }

}

