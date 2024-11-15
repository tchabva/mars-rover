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
        assertEquals(11, missionControl.getPlateau().getX());
        assertEquals(11, missionControl.getPlateau().getY());
    }

    @Test
    @DisplayName("Create Plateau on initialisation of MissionControl")
    void testMissionControlWithNullInputs(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L, Instruction.M, Instruction.R));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(new Position(5,5, CompassDirection.W));

        // Act
        MissionControl missionControl = new MissionControl(
                null,
                null,
                null
        );

        // Assert
        assertEquals(11, missionControl.getPlateau().getX());
        assertEquals(11, missionControl.getPlateau().getY());
    }

    @Test
    @DisplayName("Create Plateau on initialisation of MissionControl")
    void testMissionControlWithNullInstructionInputs(){
        // Arrange
        PlateauSize plateauSize = new PlateauSize(10,10);
        Queue<Instruction> queue = new LinkedList<>(List.of(Instruction.L, Instruction.M, Instruction.R));
        List<Queue<Instruction>> instructionList = new ArrayList<>(List.of(queue));
        List<Position> positionList = List.of(new Position(5,5, CompassDirection.W));

        // Act
        MissionControl missionControl = new MissionControl(
                plateauSize,
                null,
                positionList
        );

        // Assert
        assertEquals(11, missionControl.getPlateau().getX());
        assertEquals(11, missionControl.getPlateau().getY());
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
}