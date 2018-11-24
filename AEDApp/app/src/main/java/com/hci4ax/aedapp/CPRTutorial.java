package com.hci4ax.aedapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
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
    ImageView bareChest, shirtOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprtutorial);

        // hide the ugly navbar for phones without hardware buttons
        // calls function: onWindowFocusChanged(boolean hasFocus)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        handler = new Handler(getApplicationContext().getMainLooper());
        instPrompter = (TextView) findViewById(R.id.instruction_prompter);
        bareChest = (ImageView) findViewById(R.id.bareChest);
        shirtOn = (ImageView) findViewById(R.id.shirtOn);

        bareChest.setVisibility(View.INVISIBLE);
        shirtOn.setVisibility(View.INVISIBLE);

        CPRTutorial1();

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
                            final MediaPlayer speech26audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech26_warningifthisisanactualemergencycasepleasecallthescdfat);
                            speech26audio.start();
                            instPrompter.setText("WARNING: If this is an actual emergency case, please call the SCDF at 995 immediately before beginning CPR!");
                            speech26audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    speech26audio.release();
                                    instPrompter.setText("In the meantime, request for nearby help to retrieve the AED machine while the resuscitation process is ongoing!");
                                    final MediaPlayer speech29audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech29_inthemeantimerequestfornearbyhelptoretrievetheaed);
                                    speech29audio.start();
                                    speech29audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            speech29audio.release();
                                            final MediaPlayer speech27audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech27_letsbegin);
                                            speech27audio.start();
                                            instPrompter.setText("Let's begin.");
                                            speech27audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    speech27audio.release();
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
                speech17audio.release();
                final MediaPlayer collapseSound = MediaPlayer.create(CPRTutorial.this, R.raw.sound_bodycollapse);
                collapseSound.start();
                instPrompter.setText("(someone faints)");
                collapseSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    collapseSound.release();
                    final MediaPlayer speech18audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech18_helphelphelpsomeonefaintedoverhere);
                    speech18audio.start();
                    instPrompter.setText("BYSTANDER: Help! Help! Help! Someone fainted over here!");
                    speech18audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                        speech18audio.release();
                        final MediaPlayer speech19audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech19_thisscenarioiswherea26yearoldmanwhohadsudd);
                        speech19audio.start();
                        instPrompter.setText("This scenario is where a 26-year-old man who had suddenly collapsed in the gym.");
                        speech19audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech19audio.release();
                                CPRTutorial2();
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

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t1speech1, 100);
        handler.postDelayed(t1speech2, 20500);
    }

    Runnable t2speech1, t2speech2;
    private void CPRTutorial2() {
        t2speech1 = new Runnable() {
            @Override
            public void run() {
            final MediaPlayer speech20audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech20_letstakeacloserlookatthepatient);
            speech20audio.start();
            instPrompter.setText("Let's take a closer look at the patient.");
            final Animation animation = new AlphaAnimation((float) 0, (float) 0.5); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            shirtOn.setVisibility(View.VISIBLE);
            shirtOn.startAnimation(animation);
            bareChest.startAnimation(animation);
            bareChest.setVisibility(View.VISIBLE);
            speech20audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                speech20audio.release();
                final MediaPlayer speech21audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech21_tobeginwewilltrytocheckifthepatientresponds);
                speech21audio.start();
                instPrompter.setText("To begin, we will try to check if the patient responds.");
                speech21audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    speech21audio.release();
                    final MediaPlayer speech22audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech22_heyheycanyouhearme);
                    speech22audio.start();
                    instPrompter.setText("YOU: Hey, Hey! Can you hear me?");
                    long pattern[] = {0,750,0,0};
                    vibrator.vibrate(pattern,2);
                    speech22audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            speech22audio.release();
                            instPrompter.setText("(no response)");
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
            final MediaPlayer speech25audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech25_thispatientisnotrespondingtous);
            speech25audio.start();
            instPrompter.setText("This patient is not responding to us.");
            speech25audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                speech25audio.release();
                final MediaPlayer speech23audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech23_ifthepatientisnotrespondingtouswewillcheckhispulse);
                speech23audio.start();
                instPrompter.setText("If the patient is not responding to us, we will check his pulse.");
                speech23audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech23audio.release();
                        final MediaPlayer speech20audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech20_letstakeacloserlookatthepatient);
                        speech20audio.start();
                        instPrompter.setText("Let's take a closer look at the patient.");
                        speech20audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech20audio.release();
                                final MediaPlayer speech28audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech28_noteifthefollowingpatientisgaspingneverassumethatheisabletobreatheon);
                                speech28audio.start();
                                instPrompter.setText("NOTE: If the following patient is gasping, NEVER assume that he is able to breathe on his own. In this case, CPR is NEEDED.");
                                speech28audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        speech28audio.release();
                                        final View pulse = (View) findViewById(R.id.pulse);
                                        pulse.setVisibility(View.VISIBLE);
                                        final ObjectAnimator objAnimator = ObjectAnimator.ofFloat(pulse, "alpha",0f,1f);
                                        objAnimator.setDuration(1000);
                                        objAnimator.setRepeatMode(Animation.REVERSE);
                                        objAnimator.setRepeatCount(Animation.INFINITE);
                                        objAnimator.start();
                                        instPrompter.setText("Let's check the patient's if there is any pulse, by holding on the part of the neck as shown below.");
                                        final MediaPlayer speech30audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech30_letscheckthepatientifthereisanypulsebyholding);
                                        speech30audio.start();
                                        speech30audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                pulse.setOnLongClickListener(new View.OnLongClickListener() {
                                                    @Override
                                                    public boolean onLongClick(View v) {
                                                        objAnimator.end();
                                                        long pattern[] = {0,50,0};
                                                        vibrator.vibrate(pattern,-1);
                                                        instPrompter.setText("(no pulse detected)");
                                                        pulse.setVisibility(View.GONE);
                                                        CPRTutorial3();
                                                        return false;
                                                    }
                                                });
                                            }
                                        });
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

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t2speech1, 0); // next: 10000
        handler.postDelayed(t2speech2, 10000); // next: 29000
    }

    Runnable t3speech1;
    private void CPRTutorial3() {
        t3speech1 = new Runnable() {
            @Override
            public void run() {
                instPrompter.setText("There was no pulse detected. In this case,");
                final MediaPlayer speech31audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech31_therewasnopulsedetectedinthiscase);
                speech31audio.start();
                speech31audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        final MediaPlayer speech32audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech32_wewillfirstbeginbyconductingchestcompressionsonthepatient);
                        speech32audio.start();
                        instPrompter.setText("We will first begin by conducting chest compressions on the patient.");
                        speech32audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech32audio.release();
                                instPrompter.setText("Remove any top clothing from the patient, such as shirts. Hold down clothing to remove from patient.");
                                final MediaPlayer speech33audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech33_removeanytopclothingfromthepatientsuchasshirtsholddown);
                                speech33audio.start();
                                speech33audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        final ImageView shirtOn = (ImageView) findViewById(R.id.shirtOn);
                                        final Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
                                        animation.setDuration(1500); // duration - half a second
                                        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                                        shirtOn.setOnTouchListener(new View.OnTouchListener() { // take off shirt
                                            @Override
                                            public boolean onTouch(View v, MotionEvent event) {
                                                final MediaPlayer takeoffsinglet = MediaPlayer.create(CPRTutorial.this, R.raw.sound_takeoffsinglet);
                                                shirtOn.startAnimation(animation);
                                                vibrator.vibrate(500);
                                                shirtOn.setVisibility(View.GONE);
                                                takeoffsinglet.start();
                                                try {
                                                    Thread.sleep(200);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                takeoffsinglet.release();
                                                CPRTutorial4();
                                                return false;
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

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t3speech1, 2500);

    }

    Runnable t4speech1, t4speech2, t4speech3, t4speech4;
    private void CPRTutorial4() {
        final ImageView cprStance = (ImageView) findViewById(R.id.cprstance);
        t4speech1 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech34audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech34_locatethegapinthecenterofthecheatandapplythis);
                speech34audio.start();
                instPrompter.setText("Locate the gap in the center of the chest, and apply this stance while keeping your elbows straight.");
                Animation animation = new AlphaAnimation((float) 0, (float) 1); // Change alpha from fully visible to invisible
                animation.setDuration(700); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                cprStance.setVisibility(View.VISIBLE);
                cprStance.startAnimation(animation);
            }
        };

        t4speech2 = new Runnable() {
            @Override
            public void run() {
                Animation animation = new AlphaAnimation((float) 1, (float) 0); // Change alpha from fully visible to invisible
                animation.setDuration(700); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                cprStance.startAnimation(animation);
                cprStance.setVisibility(View.GONE);
            }
        };

        t4speech3 = new Runnable() {
            @Override
            public void run() {
                final MediaPlayer speech35audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech35_basedontherhythmofthefollowingvibrationsnippetconduct);
                speech35audio.start();
                instPrompter.setText("Based on the rhythm of the following vibration snippet, conduct 30 repetitions.");
                long pattern[] = {0,250,0,0};
                vibrator.vibrate(pattern,0);
                speech35audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech35audio.release();
                    }
                });

            }
        };

        t4speech4 = new Runnable() {
            @Override
            public void run() {
                vibrator.cancel();
                final MediaPlayer speech36audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech36_uponcompletionadministertwobreathstothepatientvia);
                speech36audio.start();
                instPrompter.setText("Upon completion, administer two breaths to the patient via the mouth, while pinching the nose.");
                speech36audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech36audio.release();
                        final MediaPlayer speech37audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech37_continuedoingsountiltheaedarrivesifnopulsedetectedorwhen);
                        speech37audio.start();
                        instPrompter.setText("Continue doing so until the AED arrives (if no pulse detected), or when the patient has regained consciousness.");
                        speech37audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                speech37audio.release();
                                try {
                                    Thread.sleep(3000);
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

        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(t4speech1, 200);
        handler.postDelayed(t4speech2, 8500);
        handler.postDelayed(t4speech3, 9000);
        handler.postDelayed(t4speech4, 25000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}



