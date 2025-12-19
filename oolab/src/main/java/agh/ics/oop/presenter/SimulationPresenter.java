package agh.ics.oop.presenter;

import agh.ics.oop.model.*;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.OptionsParser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Canvas mapGrid;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private TextField movesInput;

    private AbstractWorldMap map;

    private static final int CELL_SIZE = 40;
    private static final double BORDER_WIDTH = 1.0;
    private static final double BORDER_OFFSET = BORDER_WIDTH / 2;

    public void setWorldMap(AbstractWorldMap map) {
        this.map = map;
        map.addObserver(this);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    public void drawMap() {
        Boundary boundary = map.getCurrentBounds();

        int widthCells = boundary.upperRight().getX() - boundary.lowerLeft().getX() + 1;
        int heightCells = boundary.upperRight().getY() - boundary.lowerLeft().getY() + 1;

        mapGrid.setWidth(widthCells * CELL_SIZE + BORDER_WIDTH);
        mapGrid.setHeight(heightCells * CELL_SIZE + BORDER_WIDTH);

        clearGrid();

        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        configureFont(graphics, 18, Color.BLACK);

        for (int x = boundary.lowerLeft().getX(); x <= boundary.upperRight().getX(); x++) {
            for (int y = boundary.lowerLeft().getY(); y <= boundary.upperRight().getY(); y++) {
                Object object = map.objectAt(new Vector2d(x, y));
                if (object != null) {
                    double centerX = (x - boundary.lowerLeft().getX()) * CELL_SIZE + (CELL_SIZE / 2.0) + BORDER_OFFSET;
                    double centerY = (boundary.upperRight().getY() - y) * CELL_SIZE + (CELL_SIZE / 2.0) + BORDER_OFFSET;

                    graphics.fillText(object.toString(), centerX, centerY);
                }
            }
        }
    }

    private void clearGrid() {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());

        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(BORDER_WIDTH);

        for (int x = 0; x < mapGrid.getWidth() + 1; x += CELL_SIZE) {
            graphics.strokeLine(x + BORDER_OFFSET, 0, x + BORDER_OFFSET, mapGrid.getHeight());
        }

        for (int y = 0; y < mapGrid.getHeight() + 1; y += CELL_SIZE) {
            graphics.strokeLine(0, y + BORDER_OFFSET, mapGrid.getWidth(), y + BORDER_OFFSET);
        }
    }
    private void configureFont(GraphicsContext graphics, int size, Color color) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setFont(new Font("Arial", size));
        graphics.setFill(color);
    }

    @FXML
    private void onSimulationStartClicked() {
        String input = movesInput.getText();
        String[] moveStrings = input.split(" ");

        try {
            List<MoveDirection> directions = OptionsParser.parse(moveStrings);

            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            Simulation simulation = new Simulation(this.map, positions, directions);

            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsync();

        } catch (IllegalArgumentException e) {
            moveInfoLabel.setText(e.getMessage());
        }
    }
}
