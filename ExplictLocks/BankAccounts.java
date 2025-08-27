package MultiThreading.ExplictLocks;

public class BankAccounts {

    private int balance = 100 ;

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        if(balance >= amount){
            System.out.println(Thread.currentThread().getName() + " Proceeding with withdraws ");
            balance -= amount ;
            System.out.println(Thread.currentThread().getName() + " current balance " + balance);
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                System.out.println();
            }
        }else{
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }


    }
}
