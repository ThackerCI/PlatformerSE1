package com.test.platformerse1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

// Author: Isaiah Thacker
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// V_PopupFragment describes the fragment that will display temporary popup messages to the user.
public class V_PopupFragment extends Fragment {

    public V_PopupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popup, container, false);

        Button dismissButton = (Button) view.findViewById(R.id.popup_button);

        // give the environment access to this fragment
        M_Environment environment = M_Environment.getInstance();
        environment.setPopupFragment(V_PopupFragment.this);

        // set the buttons to execute the proper functions on touch.
        dismissButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });

        return view;
    }

    public void displayMessage(String title, String message) {
        // make the frame visible and bring it to the front
        assert getView() != null;
        View popupFrame = getView().findViewById(R.id.fragment_popup);
        popupFrame.bringToFront();
        popupFrame.setVisibility(View.VISIBLE);

        // set the title and message for the popup
        TextView titleBar = (TextView) getView().findViewById(R.id.popup_title);
        titleBar.setText(title);

        TextView messageDisplayed = (TextView) getView().findViewById(R.id.popup_text);
        messageDisplayed.setText(message);
    }

    // dismiss() makes the current popup disappear and unpauses the game
    public void dismiss() {
        assert getView() != null;
        View houdini = getView().findViewById(R.id.fragment_popup);     // get the popup fragment
        houdini.setVisibility(View.GONE);                               // make it disappear!
        M_Environment.getInstance().setShowingPopup(false);
        M_Environment.getInstance().setPaused(false);                   // unpause the game
    }
}