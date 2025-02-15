package org.northcoders.logiclayer;

import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Plateau {
    private final int x;
    private final int y;
    private final List<Rover> roverList = new ArrayList<>();

    // NOTE: will need to add 1 to each dimension if implementing a method to print out the array
    public Plateau(PlateauSize plateauSize) {
        this.x = plateauSize.x();
        this.y = plateauSize.y();
    }

    public List<Rover> getRoverList() {
        return roverList;
    }

    public boolean isPositionValid(int posX, int posY){
        return posX <= this.x && posX >= 0 && posY <= this.y && posY >= 0;
    }

    public boolean isPositionEmpty(Position nextPosition, Rover rover){
        List<Rover> roverCheckList = new ArrayList<>(roverList);
        roverCheckList.remove(rover);
        if (!roverCheckList.isEmpty()){
            for (Rover otherRover : roverCheckList){
                return nextPosition.x() != otherRover.getPosition().x() || nextPosition.y() != otherRover.getPosition().y();
            }
        }
        return true;
    }

    public BiPredicate<Integer, Integer> isPositionFree = (posX, posY) ->{
        for (Rover rover : this.roverList){
            if (posX.equals(rover.getPosition().x()) && posY.equals(rover.getPosition().y())){
                return false;
            }
        }
        return true;
    };

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
