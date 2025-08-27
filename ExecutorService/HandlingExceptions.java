package MultiThreading.ExecutorService;

import java.util.concurrent.*;

public class HandlingExceptions {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> task =  service.submit(() -> divide(5));
        service.shutdown();
        System.out.println(task.get());

    }

    public static int divide(int num){
        return num / 0 ;
    }
}
