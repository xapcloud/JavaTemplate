package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Pair<String, String> p1 = new Pair<>("1", "3");
        Pair<String, String> p2 = new Pair<>("1", "3");

        System.out.println(p1.equals(p2));
    }

    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.computeIfAbsent(T[i], x -> new ArrayList<>()).add(i);
        }

        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            boolean found = false;
            for (int j = T[i] + 1; j <= 100; j++) {
                if (map.containsKey(j)) {
                    for (int idx : map.get(j)) {
                        if (idx > i) {
                            ret[i] = idx - i;
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found)
                ret[i] = 0;
        }

        return ret;
    }
}