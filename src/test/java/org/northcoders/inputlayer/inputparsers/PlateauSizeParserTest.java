package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.PlateauSize;

import static org.junit.jupiter.api.Assertions.*;

class PlateauSizeParserTest {
    private PlateauSizeParser plateauSizeParser;

    @BeforeEach
    void setUp(){
        plateauSizeParser = new PlateauSizeParser();
    }

    @Test
    @DisplayName("Returns valid PlateauSize object with a valid string input")
    void testPlateauSizeParser(){
        // Arrange
        String inputOne = "1000 10";
        String inputTwo = "5 11";

        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(inputOne);
        PlateauSize plateauSizeTwo = plateauSizeParser.parsePlateauSize(inputTwo);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertEquals(1000, plateauSizeOne.x()),
                () -> assertEquals(10, plateauSizeOne.y()),
                () -> assertEquals(5, plateauSizeTwo.x()),
                () -> assertEquals(11, plateauSizeTwo.y()),
                () -> assertTrue(plateauSizeParser.isValidPlateauDimensions())
        );
    }

    @Test
    @DisplayName("Returns PlateauSize object [0,0] for with invalid string input")
    void testPlateauSizeParserWithInvalidStringInput(){
        // Arrange
        String inputOne = "1010";
        String inputTwo = "5";
        String inputThree = "5gfhnghgfs";

        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(inputOne);
        PlateauSize plateauSizeTwo = plateauSizeParser.parsePlateauSize(inputTwo);
        PlateauSize plateauSizeThree = plateauSizeParser.parsePlateauSize(inputThree);

        // Assert
        assertAll("Confirms the PlateauSizeParser returns a returns PlateauSize object",
                () -> assertNull(plateauSizeOne),
                () -> assertNull(plateauSizeTwo),
                () -> assertNull(plateauSizeThree)
        );
    }

    @Test
    @DisplayName("Returns PlateauSize object [0,0] for with null input")
    void testPlateauSizeParserWithNullInput(){
        // Act
        PlateauSize plateauSizeOne = plateauSizeParser.parsePlateauSize(null);

        // Assert
        assertNull(plateauSizeOne);
    }
}