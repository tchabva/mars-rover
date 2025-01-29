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

    // Calculates the next position of the Rover given a single Instruction
    public Position nextPosition(Instruction instruction){
        int x = position.x();
        int y = position.y();
        CompassDirection compassDirection = position.facing();

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
            return position;
        }
    }

    // Rotates the compass direction when given an Instruction of R/L and a CompassDirection
    private final BiFunction<CompassDirection, Instruction, CompassDirection> rotateCompassDirection = (compassDirection, instruction) -> {
        try {

            if (instruction == Instruction.M || instruction == null){
                return compassDirection;
            }
            switch (compassDirection){
                case N -> {
                    return instruction == Instruction.R? CompassDirection.E : CompassDirection.W;
                }
                case W -> {
                    return instruction == Instruction.R? CompassDirection.N : CompassDirection.S;
                }
                case S -> {
                    return instruction == Instruction.R? CompassDirection.W : CompassDirection.E;
                }
                case E -> {
                    return instruction == Instruction.R? CompassDirection.S : CompassDirection.N;
                }
                default -> {
                    return compassDirection;
                }
            }

        } catch (NullPointerException e){
            System.out.println("System Error!");
            return null;
        }
    };

    // Returns the nextPosition of X that is equal to or greater than 0
    private final BiFunction<Integer, CompassDirection, Integer> nextPositionX = (posX, direction) -> {
        if (direction.equals(CompassDirection.W)){
            return Math.max((posX - 1), 0); // .max returns the larger number of two arguments
        }else {
            return posX + 1;
        }
    };

    // Returns the nextPosition of Y that is equal to or greater than 0
    private final BiFunction<Integer, CompassDirection, Integer> nextPositionY = (posY, direction) -> {
        if (direction.equals(CompassDirection.S)){
            return Math.max((posY - 1), 0); // .max returns the larger number of two arguments
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

    @Override
    public String toString() {
        return position.x() + " " + position.y() + " " + position.facing().name();
    }
}