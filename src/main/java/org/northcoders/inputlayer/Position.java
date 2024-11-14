package org.northcoders.inputlayer;

public class Position  {
    private int x;
    private int y;
    private CompassDirection facing;

    public Position(int x,  int y,CompassDirection facing) {
        this.facing = facing;
        this.y = y;
        this.x = x;
    }

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
