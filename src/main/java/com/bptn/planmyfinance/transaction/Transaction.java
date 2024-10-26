package com.bptn.planmyfinance.transaction;

import com.bptn.planmyfinance.exceptions.IllegalAmountException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Transaction {
    private String name;
    private TransactionType type;
    private double amount;
    private LocalDate date;

    public Transaction(String name, String type, double amount, String date) {
        this.name = name;
        this.type = parseTransactionType(type);
        this.amount = validateAmount(amount);
        this.date = convertStringToDate(date);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = convertStringToDate(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = validateAmount(amount);
    }

    public void setType(String type) {
        this.type = parseTransactionType(type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.getType();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getName() + "," + getType() + "," + getAmount() + "," + getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private LocalDate convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format!");
        }
    }

    private double validateAmount(double amount) {
        if (amount > 0) {
            return new BigDecimal(amount).round(new MathContext(2)).doubleValue();
        } else {
            throw new IllegalAmountException("Amount has to be greater than 0!");
        }
    }

    private TransactionType parseTransactionType(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Transaction type has to be 'Debit' or 'Credit'!");
        }
    }
}