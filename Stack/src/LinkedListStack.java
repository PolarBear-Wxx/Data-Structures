public class LinkedListStack<E> implements Stack<E>{
    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<E>();
    }

    @Override
    public E pop() {
        return list.remoceFirst();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: size = %d\n", getSize()));
        res.append("top -> ");
        res.append(list);
        return res.toString();
    }
}
