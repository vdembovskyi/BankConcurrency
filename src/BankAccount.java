import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private double balance;
    private String accountNumber;
    private ReentrantLock reentrantLock;


    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.reentrantLock = new ReentrantLock();
    }

    public void deposit(double amount) {
        boolean status = false;
        try {
            if (reentrantLock.tryLock(1000, TimeUnit.MICROSECONDS)) {
                try {
                    balance += amount;
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Trans status "+ status);

    }

    public void withdraw(double amount) {
        boolean status = false;
        try {
            if (reentrantLock.tryLock(1000, TimeUnit.MICROSECONDS)) {
                try {
                    balance -= amount;
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println("Trans status "+ status);

    }


}
