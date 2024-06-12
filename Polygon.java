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

/*
        // counter-clockwise example
        int [][] points =new int [][] {
            {1,1}, {2,1},
            {2,2}, {1,2}
        };
*/

/*
        // expect 6 squares
        int [][] points =new int [][] {
            {1,1}, {2,1}, {3,1},
            {1,2}, {2,2}, {3,2},
            {1,3}, {2,3}, {3,3}
        };
*/



        // expect 21 squares
        int [][] points =new int [][] {
                          {3,1}, {4,1},
                          {3,2}, {4,2},
            {1,3}, {2,3}, {3,3}, {4,3}, {5,3}, {6,3},
            {1,4}, {2,4}, {3,4}, {4,4}, {5,4}, {6,4},
                          {3,5}, {4,5},
                          {3,6}, {4,6}
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

            int [] pointIndexSet = pointIndices[i]; // array of 4 point indices

            // collect actual points
            List<Point> pointObjects = getPoints(points, pointIndexSet);

            // sort points to be in clockwise order around the polygon
            PolygonSort polygonSort = new PolygonSort(pointObjects);
            List<Point> sortedPointObjects = polygonSort.sortPoints();

            // use distance between two points to check all sides are equal in length
            PolygonDistance pd1 = new PolygonDistance(sortedPointObjects.get(0), sortedPointObjects.get(1));
            PolygonDistance pd2 = new PolygonDistance(sortedPointObjects.get(1), sortedPointObjects.get(2));
            PolygonDistance pd3 = new PolygonDistance(sortedPointObjects.get(2), sortedPointObjects.get(3));
            PolygonDistance pd4 = new PolygonDistance(sortedPointObjects.get(3), sortedPointObjects.get(0));

            double d1 = pd1.getDistanceBetweenSquares();
            double d2 = pd2.getDistanceBetweenSquares();
            double d3 = pd3.getDistanceBetweenSquares();
            double d4 = pd4.getDistanceBetweenSquares();

            boolean hasEqualDistances = d1 == d2 && d2 == d3 && d3 == d4 && d4 == d1;

            // use dot product to check all angles are 90 degrees
            Point p1 = sortedPointObjects.get(0);
            Point p2 = sortedPointObjects.get(1);
            Point p3 = sortedPointObjects.get(2);
            PolygonDotProduct dotProduct1 = new PolygonDotProduct(p1, p2, p3, d1, d2);

            p1 = sortedPointObjects.get(1);
            p2 = sortedPointObjects.get(2);
            p3 = sortedPointObjects.get(3);
            PolygonDotProduct dotProduct2 = new PolygonDotProduct(p1, p2, p3, d2, d3);

            p1 = sortedPointObjects.get(2);
            p2 = sortedPointObjects.get(3);
            p3 = sortedPointObjects.get(0);
            PolygonDotProduct dotProduct3 = new PolygonDotProduct(p1, p2, p3, d3, d4);

            p1 = sortedPointObjects.get(3);
            p2 = sortedPointObjects.get(0);
            p3 = sortedPointObjects.get(1);
            PolygonDotProduct dotProduct4 = new PolygonDotProduct(p1, p2, p3, d4, d1);

            double cosineTheta1 = dotProduct1.getCosineTheta();

            boolean hasFourOrthogonalAngles = Math.abs(dotProduct1.getCosineTheta()) == 0.0d
                && Math.abs(dotProduct2.getCosineTheta()) == 0.0d
                && Math.abs(dotProduct3.getCosineTheta()) == 0.0d
                && Math.abs(dotProduct4.getCosineTheta()) == 0.0d;

            if (hasEqualDistances && hasFourOrthogonalAngles) {
                count++;
            }
        }

        return count;
    }

    public List<Point> getPoints(int [][] points, int [] pointIndexSet) {

            List<Point> result = new ArrayList<>();

            for (int j = 0; j < pointIndexSet.length; j++) {
                int pointIndex = pointIndexSet[j];
                int [] point = points[pointIndex];
                Point pointObject = new Point(point[0], point[1]);
                result.add(pointObject);
            }

            return result;
    }

}
