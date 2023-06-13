package com.alexey.familyhomeeconomis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Настройте MaterialCalendarView здесь
    }

    @Override
    public int getItemCount() {
        // Верните общее количество MaterialCalendarView
        return 12;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCalendarView calendarView;

        ViewHolder(View itemView) {
            super(itemView);
            calendarView = itemView.findViewById(R.id.calendarView);
        }
    }
}

