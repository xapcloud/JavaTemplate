package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = null;

    public static void main(String[] args) {
        Map<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < 20; i ++){
            map.put(i, fac(i));
        }

        int T = nextInt();
        for (int i = 0; i < T; i++) {
            int n = nextInt();
            int[] songLen = new int[n];
            for (int j = 0; j < n; j++) {
                songLen[j] = nextInt();
            }
            int q = nextInt();
            int [][] op = new int[q][2];
            for (int j = 0; j < q; j++) {
                op[j][0] = nextInt();
                op[j][1] = nextInt();
            }
            solve(n, songLen, q, op);
        }
    }

    private static void solve(int n, int[] songLen, int q, int[][]op) {

        TreeMap<Long, Long> tm = new TreeMap<>();
        long startIdx = 0;
        for (int i = 0; i < n; i++) {
            tm.put(startIdx, (long)i+1);
            startIdx += songLen[i];
        }
        long idx = n+1;
        long curTime = 0;
        for (int i = 0; i < q; i++) {
            if (op[i][0] == 1) {
                tm.put(startIdx, idx++);
                startIdx += op[i][1];
            } else {
                curTime = curTime + op[i][1];
                curTime = curTime % startIdx;

                if (tm.containsKey(curTime)) {
                    System.out.println(tm.get(curTime));
                } else {
                    System.out.println(tm.lowerEntry(curTime).getValue());
                }
            }
        }
    }


    public static long fac(int n ) {
        if (n == 0) {
            return 1;
        }
        return  fac(n-1) * n;
    }


    static String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}