package com.test.platformerse1;


// Author: Isaiah Thacker
// Last Modified: 4/30/16 by Isaiah Thacker
// Iteration 4
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
        Button pauseButton = (Button) view.findViewById(R.id.pause_button);
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

        pauseButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ((V_LevelActivity) getActivity()).displayPauseMenu();
                }
                return true;
            }
        });

        return view;
    }

    // stop the character's movement
    private void stopCharacter() {
        M_Character.getInstance().setVelocityX(0);
    }


    // set the character moving in the given direction
    private void move(int direction) {
        // make the character move in the given direction.
        C_CharacterController.horizontalMove(direction);
    }

    private void jump() {
        // make the player jump if possible.
        C_CharacterController.jump(C_EnvironmentController.onBlock(M_Character.getInstance()));
    }

    // cause the player to fire a bullet
    private void shoot() {
        M_Environment.getInstance().getBullets().add(C_CharacterController.shoot());
    }

    private void pauseGame() {
        C_EnvironmentController.pauseGame();
        V_LevelActivity levelActivity = (V_LevelActivity) getActivity();
        levelActivity.displayPauseMenu();
    }
}
