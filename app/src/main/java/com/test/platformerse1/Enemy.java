package com.test.platformerse1;

import android.graphics.Point;

// Author: Isaiah Thacker
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The Enemy class defines the template for all enemy entities

public class Enemy extends Entity {
    //name of sprite to be used
    private String sprite;
    // can the enemy shoot?
    private boolean shooter;

    public Enemy(Point location, int direction, Point dimensions, int defense, int speed, int strength, int maxHealth, String sprite, boolean shooter) {
        setLocation(location);
        setDirection(direction);
        setDimensions(dimensions);
        setDefense(defense);
        setSpeed(speed);
        setStrength(strength);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setSprite(sprite);
        setVelocity(new Point(0, 0));
        this.shooter = shooter;
    }

    // getter/setter auto-generated
    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public boolean isShooter() {
        return shooter;
    }

    public void setShooter(boolean shooter) {
        this.shooter = shooter;
    }

    // create a new bullet at the enemy's center
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
        Point vel = new Point(this.getDirection() * 5, 0);
        // return the new bullet
        return new Bullet(center, this.getStrength(), vel, true);
    }
}
