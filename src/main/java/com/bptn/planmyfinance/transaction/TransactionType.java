package com.bptn.planmyfinance.transaction;

public enum TransactionType {
    DEBIT("debit"),
    CREDIT("credit");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}