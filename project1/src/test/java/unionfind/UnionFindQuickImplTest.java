package unionfind;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class UnionFindQuickImplTest {

	@Test
	public void unionTest1() {
		UnionFind uf = new UnionFindQuickImpl(12);
		uf.union(0, 1); // set1 {0,1}
		uf.union(2, 3); // set2 {2,3}
		uf.union(0, 3);
		Assert.assertTrue(uf.connected(0, 2));
	}

	@Test
	public void unionTest2() {
		UnionFind uf = new UnionFindQuickImpl(12);
		uf.union(0, 1);
		uf.union(2, 3);
		uf.union(0, 3);
		Assert.assertFalse(uf.connected(0, 4));
	}

	@Test
	public void unionTest3() {
		UnionFind uf = new UnionFindQuickImpl(12);
		// set 1 {1,4,8,9}
		uf.union(1, 4);
		uf.union(4, 8);
		uf.union(9, 8);
		// set 2 {2,3,5}
		uf.union(2, 3);
		uf.union(3, 5);
		
		uf.union(4, 5);
		Assert.assertTrue(uf.connected(1, 5));
	}
}
