package com.test.platformerse1;

// Author: Olutayo Elelu
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// The record class defines the records the player may collect within the game environment.

//- Location (Point): Defined by our game mechanics. For now, a point with x and y coordinate. Functions include getLocation and setLocation.
//- Collect(Boolean): Has the record been collected? Functions include isCollected

import android.graphics.Point;

public class M_Record extends M_WorldObject {
    // has the record been collected?
    private boolean collected;
    // the constant dimensions of all records
    private static final Point RECORD_DIMS = new Point(20, 20);

    // Point is given in "grid cell" format for convenience.
    public M_Record(Point p, boolean c) {
        setLocation(p);
        p.x *= 20;
        p.y *= 20;
        collected = c;
        setSprite(R.mipmap.goal_record);
    }

    // clone a record
    public M_Record(M_Record R) {
        setLocation(new Point(R.getLocation()));
        this.collected = R.collected;
    }

    // isCollected()
    // whether or not the record has been collected
    public boolean isCollected() {
        return collected;
    }

    public void collectRecord() {
        collected = true;
    }

}