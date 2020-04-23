import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //前序遍历BST
    public void preOrder(){
        preOrder(root);
    }
    //前序遍历以node为根的BST
    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    //后序遍历：比如用于释放内存
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    //非递归实现前序遍历，中、后序遍历当然也能非递归实现，但麻烦一些，事实上，非递归可以通过模拟系统栈的方式实现“伪递归”
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();

        if(root != null)
            stack.push(root);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    //层序遍历

    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();  //java中的Queue是一个接口，new后面要接它的某个实现
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.poll();
            System.out.println(cur.e);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null);
                q.add(cur.right);
        }
    }

    // return the element with the minimum value  --recursive
    public E minimum(){
        if(root == null)
            //return null;
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }
    private Node minimum(Node node){
        if(node.left != null)
            return minimum(node.left);
        else
            return node;
    }

    // return the element with the maximum value  --NR
    public E maximum(){
        if(root == null)
            //return null;
            throw new IllegalArgumentException("BST is empty");
        Node cur = root;
        while(cur.left != null){
            cur = cur.left;
        }
        return  cur.e;
    }

    //remove the element with the minimum value  --recursive
    //return the deleted element
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    //remove the element with the minimum value on "node"
    //return the root after deletion
    private Node removeMin(Node node){
        //递归终止条件
        if(node.left == null){
            Node noderight = node.right;
            node.right = null;    //确保该结点被回收？？
            size --;
            return noderight;
        }
        else {
            node.left = removeMin(node.left);
            return node;         //返回当前的node，它没有被删除，仍将是以它为根的BST的根
        }
    }

    //删除BST中最大值所在的结点
    //返回所删除的最大值
    public E removeMax(){
        E ret = maximum();     //当root为空时，此处就会抛出异常
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根的BST的最大值所在的结点
     * 返回删除操作后的新的BST的根
     * recursive
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node nodeleft = node.left;
            node.left = null;
            size --;
            return nodeleft;
        }
        node.right = removeMax(node.left);
        return node;
    }

    //删除BST中值为e的结点
    public void remove(E e){
        root = remove(root, e);
    }
    public Node remove(Node node, E e){
        if(node == null)
            return null;
        else if(node.e.compareTo(e) < 0){
            node.right = remove(node.right, e);
            return node;
        }
        else if(node.e.compareTo(e) > 0){
            node.left = remove(node.left, e);
            return node;
        }
        else {  //node.e.equal(e)
            //待删除结点只有左子树
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            //待删除结点只有右子树
            else if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            /**
             * 待删除结点左右子树均不为空
             * 我们选择其“后继”结点取代该节点的位置
             * “后继”结点应该是其右子树的值最小的结点
             * 当然也可以选择该节点的“前驱”来取代该节点
             */
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;

            return successor;
        }
    }

}
