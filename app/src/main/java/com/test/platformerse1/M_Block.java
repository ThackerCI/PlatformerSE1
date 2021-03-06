package com.test.platformerse1;

import android.graphics.Point;

/**
 * @author Aaron Trusty
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Iteration 4
 *         The M_Block class defines objects which represent impassible walls and floors in a level.
 */

class M_Block extends M_WorldObject {
    // BLOCK_DIM_SCALAR is the dimensions of the smallest allowed block
    private static final Point BLOCK_DIM_SCALAR = new Point(20, 20);

    //constructor
    public M_Block(Point nLocation, Point nDimension) {
        // multiply the "grid format" location by the dimensions of the smallest block
        setLocation(new Point(nLocation.x * BLOCK_DIM_SCALAR.x, nLocation.y * BLOCK_DIM_SCALAR.y));
        setDimensions(new Point(nDimension));
        // blocks don't move
        setVelocity(new Point(0, 0));
        // set the block sprite
        setSprite(R.drawable.block);
    }

}
