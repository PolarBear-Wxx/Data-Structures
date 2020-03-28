public class Array<E> {

    private E[] data;
    private int size = 0;

    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addfist(E e){
        add(0, e);
    }

    public void add(int index, E e){
        /*
        if(size == data.length){
            throw new IllegalArgumentException("Add failed. Array is full");
        }
        */
        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and <= size. ");
        }
        if(size == data.length){
            resize(2 * data.length);
        }
        for(int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size += 1;
    }

    public boolean contains(E e) {
       for(E i : data){
           if(i == e)
               return true;
       }
       return false;
    }

    public int find(E e){
        int index = -1;
        for(int i = 0; i < size; i++){
            if(data[i].equals(e)){          //“ == ”是引用比较，而此时使用泛型“E”，比较时应该使用值比较“equals”。
                index = i;
            }
            if(index >= 0)
                break;
        }
        return index;
    }

    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Deleate failed. The index is illegal.");
            //return;  "throw" will stop the method.
        }
        E res = data[index];
        for(int i = index; i < size; i++){
            data[i] = data[i+1];
        }
        size--;
        data[size] = null; // loitering objects != memory leak, but 还是回收了的好，所以最好把它的引用给摘了
        if(size <= data.length / 2){
            resize(data.length / 2);
        }
        return res;
    }

    public E removeFirst(){
       return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    void set(int index, E e){
        if(index  < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. The index is illegal.");
        }
        data[index] = e;
    }

    E get(int index) {
        if(index < 0 || index >size - 1){
            throw new IllegalArgumentException("Get failed. The index is illegal!");
        }
        return data[index];
    }

    private void resize(int newLength){
        E[] newdata = (E[]) new Object[newLength];
        for(int i = 0; i < size; i++){
            newdata[i] = data[i];
        }
        data = newdata;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d; capacity = %d\n", size, data.length));
        res.append("[");
        for(int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
