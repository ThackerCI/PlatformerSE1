package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// The Environment class defines objects which contain all data on the current gameplay environment
// (level layout, character locations, and so forth.)

import java.util.ArrayList;
import java.util.List;


public class Environment {
    // blocks in the environment
    private final List<Block> blocks;
    // non-goal records in the environment
    private final List<Record> records;
    // bullets
    private final List<Bullet> bullets;
    // the goal record
    private Record goal;
    // the enemies
    private final List<Enemy> enemies;

    // The instance of Environment (Singleton design pattern)
    private static Environment instance;



    private Environment() {
        blocks = new ArrayList<>();
        records = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
    }


    // getInstance for singleton design pattern
    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    // addBullet(b) adds bullet b to the current environment.
    public void addBullet(Bullet b) {
        this.bullets.add(b);
    }

    // setGoal(R) sets the environment's goal record to record R
    public void setGoal(Record R){
        this.goal = R;
    }

    // getGoal() returns the goal record in the current environment.
    public Record getGoal() {
        return this.goal;
    }

    // getBullets() returns the list of bullets in the current environment.
    public List<Bullet> getBullets() {
        return this.bullets;
    }

    // getRecords() returns the list of records in the current environment.
    public List<Record> getRecords() {
        return this.records;
    }

    // getBlocks() returns the list of blocks in the current environment.
    public List<Block> getBlocks() {
        return this.blocks;
    }

    // getEnemies() returns the list of blocks in the current environment.
    public List<Enemy> getEnemies() {
        return this.enemies;
    }
}