package com.alexey.familyhomeeconomis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {
    private List<com.alexey.familyhomeeconomis.Month> months = new ArrayList<>();

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month, parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {
        com.alexey.familyhomeeconomis.Month month = months.get(position);
        holder.bind(month);
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public void setMonths(List<com.alexey.familyhomeeconomis.Month> months) {
        this.months = months;
        notifyDataSetChanged();
    }

    class MonthViewHolder extends RecyclerView.ViewHolder {
        TextView monthNameTextView;
        TextView incomeTextView;
        TextView expenseTextView;

        public MonthViewHolder(@NonNull View itemView) {
            super(itemView);
            monthNameTextView = itemView.findViewById(R.id.monthNameTextView);
            incomeTextView = itemView.findViewById(R.id.incomeTextView);
            expenseTextView = itemView.findViewById(R.id.expenseTextView);
        }

        void bind(com.alexey.familyhomeeconomis.Month month) {
            monthNameTextView.setText(month.getName());
            incomeTextView.setText(String.valueOf(month.getIncome()));
            expenseTextView.setText(String.valueOf(month.getExpense()));
        }
    }
}
/*
111
 */