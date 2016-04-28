package com.test.platformerse1;

import android.graphics.Point;

// Author: Isaiah Thacker
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// M_PopupTrigger describes an object used to display popup messages to the user.
// "Stepping on" the popup trigger causes the message to be displayed, and pauses the game until
// the popup is dismissed.
public class M_PopupTrigger extends M_WorldObject {

    // the title of the message to be displayed in the resulting popup box
    private String title;
    // the message to be displayed in the resulting popup box
    private String message;

    M_PopupTrigger(Point location, Point dimensions, String title, String message) {
        // set the popup's location, dimensions, and message
        setLocation(location);
        setDimensions(dimensions);
        this.title = title;
        this.message = message;
    }

    public void popup() {
        M_Environment environment = M_Environment.getInstance();
        // pause the environment
        environment.setPaused(true);
        // set the popup title
        environment.setPopupTitle(this.title);
        // set the popup message
        environment.setPopupText(this.message);
        // set the popup to display
        environment.setShowingPopup(true);
    }
}