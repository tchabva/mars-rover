package org.northcoders.logiclayer;

import org.northcoders.inputlayer.Position;

public class Rover {
    private Position position;


    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
