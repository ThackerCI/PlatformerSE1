package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// The M_Environment class defines objects which contain all data on the current gameplay environment
// (level layout, character locations, and so forth.)

import java.util.ArrayList;
import java.util.List;


public class M_Environment {
    // blocks in the environment
    private final List<M_Block> blocks;
    // non-goal records in the environment
    private final List<M_Record> records;
    // bullets
    private final List<M_Bullet> bullets;
    // the goal record
    private M_Record goal;
    // the enemies
    private final List<M_Enemy> enemies;

    // The instance of M_Environment (Singleton design pattern)
    private static M_Environment instance;



    private M_Environment() {
        blocks = new ArrayList<>();
        records = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
    }


    // getInstance for singleton design pattern
    public static M_Environment getInstance() {
        if (instance == null) {
            instance = new M_Environment();
        }
        return instance;
    }

    // addBullet(b) adds bullet b to the current environment.
    public void addBullet(M_Bullet b) {
        this.bullets.add(b);
    }

    // setGoal(R) sets the environment's goal record to record R
    public void setGoal(M_Record R){
        this.goal = R;
    }

    // getGoal() returns the goal record in the current environment.
    public M_Record getGoal() {
        return this.goal;
    }

    // getBullets() returns the list of bullets in the current environment.
    public List<M_Bullet> getBullets() {
        return this.bullets;
    }

    // getRecords() returns the list of records in the current environment.
    public List<M_Record> getRecords() {
        return this.records;
    }

    // getBlocks() returns the list of blocks in the current environment.
    public List<M_Block> getBlocks() {
        return this.blocks;
    }

    // getEnemies() returns the list of blocks in the current environment.
    public List<M_Enemy> getEnemies() {
        return this.enemies;
    }
}