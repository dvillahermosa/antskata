package code.adapt;

public class AntImp implements Ant {
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    private static final String PATTERN_FORMAT = "[R|L]{2,}";
    private static final char R = 'R';

    private final String pattern;
    private int currentPatternItem;

    private int x;
    private int y;
    private int direction;
    private int squareContent;
    private boolean firsStep;

    public AntImp(String pattern) throws NotCorrectPatternException {
        checkLength(pattern);
        checkFormat(pattern);

        this.direction = NORTH;
        this.pattern = pattern;
        this.currentPatternItem = 0;
        this.firsStep = true;
    }

    public void setInitialPosition(int x, int y, int squareContent) {
        this.x = x;
        this.y = y;
        this.squareContent = squareContent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNextX() {
        if (direction == NORTH) {
            return x - 1;
        }
        if (direction == SOUTH) {
            return x + 1;
        }
        return x;
    }

    public int getNextY() {
        if (direction == EAST) {
            return y + 1;
        } else if (direction == WEST) {
            return y - 1;
        }
        return y;
    }

    public void changeDirection() {
        if (firsStep) {
            return;
        }

        char c = pattern.charAt(squareContent);
        if (c == R) {
            direction = ++direction % 4;
        } else {
            direction = (direction + 3) % 4;
        }
    }

    public void moveNextPosition() {
        this.x = this.getNextX();
        this.y = this.getNextY();

        if(this.firsStep) {
            this.firsStep = false;
        }
    }

    @Override
    public void yourAreInASquare(int squareContent) {
        this.squareContent = squareContent;
        this.changeDirection();
    }

    private void
    checkLength(String pattern) throws NotCorrectPatternException {
        if (pattern.length() < 2) {
            throw new NotCorrectPatternException(String.format("Not correct pattern '%s'. The pattern need to have more than one character", pattern));
        }
    }

    private void checkFormat(String pattern) throws NotCorrectPatternException {
        if (!pattern.matches(PATTERN_FORMAT)) {
            throw new NotCorrectPatternException(String.format("Not correct pattern '%s'. The pattern can only contain characters 'L' and 'R'", pattern));
        }
    }
}
