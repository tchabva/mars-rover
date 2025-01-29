package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.Instruction;

import java.util.LinkedList;
import java.util.Queue;

public class InstructionParser {

    private boolean isValidInstruction;

    // Takes an input and returns Queue of Instructions if the input is valid.
    // Assigns isValidInstruction to true or false depending on the input.
    public Queue<Instruction> parseInstructions(String input) {
        Queue<Instruction> instructions = new LinkedList<>();

        if (input == null){
            System.out.println("Please enter a valid Instruction!");
            isValidInstruction = false;
            return null;
        }else if (input.isBlank()){
            System.out.println("No instructions submitted!");
            isValidInstruction = false;
            return null;
        } else {
            String[] instructionStringArray = input.split("");

            for (String inst : instructionStringArray){
                try{
                    instructions.add(Instruction.valueOf(inst.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter a valid Instruction!");
                    isValidInstruction = false;
                    return null;
                }
            }
            isValidInstruction = true;
            return instructions;
        }
    }

    public boolean isValidInstruction() {
        return isValidInstruction;
    }
}