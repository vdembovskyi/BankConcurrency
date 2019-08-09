public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300);
                account.withdraw(50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100);
            }
        }).start();



    }
}
