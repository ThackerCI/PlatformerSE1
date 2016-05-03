package com.test.platformerse1;

// Author: John C. Hale
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// The M_Character class defines the object which will represent the data on the player's
// controlled character.

// Help from Kevin Glass and Dr. Jerry Perez's Space Invader's project.

import android.graphics.Point;

public class M_Character extends M_Entity {

    //Cooldown time after each shot
    private int shotCoolDown;
    //max cooldown time after each shot
    private final int maxShotCoolDown = 10;
    //Time the character began his jump
    private double initialJump;
    //Time character will be moving vertically in case of jump
    private int jumpTime;
    //maximum time the character will move vertically in case of jump
    private final int maxJumpTime = 16;
    //time the character is immune to damage
    private int immunity;

    // singleton instance of M_Character
    private static M_Character instance;

    // getInstance() for singleton character
    public static M_Character getInstance() {
        // if the character has not been instantiated, do so
        if (instance == null) {
            instance = new M_Character(new Point(0, 0), new Point(20, 20), 3, 3, 3, 5);
        }
        return instance;
    }

    /**
     * Initializes new M_Character
     */
    private M_Character(Point location, Point dimensions, int strength,
                        int speed, int defense, int maxHealth) {
        setLocation(new Point(location.x * 20, location.y * 20));
        setDimensions(new Point(dimensions));
        setStrength(strength);
        setSpeed(speed);
        setDefense(defense);
        setMaxHealth(maxHealth);
        setVelocity(new Point(0, 0));
        jumpTime = 0;
        immunity = 0;
        int[] firstPowerUp = {R.raw.allineed,3,3,6,0};
        M_CollectedRecords.getInstance().addPowerUp(firstPowerUp);
    }

    /**********************
     * Get/Set Functions For Various Variables
     ************************************/

    public int getShotCoolDown() {
        return shotCoolDown;
    }

    public void setShotCoolDown(int shotCoolDown) {
        this.shotCoolDown = shotCoolDown;
    }

    public int getMaxShotCoolDown() {
        return maxShotCoolDown;
    }

    public double getInitialJump() {
        return initialJump;
    }

    public void setInitialJump(double initialJump) {
        this.initialJump = initialJump;
    }

    public int getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public int getMaxJumpTime() {
        return maxJumpTime;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public int getImmunity() {
        return immunity;
    }

    // decrementImmunity() decrements this.immunity by 1
    public void decrementImmunity() {
        this.immunity = this.immunity - 1;
    }

    public void setDefaultStats() {
        this.setImmunity(0);
        this.setSpeed(3);
        this.setStrength(3);
        this.setDefense(3);
    }

}