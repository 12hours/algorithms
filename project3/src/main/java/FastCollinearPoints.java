import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nm on 25.5.17.
 */
public class FastCollinearPoints {
    private static final int MIN_SEG_SIZE = 3;
    Point[] points;
    List<LineSegment> lineSegments;

    /**
     * finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        this.points = points;
        this.lineSegments = new ArrayList<>();
        findPoints();

    }

    private void findPoints() {
        if (this.points.length < MIN_SEG_SIZE) return;
        Point[] workArray = Arrays.copyOf(this.points, this.points.length);

        for (int i = 0; i < this.points.length; i++){
            Point point0 = this.points[i];
            Arrays.sort(workArray, point0.slopeOrder());
            findSegments(workArray);
        }
    }

    private void findSegments(Point[] workArray) {
        Point point0 = workArray[0];
        List<Point> segment = new ArrayList<>();
        int start = 1, end = start;

        while (start < workArray.length) {
            double slope = point0.slopeTo(workArray[start]);
            while (end + 1 < workArray.length && point0.slopeTo(workArray[end + 1]) == slope){
                end++;
            }
            if ((end - start) > 1){
                segment.add(point0);
                for (int i = start; i <= end; i++){
                    segment.add(workArray[i]);
                    processSegment(segment);
                }
            }
            segment = new ArrayList<>();
            start = end + 1;
            end = start;
        }
    }

    private void processSegment(List<Point> segment) {
        segment.sort(segment.get(0).slopeOrder());
        Point point0 = segment.get(0);
        Point point1 = segment.get(segment.size()-1);
        LineSegment seg = new LineSegment(point0, point1);
        this.lineSegments.add(seg);
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
