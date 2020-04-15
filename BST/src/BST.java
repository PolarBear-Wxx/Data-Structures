public class BST<E extends Comparable<E>> { //二分搜索树处理的数据类型必须是可比较的

    private class Node{

        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return  size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
    public void add(E e){
        add(root, e);
    }
    private void add(Node node, E e){  //  java采用的是按值调用，传递给方法的是实参的一个拷贝,这个拷贝或许是一个基本数据类型的值，
        if(node == null){              //或者是一个和实参指向相同对象的引用。
            node = new Node(e);        //  在这里，node作为实参的一个拷贝（即和实参指向同一个对象的引用），将其重新重新指向一个刚
            size ++;                   //new的对象的操作对实参指向的对象并没有任何影响，函数调用结束后node就被回收了。
            return;
        }
        else if(e.equals(node.e))
            return;
        else if(e.compareTo(node.e) < 0){
            add(node.left, e);
        }
        else if(e.compareTo(node.e) > 0){
            add(node.right, e);
        }
    }
     */

    //向实例中添加元素e
    public void add(E e){
        root = add(root, e);
    }
    //递归地处理add操作
    private Node add(Node node, E e){
        /**
        if(node == null){
            size ++;
            return new Node(e);        //既然这个if执行的了return，那就不必接着写else if了
        }
        else if(e.equals(node.e)){     //这个判断和最后返回同一个引用，可以不写
            return node;
        }
        else if(e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        }
        else
            node.right = add(node.right, e);
        return node;
         */

        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    //查询该实例是否含有值为e的结点
    public boolean contains(E e){
        return contains(root, e);
    }
    //递归地处理查询操作
    private boolean contains(Node node, E e){
        if(node == null)
            return false;

        if(node.e.equals(e))
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else  // e.compareTo(node.e
            return contains(node.right, e);
    }
}
