package CarsTest;

import lib.Car;
import Cars.Pickup;
import Cars.Sedan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAbstract {
    private Car carPickup;
    private Car carSedan;
	@Before
    public void setCar() {
        carPickup = new Pickup("Yellow", "Australia", 5, 150, "АИ-98",1350);
        carSedan = new Sedan("Pink", "Russia",6, 105, "АИ-92", 700);
    }
    @Test
    public void getColorTest() {
        assertEquals("Yellow", carPickup.getColor());
        assertEquals("Pink", carSedan.getColor());
    }
    @Test
    public void getCountryTest() {
        assertEquals("Australia", carPickup.getCountry());
        assertEquals("Russia", carSedan.getCountry());
    }
    @Test
    public void getPeopleCountTest() {
        assertEquals(5, carPickup.getPeopleCount());
        assertEquals(6, carSedan.getPeopleCount());
    }
    @Test
    public void getPowerTest() {
        assertEquals(150, carPickup.getPower());
        assertEquals(105, carSedan.getPower());
    }
    @Test
    public void getFuelTest() {
        assertEquals("АИ-98", carPickup.getFuel());
        assertEquals("АИ-92", carSedan.getFuel());
    }
}