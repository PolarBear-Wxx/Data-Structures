public class Main {

    public static void main(String[] args) {
	// write your code here
        LinkedList<Integer> list = new LinkedList();
        for(int i = 0; i < 5; i++){
            list.addFirst(i);
            System.out.println(list);
        }

        list.add(567, 3);
        System.out.println(list);
    }
}
