package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {

        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            MoveDirection direction = switch (arg.toLowerCase()) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if (direction != null) {
                directions.add(direction);
            }
        }

        return directions;
    }
}