package src;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MathAlgorithm ma = new MathAlgorithm();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        long t1 = System.currentTimeMillis();
        int n = 100000;
        for (int i = 0; i < n; i++) {
            l1.add(ma.Fibonacci1(i));
        }

        long t2 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            l2.add(ma.Fibonacci3(i));
        }

        long t3 = System.currentTimeMillis();

        System.out.println((t2 - t1) + "\t" + (t3 - t2));

        for (int i = 0; i < n; i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                System.out.println(i);
            }
        }

    }
}