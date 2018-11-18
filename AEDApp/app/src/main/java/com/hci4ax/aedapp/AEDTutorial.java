package com.hci4ax.aedapp;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class AEDTutorial extends AppCompatActivity {
    private static CopyOnWriteArrayList<Thread> runningThreads;
    TextView instPrompter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aedtutorial);

        instPrompter = (TextView) findViewById(R.id.prompter);
        runningThreads = new CopyOnWriteArrayList<Thread>();

        // hide the ugly navbar for phones without hardware buttons
        // calls function: onWindowFocusChanged(boolean hasFocus)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // add code below
        // define draggables
        ImageView lpadDraggable = (ImageView) findViewById(R.id.lpadDraggableImg);
        ImageView rpadDraggable = (ImageView) findViewById(R.id.rpadDraggableImg);
        LinearLayout lpadDrop = (LinearLayout) findViewById(R.id.lpadDrop);
        LinearLayout rpadDrop = (LinearLayout) findViewById(R.id.rpadDrop);


        try {
            aedTutorial(lpadDraggable, rpadDraggable, lpadDrop, rpadDrop);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // end onCreate
    }

    private void aedTutorial(final ImageView lpadDraggable, final ImageView rpadDraggable, final LinearLayout lpadDrop, final LinearLayout rpadDrop) throws InterruptedException {
        // start aedTutorial
        Thread speeches;
        final Runnable speech1 = new Runnable() { // TODO: delay 2000ms before moving on
            @Override
            public void run() {
                MediaPlayer.create(AEDTutorial.this, R.raw.speech1_hiimannie).start();
                instPrompter.setText("Hi, I'm Annie.");
            }
        };
        final Runnable speech2 = new Runnable() { // TODO: delay 2000ms before moving on
            @Override
            public void run() {
                MediaPlayer.create(AEDTutorial.this, R.raw.speech2_welcometothistutorial).start();
                instPrompter.setText("Welcome to this tutorial.");
            }
        };
        final Runnable speech3 = new Runnable() { // TODO: delay 1500ms before moving on
            @Override
            public void run() {
                MediaPlayer.create(AEDTutorial.this, R.raw.speech3_inthistutorial).start();
                instPrompter.setText("In this tutorial");
            }
        };
        final Runnable speech4 = new Runnable() { // TODO: delay 4000ms before moving on
            @Override
            public void run() {
                MediaPlayer.create(AEDTutorial.this, R.raw.speech4_iamgoingtoshowyouhowtousetheaed).start();
                instPrompter.setText("I am going to show you how to use the AED.");
            }
        };
        final Runnable speech5 = new Runnable() {
            @Override
            public void run() {
                Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
                animation.setDuration(500); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                animation.setRepeatCount(5);
                animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
                lpadDraggable.startAnimation(animation);
                rpadDraggable.startAnimation(animation);

                MediaPlayer.create(AEDTutorial.this, R.raw.speech5_holdanddragthetwoaedpads).start();
                instPrompter.setText("Hold and drag the two AED pads, to the body part as shown below.");
                lpadDrop.setOnDragListener(dragListener);
                rpadDrop.setOnDragListener(dragListener);
                lpadDraggable.setOnLongClickListener(longClickListener);
                rpadDraggable.setOnLongClickListener(longClickListener);

            }
        };
        // execute speeches
        speeches = new Thread() {
            public void run() {
                try {
                    runOnUiThread(speech1);
                    Thread.sleep(2000);
                    runOnUiThread(speech2);
                    Thread.sleep(2000);
                    runOnUiThread(speech3);
                    Thread.sleep(1500);
                    runOnUiThread(speech4);
                    Thread.sleep(4000);
                    runOnUiThread(speech5);
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        };
        runningThreads.add(speeches);
        speeches.start();

        // end aedTutorial
    }

    // code required to hide the navbar when user interacting with screen
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // start onWindowFocusChanged
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
        // end onWindowFocusChanged
    }

    // when user initiate long click, start drag (L/R AED pads)
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            MediaPlayer.create(AEDTutorial.this, R.raw.sound_tear).start(); // tearing sound
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder,v,0);
            return true;
        }
    };

    // when drag initiated, do following:
    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            // start dragListener
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();
            switch(dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                case DragEvent.ACTION_DROP:
                    LinearLayout oldparent = (LinearLayout) view.getParent();
                    oldparent.removeView(view);
                    LinearLayout newParent = (LinearLayout) v;
                    newParent.addView(view);
                    MediaPlayer.create(AEDTutorial.this, R.raw.click_sound).start(); // click sound
                    break;
            }
            return true;
            // end dragListener
        }
    };
}
