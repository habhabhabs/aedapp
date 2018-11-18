package com.hci4ax.aedapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retrieve the layout for the main activity
        setContentView(R.layout.activity_main);

        // hide the ugly navbar for phones without hardware buttons
        // calls function: onWindowFocusChanged(boolean hasFocus)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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

        MediaPlayer.create(MainActivity.this, R.raw.speechintro_welcome).start();
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


        // click button to go to next activity (demonstration of aed)
        aedImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent tutorialAct = new Intent(getApplicationContext(), AEDTutorial.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptions anim = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, findViewById(R.id.aedImg), "aedImg");
                startActivity(tutorialAct, anim.toBundle());
                return false;
            }
        });

        introFooter1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent why = new Intent(getApplicationContext(), WhyLearnAED.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptions anim = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(why, anim.toBundle());
                return false;
            }
        });
    }


    // code required to hide the navbar when user interacting with screen
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
