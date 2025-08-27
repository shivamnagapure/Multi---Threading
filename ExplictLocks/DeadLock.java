package MultiThreading.ExplictLocks;


class Pen{

    public synchronized void writingWithPenAndPaper(Paper paper ){ // this method have intrinsic lock of pen
        System.out.println(Thread.currentThread().getName() + " using paper and trying to use pen");
        paper.finishWriting(); // try to access intrinsic lock of paper
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finish writing using pen");
    }
}

class Paper{

    public synchronized void writingWithPenAndPaper(Pen pen){ // this method have intrinsic lock of paper
        System.out.println(Thread.currentThread().getName() + " using pen and trying to use paper");
        pen.finishWriting(); // try to access intrinsic lock of pen
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finish writing using paper");
    }
}

class Task1 extends Thread{
    private final Pen pen ;
    private final Paper paper ;

    public Task1(Paper paper , Pen pen){
        this.paper = paper ;
        this.pen = pen ;
    }

    @Override
    public void run(){
       paper.writingWithPenAndPaper(pen);
    }

}

class Task2 extends Thread{
    private final Pen pen ;
    private final Paper paper ;

    public Task2(Paper paper , Pen pen){
        this.paper = paper ;
        this.pen = pen ;
    }

    @Override
    public void run(){
        synchronized (paper){ // first it acquires lock of paper and then pen
            pen.writingWithPenAndPaper(paper);
        }
    }
}


public class DeadLock {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Task1 task1 = new Task1(paper ,pen);
        Task2 task2 = new Task2(paper ,pen);

        Thread thread1 = new Thread(task1 , "Thread - 1 ");
        Thread thread2 = new Thread(task2 , "Thread - 1 ");

        thread1.start();
        thread2.start();
    }
}

//Solution

//Make consistent ordering of acquiring lock
