package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    @DisplayName("Returns true if x and y are within the plateau boundary")
    void testReturnsTrueIfXAndYAreInPlateau(){
        // Arrange
        Plateau plateau = new Plateau(new PlateauSize(10, 10));

        // Act
        boolean result = plateau.isPositionValid(1,1);
        boolean resultTwo = plateau.isPositionValid(11,11);
        boolean resultThree = plateau.isPositionValid(5,1);
        boolean resultFour = plateau.isPositionValid(0,0);

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
    void testReturnsFalseIfXOrYAreNotInPlateauBoundary(){
        // Arrange
        Plateau plateau = new Plateau(new PlateauSize(10, 10));

        // Act
        boolean result = plateau.isPositionValid(12,1);
        boolean resultTwo = plateau.isPositionValid(11,21);
        boolean resultThree = plateau.isPositionValid(-1,1);
        boolean resultFour = plateau.isPositionValid(12,12);

        // Assert
        assertAll("Return false as they are not all in the plateau bounds",
                () -> assertFalse(result),
                () -> assertFalse(resultTwo),
                () -> assertFalse(resultThree),
                () -> assertFalse(resultFour)
        );
    }

    @Test
    @DisplayName("Returns true if x and y are within the are not coordinates of a Rover")
    void testReturnsTrueIfXAndYAreNotCoordinatesOfARover(){
        // Arrange
        Plateau plateau = new Plateau(new PlateauSize(10, 10));

        plateau.getRoverList().add(new Rover(new Position(5,5, CompassDirection.W)));

        // Act
        boolean result = plateau.isPositionFree.test(1,1);
        boolean resultTwo = plateau.isPositionFree.test(11,11);
        boolean resultThree = plateau.isPositionFree.test(5,1);
        boolean resultFour = plateau.isPositionFree.test(0,0);

        // Assert
        assertAll("Return true as they are in all the not Rover coordinates",
                () -> assertTrue(result),
                () -> assertTrue(resultTwo),
                () -> assertTrue(resultThree),
                () -> assertTrue(resultFour)
        );
    }

    @Test
    @DisplayName("Returns false if x and y are coordinates of a Rover")
    void testReturnsFalseIfXAndYAreCoordinatesOfARover(){
        // Arrange
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        plateau.getRoverList().add(new Rover(new Position(5,1, CompassDirection.W)));
        plateau.getRoverList().add(new Rover(new Position(1,1, CompassDirection.W)));
        plateau.getRoverList().add(new Rover(new Position(11,11, CompassDirection.W)));
        plateau.getRoverList().add(new Rover(new Position(0,0, CompassDirection.W)));

        // Act
        boolean result = plateau.isPositionFree.test(1,1);
        boolean resultTwo = plateau.isPositionFree.test(11,11);
        boolean resultThree = plateau.isPositionFree.test(5,1);
        boolean resultFour = plateau.isPositionFree.test(0,0);

        // Assert
        assertAll("Return false as they are all the Rover coordinates",
                () -> assertFalse(result),
                () -> assertFalse(resultTwo),
                () -> assertFalse(resultThree),
                () -> assertFalse(resultFour)
        );
    }
}