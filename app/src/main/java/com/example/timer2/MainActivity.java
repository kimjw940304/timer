package com.example.timer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView countdownText; //타이머 현황

    private Button cancelButton; //취소 버튼


    private CountDownTimer countDownTimer;


    private long time = 0;
    private  long tempTime = 0;


    FrameLayout setting; //세팅화면
    FrameLayout timer; //타이머화면



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = findViewById(R.id.countdown_text);
        cancelButton = findViewById(R.id.cancel_btn); //취소 버튼

        setting = findViewById(R.id.setting);
        timer = findViewById(R.id.timer);

        startTimer();


        /*취소*/
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });


    }
    /* 타이머 기능 */
    private void startTimer() {

        String sMin = "00";   //분
        String sSecond = "10";  //초
        time = (Long.parseLong(sMin)*60000)+(Long.parseLong(sSecond)*1000);

        countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tempTime = millisUntilFinished;
                updateTimer();
            }

            /*타이머 시간 완료시*/
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Time Over",Toast.LENGTH_SHORT).show();

                /*
                * ★ 여기에 카카오톡 메시지 추가하는 액티비티로 넘어가는 코드 추가 ★
                * */

            }

        }.start();
    }

    /* 타이머 정지 */
    private void stopTimer() {
        countDownTimer.cancel();
        Toast.makeText(MainActivity.this,"I'm fine Thank you",Toast.LENGTH_SHORT).show();
    }

    /* 시간 업데이트 */
    private void updateTimer() {
        int minutes = (int) tempTime / 60000;
        int seconds = (int) tempTime % 60000 / 1000 ;

        String timeLeftText = "";

        if (minutes < 10) timeLeftText += "0";
        timeLeftText += minutes +":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }

}