package Cars;

import lib.Car;

public class Sedan extends Car {
	private int carrying;
	public Sedan(String color, String country, int peopleCount, int power, String fuel, int carrying) {
        super(color, country, peopleCount, power, fuel);
        this.carrying = carrying;
    }
    public int getCarrying() {
        return carrying;
    }
	public String information() {
        String info = super.information();
        info += ("\nCarrying: " + getCarrying());
        return info;
    }
}





