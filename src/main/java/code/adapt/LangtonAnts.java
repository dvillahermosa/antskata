package code.adapt;

public class LangtonAnts {
    private final int[][] grid;
    private Ant ant;

    public LangtonAnts(Ant ant, int matrixDimension) throws NotCorrectMatrixDimension {
        checkGridDimensionIsOdd(matrixDimension);
        this.ant = ant;
        grid = new int[matrixDimension][matrixDimension];
        int centerPos = (matrixDimension / 2);
        ant.setInitialPosition(centerPos, centerPos, grid[centerPos][centerPos]);
        antIsGoingIn();
    }

    private void checkGridDimensionIsOdd(int gridDimension) throws NotCorrectMatrixDimension {
        if (gridDimension % 2 == 0) {
            throw new NotCorrectMatrixDimension(String.format("Not correct Matrix dimension (%d). The need to be odd", gridDimension));
        }
    }

    private void antIsGoingIn() {
        int squareContent = grid[ant.getX()][ant.getY()];
        grid[ant.getX()][ant.getY()] = (squareContent + 1) % 2;
        ant.yourAreInASquare(squareContent);
    }

    public int[][] getGrid() {
        return grid;
    }

    public void nextSteps(int steps) {
        for (int i = 0; i < steps; i++) {
            guideTheAnt();

            ant.moveNextPosition();
            antIsGoingIn();
        }
    }

    private void guideTheAnt() {
        while (!isNextPositionInTheArea()) {
            ant.changeDirection();
        }
    }

    private boolean isNextPositionInTheArea() {
        int x = ant.getNextX();
        int y = ant.getNextY();
        return x < grid.length && x >= 0 & y < grid.length && y >= 0;
    }
}
