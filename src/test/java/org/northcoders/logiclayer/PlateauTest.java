package org.northcoders.logiclayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    private Plateau plateau;

    @BeforeEach
    void setUp() {
        plateau = new Plateau(new PlateauSize(10, 10));
    }

    @Test
    @DisplayName("Returns true if x and y are within the plateau boundary")
    void testReturnsTrueIfXAndYAreInPlateau() {
        // Arrange & Act
        boolean result = plateau.isPositionValid(1, 1);
        boolean resultTwo = plateau.isPositionValid(10, 10);
        boolean resultThree = plateau.isPositionValid(5, 1);
        boolean resultFour = plateau.isPositionValid(0, 0);

        // Assert
        assertAll("Return true as they are in all the plateau bounds",
                () -> assertTrue(result),
                () -> assertTrue(resultTwo),
                () -> assertTrue(resultThree),
                () -> assertTrue(resultFour)
        );
    }

    @Test
    @DisplayName("Returns false if x or y are not within the plateau boundary")
    void testReturnsFalseIfXOrYAreNotInPlateauBoundary() {
        // Arrange & Act
        boolean result = plateau.isPositionValid(12, 1);
        boolean resultTwo = plateau.isPositionValid(11, 21);
        boolean resultThree = plateau.isPositionValid(-1, 1);
        boolean resultFour = plateau.isPositionValid(12, 12);

        // Assert
        assertAll("Return false as they are not all in the plateau bounds",
                () -> assertFalse(result),
                () -> assertFalse(resultTwo),
                () -> assertFalse(resultThree),
                () -> assertFalse(resultFour)
        );
    }

    @Test
    @DisplayName("Returns true if next position is free")
    void testReturnsTrueIfXAndYAreNotCoordinatesOfARover() {
        // Arrange
        Rover rover = new Rover(new Position(5, 5, CompassDirection.W));
        Position nextPosition = rover.nextPosition(Instruction.M);
        plateau.getRoverList().add(rover);

        // Act
        boolean result = plateau.isPositionEmpty(nextPosition, rover);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Returns true if next position is not free")
    void returnsFalseIfNextPositionIsNotFree() {
        // Arrange
        Rover rover = new Rover(new Position(5, 1, CompassDirection.W));
        Position nextPosition = rover.nextPosition(Instruction.M);
        plateau.getRoverList().add(rover);
        plateau.getRoverList().add(new Rover(new Position(6, 1, CompassDirection.W)));

        // Act
        boolean result = plateau.isPositionEmpty(nextPosition, rover);

        // Assert
        assertTrue(result);
    }
}