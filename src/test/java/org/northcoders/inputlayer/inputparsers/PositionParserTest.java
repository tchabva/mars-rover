package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {
    private PositionParser positionParser;
    private List<Position> positionList;

    // This is invoked before each test
    @BeforeEach
    void setUp() {
        positionParser = new PositionParser();
        positionList = List.of(
                new Position(10, 10, CompassDirection.E),
                new Position(5, 5, CompassDirection.N),
                new Position(1, 1, CompassDirection.S),
                new Position(2, 2, CompassDirection.W)
        );
    }

    @Test
    @DisplayName("Returns valid Position object with a valid string input")
    void testPositionSizeParser() {
        // Arrange
        String inputOne = "0 0 N";
        String inputTwo = "5 11 W";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);
        Position positionTwo = positionParser.positionParser(inputTwo);

        // Assert
        assertAll("Confirms the PositionSizeParser returns a returns PositionSize object",
                () -> assertEquals(0, positionOne.x()),
                () -> assertEquals(0, positionOne.y()),
                () -> assertEquals(CompassDirection.N, positionOne.facing()),
                () -> assertTrue(positionParser.isValidPosition()),
                () -> assertEquals(5, positionTwo.x()),
                () -> assertEquals(11, positionTwo.y()),
                () -> assertEquals(CompassDirection.W, positionTwo.facing()),
                () -> assertTrue(positionParser.isValidPosition())
        );
    }

    @Test
    @DisplayName("Returns valid Position object with a valid lowercase string input")
    void testPositionSizeParserIsCaseSafe() {
        // Arrange
        String inputOne = "0 0 n";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);

        // Assert
        assertAll("Confirms the PositionSizeParser returns a returns PositionSize object",
                () -> assertEquals(0, positionOne.x()),
                () -> assertEquals(0, positionOne.y()),
                () -> assertEquals(CompassDirection.N, positionOne.facing()),
                () -> assertTrue(positionParser.isValidPosition())
        );
    }

    @Test
    @DisplayName("Returns a false boolean and null for a invalid string input")
    void testPositionSizeParserWithInvalidInput() {
        // Arrange
        String inputOne = "1010 N";
        String inputTwo = "5 a R";
        String inputThree = "10 10 C";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);
        Position positionTwo = positionParser.positionParser(inputTwo);
        Position positionThree = positionParser.positionParser(inputThree);

        // Assert
        assertAll("Confirms the PositionSizeParser returns a returns null",
                () -> assertNull(positionOne),
                () -> assertNull(positionTwo),
                () -> assertNull(positionThree)

        );
    }

    @Test
    @DisplayName("Returns a false boolean and Position object with co-ordinates [-1,-1, N] with a null input")
    void testPositionSizeParserWithNullInput() {
        // Act
        Position positionOne = positionParser.positionParser(null);

        // Assert
        assertNull(positionOne);
    }

    @Test
    @DisplayName("Returns a true boolean and when the newPosition does match any of the Positions in the positionList")
    void testIsLandingPositionIsFree() {
        // Arrange
        Position newPosition = new Position(0, 0, CompassDirection.N);

        // Act
        boolean result = positionParser.isLandingPositionFree(positionList, newPosition);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Returns a false boolean and when the newPosition is equal to a Position in the positionList")
    void testIsLandingPositionIsFreeForFalseCondition() {
        // Arrange
        Position newPosition = new Position(10, 10, CompassDirection.N);

        // Act
        boolean result = positionParser.isLandingPositionFree(positionList, newPosition);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Returns a false boolean and when newPosition input is null")
    void testIsLandingPositionIsFreeNullInput() {
        // Act
        boolean result = positionParser.isLandingPositionFree(positionList, null);

        // Assert
        assertFalse(result);
    }
}