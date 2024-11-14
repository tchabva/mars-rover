package org.northcoders.inputlayer.inputparsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.northcoders.inputlayer.Instruction;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTests {

    @Test
    @DisplayName("Returns valid instruction with a single uppercase character string input")
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
    @DisplayName("Returns valid instruction with a single any case character string input")
    void testInstructionParserIsCaseSafe(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String l = "L";
        String lowercaseL = "l";

        // Act
        Queue<Instruction> resultL = instructionParser.parseInstructions(l);
        Queue<Instruction> resultLowercaseL = instructionParser.parseInstructions(lowercaseL);


        // Assert
        assertAll("Confirms the Instruction parser returns a Queue with a single index for single strings",
                () -> assertEquals(Instruction.L, resultL.remove()),
                () -> assertEquals(Instruction.L, resultLowercaseL.remove())
        );
    }

    @Test
    @DisplayName("Returns null instruction console statement with a single character invalid string input")
    void testInstructionParserWithInvalidInput(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String o = "O";
        String lowercaseO = "o";
        String empty = "";

        // Act
        Queue<Instruction> result = instructionParser.parseInstructions(o);
        Queue<Instruction> resultTwo = instructionParser.parseInstructions(lowercaseO);
        Queue<Instruction> resultEmpty = instructionParser.parseInstructions(empty);

        // Assert
        assertAll("Confirms the Instruction parser returns a Queue with a single index for single strings",
                () -> assertNull(result),
                () -> assertNull(resultTwo),
                () -> assertNull(resultEmpty)
        );
    }

    @Test
    @DisplayName("Returns valid instruction on with a multiple character valid string input")
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

    @Test
    @DisplayName("Returns null instruction console statement with a multiple character invalid string input")
    void testInstructionParserIncorrectLongerString(){
        // Arrange
        InstructionParser instructionParser = new InstructionParser();
        String input = "LRMMORM";

        // Act
        Queue<Instruction> result = instructionParser.parseInstructions(input);


        // Assert
        assertNull(result);
    }
}