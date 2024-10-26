package com.bptn.planmyfinance.transaction_file;

import com.bptn.planmyfinance.transaction.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class TransactionFileLoader {

    public static List<Transaction> loadTransactionFile(String transactionFile) {
        List<Transaction> listOfTransactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionFile))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayOfLineData = line.split(","); // [name, type, amount, date]
                Transaction transaction = new Transaction(arrayOfLineData[0], arrayOfLineData[1], parseLong(arrayOfLineData[2]), arrayOfLineData[3]);
                listOfTransactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listOfTransactions;
    }
}
