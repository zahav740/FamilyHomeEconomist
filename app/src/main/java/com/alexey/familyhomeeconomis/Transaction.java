package com.alexey.familyhomeeconomis;

public class Transaction {
    private String date;
    private String category;
    private float income;
    private float expense;

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

    public float getIncome() {
        return income;
    }

    public float getExpense() {
        return expense;
    }
}


