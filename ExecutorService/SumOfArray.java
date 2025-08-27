package MultiThreading.ExecutorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SumOfArray {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int start = 0 ;
        int chunkSize = 4 ;

        int[] arr = { 1,2,3,4,5,6,7,8,9,10,11,12,13 };

        List<int[]> chunks = new ArrayList<>();

        while(start < arr.length){
            //End does not go beyond array length
            int end = Math.min(start + chunkSize , arr.length);
            int[] chunk = Arrays.copyOfRange(arr , start , end);
            chunks.add(chunk);
            start += chunkSize ;
        }

        ExecutorService service = Executors.newFixedThreadPool(2);
        Sum sum = new Sum();

        List<Future<Integer>> futures = new ArrayList<>();

        for(int[] chunk : chunks){
            futures.add(service.submit(() -> sum.sum(chunk)));
        }

        service.shutdown();

        int result = 0  ;
        for(Future<Integer> f : futures){
            result += f.get();
        }

        System.out.println("Sum of Array is " + result);
    }
}

class Sum{

    public int sum(int[] chunk){
        int sum = 0 ;
        for (int i = 0; i < chunk.length; i++) {
            sum += chunk[i];
        }
        System.out.println(Thread.currentThread().getName() + " " + Arrays.toString(chunk) + " -> " + sum);
        return sum ;
    }
}
