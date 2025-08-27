package MultiThreading.Assingment.WaitNotify;

public class NumberPrinter implements Runnable {

    private boolean change = false ;

    private int count = 0 ;
    @Override
    public  void run(){
        synchronized (this){ // object acquire lock before wait/notify
            while(change){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for(int i = 0 ; i < 5 ; i++){
                count ++ ;
                System.out.println(Thread.currentThread().getName() + " " + count);
            }
            notify();
        }
    }


    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter() ; // We create obj to run the run() method
        Thread t1 = new Thread(numberPrinter);
        Thread t2 = new Thread(numberPrinter) ;

        t1.start();
        t2.start();

    }

}


