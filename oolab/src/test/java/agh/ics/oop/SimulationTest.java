package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    @Test
    void testSimpleRotation() {
        // Given
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"r", "r", "l"});
        Simulation simulation = new Simulation(map, positions, moves);

        // When
        simulation.run();

        // Then
        Animal animal = (Animal) map.objectAt(new Vector2d(2, 2));
        assertNotNull(animal);
        assertEquals(MapDirection.EAST, animal.getOrientation());
        assertEquals(new Vector2d(2, 2), animal.getPosition());
    }

    @Test
    void testSimpleForwardMovement() {
        // Given
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "f"});
        Simulation simulation = new Simulation(map, positions, moves);

        // When
        simulation.run();

        // Then
        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal animal = (Animal) map.objectAt(new Vector2d(2, 4));
        assertNotNull(animal);
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void testMovementIsBlockedByBoundary() {
        // Given
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2, 4)); // Zaczyna przy górnej krawędzi
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "f", "f"});
        Simulation simulation = new Simulation(map, positions, moves);

        // When
        simulation.run();

        // Then
        Animal animal = (Animal) map.objectAt(new Vector2d(2, 4));
        assertNotNull(animal);
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void testMovementIsBlockedByAnotherAnimal() {
        // Given
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 3));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "r"});
        Simulation simulation = new Simulation(map, positions, moves);

        // When
        simulation.run();

        // Then
        Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 2));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(2, 3));

        assertNotNull(animal1);
        assertEquals(MapDirection.NORTH, animal1.getOrientation());

        assertNotNull(animal2);
        assertEquals(MapDirection.EAST, animal2.getOrientation());
    }

    @Test
    void testPlacingAnimalOnOccupiedPositionFails() {
        // Given
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 2));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{});

        // When

        Simulation simulation = new Simulation(map, positions, moves);

        // Then
        List<Animal> animalsInSim = simulation.getAnimals();
        assertEquals(1, animalsInSim.size());

        assertNotNull(map.objectAt(new Vector2d(2, 2)));
        assertEquals(animalsInSim.get(0), map.objectAt(new Vector2d(2, 2)));
    }
}