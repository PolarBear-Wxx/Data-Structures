public class LoopQueue<E> implements Queue<E>{

    private E[] data;   // 循环队列是基于数组的，front和tail都是“index”。
    private int front, tail;
    //private int capacity = data.length;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull(){
        return (tail + 1) % data.length == front;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void resize(int newcapacity){
        E[] newdata = (E[]) new Object[newcapacity + 1];
        int index = front;
        for(int i = 0; i < size; i++){
            newdata[i] = data[index];
            index = (index + 1) % data.length;
        }
        front = 0;
        tail = size;
        data = newdata;
    }

    @Override
    public void enqueue(E e) {
        if(isFull())
        //    throw new IllegalArgumentException("Enqueue failed. The queue is full.");
            resize(2 * getCapacity());
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Dequeue failed. The queue is empty.");
        E temp = data[front];
        front = (front + 1) % data.length;
        size--;
        if(size < data.length / 4 && data.length / 2 != 0)
            resize(getCapacity() / 2);
        return temp;
    }

    @Override
    public E getFront(){
        return data[front];
    }

    @Override
    public String toString() {
        if(this == null)
            throw new IllegalArgumentException("Failed. The queue hasn't been instanced.");
        if(size == 0)
            throw new IllegalArgumentException("Failed. The queue has no elements.");
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        //res.append(String.format("Front --> %d, Tail --> %d\n", front, tail));
        res.append("Front --> [ ");
        int index = front;
        for(int i = 0; i < this.size; i++){
           //int index = front;
           res.append(data[index]);
           if(i != size - 1)
               res.append(", ");
           index = (index + 1) % data.length;
        }
        res.append(" ]");
        return res.toString();
    }
}
