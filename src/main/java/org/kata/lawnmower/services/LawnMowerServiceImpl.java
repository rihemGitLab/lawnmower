package org.kata.lawnmower.services;

import org.kata.lawnmower.enums.Orientation;
import org.kata.lawnmower.exceptions.InvalidPositionException;
import org.kata.lawnmower.models.Mower;
import org.kata.lawnmower.models.MowerInput;
import org.kata.lawnmower.models.Position;
import org.kata.lawnmower.models.RotationStrategy;

import java.util.List;


public class LawnMowerServiceImpl implements LawnMowerService {
    private final RotationStrategy rotationStrategy;

    public LawnMowerServiceImpl(RotationStrategy rotationStrategy) {
        this.rotationStrategy = rotationStrategy;
    }

    @Override
    public void executeInstructions(List<MowerInput> mowerInputs) throws InvalidPositionException {
        if (mowerInputs == null || mowerInputs.isEmpty()) {
            return;
        }

        for (MowerInput mowerInput : mowerInputs) {
            Mower mower = mowerInput.getMower();
            String instructions = mowerInput.getInstructions();
            Position position = mower.getPosition();
           if (!isValidPosition(position, mowerInput.getLawnWidth(), mowerInput.getLawnHeight())) {
               throw new InvalidPositionException("Invalid initial position for mower: " + position.getX() + ", " + position.getY());
            }

            instructions.chars().forEach(instruction -> {
                switch (instruction) {
                    case 'G':
                        mower.rotateLeft();
                        break;
                    case 'D':
                        mower.rotateRight();
                        break;
                    case 'A':
                        Position nextPosition = calculateNextPosition(position, mower.getPosition().getOrientation());
                        if (isValidPosition(nextPosition, mowerInput.getLawnWidth(), mowerInput.getLawnHeight())) {
                            mower.getPosition().moveForward(mower.getPosition().getOrientation());
                        }
                        break;
                    default:
                        // Ignore invalid instructions
                        break;
                }
            });
        }
    }

   private boolean isValidPosition(Position position, int lawnWidth, int lawnHeight) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x <= lawnWidth && y >= 0 && y <= lawnHeight;
    }

    private Position calculateNextPosition(Position position, Orientation orientation) {
        Position nextPosition = new Position(position.getX(), position.getY(), orientation);
        nextPosition.moveForward(orientation);
        return nextPosition;
    }
    @Override
    public void printFinalPositions(List<MowerInput> mowerInputs) {
        for (MowerInput mowerInput : mowerInputs) {
            Mower mower = mowerInput.getMower();
            Position position = mower.getPosition();
            System.out.println("Final position: " + position.getX() + " " + position.getY() + " " + position.getOrientation());
        }
    }
}
