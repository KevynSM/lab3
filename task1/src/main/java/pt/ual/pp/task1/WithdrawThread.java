package pt.ual.pp.task1;

import java.util.Random;

public class WithdrawThread extends Thread {
    private BankAccount account;

    public WithdrawThread(BankAccount account) {
        this.account = account;
    }

    public void withdraw(BankAccount account) {

        try {
            for(int i = 0; i < 4; i++) {
                Random random = new Random();
                double value = random.nextDouble() * 15 + 5;
                int timeSleep = random.nextInt(4) + 2;

                synchronized (account) {
                    if (value > account.getBalance()) {
                        account.wait();
                        i--;
                    }
                    else {
                        account.withdraw(value);
                        System.out.println("Identificador: " + currentThread().getId() +
                                " Montante atual na conta: " + account.getBalance() +
                                " Numero de levantamentos realizados: " + (i + 1));
                        Thread.sleep(timeSleep * 1000);
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        withdraw(account);
    }
}
