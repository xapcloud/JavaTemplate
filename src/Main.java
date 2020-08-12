package src;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        for (int i = 0; i < 30; i++) {
            System.out.println(algorithm.Fibonacci3(i));
            System.out.println(algorithm.Fibonacci2(i));
        }
    }
}