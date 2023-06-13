package com.alexey.familyhomeeconomis;

import java.util.List;

public class TransactionUtils {

    public static float calculateTotalIncome(List<Transaction> transactions) {
        float totalIncome = 0;

        for (Transaction transaction : transactions) {
            totalIncome += transaction.getIncome();
        }

        return totalIncome;
    }

    public static float calculateTotalExpense(List<Transaction> transactions) {
        float totalExpense = 0;

        for (Transaction transaction : transactions) {
            totalExpense += transaction.getExpense();
        }

        return totalExpense;
    }
}
