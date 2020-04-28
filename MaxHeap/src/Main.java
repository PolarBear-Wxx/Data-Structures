import java.util.Random;

public class Main {

    public static void main(String[] args) {

        final int scale = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();

        for(int i = 0; i < scale; i ++){
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] test = new int[scale];

        for(int i = 0; i < scale; i++)
            test[i] = maxHeap.extractMax();

        for(int i = 1; i < scale; i ++){
            if(test[i - 1] <test[i])
                throw new IllegalArgumentException("Error!");
        }

        System.out.println("Good! The MaxHeap works well.");
    }
}
