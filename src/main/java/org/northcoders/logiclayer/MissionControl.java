package org.northcoders.logiclayer;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MissionControl {
    private final List<Position> roverPositions;
    private final List<Queue<Instruction>> roverInstructions;
    private final List<Rover> roverList = new ArrayList<>();
    private Plateau plateau;

    public MissionControl(PlateauSize plateauSize, List<Queue<Instruction>> roverInstructions, List<Position> roverPositions) {
        this.roverInstructions = roverInstructions;
        this.roverPositions = roverPositions;
        createPlateau(plateauSize);
    }

    // Creates a list of rovers
    private void createRovers (List<Position> roverPositions){
        roverPositions.forEach(position -> roverList.add(new Rover(position)));
    }

    // creates a Plateau object, calls the createRovers method and assigns the list to the list in Plateau
    public void createPlateau(PlateauSize plateauSize){
        plateau = new Plateau(plateauSize);
        createRovers(roverPositions);
        plateau.getRoverList().addAll(roverList);
    }

    // Moves the Rover one instruction at a time and checks if the position is free and valid before moving the Rover.
    public void moveRoverPosition(int i){
        Rover rover = roverList.get(i);
        Queue<Instruction> instructionList = roverInstructions.get(i);

        instructionList.forEach( instruction -> {

            Position nextPosition = rover.nextPosition(instruction);
            boolean isPositionEmpty = plateau.isPositionFree.test(nextPosition.getX(), nextPosition.getY());
            boolean isPositionValid  = plateau.isValidPosition.test(nextPosition.getX(), nextPosition.getY());
            if (isPositionEmpty && isPositionValid){
                rover.setPosition(nextPosition);
            }
            instructionList.remove();

        });
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public List<Rover> getRoverList() {
        return roverList;
    }

    public void printRoverPosition() {
        this.roverList.forEach(System.out::println);
    }
}
