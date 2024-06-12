import java.util.*;
import java.util.stream.*;

public class PolygonSort {

    public static void main(String [] args) {
        System.out.printf("Hello PolygonSort Solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java PolygonSort%n");
            return;
        }

/*
*/
        // counter-clockwise example
        Point point1 = new Point(1,1);
        Point point2 = new Point(2,1);
        Point point3 = new Point(2,2);
        Point point4 = new Point(1,2);

/*
        // clockwise example
        Point point1 = new Point(1,1);
        Point point2 = new Point(1,2);
        Point point3 = new Point(2,2);
        Point point4 = new Point(2,1);

        // counter-clockwise tilted example
        Point point1 = new Point(2,1);
        Point point2 = new Point(3,2);
        Point point3 = new Point(2,2);
        Point point4 = new Point(1,2);

        // zig-zag  example
        Point point1 = new Point(1,1);
        Point point2 = new Point(2,1);
        Point point3 = new Point(1,2);
        Point point4 = new Point(2,2);

        // criss-cross example
        Point point1 = new Point(1,1);
        Point point2 = new Point(2,2);
        Point point3 = new Point(1,2);
        Point point4 = new Point(2,1);

        // counter-clockwise tilted example
        Point point1 = new Point(2,1);
        Point point2 = new Point(3,2);
        Point point3 = new Point(2,3);
        Point point4 = new Point(1,2);

        // clockwise tilted example
        Point point1 = new Point(2,1);
        Point point2 = new Point(1,2);
        Point point3 = new Point(2,3);
        Point point4 = new Point(3,2);

        // zig-zag horizontal tilted example
        Point point1 = new Point(2,1);
        Point point2 = new Point(3,2);
        Point point3 = new Point(1,2);
        Point point4 = new Point(2,3);

        // zig-zag vertical tilted example
        Point point1 = new Point(1,2);
        Point point2 = new Point(2,3);
        Point point3 = new Point(2,1);
        Point point4 = new Point(3,2);

        // counter-clockwise irregular polygon star trek symbol
        Point point1 = new Point(1,2);
        Point point2 = new Point(2,3);
        Point point3 = new Point(3,2);
        Point point4 = new Point(2,7);
*/

        List<Point> source = new ArrayList<>();

        source.add(point1);
        source.add(point2);
        source.add(point3);
        source.add(point4);

        System.out.printf("source points%n");

        source.forEach((p) -> System.out.printf("%s%n", p.toString()));

        PolygonSort polygonSort = new PolygonSort(source);

        List<Point> result = polygonSort.sortPoints();

        System.out.printf("number of points = %d%n", result.size());

        System.out.printf("result of ordered points%n");

        result.forEach((p) -> System.out.printf("%s%n", p.toString()));
    }

    private List<Point> source;

    public PolygonSort(List<Point> source) {
        this.source = source;
    }

    public List<Point> sortPoints() {

        List<Point> result = new ArrayList<>(source);

        double centroidXCoordinate = findCentroidXCoordinate();
        double centroidYCoordinate = findCentroidYCoordinate();

        Collections.sort(result, (a, b) -> {

            // The javadocs state that the parameter names of atan2 are, first parameter, y and, second parameter, x.
            // "Returns the angle theta from the conversion of rectangular coordinates (x,y) to polar coordinates (r, theta)."
            // "This method computes the phase theta by computing an arc tangent of y/x in the rangeof -pi to pi."
            // Remember that the tangent is TOA, opposite side from angle over adacent side to the angle.
            // The y-axis coordinate is the length of the opposite side.
            // The x-axis coordinate is the length of the adjacent side.
            // As x trends to zero (0), the tan(y/x) is defined to be infinity, arctan(y/x) is 90 degrees,
            // since the hypotenuse is orthogonal to the x-axis.
            // As y trends to zero (0), the tan(y/x) is zero (0), arctan(y/x) is 0 degrees,
            // since the hypotenuse is parallel to the x-axis.
            // Note, though, that this generates the counter-clockwise sort.
            // To generate the clockwise sort, swap the y and x values passed to atan2.
            // This effectively switches angle theta to the other angle.
            // Now the x-axis value is the perpendicular value opposite theta,
            // and the y-axis value is the adjacent value next to theta.

            double xOfAPolarCoordinate = 1.0d * a.getx() - centroidXCoordinate;

            double yOfAPolarCoordinate = 1.0d * a.gety() - centroidYCoordinate;

            double arctanOfA = Math.atan2(yOfAPolarCoordinate, xOfAPolarCoordinate);

            double degreeOfA = Math.toDegrees(arctanOfA);

            double degreeOfAOffset360 = degreeOfA + 360.0d;

            double degreeOfAModulo360 = degreeOfAOffset360 % 360.0d;

            double xOfBPolarCoordinate = 1.0d * b.getx() - centroidXCoordinate;

            double yOfBPolarCoordinate = 1.0d * b.gety() - centroidYCoordinate;

            double arctanOfB = Math.atan2(yOfBPolarCoordinate, xOfBPolarCoordinate);

            double degreeOfB = Math.toDegrees(arctanOfB);

            double degreeOfBOffset360 = degreeOfB + 360.0d;

            double degreeOfBModulo360 = degreeOfBOffset360 % 360.0d;

//            int comparatorFactor = (int) (degreeOfAModulo360 - degreeOfBModulo360); // counter-clockwise sort
            int comparatorFactor = (int) (degreeOfBModulo360 - degreeOfAModulo360); // clockwise sort

            return comparatorFactor;

        });
        return result;
    }

    private double findCentroidXCoordinate() {

        double x = 0.0d;

        for(Point p : source) { 
            x += (1.0d * p.getx());
        }

        double pointCount = 1.0d * source.size();

        double centroidX = pointCount == 0 ? 0.0d : x / pointCount;

        return centroidX;
    }

    private double findCentroidYCoordinate() {

        double y = 0.0d;

        for(Point p : source) {
            y += (1.0d * p.gety());
        }

        double pointCount = 1.0d * source.size();

        double centroidY = pointCount == 0 ? 0.0d : y / pointCount;

        return centroidY;
    }

}
