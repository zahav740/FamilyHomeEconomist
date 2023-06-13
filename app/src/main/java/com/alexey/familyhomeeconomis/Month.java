package com.alexey.familyhomeeconomis;

import java.util.List;

public class Month {
    private String name;
    private float income;
    private float expense;

    public Month(String name, float income, float expense) {
        this.name = name;
        this.income = income;
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }
}
