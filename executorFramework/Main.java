package MultiThreading.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long startTime  = System.currentTimeMillis();

        //we store them in the array, so we can later loop over them to call .join() and wait for each to finish.
//        Thread[] threads = new Thread[9]; // use when we have to perform any operation after this result of all threads
//        for(int i = 1 ; i < 10 ; i++){
//            int finalI = i;
//            threads[i - 1] = new Thread(
//                    () -> {
//                        int result = factorial(finalI);
//                        System.out.println(result);
//                    }
//            );
//            threads[i-1].start();
//        }
//
//        for(Thread thread : threads){
//            try{
//                thread.join();
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 1 ; i < 10 ; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int result = factorial(finalI);
                System.out.println(result);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10 , TimeUnit.SECONDS);


        System.out.println("total time : " + (System.currentTimeMillis() - startTime));
    }

    public static int factorial(int i){
        int ans = 1 ;
        for(int j = i ; j > 0 ; j--){
            ans *= j ;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ans;
    }
}
