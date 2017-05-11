package unionfind;

public class UnionFindQuickImpl implements UnionFind {
	private int[] idArray;
	private int[] szArray;
	
	public UnionFindQuickImpl(int N) {
		
		idArray = new int[N];
		szArray = new int[N];
		for (int i = 0; i < idArray.length; i++){
			idArray[i] = i;
			szArray[i] = 1;
		}
	}
	
	@Override
	public void union(int p, int q) {
		int proot = find(p);
		int qroot = find(q);
		if (proot == qroot)	return;
		
		if (szArray[qroot] > szArray[proot]){
			idArray[proot] = qroot;
			szArray[qroot] += szArray[proot];
		} else {
			idArray[qroot] = proot;
			szArray[proot] += szArray[qroot];
		}
		
		

	}

	@Override
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public int find(int p) {
		
		int root = idArray[p];
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
