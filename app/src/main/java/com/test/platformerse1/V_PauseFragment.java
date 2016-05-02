package com.test.platformerse1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;


// Author: Isaiah Thacker
// Last Modified: 4/28/16 by Isaiah Thacker
// Iteration 4
// V_PauseFragment describes the fragment that will display the pause menu to the user.
public class V_PauseFragment extends Fragment {


    public V_PauseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pause, container, false);

        // get the buttons
        Button resumeButton = (Button) view.findViewById(R.id.resume_button);
        Button restartButton = (Button) view.findViewById(R.id.restart_button);
        Button menuButton = (Button) view.findViewById(R.id.menu_button);

        // set their onTouchListeners
        resumeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    resume();
                }
                return true;
            }
        });

        restartButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    restart();
                }
                return true;
            }
        });

        menuButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    endLevel();
                }
                return true;
            }
        });

        return view;
    }

    public void toggleVisibility() {
        assert getView() != null;
        View pauseFrame = getView().findViewById(R.id.pause_frame);
        pauseFrame.bringToFront();
        pauseFrame.setVisibility(View.VISIBLE);
    }

    public void resume() {
        assert getView() != null;
        View pauseFrame = getView().findViewById(R.id.fragment_pause);
        pauseFrame.setVisibility(View.GONE);
        C_EnvironmentController.unpause();
        V_LevelActivity activity = (V_LevelActivity) getActivity();
        Chronometer timeKeeper = activity.getTimeKeeper();
        timeKeeper.start();
    }

    // restart reinitializes the current level
    public void restart() {
        // create a new intent
        Intent levelIntent = new Intent(getActivity(), V_LevelActivity.class);
        int levelID = M_Environment.getInstance().getCurrentLevel();
        levelIntent.putExtra("levelID", levelID); // put the level ID in the intent
        startActivity(levelIntent);  // go to the new level activity with intent levelIntent
        getActivity().finish();      // finish this level activity
    }

    // endLevel() finishes the current activity
    public void endLevel() {
        V_LevelActivity levelActivity = (V_LevelActivity) getActivity();
        C_MusicController.stopMusic();
        C_MusicController.stopPowerUp();
        levelActivity.finish();
    }
}