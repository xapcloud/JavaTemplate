package src;

import java.util.*;
import java.util.stream.IntStream;

class UnionFind {
    int n;
    int[] parent;

    UnionFind(int n) {
        this.n = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    boolean union(int x, int y) {
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        if (find(min) == find(max))
            return false;
        parent[find(min)] = find(max);
        return true;
    }

    int find(int x) {
        if (x == parent[x])
            return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }

    @Override
    public String toString() {
        //return String.format("[%s]", Arrays.stream(parent).
        //       mapToObj(String::valueOf).
        //       collect(Collectors.joining(",")));
        return Arrays.toString(parent);
    }

}


public class Algorithm {
    void nextPermutation(int[] nums) {
        int maxIdx = nums.length - 1;
        int i = maxIdx;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i > 0) {
            for (int j = maxIdx; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    int temp = nums[i - 1];
                    nums[i - 1] = nums[j];
                    nums[j] = temp;
                    break;
                }
            }
        }
        int midIdx = i + (maxIdx - i) / 2;
        for (int k = i; k <= midIdx; k++) {
            int temp = nums[k];
            nums[k] = nums[i + maxIdx - k];
            nums[i + maxIdx - k] = temp;
        }
    }

    //Arrays.binarySearch(arr, target)
    //Collections.binarySearch(Arrays.stream(arr).boxed().
    //collect(Collectors.toList()), target)
    int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    int[] getSortedIndices(int[] arr) {
        return IntStream.range(0, arr.length).boxed().
                sorted(Comparator.comparingInt(i -> arr[i])).
                //sorted((i, j) -> arr[i] - arr[j]).
                        mapToInt(a -> a).toArray();
    }

    List<ArrayList<Integer>> getCombination(int n, int m) {
        List<ArrayList<Integer>> ret = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.empty()) {
            int topVar = stack.peek();
            if (stack.size() == m) {
                ret.add(new ArrayList<>(stack));
                if (topVar + 1 <= n) {
                    stack.pop();
                    stack.push(topVar + 1);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        break;
                    }
                    int secTopVar = stack.pop();
                    stack.push(secTopVar + 1);
                }
            } else {
                if (topVar + 1 <= n) {
                    stack.push(topVar + 1);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        break;
                    }
                    int secTopVar = stack.pop();
                    stack.push(secTopVar + 1);
                }
            }
        }
        return ret;
    }

    boolean topologicalSort(int n, List<Pair<Integer, Integer>> edges) {
        int[] inDegree = new int[n];
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (Pair<Integer, Integer> p : edges) {
            inDegree[p.getValue()]++;
            adj.computeIfAbsent(p.getKey(), k -> new ArrayList<>()).add(p.getValue());
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                result.add(i);
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (adj.containsKey(result.get(i))) {
                for (int target : adj.get(result.get(i))) {
                    inDegree[target]--;
                    if (inDegree[target] == 0) {
                        result.add(target);
                    }
                }
            }
        }

        return result.size() == n;
    }
}
