package CarsTest;

import Cars.Pickup;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PickupTest {
    private Pickup pickup;
	@Before
    public void setCar() throws Exception {
        pickup = new Pickup("Yellow", "Australia", 5, 150, "АИ-98", 1500);
    }
	@Test
    public void getPowerTest() {
        assertEquals(150, pickup.getPower(), 1);
    }
	@Test
    public void getInformationTest() {
        String expected = "Color: Yellow\nCountry: Australia\nPeople count: 5\nHorsepower: 150\nFuel: АИ-98\nWeight: 1500";
        assertEquals(expected, pickup.information());
    }
}