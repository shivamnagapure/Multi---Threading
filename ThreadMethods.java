package MultiThreading;

public class ThreadMethods extends Thread {

    public ThreadMethods(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);

        }
    }

    public static void main(String[] args) {
        ThreadMethods t = new ThreadMethods("High");
        ThreadMethods t1 = new ThreadMethods("Low");
        ThreadMethods t2 = new ThreadMethods("Normal");
        t.setPriority(10);
        t1.setPriority(1);
        t2.setPriority(5);
        t.start();
        t1.start();
        t2.start();
    }

    //start , run , join , yield , sleep , interrupt , setDemon , setPriority
}
