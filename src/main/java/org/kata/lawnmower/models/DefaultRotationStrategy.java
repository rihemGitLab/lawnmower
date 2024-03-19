package org.kata.lawnmower.models;


import org.kata.lawnmower.enums.Orientation;

public class DefaultRotationStrategy implements RotationStrategy {
    @Override
    public Orientation rotateLeft(Orientation orientation) {
        switch (orientation) {
            case N:
                return Orientation.W;
            case E:
                return Orientation.N;
            case S:
                return Orientation.E;
            case W:
                return Orientation.S;
            default:
                throw new IllegalArgumentException("Invalid orientation");
        }
    }

    @Override
    public Orientation rotateRight(Orientation orientation) {
        switch (orientation) {
            case N:
                return Orientation.E;
            case E:
                return Orientation.S;
            case S:
                return Orientation.W;
            case W:
                return Orientation.N;
            default:
                throw new IllegalArgumentException("Invalid orientation");
        }
    }

}
