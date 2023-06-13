package com.alexey.familyhomeeconomis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.alexey.familyhomeeconomis.Transaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthActivity extends AppCompatActivity {
    private static final String jsonFileName = "transactions.json";
    private int selectedMonth;
    private List<Transaction> transactions = new ArrayList<>();
    private TableLayout mTableLayout;
    private final float[] monthlyIncome = new float[12];
    private final float[] monthlyExpense = new float[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        Intent intent = getIntent();
        selectedMonth = intent.getIntExtra("selected_month", -1);

        mTableLayout = findViewById(R.id.tableLayout);

        loadTransactions();
        calculateMonthlyIncomeAndExpense();
        addTableRows();
    }

    private void loadTransactions() {
        try {
            FileInputStream fis = openFileInput(jsonFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Transaction>>() {}.getType();
            transactions = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateMonthlyIncomeAndExpense() {
        for (Transaction transaction : transactions) {
            int month = Integer.parseInt(transaction.getDate().split("\\.")[1]) - 1;  // months are 0-based in arrays
            monthlyIncome[month] += transaction.getIncome();
            monthlyExpense[month] += transaction.getExpense();
        }
    }

    private void addTableRows() {
        for (int i = 0; i < 12; i++) {
            TableRow row = new TableRow(this);

            TextView monthView = new TextView(this);
            monthView.setText(getMonthName(i));

            TextView incomeView = new TextView(this);
            incomeView.setText(String.valueOf(monthlyIncome[i]));

            TextView expenseView = new TextView(this);
            expenseView.setText(String.valueOf(monthlyExpense[i]));

            row.addView(monthView);
            row.addView(incomeView);
            row.addView(expenseView);

            if (i == selectedMonth) {
                row.setBackgroundColor(Color.argb(77, 0, 0, 0)); // Set semi-transparent black background
            }

            mTableLayout.addView(row);
        }
    }

    private String getMonthName(int month) {
        // TODO: Implement this method to return the name of the month for the given 0-based month number
        return "Month " + (month + 1);
    }
}
