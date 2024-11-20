package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.PlateauSize;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlateauSizeParser {
    private boolean isValidPlateauDimensions;
// TODO Update testing
    public PlateauSize parsePlateauSize(String input){
        String retryString = "Try again! Please enter a valid plateau size input:";

        if (input == null){
            System.out.println(retryString);
            isValidPlateauDimensions = false;
            return new PlateauSize(-1,-1);
        } else if (!isValidString.test(input)) {
            System.out.println(retryString);
            isValidPlateauDimensions = false;
            return new PlateauSize(-1,-1);
        }else {
            String[] inputArray = input.split(" ");
            try {
                int x = Integer.parseInt(inputArray[0]);
                int y = Integer.parseInt(inputArray[1]);
                isValidPlateauDimensions = x >= 0 && y >= 0;
                return new PlateauSize(x, y);
            } catch (NumberFormatException e) {
                System.out.println(retryString);
                isValidPlateauDimensions = false;
                return new PlateauSize(-1,-1);
            }
        }
    }

    private final Predicate<String> isValidString = input ->{
        Pattern pattern = Pattern.compile("(\\d+) (\\d+)");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    };

    public boolean isValidPlateauDimensions() {
        return isValidPlateauDimensions;
    }
}
