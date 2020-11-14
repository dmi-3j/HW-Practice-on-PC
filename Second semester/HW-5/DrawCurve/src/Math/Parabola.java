package Math;

import userInterface.Scrim;

import java.util.ArrayList;

public class Parabola extends Curve {
    private double p;

    public Parabola(double p, Scrim scrim) {
        super(scrim);
        this.p = p;
    }

    @Override
    public ArrayList<Double> valueY(double x) {
        double doubledP = p * 2;
        ArrayList<Double> values = new ArrayList<>();
        double y = (float) Math.sqrt(doubledP * x);
        if (y == 0) {
            values.add((double) 0);
            return values;
        }
        values.add(y);
        values.add(-y);
        return values;
    }


    @Override
    public String toString() {
        return "y² = " + "2*" + p + "x²";
    }
}
