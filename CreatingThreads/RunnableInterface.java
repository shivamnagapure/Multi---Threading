package MultiThreading.CreatingThreads;

public class RunnableInterface implements Runnable{
    int count = 0 ;

    @Override
    public void run() {
        while(count < 10){
            count ++ ;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {

        // we cannot the access the run method by RunnableInterface ,because it is not static
        RunnableInterface runnableInterface = new RunnableInterface();

        // Thread Constructor can take object of Runnable type reference and store it in private field
        // when we call start method , its internally run object run() method
        Thread t1 =new Thread(runnableInterface);
        Thread t2 =new Thread(runnableInterface);

        t1.start();
        t2.start();
    }
}
