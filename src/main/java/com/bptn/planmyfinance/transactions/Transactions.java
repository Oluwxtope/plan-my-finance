package com.bptn.planmyfinance.transactions;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transaction_file_processor.TransactionFileLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Transactions {
    private List<Transaction> transactions;

    public Transactions(String transactionFileName) {
        this.transactions = TransactionFileLoader.loadTransactionFile(transactionFileName);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public boolean addTransaction(Transaction transaction) {
        return transactions.add(transaction);
    }

    @Override
    public String toString() {
        return transactions.stream().map(Transaction::toString).collect(Collectors.joining("\n"));
    }
}
