package com.bptn.planmyfinance.transaction_file;

import com.bptn.planmyfinance.transactions.Transactions;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TransactionFileGenerator {
    public static boolean saveTransactionsToFile(Transactions transactions, String generatedFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(generatedFilePath))) {
            writer.write("name,type,amount($),date(dd-mm-yyyy)\n");
            writer.write(transactions.toString());
            System.out.println("You can find your financial report in " + generatedFilePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
