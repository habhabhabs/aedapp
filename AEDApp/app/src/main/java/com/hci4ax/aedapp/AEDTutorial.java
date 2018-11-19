package com.hci4ax.aedapp;

import android.content.ClipData;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
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

public class AEDTutorial extends AppCompatActivity {
    Handler handler;
    TextView instPrompter;
    ImageView lpadDraggable, rpadDraggable;
    LinearLayout lpadDrop, rpadDrop;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aedtutorial);

        instPrompter = (TextView) findViewById(R.id.prompter);
        handler = new Handler(getApplicationContext().getMainLooper());
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        // hide the ugly navbar for phones without hardware buttons
        // calls function: onWindowFocusChanged(boolean hasFocus)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // add code below
        // define draggables
        lpadDraggable = (ImageView) findViewById(R.id.lpadDraggableImg);
        rpadDraggable = (ImageView) findViewById(R.id.rpadDraggableImg);
        lpadDrop = (LinearLayout) findViewById(R.id.lpadDrop);
        rpadDrop = (LinearLayout) findViewById(R.id.rpadDrop);


        try {
            aedTutorial(lpadDraggable, rpadDraggable, lpadDrop, rpadDrop);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // end onCreate
    }

    Runnable speech1, speech2, speech3, speech4, speech5;

    private void aedTutorial(final ImageView lpadDraggable, final ImageView rpadDraggable, final LinearLayout lpadDrop, final LinearLayout rpadDrop) throws InterruptedException {
        // start aedTutorial
        speech1 = new Runnable() { // TODO: delay 2000ms before moving on
            @Override
            public void run() {
                final MediaPlayer speech1audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech1_hiimannie);
                speech1audio.start();
                instPrompter.setText("Hi, I'm Annie.");
                speech1audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech1audio.release();
                    }
                });
            }
        };
        speech2 = new Runnable() { // TODO: delay 2000ms before moving on
            @Override
            public void run() {
                final MediaPlayer speech2audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech2_welcometothistutorial);
                speech2audio.start();
                instPrompter.setText("Welcome to this tutorial.");
                speech2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech2audio.release();
                    }
                });
            }
        };
        speech3 = new Runnable() { // TODO: delay 1500ms before moving on
            @Override
            public void run() {
                final MediaPlayer speech3audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech3_inthistutorial);
                speech3audio.start();
                instPrompter.setText("In this tutorial");
                speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech3audio.release();
                    }
                });
            }
        };
        speech4 = new Runnable() { // TODO: delay 4000ms before moving on
            @Override
            public void run() {
                final MediaPlayer speech4audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech4_iamgoingtoshowyouhowtousetheaed);
                speech4audio.start();
                instPrompter.setText("I am going to show you how to use the AED.");
                speech4audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech4audio.release();
                    }
                });
            }
        };
        speech5 = new Runnable() {
            @Override
            public void run() {
                Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
                animation.setDuration(500); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                animation.setRepeatCount(5);
                animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
                lpadDraggable.startAnimation(animation);
                rpadDraggable.startAnimation(animation);

                final MediaPlayer speech5Audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech5_holdanddragthetwoaedpads);
                speech5Audio.start();
                instPrompter.setText("Hold and drag the two AED pads, to the body as shown below.");
                lpadDrop.setOnDragListener(dragAEDPadsListener);
                rpadDrop.setOnDragListener(dragAEDPadsListener);
                lpadDraggable.setOnLongClickListener(longClickListener);
                rpadDraggable.setOnLongClickListener(longClickListener);
                speech5Audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech5Audio.release();
                    }
                });
            }
        };

        // execute speeches
        handler.postDelayed(speech1, 0);
        handler.postDelayed(speech2, 2000);
        handler.postDelayed(speech3, 4000);
        handler.postDelayed(speech4, 5000);
        handler.postDelayed(speech5, 8500);
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
            final MediaPlayer tearSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_tear); // tearing sound
            tearSound.start();
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder,v,0);
            tearSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    tearSound.release();
                }
            });
            return true;
        }
    };

    // when drag for aed pads initiated, do following:
    View.OnDragListener dragAEDPadsListener = new View.OnDragListener() {
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
                    vibrator.cancel();
                    LinearLayout oldparent = (LinearLayout) view.getParent();
                    LinearLayout newParent = (LinearLayout) v;
                    MediaPlayer clickSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_click); // click sound
                    clickSound.start();
                    oldparent.removeView(view);
                    newParent.addView(view);
                    if ((lpadDraggable.getParent() == lpadDrop) && (rpadDraggable.getParent() == rpadDrop)) {
                        // moving on
                        clickSound.release();
                        final MediaPlayer correctSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_correct);
                        correctSound.start();
                        long pattern[] = {0,100,0,200,0,300,0,100,0,200,0,300};
                        vibrator.vibrate(pattern,-1);
                        final MediaPlayer correctPrompt = MediaPlayer.create(AEDTutorial.this, R.raw.speech6_excellent);
                        correctPrompt.start();
                        instPrompter.setText("Excellent!");
                        correctSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                correctPrompt.release();
                                correctSound.release();

                                // TODO: need to figure out how to remove
                                handler.removeCallbacks(speech1);
                                handler.removeCallbacks(speech2);
                                handler.removeCallbacks(speech3);
                                handler.removeCallbacks(speech4);
                            }
                        });
                    }
                    else if ((view == lpadDraggable) && (lpadDraggable.getParent() == lpadDrop)) {
                        // encourage
                        clickSound.release();
                        final MediaPlayer correctSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_correct);
                        correctSound.start();
                        long pattern[] = {0,100,0,200,0,300,0,100,0,200,0,300};
                        vibrator.vibrate(pattern,-1);
                        final MediaPlayer correctPrompt = MediaPlayer.create(AEDTutorial.this, R.raw.speech7_welldoneattachtheremainingpadtothebody);
                        correctPrompt.start();
                        instPrompter.setText("Well done! Attach the remaining pad to the body.");
                        correctPrompt.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                correctPrompt.release();
                                correctSound.release();
                            }
                        });
                    }
                    else if ((view == rpadDraggable) && (rpadDraggable.getParent() == rpadDrop)) {
                        // encourage
                        clickSound.release();
                        final MediaPlayer correctSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_correct);
                        correctSound.start();
                        long pattern[] = {0,100,0,200,0,300,0,100,0,200,0,300};
                        vibrator.vibrate(pattern,-1);
                        final MediaPlayer correctPrompt = MediaPlayer.create(AEDTutorial.this, R.raw.speech8_greatattachtheremainingpadtothebody);
                        correctPrompt.start();
                        instPrompter.setText("Great! Attach the remaining pad to the body.");
                        correctPrompt.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                correctPrompt.release();
                                correctSound.release();
                            }
                        });
                    }
                    else {
                        // please try again
                        final MediaPlayer wrongSound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_wrong);
                        wrongSound.start();
                        newParent.removeView(view);
                        oldparent.addView(view);
                        vibrator.vibrate(500);
                        wrongSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                wrongSound.release();
                            }
                        });
                    }
                    break;
            }
            return true;
            // end dragListener
        }
    };
}
