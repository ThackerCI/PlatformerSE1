package com.test.platformerse1;

// Author: Olutayo Elelu
// Last Modified: 03/31/16 by John C.  Hale
// Added music attribute to record
// Iteration 3
//03/31/16 Modifications: Added Music file variable
//Iteration 2
// The record class defines the records the player may collect within the game environment.
//- Location (Point): Defined by our game mechanics. For now, a point with x and y coordinate. Functions include getLocation and setLocation.
//- Collect(Boolean): Has the record been collected? Functions include isCollected

import android.graphics.Point;
import android.media.MediaPlayer;

import java.io.File;

public class Record {
    // the location of the record
    private Point location;
    // has the record been collected?
    private boolean collected;
    // the constant dimensions of all records
    private final Point dimensions = new Point(30, 30);
    // the music file unlocked with record
    private int music;

    // Point is given in "grid cell" format for convenience.
    public Record(Point p, int m, boolean c) {
        location = p;
        p.x *= 30;
        p.y *= 30;
        collected = c;
        music = m;
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

    public int getMusic() {
        return this.music;
    }
    public void setMusic(int m) {
        this.music = m;
    }
}