import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

    @Test
    public void testPercolationInit() {
	Percolation perc = new Percolation(10);
	assertEquals(false, perc.isFull(1, 1));
	assertEquals(false, perc.isFull(5, 5));
	assertEquals(0, perc.numberOfOpenSites());
	assertEquals(false, perc.isOpen(1, 1));
	assertEquals(false, perc.isOpen(3, 7));
	assertEquals(false, perc.isOpen(10, 10));
	assertEquals(false, perc.percolates());
    }

    @Test
    public void testPecolation1() {
	Percolation perc = new Percolation(10);
	perc.open(1, 1);
	perc.open(1, 2);
	assertEquals(true, perc.isOpen(1, 1));
	assertEquals(true, perc.isOpen(1, 2));
    }
    
    @Test
    public void testPecolation2() {
	Percolation perc = new Percolation(10);
	for (int i = 1; i <= 10; i++){
	    perc.open(i, 3);
	}
	assertEquals(true, perc.percolates());
    }
    
    @Test
    public void testPercolation3(){
	Percolation perc = new Percolation(10);
	for (int i = 1; i <= 5; i++){
	    perc.open(i, 1);
	}
	for (int i = 1; i <= 5; i++){
	    perc.open(5, i);
	}
	for (int i = 5; i <= 10; i++){
	    perc.open(i, 6);
	}
	assertEquals(true, perc.percolates());
    }
    
}
