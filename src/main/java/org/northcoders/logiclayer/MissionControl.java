package org.northcoders.logiclayer;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import java.util.List;
import java.util.Queue;

public class MissionControl {
    private List<Position> roverPositions;
    private List<Queue<Instruction>> roverInstructions;
    private PlateauSize plateauSize;
    private List<Rover> roverList;

    public MissionControl(PlateauSize plateauSize, List<Queue<Instruction>> roverInstructions, List<Position> roverPositions) {
        this.plateauSize = plateauSize;
        this.roverInstructions = roverInstructions;
        this.roverPositions = roverPositions;
    }

    // Creates a list of rovers
    private void createRovers (List<Position> roverPositions){
        roverPositions.forEach(position -> {
            roverList.add(new Rover(position));
        });
    }

    // creates a Plateau object, calls the createRovers method and assigns the list to the list in Plateau
    public Plateau createPlateau(PlateauSize plateauSize){
        Plateau plateau = new Plateau(plateauSize);
        createRovers(roverPositions);
        plateau.getRoverList().addAll(roverList);
        return plateau;
    }

    public void moveRoverPosition(int i){
        Rover rover = roverList.get(i);
        Queue<Instruction> instructionList = roverInstructions.get(i);

        instructionList.forEach( instruction -> {
            // TODO implement the compass methods from the TDD Sprint
            // TODO: decide the logic for the process
            // TOD: method that moves the compass axis based on the compass compass directiongi


        });


    }

}
