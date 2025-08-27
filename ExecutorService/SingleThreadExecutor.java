package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutor {
    public static void main(String[] args) {

        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            Print task = new Print(i);
            service.submit(task);
        }

        service.shutdown();

    }
}

class Print implements Runnable{
    private int i ;

    public Print(int i){
        this.i = i ;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}
