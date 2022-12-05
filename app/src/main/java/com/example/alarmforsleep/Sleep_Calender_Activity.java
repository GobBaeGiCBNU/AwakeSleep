package com.example.alarmforsleep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Collections;


public class Sleep_Calender_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_calender);
        
        // 오늘 색상 표시
        MaterialCalendarView materialCalendarView = findViewById(R.id.calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        
        // 해당 날짜에 색상 점 표시
        MaterialCalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setSelectedDate(CalendarDay.today());

        calendarView.addDecorator(new EventDecorator(android.R.color.holo_red_dark, Collections.singleton(CalendarDay.today())));
        
        // 토요일, 일요일 색상 변경
        MaterialCalendarView materialCalendarView1;

        materialCalendarView1 = findViewById(R.id.calendar);

        materialCalendarView1.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
                );

        // 오늘 날짜 표시
        OneDayDecorator oneDayDecorator = new OneDayDecorator();

        materialCalendarView.addDecorators(
                oneDayDecorator
        );

        Button back_home = (Button) findViewById(R.id.back_home);
        back_home.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
