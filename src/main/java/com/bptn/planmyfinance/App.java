package com.bptn.planmyfinance;

import com.bptn.planmyfinance.dashboard.Dashboard;
import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transactions.Transactions;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App implements Dashboard {

    private final String transactionFileName = "transactions.txt";
    private final Transactions transactions;
    private final Scanner scanner;

    public App() {
        this.transactions = new Transactions(transactionFileName);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        showMainMenu();
    }

    public void showMainMenu() {
        showDashboard();
        boolean exitProgram = false;
        do {

            System.out.println("[A]dd a transaction");
            System.out.println("[V]iew transactions");
            System.out.println("[E]dit a transaction");
            System.out.println("[G]enerate a report");
            System.out.println("[Q]uit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "A", "a":
                    handleAddTransaction();
                    break;
                case "V", "v":
                    handleViewTransactions();
                    break;
                case "E", "e":
                    handleEditTransaction();
                    break;
                case "Q", "q":
                    exitProgram = true;
                    System.out.println("Thank you for planning with us!");
                    break;
            }
            System.out.println();
        } while (!exitProgram);
    }

    public void handleAddTransaction() {
        try {
            System.out.print("Name of transaction: ");
            String transactionName = scanner.nextLine();
            System.out.print("[C]redit or [D]ebit: ");
            String transactionTypeInput = scanner.next();
            scanner.nextLine();
            String transactionType = transactionTypeInput.equals("C")  || transactionTypeInput.equals("c")? "credit" : transactionTypeInput.equals("D")  || transactionTypeInput.equals("d")? "debit" : "null";
            System.out.print("Total amount: $");
            double transactionAmount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Transaction date (dd-mm-yyyy): ");
            String transactionDate = scanner.next();
            scanner.nextLine();

            Transaction newTransaction = new Transaction(transactionName, transactionType, transactionAmount, transactionDate);
            boolean transactionAdded = transactions.addTransaction(newTransaction, transactionFileName);
            System.out.println(transactionAdded? "Transaction successfully added!" : "Couldn't add transaction. Please try again!");
        } catch (InputMismatchException e) {
            System.out.println("Couldn't recognize your input. Please try again!");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleViewTransactions() {
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
            System.out.println();
            transactions.viewTransactionsByTypeAndOrder(transactionTypeToView, transactionDisplayOrder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleEditTransaction() {
        int transactionsSize = transactions.getTransactions().size();
        for (int transactionIndex = 0; transactionIndex < transactions.getTransactions().size(); transactionIndex++) {
            System.out.print(transactionIndex + 1 + ". ");
            System.out.println(transactions.getTransactions().get(transactionIndex));
        }
        System.out.println("Which transaction would you like to edit?");
        if (scanner.hasNextInt()) {
            int chooseTransactionIndex = scanner.nextInt();
            scanner.nextLine();
            if (chooseTransactionIndex >= 1 && chooseTransactionIndex <= transactionsSize) {
                try {
                    System.out.print("Name of transaction: ");
                    String transactionName = scanner.next();
                    System.out.print("[C]redit or [D]ebit: ");
                    String transactionTypeInput = scanner.next();
                    String transactionType = transactionTypeInput.equals("C")  || transactionTypeInput.equals("c")? "credit" : transactionTypeInput.equals("D")  || transactionTypeInput.equals("d")? "debit" : "null";
                    System.out.print("Total amount: $");
                    double transactionAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Transaction date (dd-mm-yyyy): ");
                    String transactionDate = scanner.nextLine();

                    Transaction newTransaction = new Transaction(transactionName, transactionType, transactionAmount, transactionDate);
                    transactions.setTransaction(chooseTransactionIndex - 1, newTransaction, transactionFileName);
                    System.out.println("Transaction successfully edited!");
                } catch (InputMismatchException e) {
                    System.out.println("Couldn't recognize your input. Please try again!");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Transaction doesn't exist. Please try again!");
            }
        } else {
            System.out.println("Transaction selection not recognized. Please try again!");
        }

    }
    @Override
    public void showDashboard() {
        showAppLogo();
        greetUser();
        showCurrentDateTime();
        System.out.println();
    }

    @Override
    public void showAppLogo() {
        System.out.println("\u001B[32m" + "\n" +
                "  ____  _               __  __         _____ _                            \n" +
                " |  _ \\| | __ _ _ __   |  \\/  |_   _  |  ___(_)_ __   __ _ _ __   ___ ___ \n" +
                " | |_) | |/ _` | '_ \\  | |\\/| | | | | | |_  | | '_ \\ / _` | '_ \\ / __/ _ \\\n" +
                " |  __/| | (_| | | | | | |  | | |_| | |  _| | | | | | (_| | | | | (_|  __/\n" +
                " |_|   |_|\\__,_|_| |_| |_|  |_|\\__, | |_|   |_|_| |_|\\__,_|_| |_|\\___\\___|\n" +
                "                               |___/                                      \n");
    }

    @Override
    public void greetUser() {
        String userName = "Emmanuel";
        System.out.println("\u001B[33m" + "Welcome, " + userName + "!");
    }

    @Override
    public void showCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("\u001B[33m" + dateTime);
    }
}
