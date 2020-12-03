package test;

import app.HardAI;
import app.RandomAI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import app.GameStatus;

public class AITest {
    private GameStatus game;
    private RandomAI ai1;
    private HardAI ai2;

    @Before
    public void setUp() {
        game = new GameStatus();
        ai1 = new RandomAI();
        ai2 = new HardAI();
    }

    @Test
    public void aiTest() {
        if (game.isAIFirst()) {
            int x = ai1.action(true, 0);
            Assert.assertTrue( x >= 0 && x <= 8 && GameStatus.isFree(x));
            Assert.assertEquals(ai2.action(true, 0), 4);
            game.makeTurn(4, true);
            game.makeTurn(0, false);
            x = ai1.action(true, 2);
            Assert.assertTrue( x >= 0 && x <= 8 && GameStatus.isFree(x));
            Assert.assertEquals(ai2.action(true, 2), 6);
        }
        else {
            game.makeTurn(4, false);
            int x = ai1.action(false, 1);
            Assert.assertTrue( x >= 0 && x <= 8 && GameStatus.isFree(x));
            Assert.assertEquals(ai2.action(false, 1), 0);
            game.makeTurn(0, true);
            game.makeTurn(6, false);
            x = ai1.action(false, 3);
            Assert.assertTrue( x >= 0 && x <= 8 && GameStatus.isFree(x));
            Assert.assertEquals(ai2.action(false, 3), 2);
        }
    }
}