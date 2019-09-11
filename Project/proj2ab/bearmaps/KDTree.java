package bearmaps;

import java.util.List;
import java.util.TreeSet;

public class KDTree implements PointSet{
    private KDNode node;
    private int size;

    private class KDNode{
        private boolean isVertical;
        private KDNode leftChild;
        private KDNode rightChild;
        private Point point;

        private KDNode(Point point,KDNode leftChild, KDNode rightChild, boolean isVertical){
            this.isVertical = isVertical;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.point = point;
        }
    }

    public void insert(Point p){
        node = insert(node, p, true);
    }

    private KDNode insert(KDNode node, Point point, boolean isVertical) {
        if (node == null) {
            size += 1;
            return new KDNode(point, null, null, isVertical);
        }
        if (node.point.getX() == point.getX() && node.point.getY() == point.getY()) {
            return node;
        }
        if (node.isVertical && point.getX() < node.point.getX() || !node.isVertical && point.getY() < node.point.getY()) {
            node.leftChild = insert(node.leftChild, point, !node.isVertical);
        } else {
            node.rightChild = insert(node.rightChild, point, !node.isVertical);
        }
        return node;
    }



    public KDTree(List<Point> points){
        for(Point p : points){
            this.insert(p);
        }
    }


    @Override
    public Point nearest(double x, double y){
        Point target = new Point(x, y);
        return nearest(node, target, node.point);
    }

    private Point nearest(KDNode n, Point target, Point best){


        if (n == null){
            return best;
        }


        if (Point.distance(n.point, target)< Point.distance(best, target)) {
            best = n.point;
        }


        KDNode goodSide;
        KDNode badSide;
        if (target.getX() < n.point.getX() && n.isVertical || target.getY() < n.point.getY() && !n.isVertical) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        }else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearest(goodSide, target, best);
        if (isValueableToLook(n, target, best)){
            //best = nearest(badSide, goal, best)
            best = nearest(badSide, target, best);
        }

        return best;
    }


    private boolean isValueableToLook(KDNode n, Point target, Point best){
        double goodSideDis =Point.distance(best, target);

        double badSideDis;
        if (!n.isVertical) {
            badSideDis = Point.distance(new Point(target.getX(), n.point.getY()), target);
        } else {
            badSideDis = Point.distance(new Point(n.point.getX(), target.getY()), target);
        }
        return badSideDis < goodSideDis;

    }

}
