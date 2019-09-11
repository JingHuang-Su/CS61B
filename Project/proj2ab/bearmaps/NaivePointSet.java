package bearmaps;
import java.util.List;
import java.util.ArrayList;

public class NaivePointSet implements PointSet {
    private List<Point> point;


    public NaivePointSet(List<Point> points){
        point = new ArrayList<>();
        point.addAll(points);
    }


    public Point nearest(double x, double y){
        //Create a variable nearest for the store value.
        Point target = new Point(x, y);
        Point nearest = point.get(0);
        double nearest_int = Point.distance(target, nearest);
        //Scanning through whole the element in that List.
        for (int i = 1; i < point.size(); i += 1) {

            //Calculating the distance from given value and every iterate element.

            double is_dis = Point.distance(point.get(i), target);

            //Take the most nearest element and replace the variable nearest.

            if (is_dis < nearest_int) {
                nearest = point.get(i);
                nearest_int = is_dis;
                }
            }
        //return the nearest element.
        return nearest;
    }


}

