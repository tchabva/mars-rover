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
    private Scanner scanner;
    private String nextLine;
    private PlateauSizeParser plateauSizeParser;
    private PositionParser positionParser;
    private InstructionParser instructionParser;
    private PlateauSize plateauSize;
    private List<Position> positions = new ArrayList<>();
    private List<Queue<Instruction>> instrusctions = new ArrayList<>();

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
                "Choose the size of the plateau you want to explore\n" +
                        "Input the length and the width of the area in the following format \"10 10\" or \"4 4\":\n"
        );

        //While loop for obtaining a valid plateau dimension input
        while (!plateauSizeParser.isValidPlateauDimensions()){
            String inputString = scanner.nextLine();
            this.plateauSize = plateauSizeParser.parsePlateauSize(inputString);
        }

        System.out.println(plateauSize.toString());

    }
    // TODO: Diagram and how I want the Mars Rover project to work
}
