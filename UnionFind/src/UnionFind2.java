/***
 * quick union:
 *             find -> O(log(n))
 *             union -> O(log(n))
 */
public class UnionFind2 implements UnionFind{

    private int[] tree;

    public UnionFind2(int size){

        tree = new int[size];

        for(int i = 0; i < tree.length; i ++){
           tree[i] = i;  //初始时，每个节点的跟都是自己
        }
    }

    @Override
    public int getSize() {
        return tree.length;
    }

    private int find_root(int i){

        if(i < 0 || i > tree.length)
            throw new IllegalArgumentException("Index is illegal.");

        while (tree[i] != i)
            i = tree[i];

        return i;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find_root(p) == find_root(q);
    }

    @Override
    public void union(int p, int q) {

        int pRoot = find_root(p), qRoot = find_root(q);

        if(pRoot != qRoot)
            tree[pRoot] = qRoot;
    }
}
