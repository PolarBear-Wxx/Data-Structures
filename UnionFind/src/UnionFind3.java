/***
 * 基于size的优化
 */
public class UnionFind3 implements UnionFind{

    private int[] parent;
    private int[] size;

    public UnionFind3(int size){

        parent = new int[size];
        this.size = new int[size];

        for(int i = 0; i < size; i ++) {
            parent[i] = i;
            this.size[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find_root(int i){

        if(i < 0 || i > parent.length)
            throw new IllegalArgumentException("Index is illegal.");

        while (parent[i] != i)
            i = parent[i];

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

        if(size[pRoot] > size[qRoot]){
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        else {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }
}
