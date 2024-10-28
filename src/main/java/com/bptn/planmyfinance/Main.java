package com.bptn.planmyfinance;

public class Main {
    public static void main(String[] args) {
        App app = new App("Emmanuel", "transactions.txt");
        app.run();
    }
}