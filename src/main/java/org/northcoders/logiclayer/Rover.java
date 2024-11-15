package org.northcoders.logiclayer;

import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

import java.util.function.BiFunction;
import java.util.function.Function;

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

    public Position nextPosition(Instruction instruction){
        int x = position.getX();
        int y = position.getY();
        CompassDirection compassDirection = position.getFacing();



        return new Position(x,y, compassDirection);
    }

    // Rotates the compass direction when given an Instruction of R/L and a CompassDirection
    private BiFunction<CompassDirection, Instruction, CompassDirection> rotateCompassDirection = (compassDirection, instruction) -> {

        if (compassDirection.equals(CompassDirection.N) && instruction.equals(Instruction.R)){
            return CompassDirection.E;
        }else if (compassDirection.equals(CompassDirection.E) && instruction.equals(Instruction.R)){
            return CompassDirection.S;
        }else if (compassDirection.equals(CompassDirection.S) && instruction.equals(Instruction.R)) {
            return CompassDirection.W;
        }else if (compassDirection.equals(CompassDirection.W) && instruction.equals(Instruction.R)) {
            return CompassDirection.N;
        }else if (compassDirection.equals(CompassDirection.W) && instruction.equals(Instruction.L)) {
            return CompassDirection.S;
        }else if (compassDirection.equals(CompassDirection.S) && instruction.equals(Instruction.L)) {
            return CompassDirection.E;
        }else if (compassDirection.equals(CompassDirection.E) && instruction.equals(Instruction.L)) {
            return CompassDirection.N;
        }else if (compassDirection.equals(CompassDirection.N) && instruction.equals(Instruction.L)) {
            return CompassDirection.W;
        } else  {
            return null;
        }
    };

    // TODO: Look into the Math.max() method.

    // Returns the nextPosition of X that is equal to or greater than 0
    private BiFunction<Integer, CompassDirection, Integer> nextPositionX = (posX, direction) -> {
        if (direction.equals(CompassDirection.W)){
            if((posX - 1) >= 0){
                return posX - 1;
            }else {
                return 0;
            }
        }else {
            return posX + 1;
        }
    };

    // Returns the nextPosition of Y that is equal to or greater than 0
    private BiFunction<Integer, CompassDirection, Integer> nextPositionY = (posY, direction) -> {
        if (direction.equals(CompassDirection.S)){
            if((posY - 1) >= 0){
                return posY - 1;
            }else {
                return 0;
            }
        }else {
            return posY + 1;
        }
    };

}