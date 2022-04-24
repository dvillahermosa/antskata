package code.adapt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LangtonAntsTest {

    @Test
    public void whenTheGameStartThenTheAntIsInTheCenter() throws NotCorrectPatternException, NotCorrectMatrixDimension {
        int[][] expectedMatrix =
                {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};
        Ant ant = new AntImp("RL");
        LangtonAnts ants = new LangtonAnts(ant, 3);

        ants.nextSteps(0);

        assertArrayEquals(expectedMatrix, ants.getGrid());
    }

    @Test
    public void whenTheAntWalkOneTimeItShouldBeGoToTheNorth() throws NotCorrectPatternException, NotCorrectMatrixDimension {
        int[][] expectedMatrix =
                {{0, 1, 0},
                {0, 1, 0},
                {0, 0, 0}};
        Ant ant = new AntImp("RL");
        LangtonAnts ants = new LangtonAnts(ant, 3);

        ants.nextSteps(1);

        assertArrayEquals(expectedMatrix, ants.getGrid());
    }

    @Test
    public void whenTheAntWalkOutOfTheMatrixItShouldRotate() throws NotCorrectPatternException, NotCorrectMatrixDimension {
        int[][] expectedMatrix =
                {{0, 1, 1},
                {0, 1, 1},
                {0, 0, 0}};
        Ant ant = new AntImp("RL");
        LangtonAnts ants = new LangtonAnts(ant, 3);

        ants.nextSteps(3);

        assertArrayEquals(expectedMatrix, ants.getGrid());
    }

    @Test
    public void whenThePatterHasLessTwoCharsItShouldThrowAnNotCorrectException() {
        NotCorrectPatternException exception = assertThrows(NotCorrectPatternException.class, () -> {
            new AntImp("R");
        });

        assertEquals("Not correct pattern 'R'. The pattern need to have more than one character",
                exception.getMessage());
    }

    @Test
    public void whenTheDimensionIsNotOddtShouldThrowAnNotCorrectMatrixDimension() {
        NotCorrectMatrixDimension exception = assertThrows(NotCorrectMatrixDimension.class, () -> {
            LangtonAnts ants = new LangtonAnts(new AntImp("RL"), 4);
        });

        assertEquals("Not correct Matrix dimension (4). The need to be odd",
                exception.getMessage());
    }

    @Test
    public void whenThePatternFormatIsNotCorrectItShouldThrowANotCorrectPatternException() {
        NotCorrectPatternException exception = assertThrows(NotCorrectPatternException.class, () -> {
            new AntImp("XX");
        });

        assertEquals("Not correct pattern 'XX'. The pattern can only contain characters 'L' and 'R'",
                exception.getMessage());
    }

    @Test
    public void whenTheAntPassAgainThenChangeTheSquareContent() throws NotCorrectPatternException, NotCorrectMatrixDimension {
        int[][] expectedMatrix = {{0, 0, 0, 0, 0},
                                  {0, 0, 1, 1, 0},
                                  {0, 0, 0, 1, 0},
                                  {0, 0, 1, 0, 0},
                                  {0, 0, 0, 0, 0}};
        Ant ant = new AntImp("RL");
        LangtonAnts ants = new LangtonAnts(ant, 5);

        ants.nextSteps(5);

        assertArrayEquals(expectedMatrix, ants.getGrid());
    }
}
