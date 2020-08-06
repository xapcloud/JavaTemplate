
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.security.MessageDigest;


public class StandardInOut {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = null;
    static PrintWriter pw = new  PrintWriter(System.out);

    public static void main(String [] args){
        int T = nextInt();
        for (int i = 0; i < T; i++) {
            String s = next();
            solve(s);
        }
        pw.flush();
        pw.close();
    }

    public static void solve(String s){
        pw.println(s);
    }

    static String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
    static int nextInt(){
        return Integer.parseInt(next());
    }
}