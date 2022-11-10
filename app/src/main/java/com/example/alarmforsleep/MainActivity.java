package com.example.alarmforsleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //위젯 전역 변수
    TimePicker wake_time;
    TextView select_time;

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
                //오전, 오후를 확인하기 위한 if 문 (hour-1에서 예외사항은 나중에 조건문 수정 필요)
                if (hour > 12) {
                    hour -= 12;
                    select_time.setText("기상시간: 오후 " + (hour-1) + "시 " + minute + "분 ~ 오후" + hour + "시 " + minute + "분");
                }
                else {
                    select_time.setText("기상시간: 오전 " + (hour-1) + "시 " + minute + "분 ~ 오전" + hour + "시 " + minute + "분");
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
                Intent intent = new Intent(getApplicationContext(), Sleep_Start_Activity.class);
                startActivity(intent);
            }
        });
    }
}