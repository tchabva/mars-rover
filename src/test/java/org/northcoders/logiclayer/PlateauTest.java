package org.northcoders.logiclayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("Returns true if x and y are within the plateau boundary")
    @ParameterizedTest(name = "X = {0}, Y = {1}", quoteTextArguments = false)
    @CsvSource({
            "1, 1",
            "10, 10",
            "0, 10",
            "10, 0",
            "0, 0",
            "4, 6",
    })
    void shouldReturnTrueIfXAndYAreWithinPlateauBoundary(int posX, int posY) {
        // Arrange & Act
        boolean result = plateau.isPositionValid(posX, posY);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Returns false if x or y are not within the plateau boundary")
    @ParameterizedTest(name = "X = {0}, Y = {1}", quoteTextArguments = false)
    @CsvSource({
            "12, 1",
            "10, 21",
            "-1, 1",
            "-1, -1",
            "11, 11",
    })
    void testReturnsFalseIfXOrYAreNotInPlateauBoundary(int posX, int posY) {
        // Arrange & Act
        boolean result = plateau.isPositionValid(posX, posY);

        // Assert
        assertFalse(result);
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