package app;

import java.util.Random;

public class RandomAI extends AI {
    private static Random random = new Random();
    private static final String name = "Easy AI";

    public static String getName() {
        return name;
    }

    public int action(boolean isFirst, int turn) {
        int x = random.nextInt(9);
        while (!GameStatus.isFree(x)){
            x = random.nextInt(9);
        }
        return x;
    }
}