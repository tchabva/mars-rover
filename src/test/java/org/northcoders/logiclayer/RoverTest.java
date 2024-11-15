package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

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
        assertAll("Returns the correct rotation for a compass direction input and instruction",
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
        assertAll("Returns the correct rotation for a compass direction input and instruction",
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
        assertAll("Returns the correct rotation for a compass direction input and instruction",
                () -> assertEquals(CompassDirection.N, resultOne),
                () -> assertEquals(CompassDirection.S, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the correct CompassDirection when Rover is facing East and given an instruction")
    void testRotateFromWest(){
        // Arrange
        Rover rover = new Rover(new Position(0,0, CompassDirection.W));

        // Act
        CompassDirection resultOne = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.L);
        CompassDirection resultTwo = rover.getRotateCompassDirection().apply(rover.getPosition().getFacing(), Instruction.R);

        // Assert
        assertAll("Returns the correct rotation for a compass direction input and instruction",
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
        assertAll("Returns the correct rotation for a compass direction input and instruction",
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

}