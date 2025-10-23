package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void nextOfNorthShouldBeEast() {
        // Given
        MapDirection direction = MapDirection.NORTH;

        // When
        MapDirection result = direction.next();

        // Then
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void nextOfEastShouldBeSouth() {
        // Given
        MapDirection direction = MapDirection.EAST;

        // When
        MapDirection result = direction.next();

        // Then
        assertEquals(MapDirection.SOUTH, result);
    }

    @Test
    void nextOfSouthShouldBeWest() {
        // Given
        MapDirection direction = MapDirection.SOUTH;

        // When
        MapDirection result = direction.next();

        // Then
        assertEquals(MapDirection.WEST, result);
    }

    @Test
    void nextOfWestShouldBeNorth() {
        // Given
        MapDirection direction = MapDirection.WEST;

        // When
        MapDirection result = direction.next();

        // Then
        assertEquals(MapDirection.NORTH, result);
    }

    @Test
    void previousOfNorthShouldBeWest() {
        // Given
        MapDirection direction = MapDirection.NORTH;

        // When
        MapDirection result = direction.previous();

        // Then
        assertEquals(MapDirection.WEST, result);
    }

    @Test
    void previousOfEastShouldBeNorth() {
        // Given
        MapDirection direction = MapDirection.EAST;

        // When
        MapDirection result = direction.previous();

        // Then
        assertEquals(MapDirection.NORTH, result);
    }

    @Test
    void previousOfSouthShouldBeEast() {
        // Given
        MapDirection direction = MapDirection.SOUTH;

        // When
        MapDirection result = direction.previous();

        // Then
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void previousOfWestShouldBeSouth() {
        // Given
        MapDirection direction = MapDirection.WEST;

        // When
        MapDirection result = direction.previous();

        // Then
        assertEquals(MapDirection.SOUTH, result);
    }
}
