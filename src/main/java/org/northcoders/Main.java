package org.northcoders;

import org.northcoders.userinputlayer.UserInterface;

public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();

        userInterface.greeting();

        userInterface.getPlateauInput();

        userInterface.getPositionAndInstructions();
    }
}