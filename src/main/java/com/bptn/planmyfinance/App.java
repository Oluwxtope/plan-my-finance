package com.bptn.planmyfinance;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transactions.Transactions;

import java.util.Scanner;

public class App {

    private final Transactions transactions;
    private Scanner scanner;

    public App(Transactions transactions, Scanner scanner) {
        this.transactions = transactions;
        this.scanner = scanner;
    }

    public void addTransaction() {
        try {
            System.out.print("Name of transaction: ");
            String transactionName = scanner.next();
            System.out.print("[C]redit or [D]ebit: ");
            String transactionTypeInput = scanner.next();
            String transactionType = transactionTypeInput.equals("C")  || transactionTypeInput.equals("c")? "credit" : "debit";
            System.out.print("Total amount: $");
            double transactionAmount = scanner.nextDouble();
            System.out.print("Transaction date (dd-mm-yyyy): ");
            String transactionDate = scanner.next();

            Transaction newTransaction = new Transaction(transactionName, transactionType, transactionAmount, transactionDate);
            boolean transactionAdded = transactions.addTransaction(newTransaction);
            System.out.println(transactionAdded? "Transaction successfully added!" : "Couldn't add transaction. Please try again!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
