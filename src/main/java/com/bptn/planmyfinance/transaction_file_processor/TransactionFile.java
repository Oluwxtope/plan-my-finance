package com.bptn.planmyfinance.transaction_file_processor;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transactions.Transactions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TransactionFile {
    public static List<Transaction> loadTransactions(String transactionFilePath) {
        List<Transaction> listOfTransactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionFilePath))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayOfLineData = line.split(", "); // [name, type, amount, date]
                Transaction transaction = new Transaction(arrayOfLineData[0], arrayOfLineData[1], Double.parseDouble(arrayOfLineData[2]), arrayOfLineData[3]);
                listOfTransactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Error loading transaction. Please try again!");
            System.out.println(e.getMessage());
        }
        return listOfTransactions;
    }

    public static boolean writeTransactions(Transactions transactions, String generatedFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(generatedFilePath))) {
            writer.write("Name, Type, Amount($), Date(dd-mm-yyyy)\n");
            writer.write(transactions.toString());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
