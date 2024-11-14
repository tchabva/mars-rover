package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.Instruction;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTests {

    @Test
    @DisplayName("Returns valid instruction with a single character string input")
    void testInstructionParser(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String l = "L";
        String r = "R";
        String m = "M";

        // Act
        Queue<Instruction> resultL = instructionParser.parseInstructions(l);
        Queue<Instruction> resultR = instructionParser.parseInstructions(r);
        Queue<Instruction> resultM = instructionParser.parseInstructions(m);

        // Assert
        assertAll("Confirms the Instruction parser returns a Queue with a single index for single strings",
                () -> assertEquals(Instruction.L, resultL.remove()),
                () -> assertEquals(Instruction.R, resultR.remove()),
                () -> assertEquals(Instruction.M, resultM.remove())
        );
    }

    @Test
    @DisplayName("Returns valid instruction on with a single valid string input")
    void testInstructionParserLongerString(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String input = "LRMMRM";

        // Act
        Queue<Instruction> result = instructionParser.parseInstructions(input);

        // Assert
        assertAll("Confirms the Instruction parser returns a Queue with the correct instructions for a long input String",
                () -> assertEquals(Instruction.L, result.remove()),
                () -> assertEquals(Instruction.R, result.remove()),
                () -> assertEquals(Instruction.M, result.remove()),
                () -> assertEquals(Instruction.M, result.remove()),
                () -> assertEquals(Instruction.R, result.remove()),
                () -> assertEquals(Instruction.M, result.remove())
        );
    }

}