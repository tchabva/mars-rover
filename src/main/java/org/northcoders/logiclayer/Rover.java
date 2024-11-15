package org.northcoders.logiclayer;

import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

import java.util.function.BiFunction;

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

        try {
            switch (instruction){
                case L, R -> compassDirection = rotateCompassDirection.apply(compassDirection,instruction);
                case M -> {
                    switch (compassDirection){
                        case N, S -> y = nextPositionY.apply(y, compassDirection);
                        case W, E -> x = nextPositionX.apply(x, compassDirection);
                    }
                }
            }
            return new Position(x,y, compassDirection);

        } catch (NullPointerException e) {
            System.out.println("Invalid Instruction! No movement");
            return new Position(x,y, compassDirection);
        }
    }

    // Rotates the compass direction when given an Instruction of R/L and a CompassDirection
    private final BiFunction<CompassDirection, Instruction, CompassDirection> rotateCompassDirection = (compassDirection, instruction) -> {
        try {
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
            } else {
                return compassDirection;
            }
        } catch (NullPointerException e){

            if (compassDirection == null){
                System.out.println("System Error!");
            };
            return compassDirection;
        }
    };

    // TODO: Look into the Math.max() method.

    // Returns the nextPosition of X that is equal to or greater than 0
    private final BiFunction<Integer, CompassDirection, Integer> nextPositionX = (posX, direction) -> {
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
    private final BiFunction<Integer, CompassDirection, Integer> nextPositionY = (posY, direction) -> {
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

    public BiFunction<CompassDirection, Instruction, CompassDirection> getRotateCompassDirection() {
        return rotateCompassDirection;
    }

    public BiFunction<Integer, CompassDirection, Integer> getNextPositionX() {
        return nextPositionX;
    }

    public BiFunction<Integer, CompassDirection, Integer> getNextPositionY() {
        return nextPositionY;
    }
}