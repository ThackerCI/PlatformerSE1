package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The Entity class is a superclass of Character and Enemy, which describes common attributes and
// functionality such as location, move, and dimensions

import android.graphics.Point;

public class Entity {
    // The following are copied from the former version of the Character class
    //location of the entity
    private Point location;
    // direction is -1 for left, 1 for right
    private int direction;
    //dimensions of the entity
    private Point dimensions;
    //Scalar multiple effecting "damage" taken
    private int defense;
    //Max horizontal Velocity of the entity
    private int speed;
    //velocity of entity
    private Point velocity;
    //Scalar multiple affecting entity attack
    private int strength;
    //Current health of entity
    private int health;
    //Max health of entity
    private int maxHealth;


    public void setVelocityX(int x) {
        this.velocity.x = x;
    }

    public void setVelocityY(int y) {
        this.velocity.y = y;
    }


    // "getters" and "setters" auto-generated


    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        if (this.location == null) {
            this.location = new Point(location);
        }
        else {
            this.location.x = location.x;
            this.location.y = location.y;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Point getDimensions() {
        return dimensions;
    }

    public void setDimensions(Point dimensions) {
        if (this.dimensions == null) {
            this.dimensions = new Point(dimensions);
        }
        else {
            this.dimensions.x = dimensions.x;
            this.dimensions.y = dimensions.y;
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
