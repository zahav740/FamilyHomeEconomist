package com.alexey.familyhomeeconomis;

import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class SaturdayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final int saturdayColor;

    public SaturdayDecorator(int saturdayColor) {
        this.saturdayColor = saturdayColor;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        calendar.setTime(day.getDate());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // Суббота в java.util.Calendar - это 7
        return dayOfWeek == 7;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(saturdayColor));
    }
}

