package org.northcoders.logiclayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    @Test
    @DisplayName("Create Plateau on initialisation of MissionControl")
    void testMissionControlCreatesPlateau(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L, Instruction.M, Instruction.R));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(new Position(5,5, CompassDirection.W));

        // Act
        MissionControl missionControl = new MissionControl(
                plateauSize,
                instructionList,
                positionList
        );

        missionControl.printRoverPosition();

        // Assert
        assertEquals(10, missionControl.getPlateau().getX());
        assertEquals(10, missionControl.getPlateau().getY());
    }

    @Test
    @DisplayName("Returns a print Message null input for initialisation of MissionControl")
    void testMissionControlWithNullInputs(){
        // Arrange & Act
        MissionControl missionControl = new MissionControl(
                null,
                null,
                null
        );

        // Assert
        assertNull(missionControl.getPlateau());
        assertNull(missionControl.getPlateau());
    }

    @Test
    @DisplayName("Create Plateau on initialisation of MissionControl")
    void testMissionControlWithNullInstructionInputs(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        List<Position> positionList = List.of(new Position(5,5, CompassDirection.W));

        // Act
        MissionControl missionControl = new MissionControl(
                plateauSize,
                null,
                positionList
        );

        // Assert
        assertEquals(10, missionControl.getPlateau().getX());
        assertEquals(10, missionControl.getPlateau().getY());
    }

    @Test
    @DisplayName("Creates Rover List on initialisation in the Plateau")
    void testMissionControlCreatesListOfRoversInThePlateau(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L, Instruction.M, Instruction.R));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(
                new Position(5,5, CompassDirection.W),
                new Position(4,4, CompassDirection.W)
        );

        // Act
        MissionControl missionControl = new MissionControl(
                plateauSize,
                instructionList,
                positionList
        );

        // Assert
        assertEquals(2, missionControl.getPlateau().getRoverList().size());
    }

    @Test
    @DisplayName("Creates Rover List in the Plateau identical to the Rover List in MissionControl")
    void testIdenticalRoverLists(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L, Instruction.M, Instruction.R));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(
                new Position(5,5, CompassDirection.W),
                new Position(4,4, CompassDirection.W)
        );

        // Act
        MissionControl missionControl = new MissionControl(
                plateauSize,
                instructionList,
                positionList
        );

        // Assert
        assertEquals(missionControl.getRoverList(), missionControl.getPlateau().getRoverList());
    }

    @Test
    @DisplayName("Single Rover plateau moves the rover according to the input instruction")
    void testMissionControlMovesRover(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L,
                Instruction.M,
                Instruction.M)
        );
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(new Position(5,5, CompassDirection.W));
        MissionControl missionControl = new MissionControl(
                plateauSize,
                instructionList,
                positionList
        );

        // Act
        missionControl.printRoverPosition();
        missionControl.moveRoverPosition();
        missionControl.printRoverPosition();

        // Assert
        assertAll("Coordinates of the moved Rover",
                () -> assertEquals(5, missionControl.getRoverList().getFirst().getPosition().x()),
                () -> assertEquals(3, missionControl.getRoverList().getFirst().getPosition().y()),
                () -> assertEquals(CompassDirection.S, missionControl.getRoverList().getFirst().getPosition().facing())
        );
    }

    @Test
    @DisplayName("MissionControl moves the multiple Rovers on the plateau according to the input instruction")
    void testMissionControlMovesMultipleRovers(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5,5);
        Queue<Instruction> instructionsListOne = new LinkedList<>(List.of(
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.L,
                Instruction.M,
                Instruction.M
        ));
        Queue<Instruction> instructionsListTwo = new LinkedList<>(List.of(
                Instruction.M,
                Instruction.M,
                Instruction.R,
                Instruction.M,
                Instruction.M,
                Instruction.R,
                Instruction.M,
                Instruction.R,
                Instruction.R,
                Instruction.M
        ));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(instructionsListOne, instructionsListTwo));
        List<Position> positionList = List.of(
                new Position(1,2, CompassDirection.N),
                new Position(3,3, CompassDirection.E)
        );
        MissionControl missionControl = new MissionControl(
                plateauSize,
                instructionList,
                positionList
        );

        // Act
        missionControl.printRoverPosition();
        missionControl.moveRoverPosition();
        System.out.println();
        missionControl.printRoverPosition();

        // Assert
        assertAll("Coordinates of the moved Rover",
                () -> assertEquals(1, missionControl.getRoverList().getFirst().getPosition().x()),
                () -> assertEquals(3, missionControl.getRoverList().getFirst().getPosition().y()),
                () -> assertEquals(CompassDirection.N, missionControl.getRoverList().getFirst().getPosition().facing()),
                () -> assertEquals(5, missionControl.getRoverList().getLast().getPosition().x()),
                () -> assertEquals(1, missionControl.getRoverList().getLast().getPosition().y()),
                () -> assertEquals(CompassDirection.E, missionControl.getRoverList().getLast().getPosition().facing())
        );
    }
}