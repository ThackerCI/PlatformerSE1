package com.test.platformerse1;

// Author: Olutayo Elelu
// Last Modified: 03/20/16 by Isaiah Thacker
// Iteration 2
// The record class defines the records the player may collect within the game environment.

//- Location (Point): Defined by our game mechanics. For now, a point with x and y coordinate. Functions include getLocation and setLocation.
//- Collect(Boolean): Has the record been collected? Functions include isCollected

import android.graphics.Point;

public class Record {
    // the location of the record
    private Point location;
    // has the record been collected?
    private boolean collected;
    // the constant dimensions of all records
    private final Point dimensions = new Point(20, 20);

    // Point is given in "grid cell" format for convenience.
    public Record(Point p, boolean c) {
        location = p;
        p.x *= 20;
        p.y *= 20;
        collected = c;
    }

    // clone a record
    public Record(Record R) {
        this.location = new Point(R.location);
        this.collected = R.collected;
    }

    public void setLocation(Point p) {
        location.x = p.x;
        location.y = p.y;
    }

    // getLocation()
    // returns the x and y coordinates of the record, with the top-left corner as the origin.
    public Point getLocation() {
        return location;
    }

    // isCollected()
    // whether or not the record has been collected
    public boolean isCollected() {
        return collected;
    }

    public void collectRecord() {
        collected = true;
    }

    // getDimensions()
    // returns the size of the record's sprite, hardcoded into the class
    public Point getDimensions() {
        return dimensions;
    }

}