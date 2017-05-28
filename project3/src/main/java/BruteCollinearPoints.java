import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nm on 25.5.17.
 */
public class BruteCollinearPoints {
    private Point[] points;
    List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        this.points = points;
        Arrays.sort(points);
        this.segments = new ArrayList<>();
        findPoints();
    }

    private void findPoints() {

        // Loop 0
        for (int i = 0; i < this.points.length; i++) {
            Point point0 = this.points[i];
            if (point0 == null) throw new java.lang.NullPointerException();
            if (i < this.points.length - 1 &&
                    this.points[i].compareTo(this.points[i+1]) == 0) throw new java.lang.IllegalArgumentException();
            this.points[i] = null;

            // Loop 1
            for (int j = i+1; j < this.points.length; j++) {
                Point point1 = this.points[j];
                if (point1 == null) continue;
                double slope = point0.slopeTo(point1);
//                System.out.println("Points: "+ point0 + " " + point1 + " Slope is " + slope);

                // Loop 2
                for (int k = j+1; k < this.points.length; k++) {
                    if (k == j) continue;
                    Point point2 = this.points[k];
                    if (point2 == null) continue;
                    if (point0.slopeTo(point2) != slope) continue;
//                    System.out.println(point2 + " == "+ point0.slopeTo(point2));

                    // Loop 3
                    for (int l = k+1; l < this.points.length; l++) {
                        if (l == k || l == j) continue;
                        Point point3 = this.points[l];
                        if (point3 == null) continue;
                        if (point0.slopeTo(point3) != slope) continue;
//                        System.out.println(point3 + " == "+ point0.slopeTo(point3));

                        LineSegment segment = makeSegment(point0, point1, point2, point3);
                        if (true)
                            this.segments.add(segment);

                    }
                }
            }
        }
    }

    private LineSegment makeSegment(Point point0, Point point1, Point point2, Point point3) {
        Point[] points = new Point[4];
        points[0] = point0;
        points[1] = point1;
        points[2] = point2;
        points[3] = point3;
//        System.out.println("Unsorted: "  + Arrays.toString(points));
//        Arrays.sort(points);
//        System.out.println("Sorted: "  + Arrays.toString(points));
        LineSegment segment = new LineSegment(points[0], points[3]);
//        System.out.println("Point added: " + points[0] + " : " + points[3]);
        return segment;
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
