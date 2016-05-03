package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/28/16 by Isaiah Thacker
// Iteration 4
// The M_Level class defines objects which record data on the various levels that can be played in
// the game (list of blocks in the level, records in the level, character starting point, etc.)

import android.graphics.Point;

import java.util.List;

public class M_Level {
    private final int id;
    // list of MBlocks in the level
    private final List<M_Block> MBlocks;
    // list of MRecords in the level (minus the goal)
    private final List<M_Record> MRecords;
    // the level's goal record
    private final M_Record goal;
    // list of enemies in the level
    private final List<M_Enemy> enemies;
    // list of popup triggers in the level
    private final List<M_PopupTrigger> popups;
    // the level's starting point
    private final Point startingPoint;
    private final int backgroundMusic;

    // constructor
    public M_Level(int id, List<M_Block> b, List<M_Record> r, M_Record g, List<M_Enemy> e,
                   int m, List<M_PopupTrigger> p, Point s) {
        this.id = id;
        this.MBlocks = b;
        this.MRecords = r;
        this.goal = g;
        this.enemies = e;
        this.popups = p;
        this.startingPoint = s;
        this.backgroundMusic = m;
    }

    public int getBackgroundMusic() {
        return this.backgroundMusic;
    }

    public int getId() { return this.id; }

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

    public List<M_PopupTrigger> getPopups() {
        return this.popups;
    }

}