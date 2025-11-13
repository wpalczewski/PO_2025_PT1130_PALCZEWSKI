package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseShouldReturnCorrectDirectionsForValidInput() {
        // Given
        String[] args = {"f", "b", "l", "r"};

        // When
        List<MoveDirection> result = OptionsParser.parse(args);

        // Then
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        );
        assertEquals(expected, result);
    }

    @Test
    void parseShouldIgnoreInvalidInput() {
        // Given
        String[] args = {"f", "x", "l", "y", "r"};

        // When
        List<MoveDirection> result = OptionsParser.parse(args);

        // Then
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        );

        assertEquals(3, result.size());
        assertEquals(expected, result);
    }

    @Test
    void parseShouldReturnEmptyListForNoValidInput() {
        // Given
        String[] args = {"a", "bcd", "z"};

        // When
        List<MoveDirection> result = OptionsParser.parse(args);

        // Then
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    void parseShouldReturnEmptyListForEmptyInput() {
        // Given
        String[] args = {};

        // When
        List<MoveDirection> result = OptionsParser.parse(args);

        // Then

        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }
}