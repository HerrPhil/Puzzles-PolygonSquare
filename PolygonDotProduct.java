import java.util.*;
import java.util.stream.*;

public class PolygonDotProduct {

    public static void main(String [] args) {
        System.out.printf("Hello PolygonDotProduct Solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java PolygonDotProduct%n");
            return;
        }

        Point p1 = new Point(3, 3);
        Point p2 = new Point(5, 3);
        Point p3 = new Point(3, 1);

        double d1 = 2.236d;
        double d2 = 2.236d;

        PolygonDotProduct polygonDotProduct = new PolygonDotProduct(p1, p2, p3, d1, d2);

        double result = polygonDotProduct.getCosineTheta();
        System.out.printf("cosine theta = %.3f%n", result);
    }

    private Point p1;
    private Point p2;
    private Point p3;
    private double d1;
    private double d2;

    public PolygonDotProduct(Point p1, Point p2, Point p3, double d1, double d2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.d1 = d1;
        this.d2 = d2;
    }

    public double getCosineTheta() {

        // The dot product can be calculated by
        // multiplying same coordinates of the vector's points,
        // and then summing the products.
        // As well, the vector points must be projected to the 
        // common origin point of (0,0).
        // We subtract the middle point from each vector point.
        int x1 = p1.getx() - p2.getx();
        int y1 = p1.gety() - p2.gety();
        int x3 = p3.getx() - p2.getx();
        int y3 = p3.gety() - p2.gety();

        double dotProduct = 1.0d * x1 * x3 + 1.0d * y1 * y3;

        double productOfMagnitudes = 1.0d * Math.abs(d1) * Math.abs(d2);

        double result = dotProduct / productOfMagnitudes;

        return result;
    }
}
