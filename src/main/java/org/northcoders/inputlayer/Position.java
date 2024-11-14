package org.northcoders.inputlayer;

import org.northcoders.inputlayer.inputparsers.Coordindates;

public class Position implements Coordindates {
    private int x;
    private int y;
    private CompassDirection facing;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CompassDirection getFacing() {
        return facing;
    }

    public void setFacing(CompassDirection facing) {
        this.facing = facing;
    }
}
