import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class PercolationGridTest {
    private static class PercMock extends Percolation {
        public PercMock() {
            super(0);
        }

        private class TestGrid extends Grid {
            public TestGrid(int n) {
                super(n);
            }
        }

        private class TestGridExtended extends GridExtended {
            public TestGridExtended(int n) {
                super(n);
            }

        }

        public TestGrid getGrid(int n) {
            return new TestGrid(n);
        }

        public TestGridExtended getGridExtended(int n) {
            return new TestGridExtended(n);
        }
    }

    private static PercMock percMock;

    @BeforeClass
    public static void set() {
        percMock = new PercMock();
    }

    @Test
    public void testSimpleGrid1() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);

        assertEquals(0, grid.get(2, 4));
        assertEquals(0, grid.get(0, 1));
    }

    @Test
    public void testSimpleGrid2() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        grid.set(5, 8, 1);
        grid.set(0, 0, 1);
        assertEquals(1, grid.get(5, 8));
        assertEquals(1, grid.get(0, 0));
    }

    @Test
    public void testSimpleGrid3() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        assertEquals(10, grid.getSize());
        PercolationGridTest.PercMock.TestGrid grid2 = percMock.getGrid(0);
        assertEquals(0, grid2.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleGrid4() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSimpleGrid5() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        grid.set(-1, -1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSimpleGrid6() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        grid.set(22, 8, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSimpleGrid7() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        grid.get(0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSimpleGrid8() {
        PercolationGridTest.PercMock.TestGrid grid = percMock.getGrid(10);
        grid.get(22, 8);
    }

    @Test
    public void testExtendedGrid1() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertEquals(1, grid.convert2Dto1D(1, 1));
        assertEquals(10, grid.convert2Dto1D(1, 10));
        assertEquals(100, grid.convert2Dto1D(10, 10));
        assertEquals(26, grid.convert2Dto1D(3, 6));
    }

    @Test
    public void testExtendedGrid2() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertEquals(0, grid.get(3, 5));
        assertEquals(0, grid.get(1, 2));
    }

    @Test
    public void testExtendedGrid3() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        grid.set(6, 9, 1);
        grid.set(1, 1, 1);
        assertEquals(1, grid.get(6, 9));
        assertEquals(1, grid.get(1, 1));
    }

    @Test
    public void testExtendedGrid4() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertEquals(10, grid.getSize());
        PercolationGridTest.PercMock.TestGridExtended grid2 = percMock
                .getGridExtended(0);
        assertEquals(0, grid2.getSize());
    }

    @Test
    public void testExtendedGrid5() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertEquals(25, grid.getLeft(3, 6));
        assertEquals(27, grid.getRight(3, 6));
        assertEquals(16, grid.getTop(3, 6));
        assertEquals(36, grid.getBot(3, 6));
    }

    @Test
    public void testExtendedGrid6() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertEquals(-1, grid.getLeft(3, 1));
        assertEquals(-1, grid.getRight(3, 10));
        assertEquals(-1, grid.getTop(1, 6));
        assertEquals(-1, grid.getBot(10, 6));
    }

    @Test
    public void testExtendedGrid7() {
        PercolationGridTest.PercMock.TestGridExtended grid = percMock
                .getGridExtended(10);
        assertTrue(Arrays.equals(new int[]{1, 1}, grid.convert1Dto2D(1)));
        assertTrue(Arrays.equals(new int[]{1, 10}, grid.convert1Dto2D(10)));
        assertTrue(Arrays.equals(new int[]{3, 7}, grid.convert1Dto2D(27)));
        assertTrue(Arrays.equals(new int[]{10, 10}, grid.convert1Dto2D(100)));
    }
}
