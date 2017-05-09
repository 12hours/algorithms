package unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindImpl implements UnionFind {
	private int N;

	public UnionFindImpl(int N) {
		this.N = N;
	}

	public void union(int p, int q) {
		// TODO Auto-generated method stub

	}

	public boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return false;
	}

	public int find(int p) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		UnionFind uf = new UnionFindImpl(N);
		while (!StdIn.isEmpty()){	
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)){
				uf.union(p, q);
				StdOut.print(p + " " + q + "\n");
			}
		}
	}
}
