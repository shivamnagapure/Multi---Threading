package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {

        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            Prints task = new Prints(i);
            service.submit(task);
        }

        service.shutdown();

    }
}

class Prints implements Runnable{
    private int i ;

    public Prints(int i){
        this.i = i ;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}
