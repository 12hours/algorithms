import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nm on 28.5.17.
 */
public class BruteCollinearPointsTest {

    @Test
    public void test1() throws Exception {
        Point[] points = new Point[] {   new Point(1,1),
                                        new Point(2,2),
                                        new Point(3,3),
                                        new Point(4,4)
                                    };
        LineSegment[] expected = new LineSegment[] {new LineSegment(new Point(1,1), new Point(4,4))};

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        LineSegment[] segments = brute.segments();
        assertThat(segments, is(expected));
    }

    @Test
    public void test2() throws Exception {
        Point[] points = new Point[] {   new Point(4,4),
                                        new Point(0, 12),
                                        new Point(1,48),
                                        new Point(3,3),
                                        new Point(2,2),
                                        new Point(1,0),
                                        new Point(3, 8)
                                    };
        LineSegment[] expected = new LineSegment[] {new LineSegment(new Point(1,1), new Point(4,4))};

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        LineSegment[] segments = brute.segments();
        assertThat(segments, is(expected));
    }
}