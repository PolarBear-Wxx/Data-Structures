import java.util.Random;

public class Main {

    private static double testUF(UnionFind uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for(int i = 0; i < m; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.union(a, b);
        }

        for(int i = 0; i < m; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {

        int size = 10000000, m = 10000000;
        //UnionFind1 uf1 = new UnionFind1(size);
        //UnionFind2 uf2 = new UnionFind2(size);
        UnionFind3 uf3 = new UnionFind3(size);
        UnionFind4 uf4 = new UnionFind4(size);
        UnionFind5 uf5 = new UnionFind5(size);

        //double test1 = testUF(uf1, m);
        //double test2 = testUF(uf2, m);
        double test3 = testUF(uf3, m);
        double test4 = testUF(uf4, m);
        double test5 = testUF(uf5, m);

        //System.out.println("uf1: " + test1 + "s.");
        //System.out.println("uf2：" + test2 + "s.");
        System.out.println("uf3: " + test3 + "s.");
        System.out.println("uf4: " + test4 + "s.");
        System.out.println("uf5: " + test5 + "s.");
    }
}
