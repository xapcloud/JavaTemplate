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
            solve(n, map);
        }

    }

    private static void solve(int n, Map<Integer, Long> map) {
        int x = n; int y = 1;
        for(int i = 0; i < 20; i ++) {
            boolean flag = false;
            for(int j = 0; j  <= i ; j ++){
                if(map.get(i)/(map.get(j)*map.get(i-j)) == n && map.get(i)%(map.get(j)*map.get(i-j)) == 0){
                    x = i;
                    y = j;
                    flag = true;

                    break;
                }
            }
            if(flag){
                break;
            }
        }
        System.out.println(x+" "+y);
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