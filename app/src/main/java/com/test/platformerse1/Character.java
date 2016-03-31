package com.test.platformerse1;

// Author: John Hale
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The Character class defines the object which will represent the data on the player's
// controlled character.

// Help from Kevin Glass and Dr. Jerry Perez's Space Invader's project.

import android.graphics.Point;

public class Character extends Entity {

    //Cooldown time after each shot
    private int shotCoolDown;
    //max cooldown time after each shot
    private final int maxShotCoolDown = 10;
    //location of the character
    private Point location;
    //Time the character began his jump
    private double initialJump;
    //Time character will be moving vertically in case of jump
    private int jumpTime;
    //maximum time the character will move vertically in case of jump
    private final int maxJumpTime = 24;
    // direction is -1 for left, 1 for right
    private int direction;
    //dimensions of the character
    private Point dimensions;
    //Scalar multiple effecting "damage" taken
    private int defense;
    //Max horizontal Velocity of the character = speed
    private int speed;
    //velocity of character
    private Point velocity;
    //Scalar multiple affecting character attack
    private int strength;
    //Current health of character
    private int health;
    //Max health of character
    private int maxHealth;

    public Character() {
        location = new Point(0, 0);
        dimensions = new Point(0, 0);
        velocity = new Point(0, 0);
        direction = 1;
    }

    /**
     * Initializes new Character
     */
    public Character(Point location, Point dimensions, int strength,
                     int speed, int defense, int maxHealth) {
        this.location = new Point(location.x * 30, location.y * 30);
        this.dimensions = new Point(dimensions);
        this.setStrength(strength);
        this.setSpeed(speed);
        this.setDefense(defense);
        this.setMaxHealth(maxHealth);
        this.setVelocity(new Point(0, 0));
        this.jumpTime = 0;
    }

    /**
     * Resets Character's health, direction, jumpTime, and velocity
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

            this.setVelocityY(-Environment.GRAVITY);
            this.setJumpTime(this.maxJumpTime);
        }
    }

    // create a new bullet at the character's center
    public Bullet shoot() {
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
        Point vel = new Point(this.direction * 5, 0);
        // return the new bullet
        return new Bullet(center, this.getStrength(), vel, false);
    }
}