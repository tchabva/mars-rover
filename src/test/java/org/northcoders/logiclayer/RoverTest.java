package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @DisplayName("Rotation Tests")
    @Nested
    class RotateRover{

        @DisplayName("Rotates the Rover:")
        @ParameterizedTest(name = "{0} => {2}", quoteTextArguments = false)
        @CsvSource({
                "N, L, W",
                "N, R, E",
                "E, L, N",
                "E, R, S",
                "S, L, E",
                "S, R, W",
                "W, L, S",
                "W, R, N",
        })
        void shouldRotateTheRoverFromAnyGivenOrientation(CompassDirection compassDirection, Instruction instruction, CompassDirection expectedCompassDirection) {
            // Arrange
            Rover rover = new Rover(new Position(0, 0, compassDirection));

            // Act
            CompassDirection result = rover.rotateCompassDirection(instruction);

            // Assert
            assertEquals(expectedCompassDirection, result);
        }

        @DisplayName("Rotates the Rover After Multiple Instructions:")
        @ParameterizedTest(name = "{0} => {3}", quoteTextArguments = false)
        @CsvSource({
                "N, L, L, S",
                "N, R, R, S",
                "W, L, R, W",
        })
        void shouldCorrectlyRotateTheRoverOnReceivingMultipleInstructions(
                CompassDirection compassDirection,
                Instruction instructionOne,
                Instruction instructionTwo,
                CompassDirection expectedCompassDirection
        ) {
            // Arrange
            Rover rover = new Rover(new Position(0, 0, compassDirection));

            // Act
            Position nextPosition = rover.nextPosition(instructionOne);
            rover.position(nextPosition);
            CompassDirection result = rover.rotateCompassDirection(instructionTwo);

            // Assert
            assertEquals(expectedCompassDirection, result);
        }

        @DisplayName("Does Not Rotate the Rover When Given \"Move\" Instruction:")
        @ParameterizedTest(name = "{0} => {1}", quoteTextArguments = false)
        @CsvSource({
                "N, N",
                "W, W",
                "E, E",
                "S, S",
        })
        void shouldNotRotateWhenGivenMoveInstruction(CompassDirection compassDirection, CompassDirection expectedCompassDirection) {
            // Arrange
            Rover rover = new Rover(new Position(0, 0, compassDirection));

            // Act
            CompassDirection resultOne = rover.rotateCompassDirection(Instruction.M);
            // Assert
            assertEquals(expectedCompassDirection, resultOne);
        }

        @Test
        @DisplayName("Returns the correct CompassDirection when Rover is given null as an input")
        void shouldReturnDirectionWhenGivenNullInstruction() {
            // Arrange
            Rover rover = new Rover(new Position(0, 0, CompassDirection.W));

            // Act
            CompassDirection resultOne = rover.getRotateCompassDirection(rover.position().facing(), null);

            // Assert
            assertEquals(CompassDirection.W, resultOne);
        }
    }

    @Test
    @DisplayName("Returns the correct X Co-ordinate for a given input")
    void shouldReturnCorrectXCoordinateWhenGivenI() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.E));

        // Act
        int resultEast = rover.getNextPositionX(rover.position().x(), rover.position().facing());
        int resultWest = rover.getNextPositionX(rover.position().x(), CompassDirection.W);

        // Assert
        assertEquals(4, resultEast);
        assertEquals(2, resultWest);
    }

    @Test
    @DisplayName("Returns the correct Positions Y given input")
    void testPositionsY() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.N));

        // Act
        int resultEast = rover.getNextPositionY(rover.position().y(), rover.position().facing());
        int resultWest = rover.getNextPositionY(rover.position().y(), CompassDirection.S);

        // Assert
        assertEquals(4, resultEast);
        assertEquals(2, resultWest);
    }

    @Test
    @DisplayName("Returns 0 when when given Instruction that tries to return negative position X")
    void testDoesNotReturnPositionXBelowZero() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(1, 3, CompassDirection.W));
        Rover roverThree = new Rover(new Position(0, 3, CompassDirection.W));

        // Act
        int resultOne = rover.getNextPositionX(rover.position().x(), CompassDirection.W);
        int resultTwo = roverTwo.getNextPositionX(roverTwo.position().x(), CompassDirection.W);
        int resultThree = roverThree.getNextPositionX(roverThree.position().x(), CompassDirection.W);

        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo),
                () -> assertEquals(0, resultThree)
        );
    }

    @Test
    @DisplayName("Returns 0 when when given Instruction that tries to return negative position Y")
    void testDoesNotReturnPositionYBelowZero() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.S));
        Rover roverTwo = new Rover(new Position(3, 1, CompassDirection.S));
        Rover roverThree = new Rover(new Position(3, 0, CompassDirection.S));

        // Act
        int resultOne = rover.getNextPositionY(rover.position().y(), CompassDirection.S);
        int resultTwo = roverTwo.getNextPositionY(roverTwo.position().y(), CompassDirection.S);
        int resultThree = roverThree.getNextPositionY(roverThree.position().y(), CompassDirection.S);

        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo),
                () -> assertEquals(0, resultThree)
        );
    }

    @Test
    @DisplayName("Returns the input position when given an invalid CompassDirection")
    void testReturnsInputPositionForInvalidCompassDirection() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(3, 1, CompassDirection.W));

        // Act
        int resultOne = rover.getNextPositionY(rover.position().y(), CompassDirection.S);
        int resultTwo = roverTwo.getNextPositionY(roverTwo.position().y(), CompassDirection.S);


        // Assert
        assertAll("Returns null for a nul for a compass direction input and instruction",
                () -> assertEquals(2, resultOne),
                () -> assertEquals(0, resultTwo)
        );
    }

    @Test
    @DisplayName("Returns the next position when given correct input")
    void testNextPosition() {
        // Arrange
        Rover rover = new Rover(new Position(3, 3, CompassDirection.W));
        Rover roverTwo = new Rover(new Position(3, 3, CompassDirection.N));

        // Act
        Position positionResult = rover.nextPosition(Instruction.M);
        Position positionResultLeft = rover.nextPosition(Instruction.L);
        Position positionResultRight = rover.nextPosition(Instruction.R);
        Position positionMoveRoverTwo = roverTwo.nextPosition(Instruction.M);

        // Assert
        assertAll("Returns correction results for the nextPosition method",
                () -> assertEquals(2, positionResult.x()),
                () -> assertEquals(CompassDirection.S, positionResultLeft.facing()),
                () -> assertEquals(4, positionMoveRoverTwo.y()),
                () -> assertEquals(CompassDirection.N, positionResultRight.facing())
        );
    }

    @Test
    @DisplayName("Returns the current position when given null input")
    void testNextPositionWithNullInput() {
        // Arrange
        Rover rover = new Rover(new Position(1, 3, CompassDirection.W));

        // Act
        Position positionResult = rover.nextPosition(null);

        // Assert
        assertAll("Returns correction results for the nextPosition method",
                () -> assertEquals(1, positionResult.x()),
                () -> assertEquals(3, positionResult.y()),
                () -> assertEquals(CompassDirection.W, positionResult.facing())
        );
    }
}