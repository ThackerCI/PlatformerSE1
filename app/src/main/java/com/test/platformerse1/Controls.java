package com.test.platformerse1;


// Author: Isaiah Thacker
// Last Modified: 4/03/16
// Iteration 3
// The Controls class defines the fragment used for the player to control the character, and defines
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

public class Controls extends Fragment {
    public Controls() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        // get the level activity
        final LevelActivity levelActivity = (LevelActivity) getActivity();
        // get the buttons
        Button leftButton = (Button) view.findViewById(R.id.left_button);
        Button rightButton = (Button) view.findViewById(R.id.right_button);
        Button jumpButton = (Button) view.findViewById(R.id.jump_button);
        Button shootButton = (Button) view.findViewById(R.id.shoot_button);
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

        return view;
    }

    // stop the character's movement
    public void stopCharacter() {
        Character.getInstance().setVelocityX(0);
    }


    // set the character moving in the given direction
    public void move(int direction) {
        // make the character move in the given direction.
        Character.getInstance().horizontalMove(direction);
    }

    public void jump() {
        // make the player jump if possible.
        Character.getInstance().jump(EnvironmentController.onBlock(Character.getInstance()));
    }

    // cause the player to fire a bullet
    public void shoot() {
        Environment.getInstance().getBullets().add(Character.getInstance().shoot());
    }
}
