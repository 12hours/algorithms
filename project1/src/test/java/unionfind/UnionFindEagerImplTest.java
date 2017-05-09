package unionfind;

import org.junit.Assert;
import org.junit.Test;

public class UnionFindEagerImplTest {
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void largeNTest(){
		UnionFind uf = new UnionFindEagerImpl(Integer.MAX_VALUE + 1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void lessThanZeroNTest(){
		UnionFind uf = new UnionFindEagerImpl(-1);
	}

	@Test
	public void emptyArrayIfConnectedTest() {
		UnionFind uf = new UnionFindEagerImpl(0);
		Assert.assertFalse(uf.connected(1, 2));
	}

	@Test
	public void emptyArrayCountTest() {
		UnionFind uf = new UnionFindEagerImpl(0);
		Assert.assertEquals(uf.count(), 0);
	}

	@Test
	public void emptyArrayFindTest() {
		UnionFind uf = new UnionFindEagerImpl(0);
		Assert.assertEquals(uf.find(0), -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void emptyArrayUnionTest() {
		UnionFind uf = new UnionFindEagerImpl(0);
		uf.union(0, 1);
	}

}
