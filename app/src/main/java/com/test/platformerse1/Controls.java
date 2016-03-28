package com.test.platformerse1;


// Author: Isaiah Thacker
// Last Modified: 3/07/16
// Platformer Iteration 2

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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

        // set the buttons to execute the proper functions on touch.
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    levelActivity.move(-1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    levelActivity.stopCharacter();
                }
                return true;
            }
        });
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    levelActivity.move(1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    levelActivity.stopCharacter();
                }
                return true;
            }
        });

        jumpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    levelActivity.jump();
                }
                return true;
            }
        });

        shootButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    levelActivity.shoot();
                }
                return true;
            }
        });

        return view;
    }

    // LevelActivity functions which will be used by this class
    public interface controlListener {
        void move(int d);

        void jump();

        void shoot();

        void stopCharacter();
    }
}
