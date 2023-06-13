package com.alexey.familyhomeeconomis;

public class MainActivity1 {
    /*
    package com.alexey.familyhomeeconomis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TRANSACTIONS_KEY = "transactions";
    private static final String BALANCE_KEY = "balance";

    private final String JSON_FILE_NAME = "transactions.json";

    private Button mBalanceButton;
    private Button mExitButton;
    private TableLayout mTableLayout;
    private DrawerLayout drawer;
    private float balance;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assuming you have a NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_year) {
                    // Open YearActivity when "Year" is clicked
                    Intent intent = new Intent(MainActivity.this, YearActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_month) {
                    // Open MonthActivity when "Month" is clicked
                    Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_day) {
                    // Open DayActivity when "Day" is clicked
                    Intent intent = new Intent(MainActivity.this, DayActivity.class);
                    startActivity(intent);
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void showTransactionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_transaction, null);
        builder.setView(customLayout);

        final EditText incomeInput = customLayout.findViewById(R.id.incomeInput);
        final EditText expenseInput = customLayout.findViewById(R.id.expenseInput);
        final EditText categoryInput = customLayout.findViewById(R.id.categoryInput);

        final List<Transaction> tempTransactions = new ArrayList<>();

        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Завершить", null);

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button okButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String income = incomeInput.getText().toString();
                        String expense = expenseInput.getText().toString();
                        String category = categoryInput.getText().toString();
                        String date = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(new Date());

                        float incomeValue = income.isEmpty() ? 0.0f : Float.parseFloat(income);
                        float expenseValue = expense.isEmpty() ? 0.0f : Float.parseFloat(expense);
                        balance += incomeValue - expenseValue;

                        Transaction transaction = new Transaction(date, category, incomeValue, expenseValue);

                        tempTransactions.add(transaction);

                        incomeInput.setText("");
                        expenseInput.setText("");
                        categoryInput.setText("");
                    }
                });

                Button exitButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                exitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (Transaction transaction : tempTransactions) {
                            addRowToTable(transaction.getDate(), String.valueOf(transaction.getIncome()), transaction.getCategory(), String.valueOf(transaction.getExpense()));
                            transactions.add(transaction);
                        }
                        saveTransactions();
                        updateBalanceButton();
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();
    }


    private void addTableHeader() {
        TableRow headerRow = new TableRow(this);

        TextView dateHeader = new TextView(this);
        dateHeader.setText("Дата");

        TextView incomeHeader = new TextView(this);
        incomeHeader.setText("Доход");

        TextView categoryHeader = new TextView(this);
        categoryHeader.setText("Статья");

        TextView expenseHeader = new TextView(this);
        expenseHeader.setText("Расход");

        headerRow.addView(dateHeader);
        headerRow.addView(incomeHeader);
        headerRow.addView(categoryHeader);
        headerRow.addView(expenseHeader);

        mTableLayout.addView(headerRow);
    }

    private void addRowToTable(String date, String income, String category, String expense) {
        TableRow row = new TableRow(this);

        TextView dateView = new TextView(this);
        TextView incomeView = new TextView(this);
        TextView categoryView = new TextView(this);
        TextView expenseView = new TextView(this);

        dateView.setText(date);
        incomeView.setText(income);
        categoryView.setText(category);
        expenseView.setText(expense);

        row.addView(dateView);
        row.addView(incomeView);
        row.addView(categoryView);
        row.addView(expenseView);

        mTableLayout.addView(row);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_year) {
            // TODO: open year activity
        } else if (id == R.id.nav_month) {
            // TODO: open month activity
        } else if (id == R.id.nav_day) {
            // TODO: open day activity
        } else if (id == R.id.nav_download) {
            // TODO: open download activity
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveTransactions() {
        Gson gson = new Gson();
        String json = gson.toJson(transactions);

        File file = new File(getFilesDir(), JSON_FILE_NAME);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTransactions() {
        try {
            FileInputStream fis = openFileInput(JSON_FILE_NAME);
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

            if (transactions == null) {
                transactions = new ArrayList<>();
            } else {
                for (Transaction transaction : transactions) {
                    double incomeValue = transaction.getIncome();
                    double expenseValue = transaction.getExpense();
                    balance += incomeValue - expenseValue;

                    addRowToTable(transaction.getDate(), String.valueOf(incomeValue), transaction.getCategory(), String.valueOf(expenseValue));
                }
            }

            updateBalanceButton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBalanceButton() {
        String balanceText = String.format(Locale.getDefault(), "Баланс: %.2f", balance);
        mBalanceButton.setText(balanceText);
    }
}

     */
}
