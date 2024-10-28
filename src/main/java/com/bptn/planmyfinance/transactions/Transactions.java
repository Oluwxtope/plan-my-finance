package com.bptn.planmyfinance.transactions;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transaction_file_processor.TransactionFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Transactions {
    private List<Transaction> transactions;

    public Transactions(String transactionFileName) {
        this.transactions = TransactionFile.loadTransactions(transactionFileName);
    }

    public List<Transaction> getTransactions() {
        return sortTransactions(transactions, "des");
    }

    private void setTransactions(Transactions transactions) {
        this.transactions = sortTransactions(transactions.getTransactions(), "des");
    }

    public boolean addTransaction(Transaction transaction, String transactionFileName) {
        transactions.add(transaction);
        setTransactions(this);
        return TransactionFile.writeTransactions(this, transactionFileName);
    }

    public void setTransaction(int transactionIndex, Transaction newTransaction, String transactionFileName) {
        Transaction oldTransaction = transactions.get(transactionIndex);
        oldTransaction.setAmount(newTransaction.getAmount());
        oldTransaction.setDate(newTransaction.getDate());
        oldTransaction.setName(newTransaction.getName());
        oldTransaction.setType(newTransaction.getType());
        TransactionFile.writeTransactions(this, transactionFileName);
    }

    public List<Transaction> filterTransactionsByType(String transactionType) {
        return transactionType.isEmpty() ? this.getTransactions() : transactions.stream().filter(transaction -> transaction.getType().equals(transactionType)).toList();
    }

    @Override
    public String toString() {
        return sortTransactions(transactions, "des").stream().map(Transaction::toString).collect(Collectors.joining("\n"));
    }

    private List<Transaction> sortTransactions(List<Transaction> listOfTransaction, String sortOrder) {
        List<Transaction> copyOfTransactionsToSort = new ArrayList<>(listOfTransaction);
        Comparator<Transaction> comparator = Comparator
                .comparing(Transaction::getDate)
                .thenComparing(Transaction::getAmount)
                .thenComparing(Transaction::getName);
        if (sortOrder.equals("des")) {
            comparator = comparator.reversed();
        }
        copyOfTransactionsToSort.sort(comparator);
        return copyOfTransactionsToSort;
    }

    public void printTransactionsByTypeAndOrder(String transactionType, String sortOrder) {
        System.out.println("Name, Type, Amount ($), Date (dd-mm-yyyy)");
        List<Transaction> filteredTransactionsSorted = sortTransactions(filterTransactionsByType(transactionType), sortOrder);
        filteredTransactionsSorted.forEach(System.out::println);
    }

    public double sumTransactions() {
        return transactions.stream()
                .mapToDouble(transaction -> transaction.getType().equals("credit") ? transaction.getAmount() : -transaction.getAmount())
                .sum();
    }
}
