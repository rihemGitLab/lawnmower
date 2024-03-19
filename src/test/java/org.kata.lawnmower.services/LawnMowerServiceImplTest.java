package org.kata.lawnmower.services;

import org.junit.jupiter.api.Test;
import org.kata.lawnmower.enums.Orientation;
import org.kata.lawnmower.exceptions.InvalidPositionException;
import org.kata.lawnmower.models.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

 class LawnMowerServiceTest {

    @Test
    // Test for empty mower inputs list
    // Implication: Ensures graceful handling of scenarios with no mowers for execution
    public void testEmptyMowerInputsList() {
        LawnMowerServiceImpl service = new LawnMowerServiceImpl(new DefaultRotationStrategy());
        List<MowerInput> mowerInputs = new ArrayList<>();
        assertDoesNotThrow(() -> service.executeInstructions(mowerInputs));
    }

    @Test
    // Test for single mower with no instructions
    // Implication: Verifies that the mower remains stationary when no instructions are provided
    public void testSingleMowerNoInstructions() throws InvalidPositionException {
        LawnMowerServiceImpl service = new LawnMowerServiceImpl(new DefaultRotationStrategy());
        List<MowerInput> mowerInputs = new ArrayList<>();
        Position initialPosition = new Position(1, 2, Orientation.N);
        MowerInput mowerInput = new MowerInput(new Mower(initialPosition, new DefaultRotationStrategy()), "", 5, 5);
        mowerInputs.add(mowerInput);
        service.executeInstructions(mowerInputs);
        assertEquals(initialPosition, mowerInput.getMower().getPosition());
    }



   @Test
    // Test for empty mower inputs list
    // Implication: This test ensures that the system gracefully handles the scenario where no mowers are provided for execution.
    // It verifies that the system does not encounter any errors or unexpected behavior when there are no mowers to process.
    public void testExecuteInstructions_EmptyMowerInputsList() {
        // Arrange
        LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());

        // Act & Assert
        assertDoesNotThrow(() -> lawnMowerService.executeInstructions(new ArrayList<>()));
    }

    @Test
    // Test for single mower with no instructions
    // Implication: This test verifies that a mower remains stationary when no instructions are provided.
    // It ensures that the mower's position does not change when it receives an empty instruction set.
    public void testExecuteInstructions_SingleMower_NoInstructions() throws InvalidPositionException {
        // Arrange
        LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
        Position initialPosition = new Position(0, 0, Orientation.N);
        MowerInput mowerInput = new MowerInput(new Mower(initialPosition, new DefaultRotationStrategy()), "",5, 5);

        // Act
        lawnMowerService.executeInstructions(List.of(mowerInput));

        // Assert
        assertEquals(initialPosition, mowerInput.getMower().getPosition());
    }

    @Test
    // Test for single mower with invalid instructions
    // Implication: This test validates the system's behavior when invalid instructions are provided for a mower.
    // It ensures that the system properly handles and ignores invalid instructions, preventing any unintended changes to the mower's position.
    public void testExecuteInstructions_SingleMower_InvalidInstructions() throws InvalidPositionException {
        // Arrange
        LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
        Position initialPosition = new Position(0, 0, Orientation.N);
        MowerInput mowerInput = new MowerInput(new Mower(initialPosition, new DefaultRotationStrategy()), "XYZ",5, 5);

        // Act
        lawnMowerService.executeInstructions(List.of(mowerInput));

        // Assert
        assertEquals(initialPosition, mowerInput.getMower().getPosition());
    }

    @Test
    // Test for multiple mowers with valid instructions
    // Implication: This test validates the system's ability to execute instructions for multiple mowers simultaneously.
    // It ensures that each mower moves according to its instruction set and that their final positions are correctly determined.
    public void testExecuteInstructions_MultipleMowers_ValidInstructions() throws InvalidPositionException {
        // Arrange
        LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
        Position initialPosition1 = new Position(1, 2, Orientation.N);
        Position initialPosition2 = new Position(3, 3, Orientation.E);
        MowerInput mowerInput1 = new MowerInput(new Mower(initialPosition1, new DefaultRotationStrategy()), "GAGAGAGAA",5, 5);
        MowerInput mowerInput2 = new MowerInput(new Mower(initialPosition2, new DefaultRotationStrategy()), "AADAADADDA",5, 5);

        // Act
        lawnMowerService.executeInstructions(List.of(mowerInput1, mowerInput2));

        // Assert
        assertEquals("1 3 N", printPosition(mowerInput1.getMower().getPosition()));
        assertEquals("5 1 E", printPosition(mowerInput2.getMower().getPosition()));
    }



    public String printPosition(Position position){
        return position.getX() + " " + position.getY() + " " + position.getOrientation();

    }

     @Test
// Test for single mower with valid instructions
// Implication: Verifies that a single mower correctly follows the provided instructions and reaches the expected final position.
     public void testExecuteInstructions_SingleMower_ValidInstructions() throws InvalidPositionException {
         // Arrange
         LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
         Position initialPosition = new Position(1, 2, Orientation.N);
         MowerInput mowerInput = new MowerInput(new Mower(initialPosition, new DefaultRotationStrategy()), "GAGAGAGAA", 5, 5);

         // Act
         lawnMowerService.executeInstructions(List.of(mowerInput));

         // Assert
         assertEquals("1 3 N", printPosition(mowerInput.getMower().getPosition()));
     }

     @Test
// Test for multiple mowers with mixed instructions
// Implication: Ensures that the system correctly handles scenarios with multiple mowers having different instruction sets.
// It verifies that each mower moves independently according to its instructions, regardless of the other mowers.
     public void testExecuteInstructions_MultipleMowers_MixedInstructions() throws InvalidPositionException {
         // Arrange
         LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
         Position initialPosition1 = new Position(1, 2, Orientation.N);
         Position initialPosition2 = new Position(3, 3, Orientation.E);
         MowerInput mowerInput1 = new MowerInput(new Mower(initialPosition1, new DefaultRotationStrategy()), "GAGAGAGAA", 5, 5);
         MowerInput mowerInput2 = new MowerInput(new Mower(initialPosition2, new DefaultRotationStrategy()), "AADAADADDA", 5, 5);

         // Act
         lawnMowerService.executeInstructions(List.of(mowerInput1, mowerInput2));

         // Assert
         assertEquals("1 3 N", printPosition(mowerInput1.getMower().getPosition()));
         assertEquals("5 1 E", printPosition(mowerInput2.getMower().getPosition()));
     }

     @Test
// Test for single mower with out-of-bounds final position
// Implication: Validates that the system properly handles scenarios where a mower's final position exceeds the lawn boundaries.
// It ensures that the mower's position is capped within the defined lawn dimensions.
     public void testExecuteInstructions_SingleMower_OutOfBoundsPosition() throws InvalidPositionException {
         // Arrange
         LawnMowerService lawnMowerService = new LawnMowerServiceImpl(new DefaultRotationStrategy());
         Position initialPosition = new Position(5, 5, Orientation.N);
         MowerInput mowerInput = new MowerInput(new Mower(initialPosition, new DefaultRotationStrategy()), "AAAAAAAAAA", 5, 5);

         // Act
         lawnMowerService.executeInstructions(List.of(mowerInput));

         // Assert
         assertEquals("5 5 N", printPosition(mowerInput.getMower().getPosition())); // Mower should remain stationary
     }
}