package MultiThreading.executorFramework;

import java.util.concurrent.*;

public class Future_ {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        //Runnable runnable = () -> "Shivam" ; // gives error it doesn't return anything
        Callable<String> callable= () -> "Shivam" ;


        Future<?> future = executorService.submit(() -> "Shiva"); // submit can have both
        System.out.println();
        if(future.isDone()){
            System.out.println("Task Done");
        }
        executorService.shutdown();

    }
}
