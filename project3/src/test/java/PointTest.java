import edu.princeton.cs.algs4.StdRandom;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nm on 25.5.17.
 */
public class PointTest {

    @Test
    public void slopeTest0() throws Exception {
        int x0 = 4; int y0 = 4;
        int x1 = 1; int y1 = 0;
        Point point0 = new Point(x0, y0);
        Point point1 = new Point(x1, y1);
        double expected = (y1 - y0) / (double)(x1 - x0);
        System.out.println(expected);
        assertThat(point0.slopeTo(point1), is(expected));

    }

    @Test
    public void slopeTest1() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int y0 = StdRandom.uniform(100);
            int x1 = StdRandom.uniform(100);
            int y1 = StdRandom.uniform(100);
            double expected;
            if (x1 == x0 || y1 == y0) continue; // we don't want to check horizontal and vertical slopes here
            expected = (y1 - y0) / (x1 - x0);

            Point point1 = new Point(x0, y0);
            Point point2 = new Point(x1, y1);
            assertThat(point1.slopeTo(point2), is(expected));
        }
    }

    @Test
    public void horizontalSlopeTest() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int x1 = StdRandom.uniform(100);
            int y0 = StdRandom.uniform(100);
            int y1 = y0;

            if (x1 == x0) continue; // we don't want to check same points here

            Point point1 = new Point(x0, y0);
            Point point2 = new Point(x1, y1);

            assertThat(point1.slopeTo(point2), is(0.0));
        }
    }

    @Test
    public void verticalSlopeTest() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int x1 = x0;
            int y0 = StdRandom.uniform(100);
            int y1 = StdRandom.uniform(100);

            if (y1 == y0) continue; // we don't want to check same points here

            Point point1 = new Point(x0, y0);
            Point point2 = new Point(x1, y1);

            assertThat(point1.slopeTo(point2), is(Double.POSITIVE_INFINITY));
        }
    }

    @Test
    public void degenerateSlopeTest() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int x1 = x0;
            int y0 = StdRandom.uniform(100);
            int y1 = y0;

            Point point1 = new Point(x0, y0);
            Point point2 = new Point(x1, y1);

            assertThat(point1.slopeTo(point2), is(Double.NEGATIVE_INFINITY));
        }
    }


    @Test
    public void compareTestEquals() throws Exception {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 0);

        assertThat(point1.compareTo(point2), is(0));

        Point point3 = new Point(10, 48);
        Point point4 = new Point(10, 48);

        assertThat(point1.compareTo(point2), is(0));
    }

    @Test
    public void compareTestDifferentYsEqualXs() throws Exception {
        Point point1 = new Point(0, 12); // lesser
        Point point2 = new Point(0, 42); // bigger

        assertThat(point1.compareTo(point2), is(-1));

        Point point3 = new Point(2, 42);
        Point point4 = new Point(2, 12);

        assertThat(point3.compareTo(point4), is(1));
    }

    @Test
    public void compareTestEqualYsDifferentXs() throws Exception {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(1, 0);

        assertThat(point1.compareTo(point2), is(-1));

        Point point3 = new Point(12, 0);
        Point point4 = new Point(1, 0);

        assertThat(point3.compareTo(point4), is(1));
    }

    @Test
    public void compareTestDifferentYsAndXs() throws Exception {
        Point point1 = new Point(0, 1);
        Point point2 = new Point(2, 3);


    }

    @Test
    public void compareEqualSlopes() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int y0 = StdRandom.uniform(100);
            Point point0 = new Point(x0, y0);

            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);

            Point point1 = new Point(x, y);
            Point point2 = new Point(x, y);

            assertThat(point0.slopeOrder().compare(point1, point2), is(0));
        }
    }

    @Test
    public void compareDifferentSlopes() throws Exception {
        for (int i = 0; i < 100; i++) {
            int x0 = StdRandom.uniform(100);
            int y0 = StdRandom.uniform(100);
            Point point0 = new Point(x0, y0);

            int x1 = StdRandom.uniform(100);
            int y1 = StdRandom.uniform(100);
            int x2 = StdRandom.uniform(100);
            int y2 = StdRandom.uniform(100);

            Point point1 = new Point(x1, y1);
            Point point2 = new Point(x2, y2);

            int slope;
            double slope1 = point0.slopeTo(point1);
            double slope2 = point0.slopeTo(point2);
            if (slope1 == slope2)   slope = 0;
            else                    slope = (slope1 < slope2)? -1 : 1;


            assertThat(point0.slopeOrder().compare(point1, point2), is(slope));
        }
    }

    @Test
    public void slopeOrderTest() throws Exception {
        Point[] points = new Point[] { new Point(1,1),
                new Point(3,3),
                new Point(4,4),
                new Point(2,2)};

        Arrays.sort(points, new Point(1,1).slopeOrder());

    }
}