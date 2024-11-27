package org.northcoders.userinputlayer;

import org.northcoders.inputlayer.Instruction;
import org.northcoders.inputlayer.PlateauSize;
import org.northcoders.inputlayer.Position;
import org.northcoders.inputlayer.inputparsers.InstructionParser;
import org.northcoders.inputlayer.inputparsers.PlateauSizeParser;
import org.northcoders.inputlayer.inputparsers.PositionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private String nextLine;
    private final PlateauSizeParser plateauSizeParser;
    private final PositionParser positionParser;
    private final InstructionParser instructionParser;
    private PlateauSize plateauSize;
    private final List<Position> positions = new ArrayList<>();
    private final List<Queue<Instruction>> instructions = new ArrayList<>();

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

    public void getPositionInput(){
        System.out.print(
                """
                        
                        Choose where you want to deploy your Rover!
                        Input the X and Y coordinates that are within on Plateau and the direction your Rover is facing.
                        The possible directions are the Compass directions: N = North, E = East, S = South and W = West
                        Please use the following format "0 0 N" or "1 3 E":"""
        );

    }
    // TODO: Diagram and how I want the Mars Rover project to work
}
