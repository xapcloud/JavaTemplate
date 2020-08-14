package src;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int n = 5000;
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();

        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a1.add(algorithm.fastPower(732, i, (int) 1e9 + 7));
        }

        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a2.add((int) (Math.pow(732, i) % ((int) 1e9 + 7)));
        }

        long t2 = System.nanoTime();

        System.out.println((t1 - t0) + "\t" + (t2 - t1));

        for (int i = 0; i < n; i++) {
            if (a1.get(i).equals(a2.get(i))) {
                System.out.println(i + "\t" + a1.get(i) + "\t" + a2.get(i));
            }
        }

    }
}