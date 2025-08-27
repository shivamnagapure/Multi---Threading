package MultiThreading.Assingment.WaitNotify;

// DeadLock happen

class Chopsticks{

    private boolean taken = false;

    public synchronized void pickUp() throws InterruptedException{
        while (taken){
            wait();
        }
        taken = true;
        System.out.println(Thread.currentThread().getName() + "Left Chopstick picked ");
    }

    public synchronized void putDown(){
       if(taken){
           taken = false ;
       }
    }

}

class Philosopher extends Thread{

    private final Chopsticks left ;
    private final Chopsticks right ;
    private final int id ;

    public Philosopher(int id , Chopsticks left , Chopsticks right ){
        this.id = id ;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try {
            think();

                left.pickUp();
            right.pickUp();
            eat();
            left.putDown();
            right.putDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking");
    }

    public void eat(){
        System.out.println("Philosopher " + id + " is eating");
    }
}

public class DiningPhilosophers {

    public static void main(String[] args) {

        Chopsticks[] chopsticks = new Chopsticks[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopsticks();
        }


        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
           threads[i] = new Thread(new Philosopher(i , chopsticks[i] , chopsticks[(i + 1) % 5]));
           threads[i].start();
        }
    }
}