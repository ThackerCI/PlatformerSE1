package com.test.platformerse1;


// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// The C_Controls class defines the fragment used for the player to control the character, and defines
// the methods used by that fragment.

import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class C_Controls extends Fragment {
    public C_Controls() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        // get the level activity
        final V_LevelActivity levelActivity = (V_LevelActivity) getActivity();
        // get the buttons
        Button leftButton = (Button) view.findViewById(R.id.left_button);
        Button rightButton = (Button) view.findViewById(R.id.right_button);
        Button jumpButton = (Button) view.findViewById(R.id.jump_button);
        Button shootButton = (Button) view.findViewById(R.id.shoot_button);
        Button previousPowerUp = (Button) view.findViewById(R.id.previous_song);
        Button nextPowerUp = (Button) view.findViewById(R.id.next_song);
        Button applyPowerUp = (Button) view.findViewById(R.id.play_song);
        Chronometer timeKeeper = (Chronometer) view.findViewById(R.id.timeValues);
        timeKeeper.setBase(SystemClock.elapsedRealtime());

        levelActivity.setMeter(timeKeeper);

        // set the buttons to execute the proper functions on touch.
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    move(-1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopCharacter();
                }
                return true;
            }
        });
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    move(1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopCharacter();
                }
                return true;
            }
        });

        jumpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    jump();
                }
                return true;
            }
        });

        shootButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    shoot();
                }
                return true;
            }
        });

        nextPowerUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    nextPower();
                }

                return true;
            }
        });

        previousPowerUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    previousPower();
                }
                return true;
            }
            });

        return view;
    }

    //Select a Power-Up
    private void selectPower() {
        //?? Is C_EnvironmentController where this should be applied, or LevelActivity?
       C_EnvironmentController.applyPower( M_CollectedRecords.getInstance().getCurrentPowerUp());

    }



    //Scroll to previous Power-Up
    private void previousPower() {M_CollectedRecords.getInstance().previousPowerUp();}

    //Scroll to next Power-Up
    private void nextPower() {M_CollectedRecords.getInstance().nextPowerUp();}

    // stop the character's movement
    private void stopCharacter() {
        M_Character.getInstance().setVelocityX(0);
    }


    // set the character moving in the given direction
    private void move(int direction) {
        // make the character move in the given direction.
        M_Character.getInstance().horizontalMove(direction);
    }

    private void jump() {
        // make the player jump if possible.
        M_Character.getInstance().jump(C_EnvironmentController.onBlock(M_Character.getInstance()));
    }

    // cause the player to fire a bullet
    private void shoot() {
        M_Environment.getInstance().getBullets().add(M_Character.getInstance().shoot());
    }
}
