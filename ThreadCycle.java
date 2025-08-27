package MultiThreading;

public class ThreadCycle extends Thread{


    @Override
    public void run() {
        try{
            System.out.println("RUNNING");
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) throws InterruptedException {
       ThreadCycle t = new ThreadCycle(); //NEW state
       System.out.println(t.getState());
       t.start();
       System.out.println(t.getState());
       Thread.sleep(100);
       System.out.println( t.getState());
       t.join();
        System.out.println(t.getState());





    }
}
