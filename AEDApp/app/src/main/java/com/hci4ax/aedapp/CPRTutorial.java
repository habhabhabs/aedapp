package com.hci4ax.aedapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class CPRTutorial extends AppCompatActivity {

    // enable the vibration in this activity
    Vibrator vibrator;
    Handler handler;
    TextView instPrompter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprtutorial);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        handler = new Handler(getApplicationContext().getMainLooper());
        instPrompter = (TextView) findViewById(R.id.instruction_prompter);

        CPRTutorial1();

//        final ImageView shirtOn = (ImageView) findViewById(R.id.shirtOn);
//        final Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
//        animation.setDuration(1500); // duration - half a second
//        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
//        shirtOn.setOnTouchListener(new View.OnTouchListener() { // take off shirt
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final MediaPlayer takeoffsinglet = MediaPlayer.create(CPRTutorial.this, R.raw.sound_takeoffsinglet);
//                shirtOn.startAnimation(animation);
//                vibrator.vibrate(500);
//                shirtOn.setVisibility(View.GONE);
//                return false;
//            }
//        });
    }

    Runnable t1speech1, t1speech2;
    private void CPRTutorial1() {
        t1speech1 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech1audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech1_hiimannie);
                speech1audio.start();
                instPrompter.setText("Hi, I'm Annie.");
                speech1audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech1audio.release();
                        final MediaPlayer speech2audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech2_welcometothistutorial);
                        speech2audio.start();
                        instPrompter.setText("Welcome to this tutorial.");
                        speech2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech2audio.release();
                                final MediaPlayer speech3audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech3_inthistutorial);
                                speech3audio.start();
                                instPrompter.setText("In this tutorial");
                                speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        speech3audio.release();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        t1speech2 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech17audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech17_imgoingtoshowyouhowtoconductcoprresuscitation);
                speech17audio.start();
                instPrompter.setText("I'm going to show you how to conduct CPR resuscitation.");
                speech17audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        final MediaPlayer collapseSound = MediaPlayer.create(CPRTutorial.this, R.raw.sound_bodycollapse);
                        collapseSound.start();
                        instPrompter.setText("(someone faints)");
                    }
                });
            }
        };

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t1speech1, 0);
        handler.postDelayed(t1speech2, 4800);
    }
}
