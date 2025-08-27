package MultiThreading.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FactorialCalculator {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        Calculate calculate = new Calculate();

        int[] arr = {3 , 5  , 7};

        List<Future<Integer>> futures = new ArrayList<>();

        for(int i=0 ; i < 3 ; i++){
            Calculates task1 = new Calculates(calculate , arr[i]) ;
            futures.add(service.submit(task1));
        }

        for(Future<Integer> f : futures){
            System.out.println(f.get());
        }

        service.shutdown();
    }

}

class Calculates implements Callable<Integer>{
    public Calculate calculate ;
    public int num ;

    public Calculates(Calculate calculate, int num){
        this.calculate = calculate ;
        this.num = num ;
    }

    @Override
    public Integer call() throws Exception {
        int result = calculate.ans(num);
        System.out.println(Thread.currentThread().getName() + " " + result);
        return result ;
    }
}

class Calculate  {

    public int result ;

    public int ans(int num){
        if(num == 1){
            return num ;
        }

        return num * ans(num - 1) ;
    }
}
