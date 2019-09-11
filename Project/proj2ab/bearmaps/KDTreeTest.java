package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KDTreeTest {
    private static Random r = new Random(500);
    @Test
    public void insertTest(){
        Point p2 = new Point(3.3, 4.4);
        Point p1 = new Point(1.1, 2.2);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(4.3, 5.3);
        Point p5 = new Point(3.6, 3.4);
        Point p6 = new Point(1.5, 8.2);
        Point p7 = new Point(-4.9, 1.2);
        Point p8 = new Point(-1.3, 5.3);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6,p7, p8));

        //Point ret = nn.nearest(3.0, 4.0);
        //assertEquals(3.3, ret.getX(), 1); // evaluates to 3.3
        //assertEquals(4.4, ret.getY(), 1); // evaluates to 4.4
    }
    @Test
    public void testNearest() {
        Point p2 = new Point(3.3, 4.4);
        Point p1 = new Point(1.1, 2.2);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(4.3, 5.3);
        Point p5 = new Point(3.6, 3.4);
        Point p6 = new Point(1.5, 8.2);
        Point p7 = new Point(-4.9, 1.2);
        Point p8 = new Point(-1.3, 5.3);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6,p7, p8));

        Point ret = nn.nearest(3.0, 4.0);
        assertEquals(3.3, ret.getX(), 1); // evaluates to 3.3
        assertEquals(4.4, ret.getY(), 1); // evaluates to 4.4
    }

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            points.add(randomPoint());
        }
        return points;
    }

    private void testWithNPointsAndQQueries(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(queryCount);
        for (Point p : queries) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testWith1000PointsAnd200Queries() {
        int pointCount = 1000;
        int queryCount = 200;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

    @Test
    public void compareTimingOfNaiveVsKDTreeLikeTheSpec() {
        List<Point> randomPoints = randomPoints(1000000);
        KDTree kd = new KDTree(randomPoints);
        NaivePointSet nps = new NaivePointSet(randomPoints);
        List<Point> queryPoints = randomPoints(1000);

        Stopwatch sw = new Stopwatch();
        for (Point p : queryPoints) {
            nps.nearest(p.getX(), p.getY());
        }
        double time = sw.elapsedTime();
        System.out.println("Naive 10000 queries on 1000 points: " + time);

        sw = new Stopwatch();
        for (Point p : queryPoints) {
            kd.nearest(p.getX(), p.getY());
        }
        time = sw.elapsedTime();
        System.out.println("KDTree 10000 queries on 1000000 points: " + time);
    }
}
