package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.PlateauSize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Checks that the Plateau size inputs from the user are valid
public class PlateauSizeParser {
    private boolean isValidPlateauDimensions;

    public PlateauSize parsePlateauSize(String input) {
        String retryString = "Try again! Please enter a valid plateau size input:";

        if (input == null || !isValidString(input)) {
            System.out.println(retryString);
            isValidPlateauDimensions = false;
            return null;
        } else {
            String[] inputArray = input.split(" ");
            try {
                int x = Integer.parseInt(inputArray[0]);
                int y = Integer.parseInt(inputArray[1]);
                isValidPlateauDimensions = x >= 0 && y >= 0;
                return new PlateauSize(x, y);
            } catch (NumberFormatException e) {
                System.out.println(retryString);
                isValidPlateauDimensions = false;
                return null;
            }
        }
    }

    private boolean isValidString(String input) {
        Pattern pattern = Pattern.compile("(\\d+) (\\d+)");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean isValidPlateauDimensions() {
        return isValidPlateauDimensions;
    }
}