package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 2
// The M_Level class defines objects which record data on the various levels that can be played in
// the game (list of MBlocks in the level, MRecords in the level, and character starting point.)

import android.graphics.Point;

import java.util.List;

public class M_Level {
    // list of MBlocks in the level
    private final List<M_Block> MBlocks;
    // list of MRecords in the level (minus the goal)
    private final List<M_Record> MRecords;
    // the level's goal record
    private final M_Record goal;
    // list of enemies in the level
    private final List<M_Enemy> enemies;
    // the level's starting point
    private final Point startingPoint;

    // constructor
    public M_Level(List<M_Block> b, List<M_Record> r, M_Record g, List<M_Enemy> e, Point s) {
        this.MBlocks = b;
        this.MRecords = r;
        this.goal = g;
        this.enemies = e;
        this.startingPoint = s;
    }

    public Point getStartingPoint() {
        return this.startingPoint;
    }

    public List<M_Record> getMRecords() {
        return this.MRecords;
    }

    public List<M_Block> getMBlocks() {
        return this.MBlocks;
    }

    public M_Record getGoal() {
        return this.goal;
    }

    public List<M_Enemy> getEnemies() {
        return this.enemies;
    }

}
