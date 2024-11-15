package org.northcoders.logiclayer;

import org.northcoders.inputlayer.PlateauSize;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Plateau {
    private int x;
    private int y;
    private final List<Rover> roverList = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.x = plateauSize.getX()+1;
        this.y = plateauSize.getY()+1;
    }

    public List<Rover> getRoverList() {
        return roverList;
    }

    public BiPredicate<Integer, Integer> isValidPosition = (posX, posY) ->{
        return posX <= x && posX >= 0 && posY <= y && posY >= 0;
    };

    public BiPredicate<Integer, Integer> isPositionFree = (posX, posY) ->{
        for (Rover rover : roverList){
            int roverPosX = rover.getPosition().getX();
            int roverPosY = rover.getPosition().getY();

            return roverPosX != posX || roverPosY != posY;
        }
        return false;
    };

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
