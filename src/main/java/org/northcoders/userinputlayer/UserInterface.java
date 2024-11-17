package org.northcoders.userinputlayer;

import org.northcoders.inputlayer.inputparsers.InstructionParser;
import org.northcoders.inputlayer.inputparsers.PlateauSizeParser;
import org.northcoders.inputlayer.inputparsers.PositionParser;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private String nextLine;
    private PlateauSizeParser plateauSizeParser;
    private PositionParser positionParser;
    private InstructionParser instructionParser;

    public UserInterface() {
        this.scanner = new Scanner(System.in);

        this.plateauSizeParser = new PlateauSizeParser();
        this.positionParser = new PositionParser();
        this.instructionParser = new InstructionParser();
    }

    // TODO: Diagram and how I want the Mars Rover project to work
}
