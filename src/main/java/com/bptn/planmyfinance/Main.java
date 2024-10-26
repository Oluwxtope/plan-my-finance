package com.bptn.planmyfinance;

import com.bptn.planmyfinance.transaction_file_processor.TransactionFileGenerator;
import com.bptn.planmyfinance.transactions.Transactions;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userName = "Emmanuel";
         Date date = new Date();

         String transactionFileName = "transactions.csv";
        Transactions transactions = new Transactions(transactionFileName);

         Scanner scanner = new Scanner(System.in);
         int choice = -1;
        boolean exitProgram = false;
         do {
             showAppLogo();
             System.out.println("Welcome, Emmanuel!");
             System.out.println(date);
             System.out.println();

             System.out.println("1. Add a transaction");
             System.out.println("2. View transactions");
             System.out.println("3. Search for transactions");
             System.out.println("4. Generate a report");
             System.out.println("5. Exit");

             choice = scanner.nextInt();

             App app = new App(transactions, scanner);

             switch(choice) {
                 case 1:
                     app.addTransaction();
                     break;
                 case 2:

                 case 3:

                 case 4:
                     String reportFileName = scanner.next();
                     TransactionFileGenerator.saveTransactionsToFile(transactions, reportFileName+".csv");
                     break;
                 case 5:
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
