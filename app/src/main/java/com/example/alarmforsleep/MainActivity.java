package com.example.alarmforsleep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //위젯 전역 변수
    TimePicker wake_time;
    TextView select_time;

    int alarmHour, alarmMinute;
    Calendar alarmCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml 연결
        wake_time = findViewById(R.id.wake_time);
        select_time = findViewById(R.id.select_time);

        //TimePicker 클릭 이벤트
        wake_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {

                // 알람 시간 변수 저장
                alarmHour = hour;
                alarmMinute = minute;

                //오전, 오후를 확인하기 위한 if 문 (hour-1에서 예외사항은 나중에 조건문 수정 필요)
                if (hour >= 12) {
                    if (hour == 12)
                        select_time.setText("기상시간: 오전 " + (hour-1) + "시 " + minute + "분 ~ 오후 " + hour + "시 " + minute + "분");
                    else{
                        hour -= 12;
                        select_time.setText("기상시간: 오후 " + (hour-1) + "시 " + minute + "분 ~ 오후 " + hour + "시 " + minute + "분");
                    }
                }
                else {
                    if (hour == 0)
                        select_time.setText("기상시간: 오후 11시 " + minute + "분 ~ 오전 " + hour + "시 " + minute + "분");
                    else
                        select_time.setText("기상시간: 오전 " + (hour-1) + "시 " + minute + "분 ~ 오전 " + hour + "시 " + minute + "분");
                }
            }
        });

        //수면 Calender 클릭 시 액티비티 전환
        Button sleep_calender = (Button) findViewById(R.id.sleep_calender);
        sleep_calender.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Sleep_Calender_Activity.class);
                startActivity(intent);
            }
        });


        //수면 시작 클릭 시 액티비티 전환
        Button sleep_start = (Button) findViewById(R.id.sleep_start);
        sleep_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // 알람 세팅 함수 실행
                setAlarm();

                // 화면 전환
                Intent intent = new Intent(getApplicationContext(), Sleep_Start_Activity.class);
                startActivity(intent);


            }
        });

        //기기 확인 클릭 시 액티비티 전환
        Button sleep_bluetooth = (Button) findViewById(R.id.sleep_bluetooth);
        sleep_bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            }
        });

    }

    // 알람 세팅
    void setAlarm() {

        // 알람 시간 저장
        alarmCalendar = Calendar.getInstance();
        alarmCalendar.setTimeInMillis(System.currentTimeMillis());
        alarmCalendar.set(Calendar.HOUR_OF_DAY, alarmHour);
        alarmCalendar.set(Calendar.MINUTE, alarmMinute);
        alarmCalendar.set(Calendar.SECOND, 0);
        // TimePickerDialog 에서 설정한 시간을 알람 시간으로 설정

        if (alarmCalendar.before(Calendar.getInstance())) alarmCalendar.add(Calendar.DATE, 1);
        // 알람 시간이 현재시간보다 빠를 때 하루 뒤로 맞춤
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent.setAction(AlarmReceiver.ACTION_RESTART_SERVICE);
        PendingIntent alarmCallPendingIntent
                = PendingIntent.getBroadcast
                (MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle
                    (AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), alarmCallPendingIntent);
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact
                    (AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), alarmCallPendingIntent);
    } // 알람 설정
}