package MultiThreading.ExecutorService;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class RunnableVsCallable{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(3);

        Runnable runnable = () -> System.out.println("Runnable task is running...");

        Callable<String> callable = () -> {
            return "Current timestamp: " + LocalDateTime.now();
        };

        service.submit(runnable);
        Future<String> result = service.submit(callable) ;

        System.out.println(result.get());

        service.shutdown();
    }
}


