package com.test.platformerse1;

import android.graphics.Point;
import android.widget.ImageView;

/**
 * @author Isaiah Thacker
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Iteration 4
 *         The M_WorldObject class defines a superclass for all objects that can appear in the game
 *         environment, with attributes location, velocity, dimensions, and imageView.
 */

class M_WorldObject {
    // location defines the x- and y-coordinates of the top-left corner of the world object.
    private Point location;
    // dimensions defines the x- and y-dimensions of the world object.
    private Point dimensions;
    // velocity defines the x- and y-components of the world object's velocity within the environment.
    private Point velocity;
    // imageView defines the imageView that will be used to display the object.
    private ImageView imageView;
    // sprite defines the sprite that will be used to display the object. This can be null.
    private int sprite;

    // Getters are auto-generated. Setters for points are self-made. All other setters are
    // auto-generated.

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point newLocation) {
        if (this.location == null) {
            this.location = new Point(newLocation);
        } else {
            this.location.x = newLocation.x;
            this.location.y = newLocation.y;
        }
    }

    public Point getDimensions() {
        return dimensions;
    }

    public void setDimensions(Point newDimensions) {
        if (this.dimensions == null) {
            this.dimensions = new Point(newDimensions);
        } else {
            this.dimensions.x = newDimensions.x;
            this.dimensions.y = newDimensions.y;
        }
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point newVelocity) {
        if (this.velocity == null) {
            this.velocity = new Point(newVelocity);
        } else {
            this.velocity.x = newVelocity.x;
            this.velocity.y = newVelocity.y;
        }
    }

    public void setVelocityX(int x) {
        this.velocity.x = x;
    }

    public void setVelocityY(int y) {
        this.velocity.y = y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }
}
