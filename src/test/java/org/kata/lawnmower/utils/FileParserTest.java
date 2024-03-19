package org.kata.lawnmower.utils;

import org.junit.jupiter.api.Test;
import org.kata.lawnmower.models.MowerInput;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileParserTest {

    @Test
    public void testParseInputFile_EmptyFile() {
        try {
            List<MowerInput> result = FileParser.parseInputFile("empty_input.txt");
            assertEquals(0, result.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseInputFile_SingleMower() {
        try {
            List<MowerInput> result = FileParser.parseInputFile("single_mower_input.txt");
            assertEquals(1, result.size());
            MowerInput input = result.get(0);
            assertEquals(5, input.getLawnWidth());
            assertEquals(5, input.getLawnHeight());
            assertEquals(1, input.getMower().getPosition().getX());
            assertEquals(2, input.getMower().getPosition().getY());
            assertEquals("N", input.getMower().getPosition().getOrientation().toString());
            assertEquals("GAGAGAGAA", input.getInstructions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testParseInputFile_InvalidPosition() {
        try {
            FileParser.parseInputFile("invalid_test_input.txt");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        } catch (IOException e) {
            fail("Unexpected IOException was thrown");
        }
    }
}
