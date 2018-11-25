package com.hci4ax.aedapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // enable the vibration in this activity
    Vibrator vibrator;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retrieve the layout for the main activity
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        handler = new Handler(getApplicationContext().getMainLooper());
        handler.removeCallbacksAndMessages(null);

        // fade in the words to start the aed tutorial
        AnimationSet slowFade = new AnimationSet(true);
        final Animation slowIn = new AlphaAnimation(0.0f, 3.0f);
        slowIn.setDuration(4500);
        slowIn.setStartOffset(2000);
        slowFade.addAnimation(slowIn);

        AnimationSet slowFaded = new AnimationSet(true);
        final Animation slowOut = new AlphaAnimation(0.0f, 3.0f);
        slowOut.setDuration(3000);
        slowOut.setStartOffset(2000);
        slowFaded.addAnimation(slowOut);

        Animation blinking = new AlphaAnimation(1.0f, 0.0f); // Change alpha from fully visible to invisible
        blinking.setDuration(1000); // duration - half a second
        blinking.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinking.setRepeatCount(10);
        blinking.setStartOffset(100);
        blinking.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        AnimationSet fastFade = new AnimationSet(true);
        final Animation fastIn = new AlphaAnimation(0.0f, 1.0f);
        fastIn.setDuration(1500);
        fastFade.addAnimation(fastIn);

        final MediaPlayer intro = MediaPlayer.create(MainActivity.this, R.raw.speechintro_welcome);
        intro.start();
        // objects to animate
        final ImageView headerImage = (ImageView) findViewById(R.id.headerImage);
        headerImage.startAnimation(fastFade); // entry fade - fast
        final TextView introHeader1 = (TextView) findViewById(R.id.introHeader1);
        introHeader1.startAnimation(fastFade); // entry fade - fast
        final TextView introHeader2 = (TextView) findViewById(R.id.introHeader2);
        introHeader2.startAnimation(fastFade); // entry fade - fast
        final TextView introFooter1 = (TextView) findViewById(R.id.introFooter1);
        introFooter1.startAnimation(fastFade); // entry fade - fast
        final TextView introFooter2 = (TextView) findViewById(R.id.introFooter2);
        introFooter2.startAnimation(fastFade); // entry fade - fast
        final ImageView aedImg = (ImageView) findViewById(R.id.aedImg);
        aedImg.startAnimation(slowFaded); // entry fade - slow delayed

        final TextView credits1 = (TextView) findViewById(R.id.credits1);
        final TextView credits2 = (TextView) findViewById(R.id.credits2);
        credits1.startAnimation(slowFade); // entry fade - slow
        credits2.startAnimation(slowFade); // entry fade - slow

        final TextView startGameText = (TextView) findViewById(R.id.startGameText);
        startGameText.startAnimation(slowFade); // entry fade - slow
        aedImg.startAnimation(blinking);

        // finish playing intro
        intro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                intro.release();
                // click button to go to next activity (demonstration of aed)
                aedImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tutorialAct = new Intent(getApplicationContext(), AEDTutorial.class);
                        // create the transition animation - the images in the layouts
                        // of both activities are defined with android:transitionName="robot"
                        ActivityOptions anim = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, findViewById(R.id.aedImg), "aedImg");
                        vibrator.vibrate(100);
                        startActivity(tutorialAct, anim.toBundle());
                    }
                });

                introFooter1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent why = new Intent(getApplicationContext(), CPRTutorial.class);
                        // create the transition animation - the images in the layouts
                        // of both activities are defined with android:transitionName="robot"
                        ActivityOptions anim = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                        vibrator.vibrate(100);
                        startActivity(why, anim.toBundle());
                    }
                });
            }
        });
    }
}
