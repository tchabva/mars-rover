package org.northcoders.inputlayer;

import org.northcoders.inputlayer.inputparsers.Coordindates;

public class PlateauSize implements Coordindates {
    private final int x;
    private final int y;

    public PlateauSize(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
