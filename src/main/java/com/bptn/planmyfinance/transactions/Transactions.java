package com.bptn.planmyfinance.transactions;

import com.bptn.planmyfinance.transaction.Transaction;
import com.bptn.planmyfinance.transaction_file_processor.TransactionFile;

import java.time.LocalDate;
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
        return sortTransactionsByDate(transactions, "des");
    }

    public boolean addTransaction(Transaction transaction, String transactionFileName) {
        transactions.add(transaction);
        setTransactions(this);
        return TransactionFile.writeTransactions(this, transactionFileName);
    }

    private void setTransactions(Transactions transactions) {
        this.transactions = sortTransactionsByDate(transactions.getTransactions(), "des");
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
        return transactionType.isEmpty() ? this.getTransactions() : transactions.stream().filter(transaction -> transaction.getType().equals(transactionType) ).toList();
    }

    public List<Transaction> filterTransactionsByDate(String startDateString, String endDateString) {
        LocalDate startDate = Transaction.convertStringToDateDDMMYYYY(startDateString);
        LocalDate endDate = Transaction.convertStringToDateDDMMYYYY(endDateString);
        DateChecker dateIsWithinRange = date -> date.isBefore(endDate) && date.isAfter(startDate);
        return transactions.stream().filter(transaction -> dateIsWithinRange.check(transaction.getDate())).toList();
    }

    public List<Transaction> filterTransactionsByAmount(double startAmount, double endAmount) {
        return transactions.stream().filter(transaction -> transaction.getAmount() >= startAmount && transaction.getAmount() <= endAmount).toList();
    }

    @Override
    public String toString() {
        return sortTransactionsByDate(transactions, "des").stream().map(Transaction::toString).collect(Collectors.joining("\n"));
    }

    private List<Transaction> sortTransactionsByDate(List<Transaction> listOfTransaction, String sortOrder) {
        List<Transaction> copyOfTransactionsToSort = new ArrayList<>(listOfTransaction);
        if (sortOrder.equals("asc")) {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getDate));
        } else {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getDate).reversed());
        }
        return copyOfTransactionsToSort;
    }

    private List<Transaction> sortTransactionsByName(String sortOrder) {
        List<Transaction> copyOfTransactionsToSort = new ArrayList<>(transactions);
        if (sortOrder.equals("asc")) {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getName));
        } else {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getName).reversed());
        }
        return copyOfTransactionsToSort;
    }

    private List<Transaction> sortTransactionsByAmount(String sortOrder) {
        List<Transaction> copyOfTransactionsToSort = new ArrayList<>(transactions);
        if (sortOrder.equals("asc")) {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getAmount));
        } else {
            copyOfTransactionsToSort.sort(Comparator.comparing(Transaction::getAmount).reversed());
        }
        return copyOfTransactionsToSort;
    }

    public void viewTransactionsByTypeAndOrder(String transactionType, String sortOrder) {
        System.out.println("Name, Type, Amount ($), Date (dd-mm-yyyy)");
        List<Transaction> filteredTransactionsSorted = sortTransactionsByDate(filterTransactionsByType(transactionType), sortOrder);
        filteredTransactionsSorted.forEach(System.out::println);
    }
}
