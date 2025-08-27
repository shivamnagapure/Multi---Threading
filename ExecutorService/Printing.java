package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Printing {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            NumberPrinter task = new NumberPrinter(i);
            service.submit(task);  //task are assigned to different threads
        }
        service.shutdown();

    }
}

class NumberPrinter extends Thread{

    public int i ;

    public NumberPrinter(int i){
        this.i = i ;
    }

    @Override
    public void run(){
            System.out.println(Thread.currentThread().getName() + " " + i);
    }

}
