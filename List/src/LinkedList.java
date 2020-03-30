public class LinkedList<E> {

    private class Node{

        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;  //dummyHead虽然浪费一个Node的空间，但是却使得链表中的每一个元素都有了“previous”，
    private int size;        //由此获得了逻辑上的统一。

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //在链表头添加新的元素e
    public void addFirst(E e){
        add(e, 0);
    }

    //在链表中间（index：0 - based）添加新的元素e
    //非链表常用操作，练习用
    public void add(E e, int index){
        if(index < 0 || index > size) throw new IllegalArgumentException("failed. The index is illegal.");
        Node prev = dummyHead;
        for(int i = 0; i < index; i++){
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size ++;
    }

    public void addLast(E e){
        add(e, size);
    }

    public E get(int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException("Get failed. The index is illegal.");
        Node cur = dummyHead;
        for(int i = 0; i <= index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(E e,int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException("Get failed. The index is illegal.");
        Node cur = dummyHead;
        for(int i = 0; i <= index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();

        res.append(String.format("LinkedList: size = %d\n", size));
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "-> ");
        }
        res.append("NULL");
        return res.toString();
    }
}
