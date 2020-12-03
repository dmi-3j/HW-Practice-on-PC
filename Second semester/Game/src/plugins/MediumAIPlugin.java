package plugins;

import app.AI;
import app.HardAI;
import app.RandomAI;

import java.util.Random;

public class MediumAIPlugin extends AI {
    static Random random = new Random();
    static float randomLevel = 3/4;
    private static final String name = "Medium";

    public static String getName() {
        return name;
    }

    public int action(boolean isFirst, int turn) {
        double p = Math.random();
        if (p < randomLevel) {
            RandomAI ai = new RandomAI();
            return ai.action(isFirst, turn);
        }
        else {
            HardAI ai = new HardAI();
            return ai.action(isFirst, turn);
        }
    }

}