package org.northcoders;

import org.northcoders.logiclayer.MissionControl;
import org.northcoders.userinputlayer.UserInterface;

public class Main {
    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();

        MissionControl missionControl = new MissionControl(
                userInterface.getPlateauSize(),
                userInterface.getInstructionsQueueList(),
                userInterface.getPositions()
        );

        System.out.println("\nThe initial position(s) of your Rovers");
        missionControl.printRoverPosition();

        System.out.println("\nMoving the Rovers...");
        missionControl.moveRoverPosition();

        System.out.println("\nThe final position(s) of your Rovers");
        missionControl.printRoverPosition();
    }
}