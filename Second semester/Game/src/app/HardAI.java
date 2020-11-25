package app;

import java.util.Random;

import static app.GameStatus.*;

public class HardAI extends AI {
    private static final String name = "Hard AI";
    private Random random = new Random();

    public static String getName() {
        return name;
    }

    public int action(boolean isFirst, int turn) {
        if (isFirst) {
            if (turn == 0) return 4;
            if (firstCheck() != -1) return firstCheck();
            if (checkCorners() != -1) return checkCorners();
            int x = random.nextInt(9);
            while (!isFree(x)) x = random.nextInt(9);
            return x;
        }
        else {
            if (isFree(4) && turn == 1) return 4;
            if (turn == 1) return 0;
            if (firstCheck() != -1) return firstCheck();
            if (checkCorners() != -1) return checkCorners();
            int x = random.nextInt(9);
            while (!isFree(x)) x = random.nextInt(9);
            return x;
        }
    }

    public static int firstCheck() {
        for (int i = 0; i < 3; ++i) {
            if ((getButton(i) == 1 && getButton(3 + i) == 1 || getButton(i) == -1  && getButton(3 + i) == -1) && isFree(i + 6)) return i + 6;
            if ((getButton(i) == 1 && getButton(6 + i) == 1 || getButton(i) == -1  && getButton(6 + i) == -1) && isFree(i + 3)) return i + 3;
            if ((getButton(3 + i) == 1 && getButton(6 + i) == 1 || getButton(3 + i) == -1  && getButton(6 + i) == -1) && isFree(i)) return i;
            if ((getButton(i * 3) == 1 && getButton(i * 3 + 1) == 1 || getButton(i * 3) == -1  && getButton(i * 3 + 1) == -1) && isFree(i * 3 + 2)) return i * 3 + 2;
            if ((getButton(i * 3) == 1 && getButton(i * 3 + 2) == 1 || getButton(i * 3) == -1  && getButton(i * 3 + 2) == -1) && isFree(i * 3 + 1)) return i * 3 + 1;
            if ((getButton(i * 3 + 1) == 1 && getButton(i * 3 + 2) == 1 || getButton(i * 3 + 1) == -1  && getButton(i * 3 + 2) == -1) && isFree(i * 3)) return i * 3;
        }
        if ((getButton(0) == 1 && getButton(4) == 1 || getButton(0) == -1 && getButton(4) == -1) && isFree(8)) return 8;
        if ((getButton(0) == 1 && getButton(8) == 1 || getButton(0) == -1 && getButton(8) == -1) && isFree(4)) return 4;
        if ((getButton(4) == 1 && getButton(8) == 1 || getButton(4) == -1 && getButton(8) == -1) && isFree(0)) return 0;
        if ((getButton(2) == 1 && getButton(4) == 1 || getButton(2) == -1 && getButton(4) == -1) && isFree(6)) return 6;
        if ((getButton(2) == 1 && getButton(6) == 1 || getButton(2) == -1 && getButton(6) == -1) && isFree(4)) return 4;
        return-1;
    }
    public static int checkCorners() {
        if (getLastTurn() == 0 || getLastTurn() == 1 || getLastTurn() == 2) {
            if (isFree(6)) return 6;
            if (isFree(8)) return 8;
            if (isFree(0)) return 0;
            if (isFree(2)) return 2;
        }
        if (getLastTurn() == 6 || getLastTurn() == 7 || getLastTurn() == 8) {
            if (isFree(0)) return 0;
            if (isFree(2)) return 2;
            if (isFree(6)) return 6;
            if (isFree(8)) return 8;
        }
        if (getLastTurn() == 0 || getLastTurn() == 3 || getLastTurn() == 6) {
            if (isFree(2)) return 2;
            if (isFree(8)) return 8;
            if (isFree(0)) return 0;
            if (isFree(6)) return 6;
        }
        if (getLastTurn() == 2 || getLastTurn() == 5 || getLastTurn() == 8) {
            if (isFree(0)) return 0;
            if (isFree(6)) return 6;
            if (isFree(2)) return 2;
            if (isFree(8)) return 8;
        }
        return -1;
    }
}