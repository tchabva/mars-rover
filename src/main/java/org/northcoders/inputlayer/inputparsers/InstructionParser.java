package org.northcoders.inputlayer.inputparsers;

import org.northcoders.inputlayer.Instruction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InstructionParser {
    private String instructionString;
    private String[] instructionStringArray;
    private Queue<Instruction> instructions = new LinkedList<>();
    private boolean isValidInstruction;

    // TODO: create method to accept an input string, parses the input string to an ArrayList of instructions

    public Queue<Instruction> parseInstructions(String input) {
        if (input == null){
            System.out.println("Please enter a valid Instruction!");
            isValidInstruction = false;
            return null;
        }else if (input.equals("")){
            System.out.println("No instructions submitted!");
            isValidInstruction = false;
            return null;
        } else {
            instructionStringArray = input.split("");

            for (String inst : instructionStringArray){
                try{
                    instructions.add(Instruction.valueOf(inst.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter a valid Instruction!");
                    isValidInstruction = false;
                    return null;
                }
            }
        }
        isValidInstruction = true;
        return instructions;
    }

    public boolean isValidInstruction() {
        return isValidInstruction;
    }
}
