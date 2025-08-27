package MultiThreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            NumberPrinter task = new NumberPrinter(i);
            service.submit(task);
        }

        service.shutdown();
    }
}
