package com.hci4ax.aedapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
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

        // add code below
        // define draggables
        lpadDraggable = (ImageView) findViewById(R.id.lpadDraggableImg);
        rpadDraggable = (ImageView) findViewById(R.id.rpadDraggableImg);
        lpadDrop = (LinearLayout) findViewById(R.id.lpadDrop);
        rpadDrop = (LinearLayout) findViewById(R.id.rpadDrop);

        aedTutorial0();
        // end onCreate
    }

    Runnable t0speech1;
    MediaPlayer speech1audio;
    // step 0: powering on the aed
    private void aedTutorial0() {
        t0speech1 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech1audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech1_hiimannie);
                speech1audio.start();
                instPrompter.setText("Hi, I'm Annie.");
                speech1audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        final View powerCircle = (View) findViewById(R.id.powerCircle);
                        powerCircle.setVisibility(View.VISIBLE);
                        final ObjectAnimator objAnimator = ObjectAnimator.ofFloat(powerCircle, "alpha",0f,1f);
                        objAnimator.setDuration(1000);
                        objAnimator.setRepeatMode(Animation.REVERSE);
                        objAnimator.setRepeatCount(Animation.INFINITE);
                        objAnimator.start();
                        speech1audio.release();
                        instPrompter.setText("To begin this tutorial, press the green Power button located on the AED.");
                        final MediaPlayer speech0audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech0_tobeginthistutorialpressthegreenpowerbuttonlocated);
                        speech0audio.start();
                        powerCircle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final MediaPlayer buttonPress = MediaPlayer.create(AEDTutorial.this, R.raw.sound_pressbutton);
                                buttonPress.start();
                                objAnimator.end();
                                long pattern[] = {0,50,0};
                                vibrator.vibrate(pattern,-1);
                                powerCircle.setVisibility(View.INVISIBLE);
                                buttonPress.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        powerCircle.setOnClickListener(null);
                                        aedTutorial1(lpadDraggable, rpadDraggable, lpadDrop, rpadDrop);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t0speech1, 0);
    }

    Runnable t1Speech1, t1Speech2, t1Speech3, t1Speech4;
    // step 1: paste the aed pads
    private void aedTutorial1(final ImageView lpadDraggable, final ImageView rpadDraggable, final LinearLayout lpadDrop, final LinearLayout rpadDrop) {
        // start aedTutorial1
        t1Speech1 = new Runnable() { // TODO: delay 2000ms before moving on
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
        t1Speech2 = new Runnable() { // TODO: delay 1500ms before moving on
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
        t1Speech3 = new Runnable() { // TODO: delay 4000ms before moving on
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
        t1Speech4 = new Runnable() {
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
                lpadDraggable.setOnTouchListener(onTouchListener);
                rpadDraggable.setOnTouchListener(onTouchListener);
                speech5Audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech5Audio.release();
                    }
                });
            }
        };

        // purge existing instruction set from zeroth step
        handler.removeCallbacksAndMessages(null);
        // execute speeches
        handler.postDelayed(t1Speech1, 0);
        handler.postDelayed(t1Speech2, 2000);
        handler.postDelayed(t1Speech3, 3000);
        handler.postDelayed(t1Speech4, 6000);
        // end aedTutorial1
    }

    // when user initiate long click, start drag (L/R AED pads)
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
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

    View.OnTouchListener disableDrag = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
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
                        lpadDraggable.setOnTouchListener(disableDrag);
                        rpadDraggable.setOnTouchListener(disableDrag);
                        instPrompter.setText("Excellent!");
                        correctSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                correctPrompt.release();
                                correctSound.release();
                                AEDTutorial2();
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

    Runnable t2Speech1, t2speech2;
    // step 2: after sticking pads, do what the aed needs to do
    private void AEDTutorial2() {
        // start AEDTutorial2
        t2Speech1 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech9audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech9_aedpadsdetected);
                speech9audio.start();
                instPrompter.setText("AED Pads detected.");
                speech9audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech9audio.release();
                        final MediaPlayer speech10audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech10_analyzingheartrhythmdonottouchpatient);
                        speech10audio.start();
                        instPrompter.setText("Analyzing heart rhythm, do not touch patient!");
                        speech10audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                final MediaPlayer speech11audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech11_irepeatdonottouchpatient);
                                speech11audio.start();
                                instPrompter.setText("I repeat, do not touch patient!");
                                speech11audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        speech11audio.release();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        t2speech2 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech12audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech12_shockadvised);
                speech12audio.start();
                instPrompter.setText("Shock advised.");
                speech12audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech12audio.release();
                        final MediaPlayer speech13audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech13_stayawayfrompatientandpresstheredshock);
                        speech13audio.start();
                        instPrompter.setText("Stay away from patient, and press the red \"Shock\" button on the AED to begin.");
                        final View shockCircle = (View) findViewById(R.id.shockCircle);
                        shockCircle.setVisibility(View.VISIBLE);
                        final ObjectAnimator objAnimator = ObjectAnimator.ofFloat(shockCircle, "alpha",0f,1f);
                        objAnimator.setDuration(1000);
                        objAnimator.setRepeatMode(Animation.REVERSE);
                        objAnimator.setRepeatCount(Animation.INFINITE);
                        objAnimator.start();
                        speech13audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech13audio.release();
                                shockCircle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        long pattern[] = {0,50,0};
                                        vibrator.vibrate(pattern,-1);
                                        final MediaPlayer buttonPress = MediaPlayer.create(AEDTutorial.this, R.raw.sound_pressbutton);
                                        buttonPress.start();
                                        objAnimator.end();
                                        shockCircle.setVisibility(View.INVISIBLE);
                                        buttonPress.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                buttonPress.release();
                                                AEDTutorial3();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        // purge existing instruction set from first step
        handler.removeCallbacksAndMessages(null);
        // load second step tutorial
        handler.postDelayed(t2Speech1, 0);
        handler.postDelayed(t2speech2, 8300);

        // end AEDTutorial2
    }

    Runnable t3speech1, t3speech2;
    // step 3: after clicking shock button, prepare to shock.
    private void AEDTutorial3() {
        t3speech1 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech10audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech10_analyzingheartrhythmdonottouchpatient);
                speech10audio.start();
                instPrompter.setText("Analyzing heart rhythm, do not touch patient!");
                speech10audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech10audio.release();
                        final MediaPlayer speech14audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech14_standbypreparingtoshock);
                        speech14audio.start();
                        instPrompter.setText("Stand by. Preparing to shock!");
                        speech14audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech14audio.release();
                                final MediaPlayer speech15audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech15_administeringshocknowpleasestayclearofpatient);
                                speech15audio.start();
                                instPrompter.setText("Administering shock now. PLEASE STAY CLEAR OF PATIENT!");
                                speech15audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        speech15audio.release();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        t3speech2 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer defibsound = MediaPlayer.create(AEDTutorial.this, R.raw.sound_defibcharge);
                defibsound.start();
                instPrompter.setText("(AED sounds)");
                vibrator.vibrate(5000);
                defibsound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        defibsound.release();
                        final ImageView bareChest = (ImageView) findViewById(R.id.bareChest);
                        ObjectAnimator rotate = ObjectAnimator.ofFloat(bareChest, "rotation", 0f, 2f, 0f, -2f, 0f); // rotate o degree then 20 degree and so on for one loop of rotation.
                        rotate.setRepeatCount(5);
                        rotate.setDuration(100);
                        rotate.start();
                        vibrator.vibrate(500);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        instPrompter.setText("Shock delivered. You may continue your CPR compressions until the paramedic arrives.");
                        final MediaPlayer speech16audio = MediaPlayer.create(AEDTutorial.this, R.raw.speech16_shockdeliveredyoumaycontinueyourcprcompressions);
                        speech16audio.start();
                        speech16audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                finish();
                            }
                        });
                    }
                });
            }
        };

        // purge existing instruction set from second step
        handler.removeCallbacksAndMessages(null);
        // load third step tutorial
        handler.postDelayed(t3speech1, 0);
        handler.postDelayed(t3speech2, 8200);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        System.exit(0);
        finish();
    }
}
