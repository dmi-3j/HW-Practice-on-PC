package Cars;

import lib.Car;

public class Pickup extends Car {

    private int weight;

    public Pickup(String color, String country, int peopleCount, int power, String fuel, int weight) {
    super(color, country, peopleCount, power, fuel);
    this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    public String information() {
        String info = super.information();
        info += ("\nWeight: " + getWeight());
        return info;
    }
}





