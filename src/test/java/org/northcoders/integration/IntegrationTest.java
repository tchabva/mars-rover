package org.northcoders.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;
import org.northcoders.inputlayer.inputparsers.InstructionParser;
import org.northcoders.inputlayer.inputparsers.PlateauSizeParser;
import org.northcoders.inputlayer.inputparsers.PositionParser;
import org.northcoders.logiclayer.MissionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    @Test
    @DisplayName("Returns the correct output when combining the input and logic layers")
    void testIntegrationOfInputAndLogicLayers(){
        // Arrange
        ArrayList<String> input = new ArrayList<>(List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        ));

        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        PositionParser positionParser = new PositionParser();
        InstructionParser instructionParser = new InstructionParser();

        // Act
        PlateauSize plateauSize = plateauSizeParser.parsePlateauSize(input.getFirst());

        List<Position> positionList = new ArrayList<>();
        for (int i = 1; i < input.size(); i += 2) {
            positionList.add(positionParser.positionParser(input.get(i)));
        }

        List<Queue<Instruction>> queueList = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 2) {
            queueList.add(instructionParser.parseInstructions(input.get(i)));
        }

        MissionControl missionControl = new MissionControl(plateauSize, queueList, positionList);

        missionControl.moveRoverPosition();

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

    @Test
    @DisplayName("Returns the correct output when combining the input and logic layers")
    void testIntegrationOfInputAndLogicLayersRoversDoNotExceedBounds(){
        // Arrange
        ArrayList<String> input = new ArrayList<>(List.of(
                "5 5",
                "1 2 N",
                "MMMMMMMMMMMM",
                "3 3 E",
                "MMMMMMMMMMMM"
        ));

        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        PositionParser positionParser = new PositionParser();
        InstructionParser instructionParser = new InstructionParser();

        // Act
        PlateauSize plateauSize = plateauSizeParser.parsePlateauSize(input.getFirst());

        List<Position> positionList = new ArrayList<>();
        for (int i = 1; i < input.size(); i += 2) {
            positionList.add(positionParser.positionParser(input.get(i)));
        }

        List<Queue<Instruction>> queueList = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 2) {
            queueList.add(instructionParser.parseInstructions(input.get(i)));
        }

        MissionControl missionControl = new MissionControl(plateauSize, queueList, positionList);

        missionControl.moveRoverPosition();

        missionControl.printRoverPosition();

        // Assert
        assertAll("Coordinates of the moved Rover do not ex",
                () -> assertEquals(1, missionControl.getRoverList().getFirst().getPosition().x()),
                () -> assertEquals(5, missionControl.getRoverList().getFirst().getPosition().y()),
                () -> assertEquals(CompassDirection.N, missionControl.getRoverList().getFirst().getPosition().facing()),
                () -> assertEquals(5, missionControl.getRoverList().getLast().getPosition().x()),
                () -> assertEquals(3, missionControl.getRoverList().getLast().getPosition().y()),
                () -> assertEquals(CompassDirection.E, missionControl.getRoverList().getLast().getPosition().facing())
        );
    }
}
