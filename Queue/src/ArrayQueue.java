public class ArrayQueue<E> implements Queue<E>{

    private Array<E> data;

    public ArrayQueue(int capacity){
        data = new Array<>(capacity);
    }

    public ArrayQueue(){
        data = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    public int getCapacity(){
        return data.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        if(data == null)
            throw new IllegalArgumentException("Failed. This queue hasn't been instanced.");
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Failed. This queue has no elements.");
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("Front--> [ ");
        for(int i = 0; i < getSize(); i++){
            res.append(data.get(i));
            if(i !=  getSize() - 1)
                res.append(", ");
        }
        res.append(" ] <-- tail");
        return res.toString();
    }

}
