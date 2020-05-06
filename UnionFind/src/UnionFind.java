public interface UnionFind {

    int getSize();
    void union(int p, int q);
    boolean isConnected(int p, int q);
    
}
