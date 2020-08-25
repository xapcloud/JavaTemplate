package src;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 5;
        System.out.println(algorithm.binarySearch(arr, target));
        System.out.println(Arrays.binarySearch(arr, target));
        System.out.println(Collections.binarySearch(Arrays.stream(arr).boxed().collect(Collectors.toList()), target));
    }
}