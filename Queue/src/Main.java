public class Main {

    public static void main(String[] args) {
	// write your code here
        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0; i < queue.getCapacity(); i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        queue.enqueue(99);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.getFront());
        queue.enqueue(99);
        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.getFront());
    }
}
