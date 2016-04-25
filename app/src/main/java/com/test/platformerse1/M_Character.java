package com.test.platformerse1;

// Author: John Hale
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// The M_Character class defines the object which will represent the data on the player's
// controlled character.

// Help from Kevin Glass and Dr. Jerry Perez's Space Invader's project.

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;

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
    }

    /**
     * Resets M_Character's health, direction, jumpTime, and velocity
     */
    public void reset() {
        this.setJumpTime(0);
        this.setHealth(this.getMaxHealth());
        this.setDirection(1);
        this.setVelocity(new Point(0, 0));
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

    /**************************
     * Gameplay Functions
     *********************************************************/

    // c.horizontalMove(direction) sets character c's horizontal velocity to direction*c.speed,
    // and sets c.direction to direction.
    public void horizontalMove(int direction) {
        //update the location of the entity based on move speeds
        this.setVelocityX(direction * this.getSpeed());
        this.setDirection(direction);
    }


    // if the character can jump, set his vertical velocity to the negative of the gravity and
    // reset the jump timer to maxJumpTime
    public void jump(boolean canJump) {
        if (canJump) {

            this.setVelocityY(-C_EnvironmentController.GRAVITY);
            this.setJumpTime(this.maxJumpTime);
        }
    }

    // create a new bullet at the character's center
    public M_Bullet shoot() {
        Point leftCorner = this.getLocation();
        // get the center of the character
        int x = leftCorner.x;
        int y = leftCorner.y;
        Point dim = this.getDimensions();
        int h = dim.y;
        int w = dim.x;
        h = h / 2;
        w = w / 2;
        Point center = new Point(x + w, y + h);
        // get the velocity for the bullet
        Point vel = new Point(this.getDirection() * 3, 0);
        // return the new bullet
        return new M_Bullet(center, this.getStrength(), vel, false);
    }

    // damage(dealt) adds dealt seconds to the environment timer
    public void damage(int dealt) {
        Log.d("Damage:", Integer.toString(dealt));
    }
}