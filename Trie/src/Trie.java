import java.util.Map;
import java.util.TreeMap;

public class Trie {

    private class Node{

        boolean isWord;
        Map<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this.isWord = false;
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSie(){
        return size;
    }

    public void add(String word){

        Node cur = root;

        for(int i = 0; i < word.length(); i ++){
            Character c = word.charAt(i);
            if(!cur.next.containsKey(c))
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if(cur.isWord == false){
            cur.isWord = true;
            size ++;
        }
    }

    public boolean contains(String word){

        Node cur = root;

        for(int i = 0; i < word.length(); i ++){
            Character c = word.charAt(i);
            if(!cur.next.containsKey(c))
                return false;
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    public boolean isPrefix(String prefix){

        Node cur = root;

        for(int i = 0; i < prefix.length(); i ++){
            Character c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return  false;
            cur = cur.next.get(c);
        }

        return true;
    }

}
