package agh.ics.oop.presenter;

import agh.ics.oop.model.AbstractWorldMap;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Label infoLabel;

    private AbstractWorldMap map;

    public void setWorldMap(AbstractWorldMap map) {
        this.map = map;
        map.addObserver(this);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(this::drawMap);
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

}
