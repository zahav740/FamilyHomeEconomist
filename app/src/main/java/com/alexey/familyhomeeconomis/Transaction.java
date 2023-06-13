package com.alexey.familyhomeeconomis;

public class Transaction {
    private String date;
    private String category;
    private double income;
    private double expense;

    public Transaction(String date, String category, float income, float expense) {
        this.date = date;
        this.category = category;
        this.income = income;
        this.expense = expense;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }
}


