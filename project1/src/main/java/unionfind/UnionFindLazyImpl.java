package unionfind;

public class UnionFindLazyImpl implements UnionFind {
	private int[] idArray;
	
	public UnionFindLazyImpl(int N) {
		if (N > Integer.MAX_VALUE)	throw new ArrayIndexOutOfBoundsException();
		if (N < 0)					throw new ArrayIndexOutOfBoundsException();

		idArray = new int[N];
		for (int i = 0; i < idArray.length; i++){
			idArray[i] = i;
		}
	}
	
	@Override
	public void union(int p, int q) {
		if (this.idArray.length <= p ||
			this.idArray.length <= q) 	throw new ArrayIndexOutOfBoundsException();

		int proot = find(p);
		int qroot = find(q);
		if (proot == qroot) 	return; // already in same set
		
		idArray[qroot] = proot;
	}

	@Override
	public boolean connected(int p, int q) {
		if (this.idArray.length == 0)	return false;
		if (this.idArray.length <= p ||
			this.idArray.length <= q) 	return false;

		return find(p) == find(q);
	}

	@Override
	public int find(int p) {
		if (this.idArray.length == 0)	return -1;
		if (this.idArray.length <= p)	return -1;
		
		int root = this.idArray[p];
		while(root != p){
			p = idArray[root];
			root = idArray[p];
		}
		return root;
	}

	@Override
	public int count() {
		return idArray.length;
	}

}
