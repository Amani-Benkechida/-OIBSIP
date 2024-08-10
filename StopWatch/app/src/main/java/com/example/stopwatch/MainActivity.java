package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnStop, btnHold;

    private Handler handler = new Handler();
    private long startTime, timeInMilliseconds = 0L;
    private boolean isRunning = false;
    private boolean isHold = false;

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            if (isRunning && !isHold) {
                timeInMilliseconds = System.currentTimeMillis() - startTime;
                int secs = (int) (timeInMilliseconds / 1000);
                int mins = secs / 60;
                int hours = mins / 60;
                secs = secs % 60;
                mins = mins % 60;
                tvTimer.setText(String.format("%02d:%02d:%02d", hours, mins, secs));
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnHold = findViewById(R.id.btnHold);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTime = System.currentTimeMillis() - timeInMilliseconds;
                    handler.postDelayed(updateTimerThread, 0);
                    isRunning = true;
                    isHold = false;
                } else {
                    isHold = false;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    isRunning = false;
                    isHold = false;
                    handler.removeCallbacks(updateTimerThread);
                    timeInMilliseconds = 0;
                    tvTimer.setText("00:00:00");
                }
            }
        });

        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    if (isHold) {
                        isHold = false;
                        startTime = System.currentTimeMillis() - timeInMilliseconds;
                        handler.postDelayed(updateTimerThread, 0);
                    } else {
                        isHold = true;
                        handler.removeCallbacks(updateTimerThread);
                    }
                }
            }
        });
    }
}

