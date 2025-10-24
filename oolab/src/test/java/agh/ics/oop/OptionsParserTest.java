package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseShouldReturnCorrectDirectionsForValidInput() {
        // Given
        String[] args = {"f", "b", "l", "r"};

        // When
        MoveDirection[] result = OptionsParser.parse(args);

        // Then
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void parseShouldIgnoreInvalidInput() {
        // Given
        String[] args = {"f", "x", "l", "y", "r"};

        // When
        MoveDirection[] result = OptionsParser.parse(args);

        // Then
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void parseShouldReturnEmptyArrayForNoValidInput() {
        // Given
        String[] args = {"a", "bcd", "z"};

        // When
        MoveDirection[] result = OptionsParser.parse(args);

        // Then
        assertEquals(0, result.length);
        assertArrayEquals(new MoveDirection[0], result);
    }

    @Test
    void parseShouldReturnEmptyArrayForEmptyInput() {
        // Given
        String[] args = {};

        // When
        MoveDirection[] result = OptionsParser.parse(args);

        // Then
        assertEquals(0, result.length);
        assertArrayEquals(new MoveDirection[0], result);
    }
}
