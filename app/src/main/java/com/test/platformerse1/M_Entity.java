package com.test.platformerse1;

/**
 * @author Isaiah Thacker
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Iteration 4
 *         The M_Entity class is a superclass of M_Character and M_Enemy, which describes common attributes and
 *         functionality such as location, move, and dimensions
 */

class M_Entity extends M_WorldObject {
    // The following are copied from the former version of the M_Character class
    // direction is -1 for left, 1 for right
    private int direction;
    //Scalar multiple effecting "damage" taken
    private int defense;
    //Max horizontal Velocity of the entity
    private int speed;
    //Scalar multiple affecting entity attack
    private int strength;
    //Current health of entity
    private int health;
    //Max health of entity
    private int maxHealth;


    // "getters" and "setters" auto-generated


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
