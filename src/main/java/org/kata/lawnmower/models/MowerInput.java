package org.kata.lawnmower.models;

public class MowerInput {
    private Mower mower;
    private String instructions;
    private int lawnWidth ;
    private int lawnHeight ;

    public MowerInput(Mower mower, String instructions, int lawnWidth, int lawnHeight) {
        this.mower = mower;
        this.instructions = instructions;
        this.lawnWidth = lawnWidth;
        this.lawnHeight = lawnHeight;
    }

    public int getLawnWidth() {
        return lawnWidth;
    }

    public void setLawnWidth(int lawnWidth) {
        this.lawnWidth = lawnWidth;
    }

    public int getLawnHeight() {
        return lawnHeight;
    }

    public void setLawnHeight(int lawnHeight) {
        this.lawnHeight = lawnHeight;
    }

    public Mower getMower() {
        return mower;
    }

    public void setMower(Mower mower) {
        this.mower = mower;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Mower: " + mower + ", Instructions: " + instructions;
    }
}
