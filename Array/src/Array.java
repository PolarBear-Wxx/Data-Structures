public class Array {

    private int[] data;
    private int size = 0;

    public Array(int capacity){
        data = new int[capacity];
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

    public void addLast(int e){
        if(size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        data[size] = e;
        size++;
    }

    public void addfist(int e){
        add(0, e);
    }

    public void add(int index, int e){
        if(size == data.length){
            throw new IllegalArgumentException("Add failed. Array is full");
        }
        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and <= size. ");
        }
        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size += 1;
    }

    public boolean contains(int e) {
       for(int i : data){
           if(i == e)
               return true;
       }
       return false;
    }

    public int find(int e){
        int index = -1;
        for(int i = 0; i < size; i++){
            if(data[i] == e){
                index = i;
            }
            if(index >= 0)
                break;
        }
        return index;
    }

    public int remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Deleate failed. The index is illegal.");
            //return;  "throw" will stop the method.
        }
        int res = data[index];
        for(int i = index; i < size; i++){
            data[i] = data[i+1];
        }
        size--;
        return res;
    }

    public int removeFirst(int index){
       return remove(0);
    }

    public int removeLast(int index){
        return remove(size - 1);
    }

    public void removeElement(int e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    void set(int index, int e){
        if(index  < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. The index is illegal.");
        }
    }

    int get(int index) {
        if(index < 0 || index >size - 1){
            throw new IllegalArgumentException("Get failed. The index is illegal!");
        }
        return data[index];
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
