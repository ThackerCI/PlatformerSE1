package com.test.platformerse1;

import android.graphics.Point;
import android.util.Log;
import android.view.View;

// Author: Isaiah Thacker
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The Enemy class defines the template for all enemy entities

public class Enemy extends Entity {
    private View enemyView;
    //name of sprite to be used
    private int sprite;
    // what type of enemy is it? (0 = "crawler", 1 = "turret")
    private int enemyType;

    public Enemy(Point location, int direction, Point dimensions, int defense, int speed, int strength, int maxHealth, int sprite, int enemyType) {
        setLocation(new Point(location.x * 30, location.y * 30));
        setDirection(direction);
        setDimensions(dimensions);
        setDefense(defense);
        setSpeed(speed);
        setStrength(strength);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setSprite(sprite);
        setVelocity(new Point(0, 0));
        this.enemyType = enemyType;
    }

    // getter/setter auto-generated

    public View getEnemyView() {
        return enemyView;
    }

    public void setEnemyView(View enemyView) {
        this.enemyView = enemyView;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public int getEnemyType(){
        return enemyType;
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
