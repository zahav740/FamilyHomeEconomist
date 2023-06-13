package com.alexey.familyhomeeconomis;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class YearActivity extends AppCompatActivity {
    private RecyclerView monthsRecyclerView;
    private MonthAdapter monthAdapter;
    private int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        // Set initial selected year to current year
        selectedYear = Calendar.getInstance().get(Calendar.YEAR);

        Button selectYearButton = findViewById(R.id.selectYearButton);
        selectYearButton.setOnClickListener(v -> showYearPickerDialog());

        monthsRecyclerView = findViewById(R.id.monthsRecyclerView);
        monthAdapter = new MonthAdapter();
        monthsRecyclerView.setAdapter(monthAdapter);

        loadYearData();
    }

    private void showYearPickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            selectedYear = year;
            loadYearData();
        }, selectedYear, 0, 1);
        datePickerDialog.show();
    }

    private void loadYearData() {
        // TODO: Load transactions from DB and calculate income and expenses per month
        // Here we just create dummy data
        List<com.alexey.familyhomeeconomis.Month> months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String name = new DateFormatSymbols().getMonths()[i];
            float income = new Random().nextInt(1000);
            float expense = new Random().nextInt(1000);
            com.alexey.familyhomeeconomis.Month month = new com.alexey.familyhomeeconomis.Month(name, income, expense);
            months.add(month);
        }
        monthAdapter.setMonths(months);
    }

}
