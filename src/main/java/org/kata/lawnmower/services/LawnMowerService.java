package org.kata.lawnmower.services;


import org.kata.lawnmower.exceptions.InvalidPositionException;
import org.kata.lawnmower.models.MowerInput;

import java.util.List;


public interface LawnMowerService {
    /**
     * Executes instructions for a list of mowers on a rectangular lawn.
     *
     * @param mowers     The list of mowers to control.
     * @param lawnWidth  The width of the rectangular lawn.
     * @param lawnHeight The height of the rectangular lawn.
     * @param instructions A string representing the instructions for each mower.
     * @throws InvalidPositionException if a mower is placed outside the boundaries of the lawn.
     */
    void executeInstructions(List<MowerInput> mowerInputs) throws InvalidPositionException;
    void printFinalPositions(List<MowerInput> mowerInputs);

}
