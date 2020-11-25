package Math;

import javafx.geometry.Point2D;
public class Curve {
    public static double dist(Point2D p1, Point2D p2) {
        return Math.sqrt((p1.getX() - p2.getX())*(p1.getX() - p2.getX())
                + (p1.getY() - p2.getY())*(p1.getY() - p2.getY()));
    }
    public Point2D returnNext(Point2D prevP) {
        return null;
    }
}
