public class ArrayStack<E> implements Stack<E> {
    //private Array<E> data = new Array<>();
    private Array<E> data;

    public ArrayStack(int capacity){
        data = new Array<>(capacity);
    }

    public ArrayStack(){
        data = new Array<>();
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
    public E pop() {
        E temp = data.removeLast();
        return temp;
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public String toString() {
        if(data == null)
            throw new IllegalArgumentException("Failed. The instance is null.");
        if(getSize() == 0)
            throw new IllegalArgumentException("Failed. The size of the stack is 0.");
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("[ ");
        for(int i = 0; i < getSize(); i++){
            res.append(peek());
            if(i != getSize() - 1)
                res.append(", ");
        }
        res.append(" ] top");
        return res.toString();
    }
}
