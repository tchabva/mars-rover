package org.northcoders;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;
import org.northcoders.inputlayer.inputparsers.InstructionParser;
import org.northcoders.inputlayer.inputparsers.PlateauSizeParser;
import org.northcoders.inputlayer.inputparsers.PositionParser;
import org.northcoders.logiclayer.MissionControl;
import org.northcoders.logiclayer.Rover;

import java.lang.management.MonitorInfo;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.northcoders.inputlayer.inputparsers.InstructionParser.parseInstructions;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>(List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        ));

        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        PositionParser positionParser = new PositionParser();


        PlateauSize plateauSize = plateauSizeParser.parsePlateauSize(input.getFirst());

        List<Position> positionList = new ArrayList<>();
        for (int i = 1; i < input.size(); i += 2) {
            positionList.add(positionParser.positionParser(input.get(i)));
        }

        List<Queue<Instruction>> queueList = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 2) {
            queueList.add(parseInstructions(input.get(i)));
        }
        System.out.println(queueList);

        MissionControl missionControl = new MissionControl(plateauSize, queueList, positionList);

        missionControl.moveRoverPosition();

        missionControl.printRoverPosition();
    }
}