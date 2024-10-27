package com.bptn.planmyfinance;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transactions.Transactions;

import java.util.InputMismatchException;
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
            String transactionType = transactionTypeInput.equals("C")  || transactionTypeInput.equals("c")? "credit" : transactionTypeInput.equals("D")  || transactionTypeInput.equals("d")? "debit" : "null";
            System.out.print("Total amount: $");
            double transactionAmount = scanner.nextDouble();
            System.out.print("Transaction date (dd-mm-yyyy): ");
            String transactionDate = scanner.next();

            Transaction newTransaction = new Transaction(transactionName, transactionType, transactionAmount, transactionDate);
            boolean transactionAdded = transactions.addTransaction(newTransaction);
            System.out.println(transactionAdded? "Transaction successfully added!: " + newTransaction.toString(): "Couldn't add transaction. Please try again!");
        } catch (InputMismatchException e) {
            System.out.println("Couldn't recognize your input. Please try again!");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewTransactions() {
        try {
            System.out.println("Which transactions would you like to view?");
            System.out.println("[C]redit");
            System.out.println("[D]ebit");
            System.out.println("[A]ll");
            String transactionTypeToViewInput = scanner.nextLine();
            String transactionTypeToView = switch (transactionTypeToViewInput) {
                case "A", "a" -> "";
                case "C", "c" -> "credit";
                case "D", "d" -> "debit";
                default -> throw new RuntimeException("Transaction type not recognized!");
            };

            System.out.println("In what order by date?");
            System.out.println("[A]scending");
            System.out.println("[D]escending");
            String transactionDisplayOrderInput = scanner.nextLine();
            String transactionDisplayOrder = switch (transactionDisplayOrderInput) {
                case "A", "a" -> "asc";
                case "D", "d" -> "des";
                default -> throw new RuntimeException("Order not recognized!");
            };

            transactions.viewTransactionsByTypeAndOrder(transactionTypeToView, transactionDisplayOrder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
