package code.adapt;

public interface Ant {
    void setInitialPosition(int x, int y, int squareContent);

    void changeDirection();

    void moveNextPosition();

    void yourAreInASquare(int squareContent);

    int getX();

    int getY();

    int getNextX();

    int getNextY();

}
