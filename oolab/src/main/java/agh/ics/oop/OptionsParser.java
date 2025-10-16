package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args) {
        int count = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l")) {
                count++;
            }
        }

        MoveDirection[] directions = new MoveDirection[count];

        int index = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions[index++] = MoveDirection.FORWARD;
                case "b" -> directions[index++] = MoveDirection.BACKWARD;
                case "r" -> directions[index++] = MoveDirection.RIGHT;
                case "l" -> directions[index++] = MoveDirection.LEFT;
                default -> {}
            }
        }

        return directions;
    }
}
