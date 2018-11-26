package com.hci4ax.aedapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        handler = new Handler(getApplicationContext().getMainLooper());
        instPrompter = (TextView) findViewById(R.id.instruction_prompter);
        bareChest = (ImageView) findViewById(R.id.bareChest);
        shirtOn = (ImageView) findViewById(R.id.shirtOn);

        bareChest.setVisibility(View.INVISIBLE);
        shirtOn.setVisibility(View.INVISIBLE);

        CPRTutorial2();

    }

//    Runnable t1speech1, t1speech2;
//    private void CPRTutorial1() {
//        t1speech1 = new Runnable() {
//            @Override
//            public void run() {
//            final MediaPlayer speech1audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech1_hiimannie);
//            speech1audio.start();
//            instPrompter.setText("Hi, I'm Annie.");
//            speech1audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    speech1audio.release();
//                    final MediaPlayer speech2audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech2_welcometothistutorial);
//                    speech2audio.start();
//                    instPrompter.setText("Welcome to this tutorial.");
//                    speech2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            speech2audio.release();
//                            final MediaPlayer speech26audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech26_warningifthisisanactualemergencycasepleasecallthescdfat);
//                            speech26audio.start();
//                            instPrompter.setText("WARNING: If this is an actual emergency case, please call the SCDF at 995 immediately before beginning CPR!");
//                            speech26audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                @Override
//                                public void onCompletion(MediaPlayer mp) {
//                                    speech26audio.release();
//                                    instPrompter.setText("In the meantime, request for nearby help to retrieve the AED machine while the resuscitation process is ongoing!");
//                                    final MediaPlayer speech29audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech29_inthemeantimerequestfornearbyhelptoretrievetheaed);
//                                    speech29audio.start();
//                                    speech29audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                        @Override
//                                        public void onCompletion(MediaPlayer mp) {
//                                            speech29audio.release();
//                                            final MediaPlayer speech27audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech27_letsbegin);
//                                            speech27audio.start();
//                                            instPrompter.setText("Let's begin.");
//                                            speech27audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                                @Override
//                                                public void onCompletion(MediaPlayer mp) {
//                                                    speech27audio.release();
//                                                    final MediaPlayer speech3audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech3_inthistutorial);
//                                                    speech3audio.start();
//                                                    instPrompter.setText("In this tutorial");
//                                                    speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                                        @Override
//                                                        public void onCompletion(MediaPlayer mp) {
//                                                            speech3audio.release();
//                                                        }
//                                                    });
//                                                }
//                                            });
//                                        }
//                                    });
//                                }
//                            });
//                        }
//                    });
//                }
//            });
//            }
//        };
//
//        t1speech2 = new Runnable() {
//            @Override
//            public void run() {
//            final MediaPlayer speech17audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech17_imgoingtoshowyouhowtoconductcoprresuscitation);
//            speech17audio.start();
//            instPrompter.setText("I'm going to show you how to conduct CPR resuscitation.");
//            speech17audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                speech17audio.release();
//                final MediaPlayer collapseSound = MediaPlayer.create(CPRTutorial.this, R.raw.sound_bodycollapse);
//                collapseSound.start();
//                instPrompter.setText("(someone faints)");
//                collapseSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                    collapseSound.release();
//                    final MediaPlayer speech18audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech18_helphelphelpsomeonefaintedoverhere);
//                    speech18audio.start();
//                    instPrompter.setText("BYSTANDER: Help! Help! Help! Someone fainted over here!");
//                    speech18audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                        speech18audio.release();
//                        final MediaPlayer speech19audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech19_thisscenarioiswherea26yearoldmanwhohadsudd);
//                        speech19audio.start();
//                        instPrompter.setText("This scenario is where a 26-year-old man who had suddenly collapsed in the gym.");
//                        speech19audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//                                speech19audio.release();
//                                CPRTutorial2();
//                            }
//                        });
//                        }
//                    });
//                    }
//                });
//                }
//            });
//            }
//        };
//
//        handler.removeCallbacksAndMessages(null);
//        handler.postDelayed(t1speech1, 100);
//        handler.postDelayed(t1speech2, 20500);
//    }

    Runnable t2speech1, t2speech2;
    MediaPlayer tutorial2audio, tutorial2audio1;
    private void CPRTutorial2() {
        t2speech1 = new Runnable() {
            @Override
            public void run() {
                tutorial2audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech20_letstakeacloserlookatthepatient);
                tutorial2audio.start();
                instPrompter.setText("Let's take a closer look at the patient.");
                final Animation animation = new AlphaAnimation((float) 0, (float) 0.5); // Change alpha from fully visible to invisible
                animation.setDuration(500); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                shirtOn.setVisibility(View.VISIBLE);
                shirtOn.startAnimation(animation);
                bareChest.startAnimation(animation);
                bareChest.setVisibility(View.VISIBLE);
                tutorial2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    tutorial2audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech21_tobeginwewilltrytocheckifthepatientresponds);
                    tutorial2audio.start();
                    instPrompter.setText("To begin, we will try to check if the patient responds.");
                    tutorial2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            tutorial2audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech22_heyheycanyouhearme);
                            tutorial2audio.start();
                            instPrompter.setText("YOU: Hey, Hey! Can you hear me?");
                            long pattern[] = {0,750,0,0};
                            vibrator.vibrate(pattern,-1);
                            vibrator.vibrate(pattern,-1);
                            tutorial2audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                instPrompter.setText("(no response)");
                                tutorial2audio.release();
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
            tutorial2audio1 = MediaPlayer.create(CPRTutorial.this, R.raw.speech25_thispatientisnotrespondingtous);
            tutorial2audio1.start();
            instPrompter.setText("This patient is not responding to us.");
            tutorial2audio1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                tutorial2audio1 = MediaPlayer.create(CPRTutorial.this, R.raw.speech23_ifthepatientisnotrespondingtouswewillcheckhispulse);
                tutorial2audio1.start();
                instPrompter.setText("If the patient is not responding to us, we will check his pulse.");
                tutorial2audio1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    tutorial2audio1 = MediaPlayer.create(CPRTutorial.this, R.raw.speech20_letstakeacloserlookatthepatient);
                    tutorial2audio1.start();
                    instPrompter.setText("Let's take a closer look at the patient.");
                    tutorial2audio1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                        tutorial2audio1 = MediaPlayer.create(CPRTutorial.this, R.raw.speech28_noteifthefollowingpatientisgaspingneverassumethatheisabletobreatheon);
                        tutorial2audio1.start();
                        instPrompter.setText("NOTE: If the following patient is gasping, NEVER assume that he is able to breathe on his own. In this case, CPR is NEEDED.");
                        tutorial2audio1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                            final View pulse = (View) findViewById(R.id.pulse);
                            pulse.setVisibility(View.VISIBLE);
                            final ObjectAnimator objAnimator = ObjectAnimator.ofFloat(pulse, "alpha",0f,1f);
                            objAnimator.setDuration(1000);
                            objAnimator.setRepeatMode(Animation.REVERSE);
                            objAnimator.setRepeatCount(Animation.INFINITE);
                            objAnimator.start();
                            instPrompter.setText("Let's check the patient's if there is any pulse, by holding on the part of the neck as shown below.");
                            tutorial2audio1 = MediaPlayer.create(CPRTutorial.this, R.raw.speech30_letscheckthepatientifthereisanypulsebyholding);
                            tutorial2audio1.start();
                            tutorial2audio1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    pulse.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            objAnimator.end();
                                            instPrompter.setText("(no pulse detected)");
                                            pulse.setVisibility(View.GONE);
                                            tutorial2audio1.release();
                                            CPRTutorial3();
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
    MediaPlayer speech3audio;
    private void CPRTutorial3() {
        t3speech1 = new Runnable() {
            @Override
            public void run() {
                instPrompter.setText("There was no pulse detected. In this case,");
                speech3audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech31_therewasnopulsedetectedinthiscase);
                speech3audio.start();
                speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speech3audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech32_wewillfirstbeginbyconductingchestcompressionsonthepatient);
                        speech3audio.start();
                        instPrompter.setText("We will first begin by conducting chest compressions on the patient.");
                        speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                            instPrompter.setText("Remove any top clothing from the patient, such as shirts. Hold down clothing to remove from patient.");
                            speech3audio = MediaPlayer.create(CPRTutorial.this, R.raw.speech33_removeanytopclothingfromthepatientsuchasshirtsholddown);
                            speech3audio.start();
                            speech3audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                final ImageView shirtOn = (ImageView) findViewById(R.id.shirtOn);
                                final Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
                                animation.setDuration(1500); // duration - half a second
                                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                                shirtOn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        shirtOn.startAnimation(animation);
//                                        vibrator.vibrate(500);
                                        shirtOn.setVisibility(View.GONE);
                                        speech3audio.release();
                                        CPRTutorial4();
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

    Runnable t4speech1, t4speech3, t4speech4;
    MediaPlayer speech4audio3, speech4audio4, speech4audio2;
    private void CPRTutorial4() {
        final ImageView cprStance = (ImageView) findViewById(R.id.cprstance);
        t4speech1 = new Runnable() {
            @Override
            public void run() {
            speech4audio3 = MediaPlayer.create(CPRTutorial.this, R.raw.speech34_locatethegapinthecenterofthecheatandapplythis);
            speech4audio3.start();
            instPrompter.setText("Locate the gap in the center of the chest, and apply this stance while keeping your elbows straight.");
            Animation animation = new AlphaAnimation((float) 0, (float) 1); // Change alpha from fully visible to invisible
            animation.setDuration(700); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            cprStance.setVisibility(View.VISIBLE);
            cprStance.startAnimation(animation);
            speech4audio3.release();
            }
        };

        t4speech3 = new Runnable() {
            @Override
            public void run() {
            speech4audio4 = MediaPlayer.create(CPRTutorial.this, R.raw.speech35_basedontherhythmofthefollowingvibrationsnippetconduct);
            speech4audio4.start();
            instPrompter.setText("Based on the rhythm of the following vibration snippet, conduct 30 repetitions.");
            long pattern[] = {0,300,0,0};
            vibrator.vibrate(pattern,0);
            speech4audio4.release();
            }
        };

        t4speech4 = new Runnable() {
            @Override
            public void run() {
            vibrator.cancel();
            speech4audio2 = MediaPlayer.create(CPRTutorial.this, R.raw.speech36_uponcompletionadministertwobreathstothepatientvia);
            speech4audio2.start();
            instPrompter.setText("Upon completion, administer two breaths to the patient via the mouth, while pinching the nose.");
            speech4audio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                speech4audio2 = MediaPlayer.create(CPRTutorial.this, R.raw.speech37_continuedoingsountiltheaedarrivesifnopulsedetectedorwhen);
                speech4audio2.start();
                instPrompter.setText("Continue doing so until the AED arrives (if no pulse detected), or when the patient has regained consciousness.");
                speech4audio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                    speech4audio2.release();
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
        handler.postDelayed(t4speech3, 9000);
        handler.postDelayed(t4speech4, 25000);
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



