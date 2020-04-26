public class BSTSet<E extends Comparable<E>> implements SET<E>{

    private BST<E> bst;

    @Override
    public void add(E e){
        bst.add(e);
    }

    @Override
    public void remove(E e){
        bst.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int size() {
        return bst.getSize();
    }

    public BSTSet(){
        bst = new BST<>();
    }
}
