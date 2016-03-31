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
}
