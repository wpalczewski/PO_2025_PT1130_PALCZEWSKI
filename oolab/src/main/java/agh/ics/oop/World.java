package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("System Started");

        if (args.length == 0) {
            args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "r", "f", "f", "l", "f", "f"};
        }

        List<MoveDirection> directions;
        try {
            directions = OptionsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<Simulation> simulations = new ArrayList<>();
        ConsoleMapDisplay observer = new ConsoleMapDisplay();

        for (int i = 0; i < 1000; i++) {
            AbstractWorldMap map = new RectangularMap(5, 5);
            map.addObserver(observer);
            simulations.add(new Simulation(map, positions, directions));
        }

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();

        try {
            engine.awaitSimulationEnd();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("System Ended");
    }
}