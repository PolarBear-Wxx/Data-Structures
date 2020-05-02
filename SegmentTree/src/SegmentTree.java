public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for(int i = 0; i < arr.length; i ++){
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * data.length];

        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftChild(treeIndex), l, mid);
        buildSegmentTree(rightChild(treeIndex), mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString() {

        StringBuilder ret = new StringBuilder();

        ret.append("[ ");

        for(int i = 0; i < tree.length - 1; i ++){
            ret.append(tree[i] + ", ");
        }

        ret.append(tree[tree.length - 1] + " ]");

        return ret.toString();
    }

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 显然，每次查询在不抛出下标异常的情况下，[queryL, queryR]一定包含于[l, r]；
     * 因此，两者的位置存在三种关系，。。。。
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if(l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if(queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 将index位置的值，更新为e
    public void set(int index, E e){

        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){

        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }
}
