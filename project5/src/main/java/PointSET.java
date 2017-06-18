import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> treeSet;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        this.treeSet = new TreeSet<>();
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return treeSet.size() == 0;
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return treeSet.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p point
     */
    public void insert(Point2D p) {
        treeSet.add(p);
    }

    /**
     * @param p target point
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        return treeSet.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D point : treeSet){
            StdDraw.point(point.x(), point.y());
            }
        StdDraw.show();
    }

    /**
     * @param rect target rectangle
     * @return all points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> lst = new ArrayList<>();
        for (Point2D point : treeSet){
            if (rect.contains(point)){
                lst.add(point);
            }
        }
        return lst;
    }

    /**
     * @param p target point
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        for (Point2D point: treeSet){
            if (nearestPoint == null || p.distanceTo(point) < p.distanceTo(nearestPoint)){
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
    // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
    }
}