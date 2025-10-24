package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void equalsShouldReturnTrueForIdenticalCoordinates() {
        // Given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(2, 3);

        // When & Then
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    void toStringShouldReturnCoordinatesInParentheses() {
        // Given
        Vector2d v = new Vector2d(3, 4);

        // When
        String result = v.toString();

        // Then
        assertEquals("(3,4)", result);
    }

    @Test
    void precedesAndFollowsShouldReturnCorrectBoolean() {
        // Given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);

        // When & Then
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
        assertTrue(v2.follows(v1));
        assertFalse(v1.follows(v2));
    }

    @Test
    void upperRightAndLowerLeftShouldReturnCorrectVectors() {
        // Given
        Vector2d v1 = new Vector2d(1, 5);
        Vector2d v2 = new Vector2d(3, 2);

        // When
        Vector2d upperRight = v1.upperRight(v2);
        Vector2d lowerLeft = v1.lowerLeft(v2);

        // Then
        assertEquals(new Vector2d(3, 5), upperRight);
        assertEquals(new Vector2d(1, 2), lowerLeft);
    }

    @Test
    void addShouldReturnSumOfVectors() {
        // Given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 4);

        // When
        Vector2d result = v1.add(v2);

        // Then
        assertEquals(new Vector2d(4, 6), result);
    }

    @Test
    void subtractShouldReturnDifferenceOfVectors() {
        // Given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 4);

        // When
        Vector2d result = v1.subtract(v2);

        // Then
        assertEquals(new Vector2d(-2, -2), result);
    }

    @Test
    void oppositeShouldReturnVectorWithNegatedCoordinates() {
        // Given
        Vector2d v = new Vector2d(1, -2);

        // When
        Vector2d result = v.opposite();

        // Then
        assertEquals(new Vector2d(-1, 2), result);
    }
}
