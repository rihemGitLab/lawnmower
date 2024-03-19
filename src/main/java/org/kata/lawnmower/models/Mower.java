package org.kata.lawnmower.models;




public class Mower {
    private Position position;
    private RotationStrategy rotationStrategy;

    public Mower(Position position, RotationStrategy rotationStrategy) {
        this.position = position;
        this.rotationStrategy = rotationStrategy;
    }

    public void moveForward() {
        position.moveForward(position.getOrientation());
    }

    public void rotateLeft() {
        position.setOrientation(rotationStrategy.rotateLeft(position.getOrientation()));
    }

    public void rotateRight() {
        position.setOrientation(rotationStrategy.rotateRight(position.getOrientation()));
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public RotationStrategy getRotationStrategy() {
        return rotationStrategy;
    }

    public void setRotationStrategy(RotationStrategy rotationStrategy) {
        this.rotationStrategy = rotationStrategy;
    }
}
