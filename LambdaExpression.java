package MultiThreading;

public class LambdaExpression {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Anonymous class");
//            }
//        };

//        Runnable runnable = () -> {
//            System.out.println("Lambda Expression");
//        };

//        Runnable runnable = () -> System.out.println("Lambda Expression");


        // For Thread Creation

        Thread thread1 = new Thread(() -> System.out.println("Thread Creation"));

    }
}
