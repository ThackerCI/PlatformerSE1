package com.test.platformerse1;

import android.graphics.Point;

// Author: Isaiah Thacker
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The Enemy class defines the template for all enemy entities

public class Enemy extends Entity {
    //location of the enemy
    private Point location;
    // direction is -1 for left, 1 for right
    private int direction;
    //dimensions of the enemy
    private Point dimensions;
    //Scalar multiple effecting damage taken
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
    //name of sprite to be used
    private String sprite;

    public Enemy(Point location, int direction, Point dimensions, int defense, int speed, int strength, int maxHealth, String sprite){
        this.location = new Point(location);
        this.direction = direction;
        this.dimensions = dimensions;
        this.defense = defense;
        this.speed = speed;
        this.strength = strength;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.sprite = sprite;
        this.velocity = new Point(0,0);
    }

    // getter/setter auto-generated
    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
