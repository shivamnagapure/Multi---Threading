package MultiThreading.ExecutorService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MixedTaskSubmission {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //By default, methods like invokeAll() and invokeAny() only accept Callable tasks.
        //But we can convert a Runnable into a Callable using
        // Executors.callable(Runnable, result) or Executors.callable(Runnable) (returns null by default).

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Runnable runnable = () -> System.out.println("Runnable is running ...");
        Callable<String> runnableTask = Executors.callable(runnable , "Null") ;
        Callable<String> callable = () -> "Callable is Running" ;

        List<Callable<String>> tasks = Arrays.asList(callable , runnableTask) ;

        //Store the result of Callable
        List<Future<String>> futures = executor.invokeAll(tasks);

        for(Future<String> f : futures){
            System.out.println(f.get());
        }

        executor.shutdown();
    }
}
