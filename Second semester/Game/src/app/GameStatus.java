package app;

import java.util.Random;

public class GameStatus {
    private boolean AIFirst;
    private static int[] buttons = new int[9];
    private int turnNumber;
    private static int lastTurn;
    private final int[][] rows = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private final int[][] columns = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    private final int[][] diagonals = {{0, 4, 8}, {2, 4, 6}};

    public static boolean isFree(int i) {
        return (buttons[i] == 0);
    }
    public static int getButton(int i) {
        return buttons[i];
    }
    public GameStatus() {
        Random random = new Random();
        AIFirst = random.nextInt() % 2 == 1;
        for (int i = 0; i < 9; ++i) {
            buttons[i] = 0;
        }
        turnNumber = 0;
        lastTurn = -1;
    }

    public boolean isAIFirst() {
        return AIFirst;
    }

    public int getTurn() {
        return turnNumber;
    }

    public void makeTurn(int i, boolean isAI) {
        turnNumber++;
        buttons[i] = isAI ? 1 : -1;
        lastTurn = i;
    }

    public static int getLastTurn() {
        return lastTurn;
    }

    private boolean isDraw() {
        for (int i = 0; i < 9; ++i) {
            if (buttons[i] == 0) return false;
        }
        return true;
    }
    private int checkWin(int[][] a) {
        for (int[] x: a) {
            if (buttons[x[0]] == 1 && buttons[x[1]] == 1 && buttons[x[2]] == 1) {
                return 1;
            }
            if (buttons[x[0]] == -1 && buttons[x[1]] == -1 && buttons[x[2]] == -1) {
                return -1;
            }
        }
        return 0;
    }

    public int isGameFinished() {
        if (checkWin(rows) != 0) return checkWin(rows);
        if (checkWin(columns) != 0) return checkWin(columns);
        if (checkWin(diagonals) != 0) return checkWin(diagonals);
        if (isDraw()) return 5;
        return 0;
    }
}
