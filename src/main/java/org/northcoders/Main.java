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

        missionControl.moveRovers();
    }
}