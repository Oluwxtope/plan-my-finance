package com.bptn.planmyfinance.transaction;

import com.bptn.planmyfinance.date.DateConversion;
import com.bptn.planmyfinance.transaction.exceptions.IllegalAmountException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String name;
    private TransactionType type;
    private double amount;
    private LocalDate date;

    public Transaction(String name, String type, double amount, String date) {
        this.name = name;
        this.type = parseTransactionType(type);
        this.amount = validateAmount(amount);
        this.date = DateConversion.convertStringToDateDDMMYYYY(date);
    }

    public static TransactionType parseTransactionType(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Transaction type has to be 'debit' or 'credit'!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type.getType();
    }

    public void setType(String type) {
        this.type = parseTransactionType(type);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = validateAmount(amount);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getName() + ", " + getType() + ", " + getAmount() + ", " + getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private double validateAmount(double amount) {
        if (amount > 0) {
            return Math.round(amount * 100.0) / 100.0;
        } else {
            throw new IllegalAmountException("Amount has to be greater than 0!");
        }
    }

}