package com.test.platformerse1;

import android.graphics.Point;
import android.view.View;

// Author: Aaron Trusty
// Last Modified: 03/20/16 by Isaiah Thacker
// Iteration 2
// The Bullet class defines objects representing player and enemy bullets within a level.

public class Bullet {
    // the ImageView associated with the bullet
    private View bulletView;
    // the bullet's location
    private Point location;
    // how much base damage the bullet deals
    private int power;
    // x and y coordinates of bullet's velocity, specified as a point
    private Point velocity;
    // time remaining before auto-despawn
    private int timeRemaining;
    // is the bullet flagged for removal? default: false
    private boolean flag = false;
    // constant duration for the bullet
    private final int duration = 50;
    //constant dimensions for bullet.
    private final Point dimensions = new Point(3, 3);

    //default constructor. Will include
    //  package from player class that will
    //      make default bullet come from the players 
    //          position plus a few pixels to make it appear
    //              that the bullet is coming from the weapon.
    public Bullet() {
        location = new Point(0, 0);
        power = 0;
        velocity = new Point(0, 0);
        timeRemaining = 0;
    }

    //overloaded constructor
    public Bullet(Point coords, int nPower, Point nVelocity) {
        location = new Point(coords);
        power = nPower;
        velocity = new Point(nVelocity);
        timeRemaining = duration;
    }

    // clone a bullet
    public Bullet(Bullet b) {
        location = new Point(b.getLocation());
        power = b.getPower();
        velocity = new Point(b.getVelocity());
        timeRemaining = b.getTimeRemaining();
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

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point nVelocity) {
        velocity.set(nVelocity.x, nVelocity.y);
    }


    public Point getLocation() {
        return location;
    }

    //set location
    public void setLocation(Point nLocation) {
        location.set(nLocation.x, nLocation.y);
    }

    public Point getDimensions() {
        return dimensions;
    }


    //toString for class
    public String toString() {
        return "The bullet is currently at position (" + location.x + "," + location.y + ").\n";
    }


}
