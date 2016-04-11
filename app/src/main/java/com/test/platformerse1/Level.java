package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 2
// The Level class defines objects which record data on the various levels that can be played in
// the game (list of blocks in the level, records in the level, and character starting point.)

import android.graphics.Point;

import java.util.List;

public class Level {
    // list of blocks in the level
    private final List<Block> blocks;
    // list of records in the level (minus the goal)
    private final List<Record> records;
    // the level's goal record
    private final Record goal;
    // list of enemies in the level
    private final List<Enemy> enemies;
    // the level's starting point
    private final Point startingPoint;

    // constructor
    public Level(List<Block> b, List<Record> r, Record g, List<Enemy> e, Point s) {
        this.blocks = b;
        this.records = r;
        this.goal = g;
        this.enemies = e;
        this.startingPoint = s;
    }

    public Point getStartingPoint() {
        return this.startingPoint;
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public Record getGoal() {
        return this.goal;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

}
