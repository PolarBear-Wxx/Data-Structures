public class MaxHeap<E extends Comparable<E>> {

    /**
     * 二叉堆是一棵完全二叉树，完全二叉树的“树结构”中的“父子兄弟关系”可以用数组
     * 下标的函数运算简单的表示出来，因此，完全可以用一个底层的动态数组实现二叉堆。
     *
     * ???可是动态数组的添加和删除操作的复杂度都是O(n)啊???
     *
     * 从“0”开始存放：
     *      parent(i) = ( i - 1 ) / 2
     *      left child (i) = 2 * i + 1
     *      right child (i0 = 2 * i + 2
     * 从“1”开始存放：
     *      parent(i) = i / 2
     *      left child = 2 * i
     *      right child = 2 * i + 1
     */

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    //Heapify an array
    public MaxHeap(E[] arr){
        //if(arr.length == 0)
        //    throw new IllegalArgumentException("An empty array can not be heapified!");
        data = new Array<>(arr);
        for(int i = parent(data.getSize() - 1); i >= 0; i --)
            siftDown(i);
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parents!");
        return (index - 1) / 2;
    }

    public int leftChild(int index){
        return index * 2 + 1;
    }

    public int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0){
            /**
            E temp = data.get(parent(index));
            data.set(parent(index), data.get(index));
            data.set(index, temp); */
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("MaxHeap is empty, you can not find the max.");
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();
        data.set(0, data.get(data.getSize() - 1));
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index){
        while (leftChild(index) < data.getSize()){
            int l = leftChild(index);
            if(l + 1 < data.getSize() && data.get(l + 1).compareTo(data.get(l)) > 0)
                l ++;
            if(data.get(index).compareTo(data.get(l)) < 0){
                data.swap(index, l);
                index = l;
            }
            else
                break;
        }
    }

    //取出最大元素，并添加新元素
    public E replace(E newe){
        E ret = findMax();
        data.set(0, newe);
        siftDown(0);
        return ret;
    }

}
