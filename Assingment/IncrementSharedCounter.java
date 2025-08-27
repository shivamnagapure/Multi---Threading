package MultiThreading.Assingment;

//Increment shared counter with multiple threads

public class IncrementSharedCounter implements Runnable{

    private int count = 0 ;
    @Override
    public void run() {
        while(count < 1000){
            count ++ ;
            System.out.println(Thread.currentThread().getName() + " -> " + count);

        }

    }

    public static void main(String[] args) {
        IncrementSharedCounter incrementSharedCounter = new IncrementSharedCounter();

        Thread[] threads = new Thread[6];
        for(int i = 0 ; i < 6 ; i++){
            threads[i] = new Thread(incrementSharedCounter) ;
            threads[i].start();
        }
    }
}
