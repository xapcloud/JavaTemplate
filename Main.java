import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Pair<String, String>> set = new HashSet<>();

        Pair<String, String> p1 = new Pair("1","3");
        Pair<String, String> p2 = new Pair("1","3");

        System.out.println(p1.equals(p2));
    }
}