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

    int findInsertPosition(int[] arr, int l, int r, int target) {
        if (arr[l] >= target)
            return l;
        if(arr[r] < target) {
            return target + 1;
        }

        while(r - l > 1) {
            int mid = (r + l) / 2;
            if(arr[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l + 1;
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

    // 获取组合数
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

    // 拓扑排序
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

    // 最长回文子序列
    public int getLPSeq(String s) {
        int n = s.length();
        int [][] lp = new int[n][n];
        for (int i = 0; i < n; i++) {
            lp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; i+j < n; j++) {
                if (s.charAt(j) == s.charAt(i+j)) {
                    lp[j][i+j] = lp[j+1][i+j-1] + 2;
                } else {
                    lp[j][i+j] = Math.max(lp[j+1][i+j], lp[j][i+j-1]);
                }
            }
        }
        return lp[0][n-1];
    }


    // 最长回文子串， 直接遍历所有位置作为中心效率差不多
    public String getLPS(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 第一维参数表示起始位置坐标，第二维参数表示终点坐标
        // lps[j][i] 表示以 j 为起始坐标，i 为终点坐标是否为回文子串
        boolean[][] lps = new boolean[length][length];
        int maxLen = 1; // 记录最长回文子串最长长度
        int start = 0; // 记录最长回文子串起始位置
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j < 2) {
                    // 子字符串长度小于 2 的时候单独处理
                    lps[j][i] = chars[i] == chars[j];
                } else {
                    // 如果 [i, j] 是回文子串，那么一定有 [j+1, i-1] 也是回子串
                    lps[j][i] = lps[j + 1][i - 1] && (chars[i] == chars[j]);
                }
                if (lps[j][i] && (i - j + 1) > maxLen) {
                    // 如果 [i, j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
