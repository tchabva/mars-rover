package org.northcoders.logiclayer;

import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

public class Rover {
    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position position() {
        return position;
    }

    public void position(Position position) {
        this.position = position;
    }

    // Calculates the next position of the Rover given a single Instruction
    public Position nextPosition(Instruction instruction) {
        int x = position.x();
        int y = position.y();
        CompassDirection compassDirection = position.facing();

        try {
            switch (instruction) {
                case L, R -> compassDirection = rotateCompassDirection(instruction);
                case M -> {
                    switch (compassDirection) {
                        case N, S -> y = nextPositionY(y, compassDirection);
                        case W, E -> x = nextPositionX(x, compassDirection);
                    }
                }
            }
            return new Position(x, y, compassDirection);

        } catch (NullPointerException e) {
            System.out.println("Invalid Instruction! No movement");
            return position;
        }
    }

    // Rotates the compass direction when given an Instruction of R/L and a CompassDirection
    public CompassDirection rotateCompassDirection(Instruction instruction) {
        CompassDirection compassDirection = this.position().facing();
        try {
            if (instruction == Instruction.M || instruction == null) {
                return compassDirection;
            }
            switch (compassDirection) {
                case N -> {
                    return instruction == Instruction.R ? CompassDirection.E : CompassDirection.W;
                }
                case W -> {
                    return instruction == Instruction.R ? CompassDirection.N : CompassDirection.S;
                }
                case S -> {
                    return instruction == Instruction.R ? CompassDirection.W : CompassDirection.E;
                }
                case E -> {
                    return instruction == Instruction.R ? CompassDirection.S : CompassDirection.N;
                }
                default -> {
                    return compassDirection;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("System Error!");
            return null;
        }
    }

    // Returns the nextPosition of X that is equal to or greater than 0
    private Integer nextPositionX(Integer posX, CompassDirection direction) {
        return direction.equals(CompassDirection.W) ? Math.max(0, posX - 1) : posX + 1;
    }

    // Returns the nextPosition of Y that is equal to or greater than 0
    private Integer nextPositionY(Integer posY, CompassDirection direction) {
        return direction.equals(CompassDirection.S) ? Math.max(posY - 1, 0) : posY + 1;
    }

    public CompassDirection getRotateCompassDirection(CompassDirection compassDirection, Instruction instruction) { //TODO: remove useless compassDirection param after cleaning up tests
        return rotateCompassDirection(instruction);
    }

    public Integer getNextPositionX(Integer posX, CompassDirection direction) {
        return nextPositionX(posX, direction);
    }

    public Integer getNextPositionY(Integer posY, CompassDirection direction) {
        return nextPositionY(posY, direction);
    }

    @Override
    public String toString() {
        return position.x() + " " + position.y() + " " + position.facing().name();
    }
}