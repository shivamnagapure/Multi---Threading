package MultiThreading.Assingment.WaitNotify;

public class EvenOdd {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Even even = new Even(printer);
        Odd odd = new Odd(printer);
        Thread t1 = new Thread(even);
        Thread t2 = new Thread(odd);

        t1.start();
        t2.start();
    }
}

class Even extends Thread{

    private final Printer printer ;

    public Even(Printer printer){
        this.printer = printer ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                printer.even();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Odd extends Thread{

    private final Printer printer ;

    public Odd(Printer printer){
        this.printer = printer ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                printer.odd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Printer {

    private volatile boolean even = false ;
    private int count = 1 ;

    public synchronized void even() throws InterruptedException{
        while (!even){
            wait();
        }
        even = false ;
        System.out.println(Thread.currentThread().getName() + " " + count++);
        notify();
    }

    public synchronized void odd() throws InterruptedException{
        while (even){
            wait();
        }
        even = true ;
        System.out.println(Thread.currentThread().getName() + " " + count++);
        notify();
    }

}
