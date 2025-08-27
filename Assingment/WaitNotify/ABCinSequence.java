package MultiThreading.Assingment.WaitNotify;

public class ABCinSequence extends Thread {

    public static void main(String[] args) {
        Print print = new Print();

       Runnable a = new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   try {
                       print.printA();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       };

       Runnable b = new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   try {
                       print.printB();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       };

       Runnable c = new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i < 5; i++) {
                   try {
                       print.printC();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       };

       Thread t1 = new Thread(a);
       Thread t2 = new Thread(b);
       Thread t3 = new Thread(c);

       t1.start();
       t2.start();
       t3.start();
    }
}

class Print {

    private int state = 0 ;

    public synchronized void printA() throws InterruptedException {
        while(state != 0){
            wait();
        }
        System.out.print(Thread.currentThread().getName() + " A ");
        state = 1 ;
        notifyAll();
    }

    public synchronized void printB() throws InterruptedException {
        while(state != 1){
            wait();
        }
        System.out.print(Thread.currentThread().getName() + " B ");
        state =  2;
        notifyAll();
    }

    public synchronized void printC() throws InterruptedException {
        while(state != 2){
            wait();
        }
        System.out.println(Thread.currentThread().getName()+ " C ");
        state = 0 ;
        notifyAll();
    }
}


