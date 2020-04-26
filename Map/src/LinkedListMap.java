public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){ this(key, value,null);}

        public Node(K key) { this(key, null, null); }

        public Node() { this(null, null, null); }
    }

    private int size;
    private Node dummyHead;

    public LinkedListMap(){
        //Node head = new Node();
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if(node != null)
            return node.value;
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        else
            node.value = value;
    }

    @Override
    public boolean contains(K key) {
        /**
        if(getNode(key) == null)
            return false;
        return true ;
         */
        return getNode(key) != null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node != null)
            //set(key, value);
            node.value = value;
        else {
            Node newNode = new Node(key, value);
            newNode.next = dummyHead.next;
            dummyHead.next = newNode;
            size ++;
        }
    }

    @Override
    public V remove(K key) {

        Node pre = dummyHead;

        while(pre.next != null && !pre.next.key.equals(key)){
            pre = pre.next;
        }

        if(pre.next != null){
            // pre.next = pre.next.next;
            /**
            Node node = pre.next.next;
            pre.next.next = null;
            V removedValue = pre.next.value;
            pre.next = node;
            return removedValue;
             */
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;

        //throw new IllegalArgumentException(key + " doesn't exist!");

    }

    @Override
    public int getSize() {
        return size;
    }

    private Node getNode(K key){

        Node cur = dummyHead.next;

        while (cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }

        return null;
    }

}
