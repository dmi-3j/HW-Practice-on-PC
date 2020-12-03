package Main;

import Cars.Pickup;
import Cars.Sedan;

public class Main {
    public static void main(String[] args) throws Exception {
        Pickup toyotaTundra = new Pickup("Black", "Japan", 5, 270, "АИ-98", 1350);
        Sedan hyundaiSonata = new Sedan("Blue", "South Korea", 5, 180, "АИ-95", 760);
        System.out.println("Information about Hyundai Sonata: ");
        System.out.println(hyundaiSonata.information());
        System.out.println("\nInformation about Toyota Tundra: ");
        System.out.println(toyotaTundra.information());
    }
}