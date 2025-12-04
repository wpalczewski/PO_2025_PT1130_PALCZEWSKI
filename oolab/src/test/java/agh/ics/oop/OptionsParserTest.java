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
    void parseShouldThrowExceptionForInvalidInput() {
        // Given
        String[] args = {"f", "x", "l", "y", "r"};

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.parse(args);
        });
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