public class Main {

    public static void main(String[] args) {

        Integer[] nums = {1, 0, -4, 3, -9, 8};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });

        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 4));
        System.out.println(segmentTree.query(3, 5));

        segmentTree.set(3, 2);

        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 4));
        System.out.println(segmentTree.query(3, 5));
    }
}
