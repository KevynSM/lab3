package pt.ual.pp.task1;

import java.util.Random;

public class DepositThread extends Thread {
    private BankAccount account;
    private double totalDeposit;

    public DepositThread(BankAccount account) {
        this.account = account;
        this.totalDeposit = 0;
    }

    public void deposit(BankAccount account) {
        try {
            do {
                Random random = new Random();
                double value = random.nextDouble() * 9 + 1;
                int timeSleep = random.nextInt(3) + 1;
                account.deposit(value);
                this.totalDeposit += value;
                synchronized (account) {
                    account.notifyAll();
                }

                System.out.println("Identificador: " + currentThread().getId() +
                        " Montante atual na conta: " + account.getBalance() +
                        " Montante depositado: " + value);
                Thread.sleep(timeSleep * 1000);
            }
            while(this.totalDeposit <= 50);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        deposit(account);
    }
}
