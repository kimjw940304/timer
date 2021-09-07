package com.example.timer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;
import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;


public class MainActivity extends AppCompatActivity {

    private TextView countdownText; //타이머 현황
    private SwipeButton cancelButton; //I'm Ok 슬라이드 버튼
    private CountDownTimer countDownTimer;

    private long time = 0;
    private  long tempTime = 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = findViewById(R.id.countdown_text);
        cancelButton = (SwipeButton) findViewById(R.id.cancel_btn);

        /* 알람 울리면 바로 타이머 스타트 */
        startTimer();


        /*I'm Ok 슬라이드 버튼 */
        cancelButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Toast.makeText(MainActivity.this,"I'm fine Thank you",Toast.LENGTH_SHORT).show();
                stopTimer();
            }
        });

        cancelButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                Toast.makeText(MainActivity.this,"I'm fine Thank you",Toast.LENGTH_SHORT).show();
            }
        });

    }


    /* 타이머 메소드 */
    private void startTimer() {

        String sMin = "00";   //분
        String sSecond = "30";  //초
        time = (Long.parseLong(sMin)*60000)+(Long.parseLong(sSecond)*1000);

        countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tempTime = millisUntilFinished;
                updateTimer();
            }

            /*타이머 시간 완료시 */
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Send SOS Message !!",Toast.LENGTH_SHORT).show();

                /*
                * ★ 여기에 카카오톡 메시지 추가하는 액티비티로 넘어가는 코드 추가 ★
                * */

            }
        }.start();
    }

    /* 타이머 정지(I'm Ok 슬라이드 동작시) */
    private void stopTimer() {
        countDownTimer.cancel();
        Toast.makeText(MainActivity.this,"I'm fine Thank you",Toast.LENGTH_SHORT).show();
    }

    /* 시간 업데이트 view */
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