package com.test.platformerse1;

import android.graphics.Point;
import android.view.View;

/**
 * @author Aaron Trusty
 *         Last Modified: 4/25/16 by Isaiah Thacker
 *         Iteration 4
 *         The M_Bullet class defines objects representing player and enemy bullets within a level.
 */

public class M_Bullet extends M_WorldObject {
    // the ImageView associated with the bullet
    private View bulletView;
    // how much base damage the bullet deals
    private int power;
    // time remaining before auto-despawn
    private int timeRemaining;
    // is the bullet flagged for removal? default: false
    private boolean flag = false;
    // was the bullet fired by an enemy?
    private boolean enemyBullet = false;
    // constant duration for the bullet
    private final int duration = 50;
    // constant dimensions for bullet.
    private static final Point BULLET_DIMS = new Point(3, 3);

    //constructor
    public M_Bullet(Point coords, int nPower, Point nVelocity, boolean nEnemyBullet) {
        setLocation(new Point(coords));
        setDimensions(BULLET_DIMS);
        power = nPower;
        setVelocity(new Point(nVelocity));
        timeRemaining = duration;
        enemyBullet = nEnemyBullet;
        // set the sprite based on whose side the bullet is on.
        setSprite((enemyBullet) ? R.mipmap.enem_bullet : R.mipmap.user_bullet);
    }

    // clone a bullet
    public M_Bullet(M_Bullet b) {
        setLocation(new Point(b.getLocation()));
        power = b.getPower();
        setVelocity(new Point(b.getVelocity()));
        timeRemaining = b.getTimeRemaining();
        enemyBullet = b.isEnemyBullet();
        setSprite(b.getSprite());
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public View getBulletView() {
        return bulletView;
    }

    public void setBulletView(View bulletView) {
        this.bulletView = bulletView;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getDuration() {
        return duration;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int nPower) {
        power = nPower;
    }

    // auto-generated getters and setter

    public boolean isEnemyBullet() {
        return enemyBullet;
    }

    public void setEnemyBullet(boolean enemyBullet) {
        this.enemyBullet = enemyBullet;
    }

}
