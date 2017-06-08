import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by nm on 25.5.17.
 */
public class FastCollinearPoints {
    private static final int MIN_SEG_SIZE = 3;
    private Point[] points;
    private List<LineSegment> lineSegments;
    private List<Point> pointsList;
    private List<Point> removedList;

    /**
     * finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        this.points = points;
        this.lineSegments = new ArrayList<>();
        this.pointsList = new ArrayList<>(Arrays.asList(points));
        this.removedList = new ArrayList<>();
        findPoints();

    }

    private void findPoints() {
        if (this.points.length < MIN_SEG_SIZE) return;
        Point[] workArray = Arrays.copyOf(this.points, this.points.length);

        for (int i = 0; i < this.points.length; i++){
            Point point0 = this.points[i];
            this.pointsList.sort(point0.slopeOrder());
            findSegments();
//            this.pointsList.remove(0);
//            removedList.add(point0);
        }
    }

    private void findSegments() {
        Point point0 = this.pointsList.get(0);
        boolean segmentAdded = false;

        List<Point> segment = new ArrayList<>();
        int start = 1, end = start;

        while (start < this.pointsList.size()) {
            double slope = point0.slopeTo(this.pointsList.get(start));
            while (end + 1 < this.pointsList.size() && point0.slopeTo(this.pointsList.get(end + 1)) == slope){
                end++;
            }
            if ((end - start) > 1){
                segment.add(point0);
                for (int i = start; i <= end; i++){
                    segment.add(this.pointsList.get(i));
                }
                segmentAdded = processSegment(segment);
            }
            segment = new ArrayList<>();
            start = end + 1;
            end = start;
        }
        if (segmentAdded){
            removedList.add(point0);
        }
    }

    private boolean processSegment(List<Point> segment) {

        Collections.sort(segment);
        Point point0 = segment.get(0);
        Point point1 = segment.get(segment.size()-1);
        LineSegment seg = new LineSegment(point0, point1);
        if (checkIfNotRemoved(segment)) {
            this.lineSegments.add(seg);
            return true;
        }
        return false;
    }

    private boolean checkIfNotRemoved(List<Point> segment) {
        for (Point pointSegment : segment){
            for (Point pointRemoved : this.removedList){
                if (pointRemoved.compareTo(pointSegment) == 0)  return false;
            }
        }
        return true;
    }


    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * @return number of the line segments
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
