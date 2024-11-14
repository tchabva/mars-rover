package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.PlateauSize;

import static org.junit.jupiter.api.Assertions.*;

class PlateauSizeParserTest {

    @Test
    @DisplayName("Returns valid PlateauSize object with a valid string input")
    void testPlateauSizeParser(){
        // Arrange
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        String inputOne = "10 10";
        String inputTwo = "5 11";

        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(inputOne);
        PlateauSize plateauSizeTwo = plateauSizeParser.parsePlateauSize(inputTwo);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(10, plateauSizeOne.getX()),
                () -> assertEquals(10, plateauSizeOne.getY()),
                () -> assertEquals(5, plateauSizeTwo.getX()),
                () -> assertEquals(11, plateauSizeTwo.getY()),
                () -> assertTrue(plateauSizeParser.isValidPlateauDimensions())

        );
    }

    @Test
    @DisplayName("Returns PlateauSize object [0,0] for with invalid string input")
    void testPlateauSizeParserWithInvalidStringInput(){
        // Arrange
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        String inputOne = "1010";
        String inputTwo = "5";
        String inputThree = "";

        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(inputOne);
        PlateauSize plateauSizeTwo = plateauSizeParser.parsePlateauSize(inputTwo);
        PlateauSize plateauSizeThree = plateauSizeParser.parsePlateauSize(inputThree);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(0, plateauSizeOne.getX()),
                () -> assertEquals(0, plateauSizeOne.getY()),
                () -> assertEquals(0, plateauSizeTwo.getX()),
                () -> assertEquals(0, plateauSizeTwo.getY()),
                () -> assertEquals(0, plateauSizeThree.getX()),
                () -> assertEquals(0, plateauSizeThree.getY()),
                () -> assertFalse(plateauSizeParser.isValidPlateauDimensions())

        );
    }

    @Test
    @DisplayName("Returns PlateauSize object [0,0] for with null input")
    void testPlateauSizeParserWithNullInput(){
        // Arrange
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();

        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(null);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(0, plateauSizeOne.getX()),
                () -> assertEquals(0, plateauSizeOne.getY()),
                () -> assertFalse(plateauSizeParser.isValidPlateauDimensions())
        );
    }
}