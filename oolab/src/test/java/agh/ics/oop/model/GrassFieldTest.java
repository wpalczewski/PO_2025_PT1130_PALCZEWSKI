package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void shouldGenerateCorrectNumberOfGrassTufts() {
        // Given
        GrassField map = new GrassField(10);

        // When
        Collection<WorldElement> elements = map.getElements();

        int grassCount = 0;
        for (WorldElement element : elements) {
            if (element instanceof Grass) {
                grassCount++;
            }
        }

        // Then
        assertEquals(10, grassCount);
    }

    @Test
    void shouldAllowMovementOnGrass() {
        // Given
        GrassField map = new GrassField(10);

        Vector2d grassPosition = null;
        for (WorldElement element : map.getElements()) {
            if (element instanceof Grass) {
                grassPosition = element.getPosition();
                break;
            }
        }

        assertNotNull(grassPosition);

        // When
        boolean canMove = map.canMoveTo(grassPosition);

        // Then
        assertTrue(canMove);
    }

    @Test
    void shouldPrioritizeAnimalOverGrassInObjectAt() {
        // Given
        GrassField map = new GrassField(1);

        Vector2d position = null;
        for (WorldElement element : map.getElements()) {
            if (element instanceof Grass) {
                position = element.getPosition();
                break;
            }
        }

        Animal animal = new Animal(position);
        map.place(animal);

        // When
        WorldElement objectOnMap = map.objectAt(position);

        // Then
        assertEquals(animal, objectOnMap);
    }

    @Test
    void shouldCalculateDynamicBoundsCorrectly() {
        // Given
        GrassField map = new GrassField(0);

        map.place(new Animal(new Vector2d(-2, -2)));
        map.place(new Animal(new Vector2d(5, 5)));

        // When
        Boundary bounds = map.getCurrentBounds();

        // Then
        assertEquals(new Vector2d(-2, -2), bounds.lowerLeft());
        assertEquals(new Vector2d(5, 5), bounds.upperRight());
    }
}