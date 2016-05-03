package com.test.platformerse1;

// Author: Olutayo Elelu
// Last Modified: 04/30/16 by Isaiah Thacker
// Merged John's edits
// Iteration 4
//04/16/16 Modifications: Added Music and power-ups associated with the record
//Iteration 2
// The record class defines the records the player may collect within the game environment.
//- Location (Point): Defined by our game mechanics. For now, a point with x and y coordinate. Functions include getLocation and setLocation.
//- Collect(Boolean): Has the record been collected? Functions include isCollected

import android.graphics.Point;
import android.media.MediaPlayer;

import java.io.File;

public class M_Record extends M_WorldObject {
    // has the record been collected?
    private boolean collected;
    // the constant dimensions of all records
    private static final Point RECORD_DIMS = new Point(20, 20);
    //
    private int music;
    //
    private int strength;
    //
    private int immunity;
    //
    private int defense;
    //
    private int speed;

    // Point is given in "grid cell" format for convenience.

    public M_Record(Point p, boolean c,  int m, int strength, int defense,
                    int speed, int immunity) {
        // multiply the coordinates of p by 20 to get the actual point
        p.x *= 20;
        p.y *= 20;
        // set p as the location
        setLocation(p);
        collected = c;
        music = m;
        this.strength = strength;
        this.immunity = immunity;
        this.defense = defense;
        this.speed = speed;

        setDimensions(RECORD_DIMS);
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

    public int[] getPowerUps() {
        return new int[]{this.music, this.strength, this.defense, this.speed, this.immunity};
    }
}