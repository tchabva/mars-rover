package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {
    // TODO: look into @BeforeEach
    @Test
    @DisplayName("Returns valid Position object with a valid string input")
    void testPlateauSizeParser(){
        // Arrange
        PositionParser positionParser = new PositionParser();
        String inputOne = "0 0 N";
        String inputTwo = "5 11 W";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);
        Position positionTwo = positionParser.positionParser(inputTwo);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(0, positionOne.getX()),
                () -> assertEquals(0, positionOne.getY()),
                () -> assertEquals(CompassDirection.N, positionOne.getFacing()),
                () -> assertTrue(positionParser.isValidPosition()),
                () -> assertEquals(5, positionTwo.getX()),
                () -> assertEquals(11, positionTwo.getY()),
                () -> assertEquals(CompassDirection.W, positionTwo.getFacing()),
                () -> assertTrue(positionParser.isValidPosition())
        );
    }

    @Test
    @DisplayName("Returns valid Position object with a valid lowercase string input")
    void testPlateauSizeParserIsCaseSafe(){
        // Arrange
        PositionParser positionParser = new PositionParser();
        String inputOne = "0 0 n";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(0, positionOne.getX()),
                () -> assertEquals(0, positionOne.getY()),
                () -> assertEquals(CompassDirection.N, positionOne.getFacing()),
                () -> assertTrue(positionParser.isValidPosition())
        );
    }

    @Test
    @DisplayName("Returns a false boolean and Position object with co-ordinates [-1,-1, N] with a invalid string input")
    void testPlateauSizeParserWithInvalidInput(){
        // Arrange
        PositionParser positionParser = new PositionParser();
        String inputOne = "1010 N";
        String inputTwo = "5 a R";
        String inputThree = "10 10 C";

        // Act
        Position positionOne = positionParser.positionParser(inputOne);
        Position positionTwo = positionParser.positionParser(inputTwo);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(-1, positionOne.getX()),
                () -> assertEquals(-1, positionOne.getY()),
                () -> assertEquals(CompassDirection.N, positionOne.getFacing()),
                () -> assertFalse(positionParser.isValidPosition()),
                () -> assertEquals(-1, positionTwo.getX()),
                () -> assertEquals(-1, positionTwo.getY()),
                () -> assertEquals(CompassDirection.N, positionTwo.getFacing()),
                () -> assertFalse(positionParser.isValidPosition()),
                () -> assertEquals(-1, positionTwo.getX()),
                () -> assertEquals(-1, positionTwo.getY()),
                () -> assertEquals(CompassDirection.N, positionTwo.getFacing()),
                () -> assertFalse(positionParser.isValidPosition())

        );
    }

    @Test
    @DisplayName("Returns a false boolean and Position object with co-ordinates [-1,-1, N] with a null input")
    void testPlateauSizeParserWithNullInput(){
        // Arrange
        PositionParser positionParser = new PositionParser();

        // Act
        Position positionOne = positionParser.positionParser(null);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(-1, positionOne.getX()),
                () -> assertEquals(-1, positionOne.getY()),
                () -> assertEquals(CompassDirection.N, positionOne.getFacing()),
                () -> assertFalse(positionParser.isValidPosition())
        );
    }

}