package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/28/16 by Isaiah Thacker
// Iteration 3
// The M_Environment class defines objects which contain all data on the current gameplay environment
// (level layout, character locations, and so forth.)

import java.util.ArrayList;
import java.util.List;


public class M_Environment {
    // id of the currently active level
    private int currentLevel;
    // blocks in the environment
    private final List<M_Block> blocks;
    // non-goal records in the environment
    private final List<M_Record> records;
    // the goal record
    private M_Record goal;
    // bullets
    private final List<M_Bullet> bullets;
    // popup triggers
    private final List<M_PopupTrigger> popups;
    // the enemies
    private final List<M_Enemy> enemies;
    // whether or not the world is paused
    private boolean paused;
    // whether the popup is displayed
    private boolean showingPopup = false;
    // title of the popup box
    private String popupTitle = "";
    // text in the popup box
    private String popupText = "";

    // The instance of M_Environment (Singleton design pattern)
    private static M_Environment instance;

    private M_Environment() {
        blocks = new ArrayList<>();
        records = new ArrayList<>();
        bullets = new ArrayList<>();
        popups = new ArrayList<>();
        enemies = new ArrayList<>();

        paused = true;
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
    public void setGoal(M_Record R) {
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

    // getEnemies() returns the list of enemies in the current environment.
    public List<M_Enemy> getEnemies() {
        return this.enemies;
    }

    // getPopups() returns the list of popup triggers in the current environment.
    public List<M_PopupTrigger> getPopups() {
        return this.popups;
    }

    // is the game paused?
    public boolean isPaused() {
        return paused;
    }

    // pause the game
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    // the following getters and setters are auto-generated

    public boolean isShowingPopup() {
        return showingPopup;
    }

    public void setShowingPopup(boolean showingPopup) {
        this.showingPopup = showingPopup;
    }

    public String getPopupTitle() {
        return popupTitle;
    }

    public void setPopupTitle(String popupTitle) {
        this.popupTitle = popupTitle;
    }

    public String getPopupText() {
        return popupText;
    }

    public void setPopupText(String popupText) {
        this.popupText = popupText;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}