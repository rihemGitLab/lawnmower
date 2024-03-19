package org.kata.lawnmower;

import org.kata.lawnmower.exceptions.InvalidPositionException;
import org.kata.lawnmower.models.*;
import org.kata.lawnmower.services.LawnMowerService;
import org.kata.lawnmower.services.LawnMowerServiceImpl;
import org.kata.lawnmower.utils.FileParser;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = "input.txt";

        try {

            // Parse input file
            List<MowerInput> mowerInputs = FileParser.parseInputFile(filePath);

            // Initialize lawn dimensions (assuming they are provided in the input file)
            if (!mowerInputs.isEmpty()) {
                MowerInput firstMowerInput = mowerInputs.get(0);
                Mower firstMower = firstMowerInput.getMower();
                Position position = firstMower.getPosition();
            }

            // Initialize rotation strategy (assuming it's provided or implemented elsewhere)
            RotationStrategy rotationStrategy = new DefaultRotationStrategy();

            // Initialize LawnMowerService
            LawnMowerService lawnMowerService = new LawnMowerServiceImpl(rotationStrategy);

            // Execute instructions for each mower
            lawnMowerService.executeInstructions(mowerInputs);
            // Output final positions of mowers
            lawnMowerService.printFinalPositions(mowerInputs);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (InvalidPositionException e) {
            System.err.println("Invalid initial position for mower: " + e.getMessage());
        }
    }


}
