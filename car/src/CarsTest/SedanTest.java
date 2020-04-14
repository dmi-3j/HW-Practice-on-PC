package CarsTest;


import Cars.Pickup;
import Cars.Sedan;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SedanTest {
    private Sedan sedan;

    @Before
    public void setSedan() throws Exception {
        sedan = new Sedan("Pink", "Russia",6, 105, "АИ-92");
    }
    @Test
    public void getPowerTest() {
        assertEquals(6, sedan.getPeopleCount());
    }
    @Test
    public void getInformationTest() {
        String expected = "Color: Pink\nCountry: Russia\nPeople count: 6\nHorsepower: 105\nFuel: АИ-92";
        assertEquals(expected, sedan.information());
    }

}

















