package com.test.platformerse1;

import android.graphics.Point;

// Author: Aaron Trusty
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 2
// The Block class defines objects which represent impassible walls and floors in a level.

public class Block {
    // the location of the block
    private Point location;
    // the dimensions of the block
    private Point dimensions;

    //constructor
    public Block(Point nLocation, Point nDimension) {
        location = new Point(nLocation.x * 20, nLocation.y * 20);
        dimensions = new Point(nDimension);
    }

    //----gets and sets----
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point nLocation) {
        location.set(nLocation.x, nLocation.y);
    }

    public Point getDimensions() {
        return dimensions;
    }

    public void setDimensions(Point nDimensions) {
        dimensions.set(nDimensions.x, nDimensions.y);
    }

}
