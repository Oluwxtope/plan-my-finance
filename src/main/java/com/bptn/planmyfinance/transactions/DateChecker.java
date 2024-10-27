package com.bptn.planmyfinance.transactions;

import java.time.LocalDate;

@FunctionalInterface
public interface DateChecker {
    boolean check(LocalDate date);
}