import java.util.*;
import java.util.stream.*;

public class Polygon {

    public static void main(String [] args) {
        System.out.printf("Hello Polygon Solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java Polygon%n");
            return;
        }

        Polygon polygon = new Polygon();

        // counter-clockwise example
        int [][] points =new int [][] {
            {1,1}, {2,1},
            {2,2}, {1,2}
        };

        int result = polygon.findSquarePolygons(points);

        System.out.printf("number of squares = %d%n", result);
    }

    public int findSquarePolygons(int [][] points) {
        
        if (points.length < 4) return 0; // minimum 4 points per square

        int count = 0;

        // get permutations of given point indices
        Permutate permutate = new Permutate(points.length);
        int [][] pointIndices = permutate.solution();
        for (int i = 0; i < pointIndices.length; i++) {

            System.out.printf("candidate square point indices are %s%n", Arrays.toString(pointIndices[i]));

            int [] pointIndexSet = pointIndices[i]; // array of 4 point indices

            // collect actual points
            System.out.printf("collect point objects by indices%n");
            List<Point> pointObjects = new ArrayList<>();
            for (int j = 0; j < pointIndexSet.length; j++) {
                int pointIndex = pointIndexSet[j];
                int [] point = points[pointIndex];
                System.out.printf("Point pair (%d, %d)%n", point[0], point[1]);
                Point pointObject = new Point(point[0], point[1]);
                System.out.printf("Instance of %s%n", pointObject.toString());
                pointObjects.add(pointObject);
            }

            System.out.printf("point objects before%n");
            pointObjects.forEach((p) -> System.out.printf("%s%n", p.toString()));

            // sort points to be in clockwise order around the polygon
            PolygonSort polygonSort = new PolygonSort(pointObjects);
            List<Point> sortedPointObjects = polygonSort.sortPoints();

            System.out.printf("point objects after%n");
            sortedPointObjects.forEach((p) -> System.out.printf("%s%n", p.toString()));

            // use distance between two points to check all sides are equal in length
            PolygonDistance pd1 = new PolygonDistance(sortedPointObjects.get(0), sortedPointObjects.get(1));
            PolygonDistance pd2 = new PolygonDistance(sortedPointObjects.get(1), sortedPointObjects.get(2));
            PolygonDistance pd3 = new PolygonDistance(sortedPointObjects.get(2), sortedPointObjects.get(3));
            PolygonDistance pd4 = new PolygonDistance(sortedPointObjects.get(3), sortedPointObjects.get(0));

            double d1 = pd1.getDistanceBetweenSquares();
            double d2 = pd2.getDistanceBetweenSquares();
            double d3 = pd3.getDistanceBetweenSquares();
            double d4 = pd4.getDistanceBetweenSquares();

            System.out.printf("d1 = %.3f, d2 = %.3f, d3 = %.3f, d4 = %.3f%n", d1, d2, d3, d4);

            if (d1 == d2 && d2 == d3 && d3 == d4 && d4 == d1) {
                System.out.printf("All the distances are equal - might be a square%n");
            } else {
                System.out.printf("All the distances are not equal - cannot be a square%n");
            }

        }



        // use dot notation to check all angles are 90 degrees

        return count;
    }

}
