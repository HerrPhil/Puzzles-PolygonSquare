import java.util.*;
import java.util.stream.*;

public class PolygonDistance {

    public static void main(String [] args) {
        System.out.printf("Hello PolygonDistance Solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java PolygonDistance%n");
            return;
        }

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 6);
        PolygonDistance polygonDistance = new PolygonDistance(p1, p2);
        double result = polygonDistance.getDistanceBetweenSquares();
        System.out.printf("distance = %.3f%n", result);
    }

    private final Point p1;
    private final Point p2;

    public PolygonDistance(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double getDistanceBetweenSquares() {
        return Math.sqrt(Math.pow(p1.getx() - p2.getx(), 2) + Math.pow(p1.gety() - p2.gety(), 2));
    }

}
