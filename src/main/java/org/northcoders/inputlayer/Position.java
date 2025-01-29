package org.northcoders.inputlayer;

public record Position(int x, int y, CompassDirection facing)  {
    @Override
    public String toString() {
        return String.format("Position: (%d, %d) Direction: %s", x, y, facing.getDirection());
    }
}
