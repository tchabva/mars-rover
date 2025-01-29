package org.northcoders.inputlayer;

public record PlateauSize(int x, int y) {
    @Override
    public String toString() {
        return String.format("You have created a Plateau with dimensions: X = %d, Y = %d", x, y);
    }
}
