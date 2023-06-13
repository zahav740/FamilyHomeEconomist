package com.alexey.familyhomeeconomis;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.text.DateFormatSymbols;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;



public class CalendarActivity extends AppCompatActivity {

    // Создаем массив для хранения всех календарей
    private MaterialCalendarView[] calendarViews = new MaterialCalendarView[12];
    private TitleFormatter[] titleFormatters = new TitleFormatter[12];
    private TextView yearTextView;
    private int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        yearTextView = findViewById(R.id.yearTextView);

        // Устанавливаем текущий год в TextView
        selectedYear = Calendar.getInstance().get(Calendar.YEAR);
        yearTextView.setText(String.valueOf(selectedYear));

        yearTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearPickerDialog();
            }
        });

        final DateFormatSymbols symbols = new DateFormatSymbols(new Locale("ru"));
        final String[] weekDays = Arrays.copyOfRange(symbols.getShortWeekdays(), 1, 8);

        for (int i = 0; i < 12; i++) {
            final int monthIndex = i; // Финальная переменная для использования внутри анонимного класса
            titleFormatters[i] = new TitleFormatter() {
                @Override
                public CharSequence format(CalendarDay day) {
                    return new DateFormatSymbols(new Locale("ru")).getMonths()[monthIndex];
                }
            };

            int id = getResources().getIdentifier("calendarView" + (i + 1), "id", getPackageName());
            calendarViews[i] = findViewById(id);
            calendarViews[i].setTitleFormatter(titleFormatters[i]);
            calendarViews[i].setWeekDayFormatter(new ArrayWeekDayFormatter(weekDays));
        }
        // Обновляем все календари, чтобы отображать текущий год
        updateCalendars(selectedYear);
    }
    private void updateCalendars(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        for (int i = 0; i < 12; i++) {
            // Устанавливаем месяц и день месяца для календаря
            calendar.set(Calendar.MONTH, i);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            CalendarDay firstDayOfMonth = CalendarDay.from(calendar);

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            CalendarDay lastDayOfMonth = CalendarDay.from(calendar);

            // Если представление календаря существует, обновляем его состояние
            if (calendarViews[i] != null) {
                calendarViews[i].state().edit()
                        .setMinimumDate(firstDayOfMonth)
                        .setMaximumDate(lastDayOfMonth)
                        .commit();

                // Устанавливаем режим выбора на NONE
                calendarViews[i].setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

                // Устанавливаем форматтер для заголовка календаря
                calendarViews[i].setTitleFormatter(titleFormatters[i]);
            }
        }
    }

    private void showYearPickerDialog() {
        // Настройка NumberPicker
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(selectedYear - 6);
        numberPicker.setMaxValue(selectedYear + 6);
        numberPicker.setValue(selectedYear);

        // Создание AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите год");
        builder.setView(numberPicker);

        // Установка кнопок
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedYear = numberPicker.getValue();
                yearTextView.setText(String.valueOf(selectedYear));
                updateCalendars(selectedYear);
            }
        });
        builder.setNegativeButton("Отмена", null);

        // Показать диалог
        builder.show();
    }
}

