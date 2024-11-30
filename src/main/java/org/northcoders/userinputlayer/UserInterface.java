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
import java.util.function.Supplier;

public class UserInterface {
    private Scanner scanner;
    private String nextLine;
    private final PlateauSizeParser plateauSizeParser;
    private final PositionParser positionParser;
    private final InstructionParser instructionParser;
    private PlateauSize plateauSize;
    private final List<Position> positions = new ArrayList<>();
    private final List<Queue<Instruction>> instructionsQueueList = new ArrayList<>();

    public UserInterface() {
        this.scanner = new Scanner(System.in);

        this.plateauSizeParser = new PlateauSizeParser();
        this.positionParser = new PositionParser();
        this.instructionParser = new InstructionParser();
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
    public void getPositionInput(){
        boolean isPositionOnPlateau = false;
        System.out.print(
                """
                        
                        Choose where you want to deploy your Rover!
                        Input the X and Y coordinates that are within on Plateau and the direction your Rover is facing.
                        The possible directions are the Compass directions: N = North, E = East, S = South and W = West
                        Please use the following format "0 0 N" or "1 3 E":
                        """
        );

        while (!positionParser.isValidPosition() || !isPositionOnPlateau){
            String inputString = scanner.nextLine();
            Position prospectivePosition = positionParser.positionParser(inputString);
            Plateau plateau = new Plateau(plateauSize);

            if (prospectivePosition != null){
                isPositionOnPlateau = plateau.isPositionValid(prospectivePosition.x(), prospectivePosition.y());

                if (isPositionOnPlateau && positionParser.isValidPosition()){
                    this.positions.add(prospectivePosition);
                }else if (!isPositionOnPlateau && positionParser.isValidPosition()){
                    System.out.println("Please enter coordinates on the plateau!:");
                }
            }
        }
        System.out.println(positions.getLast());
    }

    // This method takes in the instruction input for the previously inputted rover instruction
    public void getInstructionInput(){
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

        while (!instructionParser.isValidInstruction()){
            String inputString = scanner.nextLine();
            Queue<Instruction> instructions = instructionParser.parseInstructions(inputString);

            if (instructionParser.isValidInstruction()){
                instructionsQueueList.add(instructions);
            }
        }
        System.out.println(instructionsQueueList.getLast());
    }


    public boolean addAnotherRover(){
        String userInput = scanner.nextLine();
        System.out.print(
                """
                        
                        Do you want to add another Rover to your plateau?
                        If so, please input "Y" or "Yes", otherwise press enter to proceed:
                        """
        );
        return userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y");

        /*
         TODO: If I add every new rover to my temporary plateau object, I should be able confirm if the position is free
           Might need a addAnotherRoverInputParser to process the logic for this method and I have to decide whether
           entering a non valid input
         */
        //  is free.
        // Might need anotherRover Input parser and need to decide if not answering yes will keep  you in a loop or

    }

    public Supplier<Boolean> addAnotherRover = () ->{
        String userInput = scanner.nextLine();
        System.out.print(
                """
                        
                        Do you want to add another Rover to your plateau?
                        If so, please input "Y" or "Yes", otherwise press enter to proceed:
                        """
        );
        return userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y");

        /*
         TODO: If I add every new rover to my temporary plateau object, I should be able confirm if the position is free
           Might need a addAnotherRoverInputParser to process the logic for this method and I have to decide whether
           entering a non valid input
         */
        //  is free.
        // Might need anotherRover Input parser and need to decide if not answering yes will keep  you in a loop or
    };

    public void getPositionAndInstructions(){
        getPositionInput();
        getInstructionInput();
    }
}
