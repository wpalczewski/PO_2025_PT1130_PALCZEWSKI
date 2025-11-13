package agh.ics.oop;

import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;

import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");

        List<MoveDirection> directions = OptionsParser.parse(args);

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        WorldMap map = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(map, positions, directions);

        simulation.run();

        System.out.println("System zakończył działanie");
    }
}