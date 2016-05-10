package com.test.platformerse1;

import android.graphics.Point;

/**
 * @author Isaiah Thacker
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Iteration 4
 *         M_PopupTrigger describes an object used to display popup messages to the user.
 *         "Stepping on" the popup trigger causes the message to be displayed, and pauses the game until
 *         the popup is dismissed.
 */
class M_PopupTrigger extends M_WorldObject {

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

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }
}