package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void shouldPlaceAnimalOnEmptyField() throws IncorrectPositionException {
        // Given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));

        // When
        map.place(animal);

        // Then
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void shouldThrowExceptionWhenPlacingAnimalOnOccupiedField() throws IncorrectPositionException {
        // Given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        map.place(animal1);

        // When & Then
        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal2);
        });
    }

    @Test
    void shouldNotAllowAnimalToMoveOutOfMap() throws IncorrectPositionException {
        // Given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        // When
        map.move(animal, MoveDirection.BACKWARD);

        // Then
        assertEquals(new Vector2d(0, 0), animal.getPosition());
    }

    @Test
    void shouldReturnCorrectBounds() {
        // Given
        RectangularMap map = new RectangularMap(10, 10);

        // When
        Boundary bounds = map.getCurrentBounds();

        // Then
        assertEquals(new Vector2d(0, 0), bounds.lowerLeft());
        assertEquals(new Vector2d(9, 9), bounds.upperRight());
    }
}