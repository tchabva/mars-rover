package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.Instruction;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ParserTests {

    @Test
    @DisplayName("Returns valid instruction on with valid string input")
    void testInstructionParser(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String l = "L";
        String r = "M";
        String m = "R";

        // Act
        Queue<Instruction> resultL = instructionParser.parseInstructions(l);
        Queue<Instruction> resultR = instructionParser.parseInstructions(r);
        Queue<Instruction> resultM = instructionParser.parseInstructions(m);

        // Assert
        assertAll("Confirms the Instruction parser returns a Queue with a single index for single strings",
                () -> assertEquals(Instruction.L, resultL.peek()),
                () -> assertEquals(Instruction.R, resultR.peek()),
                () -> assertEquals(Instruction.M, resultM.peek())
        );

    }

}