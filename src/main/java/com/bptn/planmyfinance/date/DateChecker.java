package com.bptn.planmyfinance.date;

import java.time.LocalDate;

@FunctionalInterface
public interface DateChecker {
    boolean check(LocalDate date);
}