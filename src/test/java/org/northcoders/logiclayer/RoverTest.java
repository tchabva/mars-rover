package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @DisplayName("Rotation Tests")
    @Nested
    class RotateRover {

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
            CompassDirection resultOne = rover.rotateCompassDirection(null);

            // Asserti
            assertEquals(CompassDirection.W, resultOne);
        }
    }

    @DisplayName("Rover Co-Ordinates")
    @Nested
    class RoverCoOrdinates {

        @DisplayName("Returns the correct X Co-ordinate for a given input")
        @ParameterizedTest(name = "{index} Direction = {0}", quoteTextArguments = false)
        @CsvSource({
                "E, 4",
                "W, 2",
                "N, 3",
                "S, 3",
        })
        void shouldReturnCorrectXCoordinate(CompassDirection compassDirection, int expectedXCoordinate) {
            // Arrange
            Rover rover = new Rover(new Position(3, 3, compassDirection));

            // Act
            int result = rover.nextPositionX(rover.position().facing());

            //
            assertEquals(expectedXCoordinate, result);
        }

        @DisplayName("Returns the correct Y Co-ordinate for a given input")
        @ParameterizedTest(name = "{index} Direction = {0}", quoteTextArguments = false)
        @CsvSource({
                "E, 3",
                "W, 3",
                "N, 4",
                "S, 2",
        })
        void shouldReturnCorrectYCoordinateWhenGivenI(CompassDirection compassDirection, int expectedYCoordinate) {
            // Arrange
            Rover rover = new Rover(new Position(3, 3, compassDirection));

            // Act
            int result = rover.nextPositionY(rover.position().facing());

            //
            assertEquals(expectedYCoordinate, result);
        }

        @DisplayName("Does Not Return Coordinates Below Zero")
        @ParameterizedTest(name = "For {0} on {1}-axis", quoteTextArguments = false)
        @CsvSource({
                "W, X",
                "S, Y",
        })
        void shouldNotReturnCoordinatesBelowZero(CompassDirection compassDirection, String axis) {
            // Arrange
            Rover rover = new Rover(new Position(0, 0, compassDirection));

            // Act
            int resultOne = rover.nextPositionX(compassDirection);

            // Assert
            assertEquals(0, resultOne);
        }

        @Test
        @DisplayName("Returns the input position when given an null CompassDirection for next X-axis co-ordinate")
        void shouldReturnInputPositionForNullCompassDirectionForNextXCoOrdinatePosition() {
            // Arrange
            Rover rover = new Rover(new Position(3, 3, CompassDirection.W));

            // Act
            int resultOne = rover.nextPositionX(null);

            // Assert
            assertEquals(3, resultOne);
        }

        @Test
        @DisplayName("Returns the input position when given an null CompassDirection for next Y-axis co-ordinate")
        void shouldReturnInputPositionForNullCompassDirectionForNextYCoOrdinatePosition() {
            // Arrange
            Rover rover = new Rover(new Position(3, 3, CompassDirection.W));

            // Act
            int resultOne = rover.nextPositionY(null);

            // Assert
            assertEquals(3, resultOne);
        }
    }

    @DisplayName("Rover Position")
    @Nested
    class RoverPosition {

        @DisplayName("Should Return the Next Rover Position")
        @ParameterizedTest(name = "For {index}", quoteTextArguments = false)
        @CsvSource({
                "W, M, 2, 3, W",
                "N, M, 3, 4, N",
                "W, L, 3, 3, S",
                "N, R, 3, 3, E",
        })
        void shouldReturnTheNextRoverPosition(
                CompassDirection compassDirection,
                Instruction instruction,
                int x,
                int y,
                CompassDirection expectedCompassDirection
        ) {
            // Arrange
            Rover rover = new Rover(new Position(3, 3, compassDirection));
            Position expectedNextPosition = new Position(x, y, expectedCompassDirection);

            // Act
            Position positionResult = rover.nextPosition(instruction);

            // Assert
            assertEquals(expectedNextPosition, positionResult);
        }

        @Test
        @DisplayName("Returns the current position when given null input")
        void testNextPositionWithNullInput() {
            // Arrange
            Position expectedPosition = new Position(1, 3, CompassDirection.W);
            Rover rover = new Rover(expectedPosition);

            // Act
            Position nextPosition = rover.nextPosition(null);

            // Assert
            assertEquals(expectedPosition, nextPosition);
        }
    }
}