public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> data;

    public PriorityQueue(){
        data = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        data.add(e);
    }

    @Override
    public E dequeue() {
        return data.extractMax();
    }

    @Override
    public E getFront() {
        return data.findMax();
    }
}
