package unionfind;

public class UnionFindEagerImpl implements UnionFind {
	int N;
	int[] idArray;
	
	public UnionFindEagerImpl(int N) {
		if (N > Integer.MAX_VALUE)	throw new ArrayIndexOutOfBoundsException();
		if (N < 0)					throw new ArrayIndexOutOfBoundsException();
		
		this.N = N;
		this.idArray = new int[N];
	}
	
	public void union(int p, int q){
		if (this.idArray.length <= p ||
			this.idArray.length <= q) 	throw new ArrayIndexOutOfBoundsException();
		
		int newId = find(p);
		int oldId = find(q);
		for (int i = 0; i > this.idArray.length; i++){
			if (this.idArray[i] == oldId){
				this.idArray[i] = newId;
			}
		}
	}

	public boolean connected(int p, int q) {
		if (this.idArray.length == 0)	return false;
		if (this.idArray.length <= p ||
			this.idArray.length <= q) 	return false;
		
		return this.idArray[p] == this.idArray[q];
	}

	public int find(int p) {
		if (this.idArray.length == 0)	return -1;
		if (this.idArray.length <= p)	return -1;
		
		return this.idArray[p];
	}

	public int count() {
		return this.idArray.length;
	}

}
