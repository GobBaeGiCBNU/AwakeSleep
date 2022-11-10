package com.example.alarmforsleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;


public class Survey_Activity extends AppCompatActivity {

    private RadioGroup radioGroup1, radioGroup2, radioGroup2_1, radioGroup3;
    private int q1, q2, q2_1=0, q3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        //질문 1 설문조사 값 설정
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch (checkedID) {
                    case R.id.q1Btn1:
                        q1 = 1;
                        break;
                    case R.id.q1Btn2:
                        q1 = 2;
                        break;
                    case R.id.q1Btn3:
                        q1 = 3;
                }
            }
        });

        //질문 2 설문조사 값 설정
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch (checkedID) {
                    case R.id.q2Btn1:
                        q2 = 1;
                        break;
                    case R.id.q2Btn2:
                        q2 = 2;
                        break;
                    case R.id.q2Btn3:
                        q2 = 3;
                }
            }
        });

        //질문 2-1 설문조사 값 설정
        radioGroup2_1 = (RadioGroup) findViewById(R.id.radioGroup2_1);
        radioGroup2_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch (checkedID) {
                    case R.id.q2_1Btn1:
                        q2_1 = 1;
                        break;
                    case R.id.q2_1Btn2:
                        q2_1 = 2;
                        break;
                }
            }
        });

        //질문 3 설문조사 값 설정
        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch (checkedID) {
                    case R.id.q3Btn1:
                        q3 = 1;
                        break;
                    case R.id.q3Btn2:
                        q3 = 2;
                        break;
                    case R.id.q3Btn3:
                        q3 = 3;
                }
            }
        });

        Button sleep_evaluation = (Button) findViewById(R.id.submit);
        sleep_evaluation.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Sleep_Calender_Activity.class);
                startActivity(intent);
            }
        });

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
