package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void shouldOrientateCorrectly() {
        // Given
        Animal animal = new Animal();

        // When & Then
        animal.move(MoveDirection.RIGHT, null);
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.RIGHT, null);
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT, null);
        assertEquals(MapDirection.EAST, animal.getOrientation());
    }

    @Test
    void shouldMoveForwardCorrectly() {
        // Given
        Animal animal = new Animal(new Vector2d(2, 2));

        RectangularMap map = new RectangularMap(10, 10);

        // When
        animal.move(MoveDirection.FORWARD, map);

        // Then
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    void shouldMoveBackwardCorrectly() {
        // Given
        Animal animal = new Animal(new Vector2d(2, 2));
        RectangularMap map = new RectangularMap(10, 10);

        // When
        animal.move(MoveDirection.BACKWARD, map);

        // Then
        assertEquals(new Vector2d(2, 1), animal.getPosition());
    }
}