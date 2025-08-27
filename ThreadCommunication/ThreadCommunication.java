package MultiThreading.ThreadCommunication;

class SharedResources {

    private int data ;

    private boolean hasData ;

    public synchronized void produce(int value){
        while (hasData){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        data = value ;
        hasData = true ;
        System.out.println("Produced : " + value);
        notify();
    }

    public synchronized int consume(){
        while (!hasData){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        hasData = false ;
        System.out.println("Consumed : " + data);
        notify();
        return data ;
    }
}

class Produce implements Runnable{

    private SharedResources resource ;

    public Produce(SharedResources resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            resource.produce(i);
        }
    }
}

class Consume implements Runnable{

    private SharedResources resource ;

    public Consume(SharedResources resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.consume();
        }
    }
}


public class ThreadCommunication{

    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        Thread thread1 = new Thread(new Produce(sharedResources));
        Thread thread2 = new Thread(new Consume(sharedResources));
        thread1.start();
        thread2.start();

    }
}
