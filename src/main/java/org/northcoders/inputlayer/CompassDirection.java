package org.northcoders.inputlayer;

public enum CompassDirection {
    N("North"),
    W("West"),
    S("South"),
    E("East");

    private final String direction;

    CompassDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
