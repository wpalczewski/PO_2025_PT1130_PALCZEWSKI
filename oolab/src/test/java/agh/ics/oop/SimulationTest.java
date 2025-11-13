package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    private List<Animal> runSimulation(String[] args, List<Vector2d> initialPositions) {
        // Given
        List<MoveDirection> directions = OptionsParser.parse(args);
        Simulation simulation = new Simulation(initialPositions, directions);

        // When
        simulation.run();

        return simulation.getAnimals();
    }

    @Test
    void shouldAnimalsEndUpOnCorrectFinalPositionsAndOrientations() {
        // Given
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> initialPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        // When
        List<Animal> animals = runSimulation(moves, initialPositions);

        // Then
        assertEquals(new Vector2d(1, 2), animals.get(0).getPosition());
        assertEquals(MapDirection.WEST, animals.get(0).getOrientation());

        assertEquals(new Vector2d(4, 4), animals.get(1).getPosition());
        assertEquals(MapDirection.EAST, animals.get(1).getOrientation());
    }

    @Test
    void shouldBlockForwardMovementAtMapBoundary() {
        // Given
        List<Vector2d> initialPositions = List.of(new Vector2d(4, 4), new Vector2d(0, 3));
        String[] moves = {"f", "f", "f", "f"};

        // When
        List<Animal> animals = runSimulation(moves, initialPositions);

        // Then
        assertEquals(new Vector2d(4, 4), animals.get(0).getPosition());
        assertEquals(new Vector2d(0, 4), animals.get(1).getPosition());
    }

    @Test
    void shouldBlockBackwardMovementAtMapBoundary() {
        // Given
        List<Vector2d> initialPositions = List.of(new Vector2d(0, 0), new Vector2d(4, 1));
        String[] moves = {"b", "b", "b", "b"};

        // When
        List<Animal> animals = runSimulation(moves, initialPositions);

        // Then
        assertEquals(new Vector2d(0, 0), animals.get(0).getPosition());
        assertEquals(new Vector2d(4, 0), animals.get(1).getPosition());
    }

    @Test
    void shouldHandleParsingOfInputArguments() {
        // Given
        String[] args = {"f", "invalid", "r", "l", "przod", "b"};
        List<Vector2d> initialPositions = List.of(new Vector2d(2, 2));

        // When
        List<Animal> animals = runSimulation(args, initialPositions);

        // Then
        assertEquals(new Vector2d(2, 2), animals.get(0).getPosition());
        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
    }
}