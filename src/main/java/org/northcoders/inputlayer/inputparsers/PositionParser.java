package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.CompassDirection;
import org.northcoders.inputlayer.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionParser {
    private boolean isValidPosition;

    public Position positionParser(String input){
        if (input == null){
            System.out.println("Please enter a valid Position input!");
            isValidPosition = false;
            return null;
        } else if (!isValidString.test(input)) {
            System.out.println("Please enter a valid Position input!");
            isValidPosition = false;
            return null;
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
                return null;
            }
        }
    }

    private final Predicate<String> isValidString = input ->{
        Pattern pattern = Pattern.compile("(\\d+) (\\d+) [NWSE]{1}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    };

    public Boolean isLandingPositionFree(List<Position> positions, Position newPosition){
        if(newPosition == null){
            return false;
        }

        if (!positions.isEmpty()){
            for (Position position : positions){
                if (newPosition.x() == position.x() && newPosition.y() == position.y()){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidPosition() {
        return isValidPosition;
    }
}
