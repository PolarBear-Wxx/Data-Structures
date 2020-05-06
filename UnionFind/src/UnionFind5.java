/***
 * 路径压缩（rank其实仍是合理的，因为他是“排名”，而不是真正的“高度”）
 */
public class UnionFind5 implements UnionFind{

    private int[] parent;
    private int[] rank;

    public UnionFind5(int size){

        parent = new int[size];
        rank = new int[size];

        for(int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find_root(int i){

        if(i < 0 || i > parent.length)
            throw new IllegalArgumentException("Index is illegal.");

        while (parent[i] != i) {
            i = parent[i];
            parent[i] = parent[parent[i]];
        }
        return i;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find_root(p) == find_root(q);
    }

    @Override
    public void union(int p, int q) {

        int pRoot = find_root(p), qRoot = find_root(q);

        if(pRoot == qRoot)
            return;

        if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else {
            parent[pRoot] = qRoot;
            rank[qRoot] ++;
        }
    }
}
