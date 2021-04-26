package pt.ual.pp.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        List<Thread> threads = new ArrayList<>();
        int withdrawThreadAmount = 2;
        int depositThreadAmount = 2;

        for(int i = 0; i < depositThreadAmount; i++) {
            Thread newThread = new DepositThread(bankAccount);
            threads.add(newThread);
            newThread.start();
        }

        for(int i = 0; i < withdrawThreadAmount; i++) {
            Thread newThread = new WithdrawThread(bankAccount);
            threads.add(newThread);
            newThread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        System.out.println("Montante atual: " + bankAccount.getBalance());
    }
}
