/***
 * quick find:
 *            find -> O(1)
 *            union -> O(n)
 */
public class UnionFind1 implements UnionFind {

    private int[] id;

    public UnionFind1(int size){

        id = new int[size];

        for(int i = 0; i < id.length; i ++){
            id[i] = i;    //初始时每个元素的id不同
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int i){
        if(i < 0 || i > id.length)
            throw new IllegalArgumentException("Index is illegal.");
        return id[i];
    }

    @Override
    public void union(int p, int q) {

        int pid = id[p], qid = id[q];

        //每一次union都是O(n)的复杂度
        if(pid == qid)
            return;
        else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == qid)
                    id[i] = pid;
            }
        }
    }
}
