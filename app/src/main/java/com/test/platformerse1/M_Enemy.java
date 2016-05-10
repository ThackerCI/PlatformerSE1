package com.test.platformerse1;

import android.graphics.Point;

/**
 * @author Isaiah Thacker
 *         Last Modified: 4/11/16 by Isaiah Thacker
 *         Iteration 3
 *         The M_Enemy class defines the template for all enemy entities
 */

public class M_Enemy extends M_Entity {
    // what type of enemy is it? (0 = "crawler", 1 = "turret")
    private int enemyType;
    // how long between enemy shots?
    private int shotInterval;
    // how long until next shot is possible?
    private int shotCooldown;

    public M_Enemy(Point location, int direction, Point dimensions, int defense, int speed, int strength, int maxHealth, int sprite, int enemyType) {
        setLocation(new Point(location.x * 20, location.y * 20));
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

    // getters/setters auto-generated

    public int getEnemyType() {
        return enemyType;
    }

    public int getShotInterval() {
        return shotInterval;
    }

    public void setShotInterval(int shotInterval) {
        this.shotInterval = shotInterval;
    }

    public int getShotCooldown() {
        return shotCooldown;
    }

    public void setShotCooldown(int shotCooldown) {
        this.shotCooldown = shotCooldown;
    }

    public void decrementShotCooldown() {
        shotCooldown = shotCooldown - 1;
    }

    // create a new bullet at the enemy's center
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
        // set the enemy's shot cooldown to its shot interval.
        setShotCooldown(getShotInterval());
        // return the new bullet
        return new M_Bullet(center, this.getStrength(), vel, true);
    }
}
