package lib;

public abstract class Car {
    private String color;
    private String country;
    private int peopleCount;
    private String fuel;
    private int power;

    public Car(String color, String country, int peopleCount, int power, String fuel) {
        this.color = color;
        this.country = country;
        this.peopleCount = peopleCount;
        this.power = power;
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }
    public String getCountry() {
        return country;
    }
    public int getPeopleCount() {
        return peopleCount;
    }
    public int getPower() {
        return power;
    }
    public String getFuel() {
        return fuel;
    }
    public String information() {
        String info = "Color: " + getColor() + "\nCountry: " + getCountry() + "\nPeople count: " + getPeopleCount() + "\nHorsepower: " + getPower() + "\nFuel: " + getFuel();
        return info;
    }
}