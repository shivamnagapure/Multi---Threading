package MultiThreading.CreatingThreads;

public class UsingLambda extends Thread {


    public static void main(String[] args) {
        //on the fly class
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Thread t2 = new Thread(runnable);

        // To call the method of Functional interface we use lambda
        //Thread takes Runnable Interface reference obj
        // instead of passing obj we're passing obj method implementation
        Thread t1 = new Thread(() -> {
            System.out.println("Thread running using Lambda Function");
        });

    }


}


