package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is facing North and given an instruction")
    void testRotateFromNorth(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.N));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.L);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.R);

        // Assert
        assertAll("Returns the correct rotation for a North compass direction input and instruction",
                () -> assertEquals(CompassDirection.W, resultOne),
                () -> assertEquals(CompassDirection.E, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is facing South and given an instruction")
    void testRotateFromSouth(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.S));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.L);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.R);

        // Assert
        assertAll("Returns the correct rotation for a South compass direction input and instruction",
                () -> assertEquals(CompassDirection.E, resultOne),
                () -> assertEquals(CompassDirection.W, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is facing East and given an instruction")
    void testRotateFromEast(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.E));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.L);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.R);

        // Assert
        assertAll("Returns the correct rotation for an East compass direction input and instruction",
                () -> assertEquals(CompassDirection.N, resultOne),
                () -> assertEquals(CompassDirection.S, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is facing West and given an instruction")
    void testRotateFromWest(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.W));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.L);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.R);

        // Assert
        assertAll("Returns the correct rotation for a West compass direction input and instruction",
                () -> assertEquals(CompassDirection.S, resultOne),
                () -> assertEquals(CompassDirection.N, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is given an the Move instruction")
    void testRotateWithMoveInstruction(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.N));
        Rover roverTwo = new Rover(new Position(1,0, CompassDirection.E));
        Rover roverThree = new Rover(new Position(2,0, CompassDirection.S));
        Rover roverFour = new Rover(new Position(3,0, CompassDirection.W));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(CompassDirection.N, Instruction.M);
        CompassDirection resultTwo = roverTwo.getRotateCompassDirection().apply(roverTwo.getPosition().getFacing(), Instruction.M);
        CompassDirection resultThree = roverThree.getRotateCompassDirection().apply(roverThree.getPosition().getFacing(), Instruction.M);
        CompassDirection resultFour = roverFour.getRotateCompassDirection().apply(roverFour.getPosition().getFacing(), Instruction.M);
        // Assert
        assertAll("Returns the correct rotation for a compass direction input and M Instruction input",
                () -> assertEquals(CompassDirection.N, resultOne),
                () -> assertEquals(CompassDirection.E, resultTwo),
                () -> assertEquals(CompassDirection.S, resultThree),
                () -> assertEquals(CompassDirection.W, resultFour)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is given null as an input")
    void testRotateWithNullInput(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.W));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), null);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(null, Instruction.R);
        CompassDirection resultThree = rover.getRotateCompassDirection().apply(null, null);

        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(CompassDirection.W, resultOne),
                () -> assertNull(resultTwo),
                () -> assertNull(resultThree)
        );
    }

    @Test
    @DisplayName("Returns the correct Postions X given input")
    void testPositionsX(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.E));

        // Act
        int resultEast = rover.getNextPositionX().apply(rover.getPosition().getX(), rover.getPosition().getFacing());
        int resultWest = rover.getNextPositionX().apply(rover.getPosition().getX(), CompassDirection.W);

        // Assert
        assertEquals(4, resultEast);
        assertEquals(2, resultWest);
    }

    @Test
    @DisplayName("Returns the correct Postions Y given input")
    void testPositionsY(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.N));

        // Act
        int resultEast = rover.getNextPositionY().apply(rover.getPosition().getY(), rover.getPosition().getFacing());
        int resultWest = rover.getNextPositionY().apply(rover.getPosition().getY(), CompassDirection.S);

        // Assert
        assertEquals(4, resultEast);
        assertEquals(2, resultWest);
    }

    @Test
    @DisplayName("Returns 0 when when given Instruction that tries to return negative position X")
    void testDoesNotReturnPositionXBelowZero(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(1,3, CompassDirection.W));
        Rover roverThree = new Rover(new Position(0,3, CompassDirection.W));

        // Act
        int resultOne = rover.getNextPositionX().apply(rover.getPosition().getX(), CompassDirection.W);
        int resultTwo= roverTwo.getNextPositionX().apply(roverTwo.getPosition().getX(), CompassDirection.W);
        int resultThree = roverThree.getNextPositionX().apply(roverThree.getPosition().getX(), CompassDirection.W);

        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo),
                () -> assertEquals(0,resultThree)
        );
    }

    @Test
    @DisplayName("Returns 0 when when given Instruction that tries to return negative position Y")
    void testDoesNotReturnPositionYBelowZero(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.S));
        Rover roverTwo = new Rover(new Position(3,1, CompassDirection.S));
        Rover roverThree = new Rover(new Position(3,0, CompassDirection.S));

        // Act
        int resultOne = rover.getNextPositionY().apply(rover.getPosition().getY(), CompassDirection.S);
        int resultTwo= roverTwo.getNextPositionY().apply(roverTwo.getPosition().getY(), CompassDirection.S);
        int resultThree = roverThree.getNextPositionY().apply(roverThree.getPosition().getY(), CompassDirection.S);

        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo),
                () -> assertEquals(0,resultThree)
        );
    }

    @Test
    @DisplayName("Returns the input position when given an invalid CompassDirection")
    void testReturnsInputPositionForInvalidCompassDirection(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(3,1, CompassDirection.W));

        // Act
        int resultOne = rover.getNextPositionY().apply(rover.getPosition().getY(), CompassDirection.S);
        int resultTwo= roverTwo.getNextPositionY().apply(roverTwo.getPosition().getY(), CompassDirection.S);


        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the next position when given correct input")
    void testNextPosition(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(3,3, CompassDirection.N));

        // Act
        Position positionResult = rover.nextPosition(Instruction.M);
        Position positionResultLeft = rover.nextPosition(Instruction.L);
        Position positionResultRight = rover.nextPosition(Instruction.R);
        Position positionMoveRoverTwo = roverTwo.nextPosition(Instruction.M);

        // Assert
        assertAll("Returns correction results for the nextPosition method",
                () -> assertEquals(2, positionResult.getX()),
                () -> assertEquals(CompassDirection.S, positionResultLeft.getFacing()),
                () -> assertEquals(4, positionMoveRoverTwo.getY()),
                () -> assertEquals(CompassDirection.N, positionResultRight.getFacing())
        );

    }

    @Test
    @DisplayName("Returns the current position when given null input")
    void testNextPositionWithNullInput(){
        // Arrange
        Rover rover = new Rover(new Position(3,3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(3,3, CompassDirection.N));

        // Act
        Position positionResult = rover.nextPosition(null);

        // Assert
        assertAll("Returns correction results for the nextPosition method",
                () -> assertEquals(3, rover.getPosition().getX()),
                () -> assertEquals(3, rover.getPosition().getY()),
                () -> assertEquals(CompassDirection.W ,  rover.getPosition().getFacing())
        );

    }

}