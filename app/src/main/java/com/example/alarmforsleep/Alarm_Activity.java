package com.example.alarmforsleep;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Alarm_Activity  extends AppCompatActivity {

    Calendar calendar;
    Button Button;
    //TextView timeText;
    MediaPlayer mediaPlayer;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        calendar = Calendar.getInstance();
        Button = (Button) findViewById(R.id.sleep_end);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        // 잠금 화면 위로 activity 띄워줌

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.beep);   // 소리를 재생할 MediaPlayer
        mediaPlayer.setLooping(true);   // 무한반복
        mediaPlayer.start();

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                flag=false;
                finish();

                Intent intent = new Intent(getApplicationContext(), Sleep_Pattern_Activity.class);
                startActivity(intent);
            }
        }); // button 눌러서 해제
    }
}
