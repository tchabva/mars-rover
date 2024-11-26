package org.northcoders.logiclayer;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MissionControl {
    private final List<Queue<Instruction>> roverInstructions = new ArrayList<>();
    private final List<Rover> roverList = new ArrayList<>();
    private Plateau plateau;

    /*
    MissionControl constructor creates the plateau, the list of Rover objects and then a List of Instruction
    Queues
     */
    public MissionControl(PlateauSize plateauSize, List<Queue<Instruction>> roverInstructions, List<Position> roverPositions) {
        try {
            createPlateau(plateauSize);
        }catch (NullPointerException e){
            System.out.println("No Plateau information provided");
        }

        if (roverPositions != null){
            createRovers(roverPositions);
            plateau.getRoverList().addAll(roverList);
        }

        if (roverInstructions != null){
            this.roverInstructions.addAll(roverInstructions);
        }
    }

    // Creates the list of rovers from the list of Position objects
    private void createRovers (List<Position> roverPositions){
        roverPositions.forEach(position -> roverList.add(new Rover(position)));
    }

    // creates a Plateau object, calls the createRovers method and assigns the list to the list in Plateau
    private void createPlateau(PlateauSize plateauSize){
        plateau = new Plateau(plateauSize);
    }

    // Moves the Rover one instruction at a time and checks if the position is free and valid before moving the Rover.
    public void moveRoverPosition(){

        for (int i = 0; i < roverList.size(); i++) {
            Rover rover = roverList.get(i);
            Queue<Instruction> currentRoverInstructions = roverInstructions.get(i);
            while (!currentRoverInstructions.isEmpty()){
                Position nextPosition = rover.nextPosition(currentRoverInstructions.remove());
                boolean isPositionEmpty = plateau.isPositionEmpty(nextPosition, rover);
                boolean isPositionValid  = plateau.isPositionValid(nextPosition.x(), nextPosition.y());
                if (isPositionEmpty && isPositionValid){
                    rover.setPosition(nextPosition);
                }
            }
        }
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
