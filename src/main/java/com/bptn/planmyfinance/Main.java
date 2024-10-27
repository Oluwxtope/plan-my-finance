package com.bptn.planmyfinance;

import com.bptn.planmyfinance.transaction_file_processor.TransactionFile;
import com.bptn.planmyfinance.transactions.Transactions;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userName = "Emmanuel";
         Date date = new Date();

         String transactionFileName = "transactions.txt";
        Transactions transactions = new Transactions(transactionFileName);

         Scanner scanner = new Scanner(System.in);
         String choice = "";
        boolean exitProgram = false;
         do {
             showAppLogo();
             System.out.println("Welcome, Emmanuel!");
             System.out.println(date);
             System.out.println();

             System.out.println("[A]dd a transaction");
             System.out.println("[V]iew transactions");
             System.out.println("[S]earch for transactions");
             System.out.println("[G]enerate a report");
             System.out.println("[E]xit");

             choice = scanner.nextLine();

             App app = new App(transactions, scanner);

             switch(choice) {
                 case "A", "a":
                     app.addTransaction();
                     break;
                 case "V", "v":
                     app.viewTransactions();
                     break;
                 case "S", "s":
                    break;
                 case "G", "g":
                     String reportFileName = scanner.next();
                     TransactionFile.writeTransactions(transactions, reportFileName+".txt");
                     break;
                 case "E", "e":
                     exitProgram = true;
                     System.out.println("Thank you for planning with us!");
                     break;
             }
         } while (!exitProgram);
         scanner.close();
    }

    public static void showAppLogo() {
        System.out.println("\n" +
                "  ____  _               __  __         _____ _                            \n" +
                " |  _ \\| | __ _ _ __   |  \\/  |_   _  |  ___(_)_ __   __ _ _ __   ___ ___ \n" +
                " | |_) | |/ _` | '_ \\  | |\\/| | | | | | |_  | | '_ \\ / _` | '_ \\ / __/ _ \\\n" +
                " |  __/| | (_| | | | | | |  | | |_| | |  _| | | | | | (_| | | | | (_|  __/\n" +
                " |_|   |_|\\__,_|_| |_| |_|  |_|\\__, | |_|   |_|_| |_|\\__,_|_| |_|\\___\\___|\n" +
                "                               |___/                                      \n");
    }
}
