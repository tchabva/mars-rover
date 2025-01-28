package org.northcoders.userinputlayer;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;
import org.northcoders.inputlayer.inputparsers.InstructionParser;
import org.northcoders.inputlayer.inputparsers.PlateauSizeParser;
import org.northcoders.inputlayer.inputparsers.PositionParser;
import org.northcoders.logiclayer.Plateau;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final PlateauSizeParser plateauSizeParser;
    private final PositionParser positionParser;
    private final InstructionParser instructionParser;
    private PlateauSize plateauSize;
    private final List<Position> positions = new ArrayList<>();
    private final List<Queue<Instruction>> instructionsQueueList = new ArrayList<>();
    private Boolean addAnotherRover = false;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.plateauSizeParser = new PlateauSizeParser();
        this.positionParser = new PositionParser();
        this.instructionParser = new InstructionParser();

        greeting();
    }

    // Greeting to the user
    public void greeting(){
        System.out.println("Welcome to Mars!\nGet ready to deploy...");
    }

    public void getPlateauInput(){
        System.out.print(
                """
                        Choose the size of the plateau you want to explore
                        Input the length and the width of the area in the following format "10 10" or "4 4":
                        """
        );

        //While loop for obtaining a valid plateau dimension input
        while (!plateauSizeParser.isValidPlateauDimensions()){
            String inputString = scanner.nextLine();
            this.plateauSize = plateauSizeParser.parsePlateauSize(inputString);
        }

        // TODO plateau size toString method override
        System.out.println(plateauSize.toString());
    }

    // This method gets the position coordinates and direction from the user input
    // It checks whether the input is a valid coordinate format and if the position is within the plateau
    private void getPositionInput(){
        boolean isPositionOnPlateau = false;
        boolean isPositionEmpty = false;
        System.out.print(
                """
                        
                        Choose where you want to deploy your Rover!
                        Input the X and Y coordinates that are within on Plateau and the direction your Rover is facing.
                        The possible directions are the Compass directions: N = North, E = East, S = South and W = West
                        Please use the following format "0 0 N" or "1 3 E":
                        """
        );

        while (!positionParser.isValidPosition() || !isPositionOnPlateau || !isPositionEmpty){
            String inputString = scanner.nextLine();
            Position prospectivePosition = positionParser.positionParser(inputString);
            Plateau plateau = new Plateau(plateauSize);

            isPositionEmpty = positionParser.isLandingPositionFree(positions, prospectivePosition);
            if (prospectivePosition != null){
                isPositionOnPlateau = plateau.isPositionValid(prospectivePosition.x(), prospectivePosition.y());
//                if (!positions.isEmpty()){
//
//                }

                if (isPositionOnPlateau && positionParser.isValidPosition() && isPositionEmpty){
                    this.positions.add(prospectivePosition);
                }else if (!isPositionOnPlateau && positionParser.isValidPosition()){
                    System.out.println("Please enter coordinates on the plateau!:");
                } else if (!isPositionEmpty){
                    System.out.println("There is already a Rover here, please enter different coordinates within the plateau!:");
                }
            }
        }
        System.out.println(positions.getLast());
    }

    // This method takes in the instruction input for the previously inputted rover instruction
    private void getInstructionInput(){
        boolean isValidInstruction = false;
               System.out.print(
                """
                        
                        Now it is time to choose how you want to move your Rover!
                        The the three possible instructions are:
                        R = turn your Rover by one compass direction to the right e.g If your is facing North, it will now face West.
                        L = turn your Rover by one compass direction to the left e.g. If your Rover's facing North, it will now face East.
                        M = move your Rover one position in the direction it is facing.
                        Please enter your instructions in the following format "RMLRM":
                        """
        );

        while (!isValidInstruction){
            String inputString = scanner.nextLine();
            Queue<Instruction> instructions = instructionParser.parseInstructions(inputString);

            if (instructionParser.isValidInstruction()){
                instructionsQueueList.add(instructions);
                isValidInstruction = instructionParser.isValidInstruction();
            }
        }
        System.out.println(instructionsQueueList.getLast());
    }

    private void addAnotherRover(){
        System.out.print(
                """
                        
                        Do you want to add another Rover to your plateau?
                        If so, please input "Y" or "Yes", otherwise press enter to proceed:
                        """
        );

        String userInput = scanner.nextLine().toUpperCase();
        addAnotherRover = userInput.equals("YES") || userInput.equals("Y");
    }

    public void getPositionAndInstructions(){
        /*
        TODO: Add logic for confirming that Rover is not in the same place as another rover
        LOOK INTO CONSOLE TERMINAL BUG FOR Instruction
        THERE ARE BUGS TO DEAL WITH!!!
         */

        do {
            getPositionInput();
            getInstructionInput();
            addAnotherRover();
        } while (addAnotherRover);

    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Queue<Instruction>> getInstructionsQueueList() {
        return instructionsQueueList;
    }
}