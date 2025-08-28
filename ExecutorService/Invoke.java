package MultiThreading.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// invokeAll() is used when you want to submit multiple Callable tasks at once and wait for all of them to finish.
// its blocks tasks to other get complete

// invokeAny()

//In ExecutorService, invokeAny() is used when you submit a collection of Callable tasks,
// but you only care about the result of the first one that successfully finishes.
//👉 Unlike invokeAll(), it does not return all results — only one result (the first to finish).

public class Invoke {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        RandomNumber randomNumber = new RandomNumber();

        List<Callable<Double>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            tasks.add(randomNumber::number);
        }

        //List<Future<Double>> futures = executor.invokeAll(tasks);

        Double task = executor.invokeAny(tasks);

        System.out.println(task);

//        for (Future<Double> f : futures){
//            try{
//                System.out.println(f.get());
//            }catch (ExecutionException e){
//                System.out.println("Task threw an exception: " + e.getCause());
//            }
//
//        }

        executor.shutdown();
    }
}

class RandomNumber{

    public double number(){
        System.out.println(Thread.currentThread().getName());
        return Math.floor(Math.random() * 10) ;
    }
}

