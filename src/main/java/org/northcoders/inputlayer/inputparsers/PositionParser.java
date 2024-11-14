package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Position;

import javax.naming.InsufficientResourcesException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionParser {
    private boolean isValidPosition;

    /*
    TODO: Takes in an input and returns a Position object.
     */

    public Position positionParser(String input){
        if (input == null){
            System.out.println("Please enter a valid Position input!");
            isValidPosition = false;
            return new Position(-1,-1, CompassDirection.N);
        } else if (!isValidString.test(input)) {
            System.out.println("Please enter a valid Position input!");
            isValidPosition = false;
            return new Position(-1,-1, CompassDirection.N);
        } else {
            String[] inputArray = input.split(" ");
            try {
                int x = Integer.parseInt(inputArray[0]);
                int y = Integer.parseInt(inputArray[1]);
                CompassDirection compassDirection  = CompassDirection.valueOf(inputArray[2].toUpperCase());
                isValidPosition = true;
                return new Position(x,y, compassDirection);
            } catch (IllegalArgumentException e){
                System.out.println("Please enter a valid Position input!");
                isValidPosition = false;
                return new Position(-1,-1, CompassDirection.N);
            }
        }
    }

    private final Predicate<String> isValidString = input ->{
        Pattern pattern = Pattern.compile("(\\d+) (\\d+) [NWSE]{1}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    };

    public boolean isValidPosition() {
        return isValidPosition;
    }
}
