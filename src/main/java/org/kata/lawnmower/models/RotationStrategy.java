package org.kata.lawnmower.models;


import org.kata.lawnmower.enums.Orientation;

public interface RotationStrategy {
    Orientation rotateLeft(Orientation orientation);

    Orientation rotateRight(Orientation orientation);


}

