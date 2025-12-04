package agh.ics.oop.model;


public class Animal implements WorldElement{
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d newPosition) {
        this.position = newPosition;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void setOrientation(MapDirection newOrientation) {
        this.orientation = newOrientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d moveVector = orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD)
                    moveVector = moveVector.opposite();

                Vector2d newPosition = position.add(moveVector);
                if (validator.canMoveTo(newPosition))
                    position = newPosition;
            }
        }
    }
    @Override
    public String toString() {
        return this.orientation.toSymbol();
    }
}



