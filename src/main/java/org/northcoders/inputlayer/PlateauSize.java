package org.northcoders.inputlayer;

public record PlateauSize(int x, int y) {
    @Override
    public String toString() {
        return String.format("Plateau Coordinates: X = %d, Y = %d", x, y);
    }
}
