package MultiThreading.CreatingThreads;

public class ThreadClass extends  Thread{
    int count = 0 ;
    @Override
    public void run() {
        while(count < 10){
            count++ ;
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        ThreadClass a = new ThreadClass();
        ThreadClass b = new ThreadClass();
        //Thread class doesn't have run method , so it can't run run() method .
        //we have to pass object of a class which extends Thread Class or Implements Runnable Interface
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();

        //Here Thread Class override run method , so we can start the run method directly from its obj
        a.start();
        b.start();

    }
}
