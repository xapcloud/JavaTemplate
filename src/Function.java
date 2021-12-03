package src;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
 interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    List<NestedInteger> nestedList;
    List<Integer> list;


    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.list = new ArrayList<>();
        for (NestedInteger ni: nestedList) {
            this.list.addAll(flattenList(ni));
        }
    }

    public List<Integer> flattenList(NestedInteger ni) {
        List<Integer> ret = new ArrayList<>();
        if (ni.isInteger()) {
            ret.add(ni.getInteger());
        } else {
            for (NestedInteger child: ni.getList()) {
                ret.addAll(flattenList(child));
            }
        }
        return ret;
    }

    @Override
    public Integer next() {
        return list.remove(0);
    }

    @Override
    public boolean hasNext() {
        return list.size() > 0;
    }
}

class MinStack {
    Stack<Integer> stack;
    List<Integer> list;

    public MinStack() {
        stack = new Stack<>();
        list = new ArrayList<>();
    }

    public void push(int val) {
        stack.push(val);
        if (list.isEmpty() || val < list.get(list.size()-1)) {
            list.add(val);
        } else {
            list.add(list.get(list.size()-1));
        }
    }

    public void pop() {
        stack.pop();
        list.remove(list.size()-1);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.get(list.size()-1);
    }
}

class Solution {
    int [] origin;
    int n;

    public Solution(int[] nums) {
        this.origin = nums;
        this.n = nums.length;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int [] ret = Arrays.copyOf(origin, n);
        for (int i = n-1; i > 0 ; i--) {
            int cur = new Random().nextInt(i+1);
            int tmp = ret[i];
            ret[i] = ret[cur];
            ret[cur] = tmp;
        }
        return ret;
    }
}

class MyCircularDeque {
    int capacity;
    List<Integer> list;

    public MyCircularDeque(int k) {
        this.capacity = k;
        this.list = new ArrayList<>();
    }

    public boolean insertFront(int value) {
        if (list.size() < capacity) {
            list.add(0, value);
            return true;
        } else {
            return false;
        }
    }

    public boolean insertLast(int value) {
        if (list.size() < capacity) {
            list.add(value);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteFront() {
        if (!list.isEmpty()) {
            list.remove(0);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteLast() {
        if (!list.isEmpty()) {
            list.remove(list.size() -1);
            return true;
        } else {
            return false;
        }
    }

    public int getFront() {
        return list.isEmpty() ? -1 : list.get(0);
    }

    public int getRear() {
        return list.isEmpty() ? -1 : list.get(list.size() -1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return list.size() == capacity;
    }
}

// 729
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            map.put(val, list.size()-1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            if (map.get(val) == list.size() -1) {
                list.remove(list.size()-1);
                map.remove(val);
            } else {
                list.set(map.get(val), list.get(list.size()-1));
                list.remove(list.size()-1);
                map.put(list.get(map.get(val)), map.get(val));
                map.remove(val);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        int r = (int)(Math.random() * list.size());
        return list.get(r);
    }
}

class MyCalendar {

    public MyCalendar() {

    }

    public boolean book(int start, int end) {

        return true;
    }
}

class LockingTree {
    private int [] parent;
    private int [] status;
    private Map<Integer, Set<Integer>> children;
    private int n;

    public LockingTree(int[] parent) {
        this.n = parent.length;
        this.parent = parent;
        status = new int[n];

        children = new HashMap<>();
        for (int i = 0; i < n; i++) {
            children.computeIfAbsent(parent[i], x -> new HashSet<>()).add(i);
        }
        getChildren(0);
    }

    private void getChildren(int num) {
        if (children.containsKey(num)) {
            Set<Integer> cur = children.get(num);
            Set<Integer> des = new HashSet<>();
            for (int x : cur) {
                if (children.containsKey(x)){
                    getChildren(x);
                    des.addAll(children.get(x));
                }
            }
            cur.addAll(des);
            children.put(num, cur);
        }
    }

    public boolean lock(int num, int user) {
        if (status[num] == 0) {
            status[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (status[num] == user) {
            status[num] = 0;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        System.out.println(Arrays.toString(status));
        if (status[num] != 0 || !children.containsKey(num)) {
            return false;
        }
        int tmp = num;
        while(tmp != -1 && status[tmp] == 0) {
            tmp = parent[tmp];
        }
        if (tmp != -1){
            return false;
        }

        boolean hasLockedChildren = false;
        for(int x: children.get(num)) {
            if(status[x] != 0) {
                hasLockedChildren = true;
                break;
            }
        }
        if (hasLockedChildren) {
            status[num] = user;
            for(int x: children.get(num)) {
                status[x] = 0;
            }
            return true;
        }

        return false;
    }
}

class Test {
    public int a;

    public Test(int a){
        this.a= a;
    }

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                '}';
    }
}


class CQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;
    
    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.add(value);
    }

    public int deleteHead() {
        if (s1.isEmpty()) {
            return -1;
        } else {
            while (!s1.isEmpty()){
                s2.add(s1.pop());
            }
            int ret = s2.pop();
            while(!s2.isEmpty()){
                s1.add(s2.pop());
            }
            return ret;
        }
    }
}


class FindSumPairs {
    int[] nums1;
    int[] nums2;
    Map<Integer, Integer> nums1Map;
    Map<Integer, Integer> nums2Map;


    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        nums1Map = new HashMap<>();
        nums2Map = new HashMap<>();
        for(int x: nums1) {
            nums1Map.put(x, nums1Map.getOrDefault(x,0) + 1);
        }
        for(int x: nums2){
            nums2Map.put(x, nums2Map.getOrDefault(x,0) + 1);
        }
    }

    public void add(int index, int val) {
        int oldValue = nums2[index];
        nums2[index] += val;
        int remain = nums2Map.get(oldValue) - 1;
        if (remain == 0) {
            nums2Map.remove(oldValue);
        } else {
            nums2Map.put(oldValue, remain);
        }
        nums2Map.put(nums2[index], nums2Map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        System.out.println(nums1Map.toString());
        System.out.println(nums2Map.toString());
        int ret = 0;
        for(int x: nums1Map.keySet()) {
            ret += nums1Map.get(x) * nums2Map.getOrDefault(tot -x, 0);
        }
        return ret;
    }
}

/*
There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that exactly
k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.

For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 109 + 7.


 */


class AA {
    float a;

    AA(float a){
        this.a = a;
    }

    @Override
    public String toString() {
        return "AA{" +
                "a=" + a +
                '}';
    }
}

public class Function {
    static DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Map<Integer, Integer> map = new HashMap<>();




    public static void main(String[] args) throws IOException {
        Function f = new Function();
//        Algorithm a = new Algorithm();
//        List<ArrayList<Integer>> ret = a.getCombination(4,3);
//        for (List<Integer> l : ret) {
//            System.out.println(l);
//        }

//        Map<Integer, Integer> m = new HashMap<>();
//        System.out.println(test(m));
//        System.out.println(m);

//        try (InputStream input = new FileInputStream("/Users/xyma/IdeaProjects/JavaTemplate/base.properties")) {
//
//            Properties prop = new Properties();
//
//            // load a properties file
//            prop.load(input);
//
//            // get the property value and print it out
//            System.out.println(prop.getProperty("db.url"));
//            System.out.println(prop.getProperty("macdonaldsSep"));
//            System.out.println(prop.getProperty("appSep"));
//
//            String s = "a,b";
//            System.out.println(Arrays.toString(s.split(prop.getProperty("macdonaldsSep"))));
//                    BufferedReader reader;
//        try {
//            reader = new BufferedReader(new FileReader(
//                    "/Users/xyma/Downloads/part-00221"));
//            String line = reader.readLine();
//            while (line != null) {
//                System.out.println(Arrays.toString(line.split(prop.getProperty("appSep"))));
//                // read next line
//                line = reader.readLine();
//                break;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

//        MathAlgorithm a = new MathAlgorithm();
//
//        double ret = 0;
//        for(int i = 6; i <= 10; i ++) {
//            long cur = a.combination(10, i);
//            ret += cur * Math.pow(1/4.0, i) * Math.pow(3/4.0, 10-i);
//        }
//        System.out.println(ret);

//        System.out.println(f.splitIntoFibonacci("214748364721474836422147483641"));
//        System.out.println(f.splitIntoFibonacci("214748364612147483647"));
//        //System.out.println(Integer.MAX_VALUE - 1);

//        String [] ss = {"aaaa","asas","able","ability","actt","actor","access"};
//        String [] x = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
//        System.out.println(f.findNumOfValidWords(ss, x));
        //char[][] s = {{'.','.'},{'X','X'}};
        //System.out.println(f.countBattleships(s));


        //"mississippi"
        //"m??*ss*?i*pi"

//        char [][] x = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        //int [] x = {2,4,5,6,7,8,11,13,14,15,21,22,34};
        //int [] x = {2,4,5,6,7,8,11,13,14};
        //int [] x = {6,7,13,14,20};
        int [] x = {2,4,7,8,9,10,14,15,18,23,32,50};
        System.out.println(f.lenLongestFibSubseq(x));
    }


    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        int [] max = new int[n];
        int [] pre = new int[n];
        int ret = 0;
        max[0] = 1;
        max[1] = 1;
        pre[0] = -1;
        pre[1] = -1;
        map.put(arr[0], 0);
        map.put(arr[1], 1);
        for (int i = 2; i < n; i++) {
            max[i] = 1;
            map.put(arr[i], i);
            for (int j = i-1; j > 0; j--) {
                int diff = arr[i] - arr[j];
                if (map.containsKey(diff) && map.get(diff) < j){
                    if (pre[map.get(diff)] == -1 || arr[pre[map.get(diff)]] + arr[map.get(diff)] == arr[j]) {
                        max[i] = Math.max(max[i], max[map.get(diff)] + 2);
                        ret = Math.max(max[i], ret);
                        pre[i] = j;
                    } else {
                        max[i] = Math.max(max[i], 3);
                        pre[i] = j;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%d: %d %d %d\n", i, arr[i], max[i], pre[i]);
        }

        return ret;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int [][] lenOne = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    lenOne[i][j] = (i > 0 ? lenOne[i-1][j]: 0) + 1;
                } else {
                    lenOne[i][j] = 0;
                }
            }
        }
//        for(int [] x : lenOne) {
//            System.out.println(Arrays.toString(x));
//        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (lenOne[i][j] > 0) {
                    int min = lenOne[i][j];
                    for (int k = j; k >= 0 ; k--) {
                        min = Math.min(min, lenOne[i][k]);
                        max = Math.max(min * (j-k+1), max);
                    }
                }
            }
        }

        return max;
    }


    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int [] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = nums[i] + (i > 0 ? sum[i-1] : 0);
        }
        for (int i = 0; i < n; i++) {
            int left = sum[i] - nums[i];
            int right = sum[n-1] - sum[i];
            if (left == right) {
                return i;
            }
        }

        return -1;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> set = new TreeMap<>();
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : accounts) {
            for (int i = 1; i < list.size(); i++) {
                set.put(list.get(i), list.get(0));
            }
        }
        int idx = 0;
        for(String key : set.keySet()) {
            map.put(key, idx++);
        }

        UnionFind uf = new UnionFind(set.size());
        for (List<String> list : accounts) {
            for (int i = 1; i < list.size() -1; i++) {
                uf.union(map.get(list.get(i)), map.get(list.get(i+1)));
            }
        }

        Map<Integer, List<String>> tmp = new HashMap<>();
        for(String key: set.keySet()) {
            int p = uf.find(map.get(key));
            if (!tmp.containsKey(p)) {
                tmp.computeIfAbsent(p, x-> new ArrayList<>()).add(set.get(key));
            }
            tmp.get(p).add(key);
        }
        List<List<String>> ret = new ArrayList<>(tmp.values());
        return ret;
    }


    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets <= 1)
            return 0;
        int n = minutesToTest/minutesToDie + 1;
        int cnt = 1;
        int idx = 0;
        while (true) {
            cnt *= n;
            idx += 1;
            if (cnt >= buckets) {
                break;
            }
        }
        return idx;
    }

    public int poorPigs1(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets <= 1)
            return 0;
        int n = minutesToTest/minutesToDie + 1;
        int tmp = (int)(Math.log(buckets)/Math.log(n));
        if ((int)Math.pow(n, tmp) == buckets) {
            return tmp;
        } else {
            return tmp + 1;
        }
    }


    public int tupleSameProduct(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                map.computeIfAbsent(nums[i] * nums[j], x->new ArrayList<>()).add(nums[i]);
            }
        }
        int ret = 0;
        for (int k : map.keySet()) {
            int s = map.get(k).size();
            if (s > 1) {
                ret += s*(s-1)*4;
            }
        }
        return ret;
    }


    //Your input
    //[[0,2],[5,10],[13,23],[24,25]]
    //[[1,5],[8,12],[15,24],[25,26]]
    //Output
    //[[8,2],[15,10],[13,5],[13,12],[25,23],[24,5],[24,12]]
    //Expected
    //[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int m = firstList.length;
        int n = secondList.length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int [] f = firstList[i];
                int [] s = secondList[j];
                if (f[1] >= s[0] && s[1] >= f[0]) {
                    list.add(new int [] {Math.max(s[0], f[0]), Math.min(s[1], f[1])});
                }

                if (s[0] > f[1]) {
                    break;
                }
            }
        }
        int [][] ret = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public String isMatchHelper(String p) {
        Stack<Character> stack = new Stack<>();
        char[] ss = p.toCharArray();
        for(char c : ss) {
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                switch (c) {
                    case '*':
                        while(!stack.isEmpty() && stack.peek() == '*'){
                            stack.pop();
                        }
                        stack.push(c);
                        break;
                    default:
                        stack.push(c);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        Iterator<Character> itr = stack.iterator();
        while(itr.hasNext()) {
            sb.append(itr.next());
        }
        return sb.toString();
    }

    public boolean isMath1(String s, String p) {
        //System.out.printf("s:%s, p:%s\n", s, p);
        if(s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        if (s.length() == 0) {
            return p.equals("*");
        }

        switch (p.charAt(0)) {
            case '*':
                return isMath1(s, p.substring(1)) || isMath1(s.substring(1), p);
            case '?':
                return isMath1(s.substring(1), p.substring(1));
            default:
                return s.charAt(0) == p.charAt(0) && isMath1(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatch(String s, String p) {
        p = isMatchHelper(p);
        System.out.println(p);
        return isMath1(s,p);
    }

    //[68,35,52,47,86]
    //[67,17,1,81,3]
    //[92,10,85,84,82]
    // 204 324
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            tm.put(difficulty[i], Math.max(tm.getOrDefault(difficulty[i], 0), profit[i]));
        }
        int curMax = 0;
        for(int key : tm.keySet()) {
            curMax = Math.max(curMax, tm.get(key));
            tm.put(key, curMax);
        }

        int ret = 0;
        int m = worker.length;
        for (int i = 0; i < m; i++) {
            if (tm.containsKey(worker[i])) {
                ret += tm.get(worker[i]);
            } else {
                if (tm.lowerEntry(worker[i]) != null) {
                    ret += tm.lowerEntry(worker[i]).getValue();
                }
            }
        }

        return ret;
    }


    //1 <= nums.length <= 2 * 104
    //1 <= nums[i] <= 105
    public int largestComponentSize(int[] nums) {
        UnionFind uf = new UnionFind((int)1e5+1);
        Set<Integer> set = new HashSet<>();
        for(int x : nums) {
            set.add(x);
            for (int i = 2; i <= (int)Math.sqrt(x); i++) {
                if(x%i==0) {
                    uf.union(i, x);
                    uf.union(x/i, x);
                }
            }
        }
        int ret = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 1; i <= (int)1e5 ; i++) {
            if (set.contains(i)) {
                int p = uf.find(i);
                cnt.put(p, cnt.getOrDefault(p, 0)+1);
                ret = Math.max(ret, cnt.get(p));
            }
        }
//        for (int i = 1; i <= 40; i++) {
//            if (set.contains(i))
//                System.out.println(i + "\t"+uf.find(i));
//        }


        return ret;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        Collections.sort(list);
        return list.get(k-1);
    }
    public static int test(Map<Integer, Integer> test) {
        test.put(1, 1);
        return 1;
    }

    /**
     * Input
     * [5,9,18,54,108,540,90,180,360,720]
     * Output
     * [5,540,90,180]
     * Expected
     * [9,18,90,180,360,720]
     * @param nums
     * @return
     */

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int [] max = new int[n];
        int [] pre = new int[n];
        max[0] = 1;
        pre[0] = 0;
        int maxLen = 0;
        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            max[i] = 1;
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i]%nums[j] == 0) {
                    if (max[j]+1 > max[i]) {
                        max[i] = max[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (max[i] > maxLen) {
                maxLen = max[i];
                maxIdx = i;
            }
        }
        List<Integer> ret = new ArrayList<>();
        
        while(pre[maxIdx] != maxIdx) {
            ret.add(nums[maxIdx]);
            maxIdx = pre[maxIdx];
        }
        ret.add(nums[maxIdx]);
        return ret;
    }


    public int maxValueAfterReverse(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n -1 ; i++) {
            res += Math.abs(nums[i] - nums[i+1]);
        }

        int diff = 0;
        int maxMin = Integer.MIN_VALUE;
        int minMax = Integer.MAX_VALUE;
        for (int i = 0; i < n-1; i++) {
            maxMin = Math.max(maxMin, Math.min(nums[i], nums[i+1]));
            minMax = Math.min(minMax, Math.max(nums[i], nums[i+1]));
        }
        diff = Math.max(diff, 2 * (maxMin - minMax));
        for (int i = 1; i < n-1; i++) {
            diff = Math.max(diff, Math.abs(nums[i+1] - nums[0]) - Math.abs(nums[i+1] - nums[i]));
            diff = Math.max(diff, Math.abs(nums[n-i-2] - nums[n-1]) - Math.abs(nums[n-i-2] - nums[n-i-1]));
        }
        return res + diff;
    }

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    ret += 1;
                } else {
                    continue;
                }

                for (int k = i+1; k < m; k++) {
                    if (board[k][j] == 'X') {
                        board[k][j] = '.';
                    } else {
                        break;
                    }
                }

                for (int k = j; k < n; k++) {
                    if (board[i][k] == 'X') {
                        board[i][k] = '.';
                    } else {
                        break;
                    }
                }
            }
        }

        return ret;
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> ws = new HashMap<>();
        for (String word : words) {
            int cur = 0;
            for(char c : word.toCharArray()) {
                cur |=  (1 << (c-'a'));
            }
            ws.put(cur, ws.getOrDefault(cur, 0) + 1);
        }

        List<Integer> ret = new ArrayList<>();
        for(String p : puzzles) {
            Set<Integer> curSet = new HashSet<>();
            curSet.add(1 << (p.charAt(0)-'a'));
            char[] ss = p.toCharArray();
            for (int i = 1; i < ss.length; i++) {
                Set<Integer> newSet = new HashSet<>();
                for (int x : curSet) {
                    newSet.add(x | (1<< (ss[i] - 'a')));
                }
                curSet.addAll(newSet);
            }

            int curCnt = 0;
            for (Integer x: curSet) {
                curCnt += ws.getOrDefault(x, 0);
            }
            ret.add(curCnt);
        }
        return ret;
    }


    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for(String s : equations) {
            if (s.charAt(1) == '=') {
                uf.union(s.charAt(0)-'a', s.charAt(3)-'a');
            }
        }
        for(String s :equations) {
            if (s.charAt(1) != '=') {
                if (uf.find(s.charAt(0)-'a') == uf.find(s.charAt(3)-'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    public int numTrees(int n) {
        if (n == 1)
            return 1;
        int [] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <=n ; i++) {
            int cur = 0;
            for (int j = 1; j <= i ; j++) {
                cur += arr[j-1] * arr[i-j];
            }
            arr[i] = cur;
        }

        return arr[n];
    }

    public int arrangeCoins(int n) {
        long pre = (long)Math.sqrt((long)n * 2)-1;
        long sum = (1+pre) * pre/2;
//        System.out.println(pre);
//        System.out.println(sum);
        for (long i = pre+1; i <= Integer.MAX_VALUE; i++) {
            sum += i;
            if (sum > n) {
                return (int)i-1;
            }
        }
        return 0;
    }

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public int maximumSum(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        int max = Integer.MIN_VALUE;
        int n = arr.length;
        Pair<Integer, Integer> [] parr = new Pair[n+1];
        parr[0] = new Pair<>(0, 0);
        for (int i = 0; i < n; i++) {
            int continues = parr[i].getKey() + arr[i];
            int del = Math.max(parr[i].getKey(), parr[i].getValue()+arr[i]);
            max = Math.max(max, Math.max(parr[i].getValue()+arr[i], continues));
            parr[i+1] = new Pair<>(continues > 0 ? continues: 0, del > 0 ? del : 0);

        }
        //System.out.println(Arrays.toString(parr));
        return max;
    }

//    public int maximumSum(int[] arr) {
//        int max = Integer.MIN_VALUE;
//        List<Integer> list = new ArrayList<>();
//        for(int x: arr) {
//            list.add(x);
//        }
//        max = Math.max(max, maximumSumHelper(list));
//        int n = arr.length;
//        for (int i = 0; i < n; i++) {
//            int cur = list.remove(i);
//            max = Math.max(max, maximumSumHelper(list));
//            //System.out.println(Arrays.toString(sum));
//            list.add(i, cur);
//        }
//        return max;
//    }
//
//    public int maximumSumHelper(List<Integer> list) {
//        int n = list.size();
//        int [] sum = new int[n+1];
//        int max = Integer.MIN_VALUE;
//
//        for (int j = 0; j < n; j++) {
//            max = Math.max(max, list.get(j) + sum[j]);
//            if(list.get(j) + sum[j] > 0) {
//                sum[j+1] = list.get(j) + sum[j];
//            } else {
//                sum[j+1] = 0;
//            }
//        }
//        return max;
//    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left != null){
            if (root.left.left == null && root.left.right == null) {
                return root.left.val + sumOfLeftLeaves(root.right);
            } else {
                return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
            }
        } else {
            return sumOfLeftLeaves(root.right);
        }
    }

    public int countArrangement(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j < 15; j++) {
                if(i%j==0||j%i==0) {
                    map.computeIfAbsent(i, x->new ArrayList<>()).add(j);
                }
            }
        }
        return 0;
    }

    public int findMaxLength(int[] nums) {
        int diff = 0; // 1's num - 0' num
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                diff ++ ;
            } else {
                diff --;
            }

            if (!map.containsKey(diff)) {
                map.put(diff, i);
            } else {
                max = Math.max(max, i-map.get(diff));
            }
        }

        return max;
    }

    public String removeOccurrences(String s, String part) {
        int m = s.length();
        int n = part.length();
        while(!s.replaceFirst(part, "").equals(s)) {
            s = s.replaceFirst(part, "");
        }

        return s;
    }
    //TODO: 不会
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int si=0, sj=0, ei=0, ej=0, empCnt=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 1:
                        si = i; sj =j;
                        break;
                    case 2:
                        ei = i; ej = j;
                        break;
                    case 0:
                        empCnt += 1;
                }
            }
        }

        System.out.printf("start=(%d, %d), end=(%d, %d), total_empty=%d", si, sj, ei, ej, empCnt);

        return 0;
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean [][] visited = new boolean[m][n];
        int [][] adj = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 && i != m-1 && j != 0 && j!= n-1) {
                    continue;
                }
                if (board[i][j] == 'X') {
                    continue;
                }
                //System.out.printf("i=%d,j=%d\n", i, j);
                Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                queue.add(new Pair<>(i,j));
                visited[i][j] = true;
                while(!queue.isEmpty()) {
                    Pair<Integer, Integer> cur = queue.poll();
                    for(int[]x: adj) {
                        int ii = cur.getKey() + x[0];
                        int jj = cur.getValue() + x[1];

                        if (ii >= 0 && ii < m && jj >= 0 & jj < n && visited[ii][jj] == false && board[ii][jj] == 'O') {
                            queue.add(new Pair<>(ii,jj));
                            visited[ii][jj] = true;
                        }
                    }
                }

            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public boolean validPalindrome(String s) {
        char[] ss = s.toCharArray();
        boolean isPal = true;
        int i = 0;
        int j = ss.length - 1;
        String s1 = "";
        String s2 = "";
        int n = s.length();
        while(i < j ) {
            if (ss[i] != ss[j]) {
                isPal = false;
                s1 = s.substring(0,i) + s.substring(i+1, n);
                s2 = s.substring(0,j)+ s.substring(j+1, n);
                break;
            }
            i++;
            j--;
        }

        if (isPal) {
            return true;
        }
        boolean b1 = true;
        boolean b2 = true;
        for (int k = 0; k < (n-1)/2; k++) {
            if (s1.charAt(k) != s1.charAt(n-2-k)) {
                b1 = false;
                break;
            }
        }
        for (int k = 0; k < (n-1)/2; k++) {
            if (s2.charAt(k) != s2.charAt(n-2-k)) {
                b2 = false;
                break;
            }
        }

        return b1||b2;
    }

    public int longestZigZag(TreeNode root) {
        Map<TreeNode, Pair<Integer, Integer>> map = new HashMap<>();
        longestZigZagHelper(root, map);
        int max = 0;
        for (TreeNode t : map.keySet()) {
            max = Math.max(max, map.get(t).getValue());
            max = Math.max(max, map.get(t).getKey());
        }
        return max;
    }
    public Pair<Integer, Integer> longestZigZagHelper(TreeNode root, Map<TreeNode, Pair<Integer, Integer>> map) {
        if (map.containsKey(root)) {
            return map.get(root);
        }

        if (root.left == null && root.right == null) {
            Pair<Integer, Integer> p = new Pair<>(1, 1);
            map.put(root, p);
            return p;
        } else {
            int left = 1;
            int right = 1;
            if (root.left != null){
                left += longestZigZagHelper(root.left, map).getValue();
            }
            if (root.right != null) {
                right += longestZigZagHelper(root.right, map).getKey();
            }
            Pair<Integer, Integer> p = new Pair<>(left, right);
            map.put(root, p);
            return p;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int dis = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            map.computeIfAbsent(dis, x -> new ArrayList<>()).add(i);
        }
        int [][] ret = new int[k][2];
        for (int i = 0; i < k;) {
            List<Integer> idx = map.firstEntry().getValue();
            for (int j = 0; j < idx.size(); j++) {
                ret[i++] = points[idx.get(j)];
            }
            map.remove(map.firstKey());
        }
        return ret;
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int [][] time = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 0:
                    case 1:
                        time[i][j] = Integer.MAX_VALUE;
                        break;
                    case 2:
                        time[i][j] = 0;
                }
            }
        }

        int [][] adj = {{0,1},{0,-1},{1,0},{-1,0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int[][]visited = new int[m][n];
                    visited[i][j] = 1;
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.add(new Pair<>(i,j));
                    while (!queue.isEmpty()) {
                        Pair<Integer, Integer> cur = queue.poll();
                        //System.out.println(cur);
                        for (int [] x : adj) {
                            int ii = cur.getKey() + x[0];
                            int jj = cur.getValue() + x[1];
                            if (ii >= 0 && ii < m && jj >= 0 && jj < n && grid[ii][jj] != 0 && visited[ii][jj] == 0) {
                                time[ii][jj] = Math.min(time[ii][jj], time[cur.getKey()][cur.getValue()] + 1);
                                queue.add(new Pair<>(ii,jj));
                                visited[ii][jj] = 1;
                            }
                        }
                    }
                }
//                System.out.printf("i:%d, j:%d\n", i, j);
//                for (int [] t : time){
//                    System.out.println(Arrays.toString(t));
//                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0){
                    if (time[i][j] < 100) {
                        max = Math.max(max, time[i][j]);
                    } else {
                        return -1;
                    }
                }
            }
        }

        return max;
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map.put(cur.val, cur);
            for(Node n: cur.neighbors) {
                if (!map.containsKey(n.val)) {
                    queue.add(n);
                }
            }
        }
        Map<Integer, Node> mapClone = new HashMap<>();
        for(int k: map.keySet()) {
            mapClone.put(k, new Node(k));
        }

        for(int k: mapClone.keySet()) {
            List<Node> list = new ArrayList<>();
            for(Node n: map.get(k).neighbors) {
                list.add(mapClone.get(n.val));
            }
            mapClone.get(k).neighbors = list;
        }
        return mapClone.get(node.val);
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        List<String> ret = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for(char[] x: board){
            for(char y: x){
                set.add(y);
            }
        }
        for(String word: words) {
            boolean flag = false;
            for(char x: word.toCharArray()){
                if (!set.contains(x)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            for(int idx = 0; idx < m*n; idx ++) {
                int i = idx/n;
                int j = idx%n;
                if (board[i][j] == word.charAt(0)) {
                    int [][] visited = new int[m][n];
                    visited[i][j] = 1;
                    if (findWordsHelper(board, word.substring(1), i, j, visited)) {
                        ret.add(word);
                        break;
                    }
                }
            }

        }

        return ret;
    }

    public boolean findWordsHelper(char[][] board, String word, int i, int j, int[][] visited) {
        if (word.length() == 0) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        int [][] adj = {{0,1},{0,-1},{1,0},{-1,0}};

        for(int [] x : adj) {
            int ii = i+x[0];
            int jj = j+x[1];
            if (ii>=0 && ii<m && jj>=0 && jj<n && board[ii][jj]==word.charAt(0)&&visited[ii][jj]==0){
                visited[ii][jj] = 1;
                if (findWordsHelper(board, word.substring(1), ii, jj, visited)){
                    return true;
                }
                visited[ii][jj] = 0;
            }
        }
        return false;
    }

    public int[][] construct2DArray(int[] original, int m, int n) {
        int l = original.length;
        if (m * n != l) {
            return new int[0][0];
        }
        int [][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = original[i*n+j];
            }
        }
        return ret;
    }

    public String largestOddNumber(String num) {
        int n = num.length();
        for(int i = n-1; i >= 0; i --){
            if((num.charAt(i)-'0')%2==1){
                return num.substring(0,i+1);
            }
        }

        return "";
    }

    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[][]trans = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                trans[i][j] = board[j][i];
            }
        }


        return placeWordInCrosswordHelper(board, word) || placeWordInCrosswordHelper(trans, word);
    }

    public boolean placeWordInCrosswordHelper(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int l = word.length();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n;) {
                //System.out.println(i+"\t"+j);
                if ((board[i][j]==' '||(board[i][j] >='a'&&board[i][j] <= 'z'))&&(j==0||board[i][j-1]=='#')) {
                    for (int k = j; k < n; k++) {
                        if (board[i][k] == '#' || k == n-1) {
                            if (board[i][k] != '#'){
                                k++;
                            }
                            int dis = k - j;
                            if (dis == l) {
                                StringBuffer s = new StringBuffer(String.valueOf(Arrays.copyOfRange(board[i], j, k)));
                                if (checkWordSame(word, s.toString()) || checkWordSame(word, s.reverse().toString())) {
                                    return true;
                                }
                            }
                            j = k + 1;
                        }
                    }
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    public boolean checkWordSame(String a, String b) {
        int n = a.length();
        for (int i = 0; i < n; i++) {
            if(b.charAt(i) != ' ' && b.charAt(i) != a.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public int rob(TreeNode root) {
        Map<TreeNode, Pair<Integer, Integer>> map = new HashMap<>();
        Pair<Integer, Integer> p = robHelper(root, map);
        return Math.max(p.getKey(), p.getValue());
    }

    public Pair<Integer, Integer> robHelper(TreeNode root, Map<TreeNode, Pair<Integer, Integer>> map) {
        if (map.containsKey(root)) {
            return map.get(root);
        }
        if (root.left == null && root.right == null) {
            Pair<Integer, Integer> p = new Pair<>(root.val, 0);
            map.put(root, p);
            return p;
        } else {
            int inclusive = root.val;
            int exclusive = 0;

            if (root.left != null) {
                Pair<Integer, Integer> l =  robHelper(root.left, map);
                inclusive += l.getValue();
                exclusive += Math.max(l.getKey(), l.getValue());
            }

            if (root.right != null) {
                Pair<Integer, Integer> r =  robHelper(root.right, map);
                inclusive += r.getValue();
                exclusive += Math.max(r.getKey(), r.getValue());
            }
            Pair<Integer, Integer> p = new Pair<>(inclusive, exclusive);
            map.put(root, p);
            return p;
        }
    }

    public int smallestRangeI(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        return max - min > 2 * k ? max-min-2*k : 0;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }
        Set<Integer> iSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (iSet.contains(nums[i])) {
                continue;
            } else {
                iSet.add(nums[i]);
            }
            Set<Integer> jSet = new HashSet<>();

            for (int j = i+1; j < n; j++) {
                if (jSet.contains(nums[j])) {
                    continue;
                } else {
                    jSet.add(nums[j]);
                }
                int c = 0-nums[i]-nums[j];

                if (map.containsKey(c)) {
                    if (map.get(c).get(map.get(c).size()-1) > j) {
                        ret.add(Arrays.asList(nums[i], nums[j], c));
                    }

                }

            }
        }
        return ret;
    }

    public int smallestRangeII(int[] nums, int k) {

        return 0;
    }

    public int countDigitOneVerify(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            char[] x = String.valueOf(i).toCharArray();
            for (char c : x) {
                if (c == '1') {
                    ret++;
                }
            }
        }
        return ret;
    }

    public int countDigitOne(int n) {
        int ret = 0;
        char[] cc = String.valueOf(n).toCharArray();
        if (cc.length == 1) {
            return n > 0 ? 1 : 0;
        }
        ret += countDigitOneHelper(cc.length - 1);


        //System.out.println(ret);
        int sub = n % ((int) Math.pow(10, cc.length - 1));
        if (cc[0] == '1') {
            ret += (sub + 1 + countDigitOne(sub));
        } else {
            ret += ((int) Math.pow(10, cc.length - 1));
            ret += (cc[0] - '0' - 1) * countDigitOneHelper(cc.length - 1);
            ret += countDigitOne(sub);
        }
        return ret;
    }

    public int countDigitOneHelper(int n) {
//        if (n == 0) {
//            return 0;
//        } else {
//            return (int) Math.pow(10, n - 1) + 10 * countDigitOneHelper(n - 1);
//        }
        // 与上面代码等价
        return n * (int) Math.pow(10, n - 1);
    }

    // 842
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        for (int i = 1; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                List<Integer> tmp = checkFibonacci(num, 0, i, j);
                if (tmp.size() > 0) {
                    return tmp;
                }
            }
        }
        return new ArrayList<>();
    }

    public List<Integer> checkFibonacci(String num, int begin, int i , int j) {
        List<Integer> ret = new ArrayList<>();
        while( j < num.length()) {
            String a = num.substring(begin, i);
            String b = num.substring(i, j);
            //System.out.println(a+"\t"+b);

            if (a.length() > 10 || b.length() > 10 ){
                return new ArrayList<>();
            }
            long la = Long.parseLong(a);
            long lb = Long.parseLong(b);

            //System.out.println(la+"\t"+lb);

            if (la > Integer.MAX_VALUE || lb > Integer.MAX_VALUE || la + lb > Integer.MAX_VALUE) {
                return new ArrayList<>();
            }

            if (begin == 0) {
                ret.add(Integer.parseInt(a));
                ret.add(Integer.parseInt(b));
            }
            if ((a.length() > 1 && a.charAt(0) == '0') || (b.length() > 1 && b.charAt(0) == '0')) {
                return new ArrayList<>();
            }
            String sum = String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
            //System.out.println(a +"\t"+b+"\t"+num.substring(j, Math.min(j+sum.length(), num.length())));
            if (num.substring(j, Math.min(j+sum.length(), num.length())).equals(sum)) {
                ret.add(Integer.parseInt(sum));
                begin = i;
                i = j;
                j = j + sum.length();
                if (j == num.length()) {
                    return ret;
                }
            } else {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        } else {
            invertTree(root.left);
            invertTree(root.right);

            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            return root;
        }
    }

    public int makeStringSorted(String s) {
        int module = (int)1e9+7;

        return 0;
    }

    // 115
    public int numDistinct(String s, String t) {


        return 0;
    }

    // 1835
    public int getXORSum(int[] arr1, int[] arr2) {
        long m = arr1.length;
        long n = arr2.length;
        long [] arr1Bit = new long[32];
        long [] arr2Bit = new long[32];

        for(int x: arr1) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1<<i)) > 0) {
                    arr1Bit[i]++;
                }
            }
        }
        for(int x: arr2) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1<<i)) > 0) {
                    arr2Bit[i]++;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < 32; i++) {
            long oneCnt = arr1Bit[i] * arr2Bit[i];
            ret |= ((oneCnt%2==0 ? 0 : 1) << i);
        }
        return ret;
    }

    public int dominantIndex(int[] nums) {
        int max = -1;
        int idx = -1;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != idx && nums[i] * 2 > max) {
                return -1;
            }
        }
        return idx;
    }

    public int numFactoredBinaryTrees(int[] arr) {
        int module = (int)1e9+7;
        Set<Integer> set = new HashSet<>();
        for (int x: arr) {
            set.add(x);
        }
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long tmp = (long)arr[i] * (long)arr[j];
                if (tmp < Integer.MAX_VALUE && set.contains((int)tmp)) {
                    map.computeIfAbsent((int)tmp, x -> new ArrayList<>()).add(arr[i]);
                }
            }
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                long curSum = 1;
                for(int x: map.get(arr[i])) {
                    int leftCnt = cnt.get(x);
                    int rightCnt = cnt.get(arr[i]/x);
                    long product = (int)(((long)leftCnt*(long)rightCnt)%(long)(module));
                    if (x * x == arr[i]) {
                        curSum += product;
                        curSum %= module;
                    } else {
                        curSum += (int)product * 2 %module;
                    }
                }
                cnt.put(arr[i], (int)(curSum % module));
            } else {
                cnt.put(arr[i],1);
            }
        }

        for (int x : cnt.keySet()) {
            ret += cnt.get(x);
            ret %= module;
        }

//        System.out.println(Arrays.toString(arr));
//        System.out.println(map);
//        System.out.println(cnt);
        return (int)ret;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int [] cntRansom = new int[26];
        int [] cntMagazine = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            cntRansom[ransomNote.charAt(i)-'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            cntMagazine[magazine.charAt(i)-'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (cntRansom[i] > cntMagazine[i]){
                return false;
            }
        }
        return true;
    }

    public int longestMountain(int[] arr) {
        int n = arr.length;
        int [] left = new int[n];
        int [] right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i==0 || arr[i] <= arr[i-1]) {
                left[i] = 1;
            } else {
                left[i] = left[i-1] + 1;
            }
            int j = n-1-i;
            if (j==n-1 || arr[j] <= arr[j+1]) {
                right[j] = 1;
            } else {
                right[j] = right[j+1] + 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > 1 & right[i] > 1)
                ret = Math.max(ret, left[i]+right[i]-1);
        }
        return ret >= 3 ? ret : 0;
    }

    public void printMatrix(int [][] x) {
        System.out.println("================");
        for( int[] xx: x) {
            System.out.println(Arrays.toString(xx));
        }
    }

    public int getValue(int [][] x, int m, int n, int i, int j) {
        if (i >= 0 && i < m && j >=0 && j < n) {
            return x[i][j];
        } else {
            return 0;
        }
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int [][] rowSum = new int[m][n];
        int [][] colSum = new int[m][n];
        int [][] digLeft = new int[m][n];
        int [][] digRight = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j] = getValue(rowSum, m, n, i, j-1) + grid[i][j];
                colSum[i][j] = getValue(colSum, m, n, i-1, j) + grid[i][j];
                digLeft[i][j] = getValue(digLeft, m, n, i-1, j-1) + grid[i][j];
                digRight[i][j] = getValue(digRight, m, n, i-1, j+1) + grid[i][j];
            }
        }

        int ret = 1;
        for(int i = 0; i < m ;i ++) {
            for(int j = 0; j < n ;j ++){
                for (int k = 1; i+k < m && j+k < n; k++) {
                    int s = rowSum[i][j+k] - rowSum[i][j] + grid[i][j];
                    boolean valid = true;
                    for (int l = 0; l <= k; l++) {
                        if (rowSum[i+l][j+k] - rowSum[i+l][j] + grid[i+l][j] != s) {
                            valid = false;
                            break;
                        }
                        if (colSum[i+k][j+l] - colSum[i][j+l] + grid[i][j+l] != s) {
                            valid = false;
                            break;
                        }
                    }
                    if (digLeft[i+k][j+k] - digLeft[i][j] + grid[i][j] != s ||
                            digRight[i+k][j] - digRight[i][j+k] + grid[i][j+k] != s){
                        valid = false;
                    }

                    if (!valid) {
                        continue;
                    } else {
                        ret = Math.max(ret, k+1);
                    }
                }
            }
        }
        return ret;
    }

    public String frequencySort(String s) {
        char [] ss = s.toCharArray();
        int [] cnt = new int[123];
        for (char c : ss) {
            cnt[(int)c] ++;
        }
        int [] idx = IntStream.range(0, cnt.length).boxed().
            sorted(Comparator.comparingInt(i -> cnt[i])).
                    mapToInt(a -> a).toArray();
        StringBuffer sb = new StringBuffer();
        for (int i = idx.length-1; i >=0; i--) {
            if (cnt[idx[i]] > 0) {
                char c = (char)idx[i];
                for (int j = 0; j < cnt[idx[i]]; j++) {
                    sb.append(c);
                }

            } else {
                break;
            }
        }
        return sb.toString();
    }

    public String longestPrefix(String s) {
        int n = s.length();
        int [] next = new int[n];
        int idx = 1;
        int now = 0;

        while (idx < n) {
            if (s.charAt(idx) == s.charAt(now)) {
                now += 1;
                next[idx] = now;
                idx += 1;
            } else {
                if (now > 0 ) {
                    now = next[now-1];
                } else {
                    next[idx] = 0;
                    idx ++;
                }
            }
        }
        return s.substring(0, next[n-1]);
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nb = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int n = nums2.length;
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && stack.peek() < nums2[i]) {
                nb.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int m = nums1.length;
        int [] ret = new int[m];
        for (int i = 0; i < m; i++) {
            ret[i] = nb.getOrDefault(nums1[i],-1);
        }
        return ret;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {


        return 0;
    }

    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        int n = changed.length;
        if (n%2 != 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(changed[i], map.getOrDefault(changed[i], 0) + 1);
        }
        int twice = 0;
        int half = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(changed[i] * 2)) {
                twice ++;
            }
            if (changed[i]%2==0 &&map.containsKey(changed[i]/2)) {
                half ++;
            }
        }
        if (twice != half) {
            return new int[0];
        }






        return null;
    }

    public int countKDifference(int[] nums, int k) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ret += map.getOrDefault(nums[i]+k, 0);
            ret += map.getOrDefault(nums[i]-k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ret;
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        Map<Integer, List<Integer>> children = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(parents[i] >= 0) {
                children.computeIfAbsent(parents[i], x-> new ArrayList<>()).add(i);
            }
        }
        Map<Integer, Integer> childBit = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int curValue = 0;
            for(int x : children.get(i)) {
                curValue |= x;
            }
        }

        return null;
    }

    public int maxProduct(String s) {
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < (1<<s.length()); i++) {
            String curS = (new StringBuffer(Integer.toBinaryString(i)).reverse()).toString();
            List<Integer> oneIndex = new ArrayList<>();
            for (int j = 0; j < curS.length(); j++) {
                if (curS.charAt(j) == '1') {
                    oneIndex.add(j);
                }
            }
            boolean isPar = true;
            for (int j = 0; j < oneIndex.size()/2; j++) {
                if (s.charAt(oneIndex.get(j)) != s.charAt(oneIndex.get(oneIndex.size()-1-j))) {
                    isPar = false;
                    break;
                }
            }
            if (isPar) {
                map.put(i, oneIndex.size());
            }
        }
        int max = 1;
        //System.out.println(map);
        List<Integer> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            for (int j = i+1; j < keys.size(); j++) {
                if ((keys.get(i) & keys.get(j)) == 0) {
                    max = Math.max(map.get(keys.get(i)) * map.get(keys.get(j)), max);
                }
            }
        }
        return max;
    }

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index < 0) {
            return word;
        } else {
            StringBuffer sb = new StringBuffer(word.substring(0,index+1));
            return sb.reverse() + word.substring(index+1);
        }
    }

    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> map = new HashMap<>();
        for(int i = 0; i < rectangles.length; i ++) {
            double cur = rectangles[i][0]/(double)rectangles[i][1];
            map.put(cur, map.getOrDefault(cur, (long)0) + 1);
        }
        long ret = 0;
        for(double k: map.keySet()) {
            if (map.get(k) > 1) {
                ret+= (map.get(k) * (map.get(k) -1) /2);
            }
        }
        return ret;
    }

    public static Map<String, String> getProductInfo(String s) {
        Map<String, String> ret = new HashMap<>();
        String [] sourceInfo = s.split("::");

        for (String si: sourceInfo) {
            int index = si.indexOf(":");
            ret.put(si.substring(0, index), si.substring(index));
        }
        return ret;
    }

    public static int getGCD(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        if (b == 0) {
            return a;        }
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int x: nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        return getGCD(min,max);

    }

    public int minimizeTheDifference(int[][] mat, int target) {
        return minimizeTheDifferenceHelper(mat, target, 0);
    }

    public int minimizeTheDifferenceHelper(int [][] mat, int target, int row) {
        int ret = Integer.MAX_VALUE;
        if (row == mat.length -1) {
            for(int x : mat[row]) {
                ret = Math.min(ret, Math.abs(target - x));
            }
            return ret;
        }
        for (int x : mat[row]) {
            if (target > x) {
                ret = Math.min(ret, minimizeTheDifferenceHelper(mat, target-x, row + 1));
            }
        }
        return ret;
    }

    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();
        for(String s : nums){
            set.add(Integer.parseInt(s, 2));
        }
        int n = nums.length;
        for(int i = 0; i < 65536; i ++){
            if(!set.contains(i)) {
                String s = Integer.toBinaryString(i);
                while (s.length() < n) {
                    s = '0' + s;
                }
                return s;
            }
        }

        return null;
    }

    public int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);

        return 0;
    }

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o2.compareTo(o1);
            } else {
                return o2.length() - o1.length();
            }
        });

        return nums[k-1];
    }

    public int[][] findFarmland(int[][] land) {
        List<int[]> list = new ArrayList<>();
        int m = land.length;
        int n = land[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if((land[i][j] == 1) && (i==0 || land[i-1][j] == 0) && (j==0 || land[i][j-1] == 0)) {
                    int ti = i;
                    int tj = j;
                    while(ti+1 < m && land[ti+1][tj] == 1) {
                        ti++;
                    }
                    while(tj+1 < n && land[ti][tj+1] == 1) {
                        tj++;
                    }
                    list.add(new int [] {i,j,ti,tj});
                }
            }
        }
        int [][] ret = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for(int x : nums) {
            sum += x;
        }
        int curSum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (curSum == sum - curSum - nums[i]) {
                return i;
            }
            curSum += nums[i];
        }
        return -1;
    }

    public boolean gcdSort(int[] nums) {

        return true;
    }

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        int [] sum = new int[n + 1];
        int module = (int)1e9+7;
        sum[1] = 2;
        for(int i = 1; i < n - 1;i ++) {
            System.out.println(Arrays.toString(sum));
            int cur = (sum[i] + module - sum[nextVisit[i]] + 2 ) % module;
            sum[i+1] = (sum[i] + cur) % module;
        }
        return sum[n-1];
    }

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, Comparator.comparingInt(o -> o[0]));
        int n = properties.length;
        int [] max = new int[n];
        max[n-1] = properties[n-1][1];
        for(int i = n-2 ; i >= 0; i --) {
            max[i] = Math.max(max[i+1], properties[i][1]);
        }

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            if (properties[i][0] > properties[i-1][0]) {
                tm.put(i,1);
            }
        }
        System.out.println(tm);
        for(int [] x : properties) {
            System.out.println(Arrays.toString(x));
        }
        System.out.println(Arrays.toString(max));

        int ret = 0;
        for (int i = 0; i < n; i++) {
            if(tm.higherKey(i) != null && max[tm.higherKey(i)] > properties[i][1]) {
                ret += 1;
            }
        }
        return ret;
    }

    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n-3; i++) {
            for (int j = i+1; j < n-2; j++) {
                for (int k = j+1; k < n-1; k++) {
                    for (int l = k+1; l < n; l++) {
                        if(nums[i] + nums[j] + nums[k] == nums[l]){
                            ret += 1;
                        }
                    }
                }
            }
        }
        return ret;
    }

    public int chalkReplacer(int[] chalk, int k) {
        int index = 0;
        int n = chalk.length;
        int sum = 0;
        for(int x : chalk) {
            sum += x;
        }
        k -= ((k/sum) * sum);

        while(k >= 0) {
            k -= chalk[index % n];
            index ++;

            System.out.println(k+"\t"+ index);
        }
        return (index -1 + n) % n;

    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        int [] arr = new int[52];
        for(int [] x : ranges) {
            arr[x[0]] ++;
            arr[x[1]+1] --;
        }
        for (int i = 0; i < 52; i++) {
            arr[i] = i == 0 ? arr[i] : arr[i-1] + arr[i];
        }

        for(int i = left ; i <= right; i ++) {
            if (arr[i] == 0) {
                return false;
            }
        }

        return true;
    }


    public int minFlips(String s) {
        int zeroCnt = 0;
        int zeroEven = 0;
        int oneCnt = 0;
        int OneOdd = 0;
        char [] ss = s.toCharArray();
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            zeroCnt += (ss[i] == '0' ? 1 : 0);
            oneCnt += (ss[i] == '1' ? 1 : 0);
            zeroEven += (ss[i] == '0' && i%2 == 0 ? 1 : 0);
            OneOdd += (ss[i] == '1' && i%2 == 1 ? 1 : 0);
        }

        int ret = Integer.MAX_VALUE;






        return 0;
    }



    public boolean exist(char[][] board, String word) {

        boolean ret = false;
        int m = board.length;
        int n = board[0].length;


        Set<String> sp = new HashSet<>();
        for(int i = 0 ; i < m; i ++) {
            for(int j = 0; j < n ; j ++){
                sp.add(i+"_"+j);
                if(word.charAt(0) == board[i][j]) {
                    ret |= existHelper(board, word, i, j, 0, sp);
                }
                sp.remove(i+"_"+j);
            }
        }

        return ret;
    }



    public boolean existHelper(char[][]board, String word, int i, int j,  int index, Set<String> sp) {
        //System.out.println(i+"\t"+j+"\t"+index+"\t"+sp);

        int m = board.length;
        int n = board[0].length;
        if (index == word.length() -1 ){
            return board[i][j] == word.charAt(index);
        }
        int [][] adj = {{0,1},{0,-1},{1,0},{-1,0}};

        boolean ret = false;
        for (int [] x: adj ){
            int newI = i + x[0];
            int newJ = j + x[1];

            String curKey = newI+"_"+newJ;

            if(newI >= 0 && newI < m && newJ >=0 && newJ < n && board[newI][newJ] == word.charAt(index+1) && !sp.contains(curKey)) {
                sp.add(curKey);
                ret |= existHelper(board, word, newI, newJ, index+1, sp);
                sp.remove(curKey);

            }
        }
        //System.out.println(ret);
        return ret;
    }


    public String maxValue(String n, int x) {

        if(n.startsWith("-")) {
            for(int i = 1; i < n.length(); i ++) {
                if (x + '0' < n.charAt(i)) {
                    return n.substring(0,i) + x + n.substring(i);
                }
            }
            return n+x;
        } else {
            for (int i = 0; i < n.length(); i++) {
                if(x+'0' > n.charAt(i)) {
                    return n.substring(0,i) + x + n.substring(i);
                }
            }
            return n+x;
        }


    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();

        for(int i = 0; i < firstWord.length(); i ++){
            sb1.append(firstWord.charAt(i) - 'a');
        }
        for(int i = 0; i < secondWord.length(); i ++){
            sb2.append(secondWord.charAt(i) - 'a');
        }
        for(int i = 0; i < targetWord.length(); i ++){
            sb3.append(targetWord.charAt(i) - 'a');
        }
        return Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString()) == Integer.parseInt(sb3.toString());
    }

    public int minimumXORSum(int[] nums1, int[] nums2) {

        int n = nums1.length;
        List<Pair<Pair<Integer, Integer>, Integer>> xorList = new ArrayList<>();
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                xorList.add(new Pair<>(new Pair<>(i,j), nums1[i]^nums2[j]));
            }
        }

        Collections.sort(xorList, Comparator.comparingInt(Pair::getValue));
        int ret = 0;
        for(Pair<Pair<Integer, Integer>, Integer> p : xorList) {
            System.out.println(p);
            if((!visited1.contains(p.getKey().getKey())) && (!visited2.contains(p.getKey().getValue()))) {
                ret += p.getValue();
                visited1.add(p.getKey().getKey());
                visited2.add(p.getKey().getValue());
            }
        }
        return ret;
    }

    public int[] getBiggestThree(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int [][] leftSum = new int[n][m];
        int [][] rightSum = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = m-1; j >= 0; j --){
                if(i-1 >= 0 && j + 1< m) {
                    leftSum[i][j] = leftSum[i-1][j+1] + grid[i][j];
                } else {
                    leftSum[i][j] = grid[i][j];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j ++){
                if(i-1 >= 0 && j - 1 >= 0) {
                    rightSum[i][j] = rightSum[i-1][j-1] + grid[i][j];
                } else {
                    rightSum[i][j] = grid[i][j];
                }
            }
        }
        int maxR = Math.min(m,n)/2;
        Set<Integer> allSumSet = new HashSet<>();
        for(int i = 0;i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                for(int k = 0; k <= maxR; k ++ ){
                    if(i+k < n && i-k >= 0 && j+k < m && j-k >= 0){
                        int curSum;
                        if (k == 0)
                            curSum = grid[i][j];
                        else
                            curSum = leftSum[i][j-k] - leftSum[i-k][j] + leftSum[i+k][j] - leftSum[i][j+k] +
                                rightSum[i][j+k] - rightSum[i-k][j] + rightSum[i+k][j] - rightSum[i][j-k] - grid[i+k][j] + grid[i-k][j];
                        allSumSet.add(curSum);
                        //System.out.println(String.format("i=%d,j=%d,k=%d,sum=%d,m=%d,n=%d", i,j,k,curSum,m,n));
                    }
                }
            }
        }
        List<Integer> allSum = new ArrayList<>(allSumSet);
        Collections.sort(allSum);
        int l = Math.min(3, allSum.size());
        int [] ret = new int[l];

        for(int i = 0;i < l;i ++) {
            ret[i] = allSum.get(allSum.size()-i-1);
        }

//        for(int [] x: leftSum) {
//            System.out.println(Arrays.toString(x));
//        }
//        for(int [] x: rightSum) {
//            System.out.println(Arrays.toString(x));
//        }


        return ret;
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int min = 0;
        int n = nums.length;
        for(int i = 0; i < n/2; i ++) {
            min = Math.max(nums[i] + nums[n-i-1],min);
        }
        return min;
    }

    public int countGoodSubstrings(String s) {
        char[] ss = s.toCharArray();
        int ret = 0;
        for(int i = 1; i < ss.length -1 ; i++) {
            if(ss[i-1] != ss[i] && ss[i] != ss[i+1] && ss[i-1] != ss[i+1]) {
                ret += 1;
            }
        }
        return ret;
    }

    public int stoneGameVIII(int[] stones) {


        return 0;
    }
    public boolean canReach(String s, int minJump, int maxJump) {
        char [] ss = s.toCharArray();
        int n = ss.length;
        int [] canReach = new int[n]; // 0 未知， 1 can, 2 cannot

        return canReachHelper(ss, minJump, maxJump, 0, canReach);
    }


    private boolean canReachHelper(char[] ss, int minJump, int maxJump, int cur, int [] canReach) {
        if(cur == ss.length -1)
            return true;
        boolean ret = false;
        for(int i = cur+minJump; i <= Math.min(cur+maxJump, ss.length-1); i++){
            if(ss[i] == '1') {
                continue;
            }
            if(canReach[i] == 1) {
                return true;
            } else if (canReach[i] == 2) {
                continue;
            } else {
                ret |= canReachHelper(ss, minJump, maxJump, i, canReach);
            }
        }
        canReach[cur] = ret ? 1 : 2;
        System.out.println(Arrays.toString(canReach));
        return ret;
    }


    public int minSpeedOnTime(int[] dist, double hour) {
        int r = (int)1e7;

        if(dist.length - 1 + dist[dist.length-1]/(double)r > hour) {
            return -1;
        }
        int l = 1;
        while(l < r) {
            int mid = (l + r)/2;
            if(checkOnTime(dist, hour, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean checkOnTime(int[] dist, double hour, int mid) {
        double costTime = 0;
        for(int i = 0; i < dist.length -1; i++) {
            costTime += Math.ceil(dist[i]/(double)mid);
        }
        costTime += dist[dist.length -1]/(double)mid;
        return costTime <= hour;
    }

    public boolean checkZeroOnes(String s) {
        String[] ones = s.split("0");
        String[] zeros = s.split("1");
        int maxOne = 0;
        int maxZero = 0;
        for(String cur: ones) {
            maxOne = Math.max(maxOne, cur.length());
        }
        for(String cur: zeros) {
            maxZero = Math.max(maxZero, cur.length());
        }
        return maxOne > maxZero;
    }

    public int rearrangeSticks(int n, int k) {
        int modulo = (int)1e9+7;

        return 0;
    }

    public int minSwaps(String s) {
        char[] ss = s.toCharArray();
        int [] cnt = new int[2];
        for(char c : ss) {
            cnt[c-'0'] += 1;
        }
        if(Math.abs(cnt[0] - cnt[1]) > 1) {
            return -1;
        }
        int pre = '0';
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < ss.length; i++) {
            if(ss[i] != (i%2 + '0')) {
                s1 += 1;
            }
            if(ss[i] != ((-i%2+1) + '0')) {
                s2 += 1;
            }
        }
        if(s1%2!=0) {
            return s2/2;
        } else {
            if(s2%2!=0) {
                return s1/2;
            } else {
                return Math.min(s1,s2)/2;
            }
        }
    }

    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int allOr = 0;
        for(int x : nums) {
            allOr |= x;
        }
        int ret = 0;
        for (int i = 0; i < 5; i++) {
            if(((allOr >> i) & 1) == 1) {
                ret += Math.pow(2,i + n-1);
            }
        }
        return ret;
    }

    public int sumOfFlooredPairs(int[] nums) {
        int modulo = (int)1e9+7;
        Arrays.sort(nums);
        int n = nums.length;

        return 0;
    }

    public char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        for (int i = 0; i < n; i++) {
            int obs = m;
            for(int j = m-1; j >=0; j--) {
                switch (box[i][j]){
                    case '#':
                        if(j < obs -1) {
                            box[i][obs-1] = '#';
                            box[i][j] = '.';
                        }
                        obs = obs -1;
                        break;
                    case '*':
                        obs = j;
                        break;
                }
            }
        }
        char [][] result = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n-1-i] = box[i][j];
            }
        }

        return result;
    }


    public int[] memLeak(int memory1, int memory2) {

        int i = 1;
        while(true){
            if(memory1 >= memory2) {
                if(memory1 >= i) {
                    memory1 -= i;
                } else {
                    break;
                }
            } else {
                if (memory2 >= i) {
                    memory2 -= i;
                } else {
                    break;
                }
            }
            i ++;
        }
        return new int[] {i,memory1,memory2};
    }

    public String sortSentence(String s) {
        String[] list = s.split( " ");
        String [] result = new String[list.length];
        for(String cur : list) {
            int index = Integer.parseInt(cur.charAt(cur.length()-1)+"");
            result[index-1] = cur.substring(0, cur.length()-1);
        }

        return String.join(" ", result);
    }

    public int largestPathValue(String colors, int[][] edges) {
        char [] colorArr = colors.toCharArray();
        int n = colorArr.length;
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        Set<Integer> zeroIndegree = new HashSet<>();
        int [] indegree = new int[n];
        for(int [] x : edges) {
            edgeMap.computeIfAbsent(x[0], k->new ArrayList<Integer>()).add(x[1]);
            indegree[x[1]] ++;
        }
        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0){
                zeroIndegree.add(i);
            }
        }
        Map<Integer, Map<Character, Integer>> colorLen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colorLen.computeIfAbsent(i, k-> new HashMap<>()).put(colorArr[i],1);
        }

        int visited = 0;
        while(!zeroIndegree.isEmpty()) {
            Set<Integer> cur = new HashSet<>();
            for(int key: zeroIndegree) {
                visited += 1;
                List<Integer> targets = edgeMap.get(key);
                if(targets == null)
                    continue;
                for(int t: targets) {
                    if(--indegree[t] == 0){
                        cur.add(t);
                    }
                    for(char c: colorLen.get(key).keySet()) {
                        colorLen.get(t).put(c, Math.max(colorLen.get(key).get(c) + (c==colorArr[t] ? 1 : 0),
                                colorLen.get(t).getOrDefault(c, 0)));
                    }
                }

            }
            zeroIndegree = cur;

        }

        if(visited != n){
            return -1;
        }
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (char c : colorLen.get(i).keySet()) {
                maxLen = Math.max(maxLen, colorLen.get(i).get(c));
            }
        }
        return maxLen;
    }

    public int maxSumMinProduct(int[] nums) {
        int modulo = (int)1e9+7;
        int n = nums.length;
        long [] sumArr = new long[n];
        sumArr[0]= nums[0];
        for (int i = 1; i < n; i++) {
            sumArr[i] = sumArr[i-1] + (long)nums[i];
        }
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        Map<Integer, Integer> nextSmaller = new HashMap<>();
        Map<Integer, Integer> beforeSmaller = new HashMap<>();
        stack.push(new Pair(nums[0], 0));
        for (int i = 1; i < n; i++) {
            while(!stack.isEmpty() && stack.peek().getKey() > nums[i]) {
                Pair<Integer, Integer> cur = stack.pop();
                nextSmaller.put(cur.getValue(), i);
            }
            stack.push(new Pair<>(nums[i], i));
        }

        stack.clear();
        stack.push(new Pair(nums[n-1], n-1));
        for(int i = n-2; i >= 0; i --) {
            while(!stack.isEmpty() && stack.peek().getKey() > nums[i]){
                Pair<Integer, Integer> cur = stack.pop();
                beforeSmaller.put(cur.getValue(), i);
            }
            stack.push(new Pair<>(nums[i],i));
        }
        long maxValue = 0;
        for(int i = 0; i < n ;i ++){
            int left = beforeSmaller.getOrDefault(i, -1);
            int right = nextSmaller.getOrDefault(i, n);
            maxValue = Math.max(maxValue, nums[i] * (sumArr[right-1] -  (left == -1 ? 0 : sumArr[left])));
        }

//        System.out.println(nextSmaller.toString());
//        System.out.println(beforeSmaller.toString());

        return (int) (maxValue % modulo);
    }

    int findInsertPosition(int[] arr, int l, int r, int target) {
        if (arr[r] >= target)
            return r;
        if(arr[l] < target) {
            return l;
        }

        while(r - l > 1) {
            int mid = (r + l) / 2;
            if(arr[mid] >= target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int maxDis = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        for(int i = 0; i < n1 && i < n2; i ++) {
            maxDis = Math.max(maxDis, findInsertPosition(nums2, i, n2-1, nums1[i]) - i);
            System.out.println(i + "\t" + nums1[i] + "\t" + findInsertPosition(nums2, i, n2-1, nums1[i]));
        }
        return maxDis;
    }

    public int maximumPopulation(int[][] logs) {
        int [] list = new int [120];

        for(int [] x : logs) {
            list[x[0]-1950] +=1;
            list[x[1]-1950] -=1;
        }
        int max = list[0];
        int maxYear = 0;
        for(int i = 1; i < 120; i++) {
            list[i] = list[i-1] + list[i];
            if(list[i] > max) {
                max = list[i];
                maxYear = i;
            }
        }
        return maxYear+1950;
    }

    public int[] minInterval(int[][] intervals, int[] queries) {


        return new int[10];
    }


    /**
     * n == rooms.length
     * 1 <= n <= 105
     * k == queries.length
     * 1 <= k <= 104
     * 1 <= roomIdi, preferredj <= 107
     * 1 <= sizei, minSizej <= 107
     */


    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int k = queries.length;
        int[] ret = new int[k];
        Arrays.sort(rooms, Comparator.comparingInt(x -> x[0]));
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            tm.put(rooms[i][0], i);
        }

        for (int i = 0; i < k; i++) {
            int cur = queries[i][0];
            if (tm.containsKey(cur) && rooms[tm.get(cur)][1] >= queries[i][1]){
                ret[i] = cur;
                continue;
            }

            int tmpH = tm.higherEntry(cur) != null ? tm.higherEntry(cur).getValue(): n;
            int tmpL = tmpH - 1;

            while(tmpH < n && rooms[tmpH][1] < queries[i][1]) {
                tmpH ++;
            }
            while(tmpL >= 0 && rooms[tmpL][1] < queries[i][1]) {
                tmpL --;
            }

            if (tmpH == n) {
                if (tmpL == -1) {
                    ret[i] = -1;
                } else {
                    ret[i] = rooms[tmpL][0];
                }
            } else {
                if (tmpL == -1) {
                    ret[i] = rooms[tmpH][0];
                } else {
                    ret[i] = (cur - rooms[tmpL][0]) <=(rooms[tmpH][0] - cur) ? rooms[tmpL][0] : rooms[tmpH][0];
                }
            }

        }

        return ret;
    }

    public int getMinSwaps(String num, int k) {
        String newNum = num;
        for (int i = 0; i < k; i++) {
            newNum = nexPermutation(newNum);
        }
        System.out.println(newNum);
        int n = num.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if(num.charAt(i) != newNum.charAt(i)){
                System.out.println(num + "\t"+ newNum + "\t" + i);
                for (int j = i + 1; j < n; j++) {
                    if(num.charAt(j) == newNum.charAt(i)) {
                        num = num.substring(0,i) + newNum.charAt(i) + num.substring(i, j) + num.substring(j+1);
                        ret += (j-i);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public String nexPermutation(String num) {
        char [] ss = num.toCharArray();
        int n = num.length();
        for (int i = n-1; i > 0; i--) {
            if(ss[i-1] < ss[i]){
                for(int j = i; j < (n-i)/2 + i;  j++){
                    char temp = ss[j];
                    ss[j] = ss[n-1-(j-i)];
                    ss[n-1-(j-i)] = temp;
                }
                for(int j = i; j < n; j++){
                    if (ss[j] > ss[i-1]){
                        char temp = ss[i-1];
                        ss[i-1] = ss[j];
                        ss[j] = temp;
                        break;
                    }
                }
                break;
            }
        }
        return new String(ss);
    }

    public boolean splitString(String s) {
        int n = s.length();
        for(int i = 1; i < n; i ++) {
            long cur = Long.parseLong(s.substring(0,i));
            String sub = s.substring(i);
            if (cur > 5e10) {
                return false;
            }
            while(findMinusOneValue(cur, sub) > 0) {
                sub = sub.substring(findMinusOneValue(cur, sub));
                if (sub.length() == 0){
                    return true;
                }
                cur --;
            }
        }
        return false;
    }

    public static int findMinusOneValue(long n, String s){
        int ret  = -1;
        for(int i = 1; i <= s.length(); i ++) {
            long cur = Long.parseLong(s.substring(0,i));
            if (cur > 5e10) {
                return -1;
            }
            if (cur == n-1){
                ret = i;
            }
        }
        return ret;
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] == target) {
                min = Math.min(min, Math.abs(i-start));
            }
        }
        return min;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        arr[0] = 1;
        for(int i = 1; i < n; i ++) {
            arr[i] = Math.min(arr[i], arr[i-1] + 1);
        }
        System.out.println(Arrays.toString(arr));

        return arr[n-1];
    }

    public static long getDiffDates(String s1, String s2) {
        LocalDateTime d1 = Instant.ofEpochMilli(Long.parseLong(s1) * 1000).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        LocalDateTime d2 = LocalDateTime.parse(s2, dft);
        return ChronoUnit.HOURS.between(d1,d2);
    }


    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, Comparator.comparingInt(o -> o[0]));
        int cnt = restrictions.length;
        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                restrictions[i][1] = Math.min(restrictions[i][0]-1, restrictions[i][1]);
            } else {
                if(restrictions[i][1] >= restrictions[i-1][1]) {
                    restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i-1][1] + restrictions[i][0]-restrictions[i-1][0]);
                }
            }
        }
        for (int i = cnt - 2; i >= 0; i--) {
            if(restrictions[i][1] >= restrictions[i+1][1]) {
                restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i+1][1] + restrictions[i+1][0] - restrictions[i][0]);
            }
        }
        // 这个特殊条件可以通过数组里增加[1,0],[n,Math.min(Integer.MAX_VALUE< maxHeight_n)]

        int max = cnt > 0 ? Math.max(restrictions[0][1], restrictions[cnt-1][1] + n-restrictions[cnt-1][0]):n-1;
        max = cnt > 0 ? Math.max(max, (restrictions[0][0] -1 - restrictions[0][1])/2+restrictions[0][1]) : max;
        for (int i = 1; i < cnt; i++) {
            int curMax = (restrictions[i][0] - restrictions[i-1][0] - (Math.abs(restrictions[i][1] -
                    restrictions[i-1][1])))/2 + Math.max(restrictions[i][1] , restrictions[i-1][1]);
            max = Math.max(max, curMax);
        }
        return max;
    }

    public int maxFrequency(int[] A, long k) {
        int i = 0, j;
        Arrays.sort(A);
        for (j = 0; j < A.length; ++j) {
            k += A[j];
            if (k < (long)A[j] * (j - i + 1))
                k -= A[i++];
        }
        return j - i;
    }
    
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int [] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1]+nums[i];
        }

        int max = 1;
        int j = 0;
        for (int i = 1; i < n; i++) {
            for (;j < i; j++) {
                if(nums[i] * (i-j+1) - (sum[i] - sum[j]+nums[j]) <= k){
                    max = Math.max(i-j+1, max);
                    break;
                }
            }
        }
        return max;
    }

    public static double[] normalize(int [] vector) {
        int sum = 0;
        int len = vector.length;
        for (int i = 0; i < len; i++) {
            sum += vector[i] * vector[i];
        }
        double sqrtSum = Math.sqrt(sum);
        double [] ret = new double[len];
        for (int i = 0; i < len; i++) {
            ret[i] = vector[i] / sqrtSum;
        }
        return ret;
	}

	public static double euclideanDistance(double[] vector1, double[] vector2) {
		double distance = 0;
		// 需要两个向量严格相等，否则返回Infinity
		if (vector1.length == vector2.length) {
			for (int i = 0; i < vector1.length; i++) {
                double temp = Math.pow((vector1[i] - vector2[i]), 2);
				distance += temp;
			}
            distance = Math.sqrt(distance);
            return distance;
		} else {
            return Double.MAX_VALUE;
        }
	}

	public static int getType(int[] vvCount, double [][] center) {
		int m = center.length;
		double[] normalized = normalize(vvCount);
		double minDistance = Double.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0; i < m; i++) {
            double curDistance = euclideanDistance(normalized, center[i]);
			if (curDistance < minDistance) {
				minDistance = curDistance;
				minIndex = i;
			}
		}
		return minIndex;
	}

    public static List<String> getDateList(String strStart, String strEnd, int period) {
		LocalDate startDate;
		LocalDate endDate;
        if (strStart.length() == 0) {
            endDate = LocalDate.parse(strEnd, dft);
            startDate = endDate.minusDays((long)period - 1);
        } else if (strEnd.length() == 0) {
            startDate = LocalDate.parse(strStart, dft);
            endDate = startDate.plusDays((long)period - 1);
        } else {
            startDate = LocalDate.parse(strStart, dft);
            endDate = LocalDate.parse(strEnd, dft);
        }
        List<String> ret = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            ret.add(startDate.format(dft));
            startDate = startDate.plusDays(1);
        }
        return ret;
	}

    public int numberOfSets(int n, int k) {
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < Math.min(i, k + 1); j++) {
                int sum = 0;
                for (int l = 2; l <= i; l++) {
                    sum += dp[i - l + 1][j - 1];
                    sum %= mod;

                }
                sum += dp[i - 1][j];
                sum %= mod;
                dp[i][j] = sum;
            }
        }
        return dp[n][k];
    }


    public int maxLengthBetweenEqualCharacters(String s) {
        char[] ss = s.toCharArray();
        int max = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ss.length; i++) {
            if (map.containsKey(ss[i])) {
                max = Math.max(max, i - map.get(ss[i]) - 1);
            } else {
                map.put(ss[i], i);
            }
        }
        return max;
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int n = towers.length;
        int max = 0;
        int[] ret = new int[2];
        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    double d = Math.sqrt(Math.pow(towers[k][0] - i, 2) + Math.pow(towers[k][1] - j, 2));
                    if (d <= radius) {
                        sum += (int) (towers[k][2] / (1 + d));
                    }
                }

                if (sum > max) {
                    ret[0] = i;
                    ret[1] = j;
                    max = sum;
                }
            }
        }

        return ret;
    }

    public double trimMean(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int remove = (int) (len * 0.05);
        int totalSum = 0;
        for (int i = remove; i < len - remove; i++) {
            totalSum += arr[i];
        }

        return totalSum / (double) (len - (remove * 2));

    }

    public int minimumOneBitOperations(int n) {
        if (map.containsKey(n))
            return map.get(n);
        String ns = Integer.toBinaryString(n);
        int len = ns.length();
        if (ns.length() <= 1) {
            return n == 1 ? 1 : 0;
        }
        int tmp = ((1 << (len - 1)) + (1 << (len - 2))) ^ n;
        int ret;
        if (ns.charAt(1) == '1') {
            ret = minimumOneBitOperations(tmp) + 1 +
                    minimumOneBitOperations(1 << len - 2);
        } else {
            ret = minimumOneBitOperations(tmp) + minimumOneBitOperations((1 << (len - 1)) + (1 << (len - 2)));
        }
        map.put(n, ret);
        return ret;
    }


    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[][] reqs = new int[arrival.length][2];
        for (int i = 0; i < reqs.length; i++) {
            reqs[i][0] = arrival[i];
            reqs[i][1] = arrival[i] + load[i];
        }


        List<Integer> ret = new ArrayList<>();
        return ret;
    }

    public static int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        int[] dp = new int[1 << (n + 1)];

        for (int i = 0; i < edges.length; i++) {
            dist[edges[i][0] - 1][edges[i][1] - 1] = 1;
            dist[edges[i][1] - 1][edges[i][0] - 1] = 1;
            dp[(1 << (edges[i][1] - 1)) + (1 << (edges[i][0] - 1))] = 1;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] == Integer.MAX_VALUE ||
                            dist[i][k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dist[j][i] + dist[i][k]);
                }
            }
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] != 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                int pre = i ^ (1 << j);
                if ((i & (1 << j)) != 0 && dp[pre] != 0) {
                    int newMax = -1;
                    int minDist = Integer.MAX_VALUE;
                    for (int k = 0; k < n; k++) {
                        if ((pre & (1 << k)) != 0) {
                            newMax = Math.max(newMax, dist[k][j]);
                            minDist = Math.min(minDist, dist[k][j]);
                        }
                    }

                    if (newMax < 0 || minDist > 1) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.max(dp[pre], newMax);
                    }
                    break;
                }
            }
        }
        int[] ret = new int[n - 1];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > 0) {
                ret[dp[i] - 1] += 1;
            }
        }
        return ret;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        String ar = new StringBuffer(a).reverse().toString();
        String br = new StringBuffer(b).reverse().toString();
        return palindromeHelper(a, b) || palindromeHelper(b, a) || palindromeHelper(ar, br) || palindromeHelper(br, ar);
    }

    public boolean palindromeHelper(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int n = a.length();
        boolean ret = true;
        for (int i = 0; i < n / 2; i++) {
            if (aa[i] != bb[n - i - 1]) {
                return isPalindrome(b.substring(i, n - i));
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        char[] ss = s.toCharArray();
        int n = ss.length;
        for (int i = 0; i < n / 2; i++) {
            if (ss[i] != ss[n - i - 1])
                return false;
        }
        return true;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, Integer> map = new HashMap<>();
        int[][] adj = new int[n][n];
        for (int i = 0; i < roads.length; i++) {
            map.put(roads[i][0], map.getOrDefault(roads[i][0], 0) + 1);
            map.put(roads[i][1], map.getOrDefault(roads[i][1], 0) + 1);
            adj[roads[i][0]][roads[i][1]] = 1;
            adj[roads[i][1]][roads[i][0]] = 1;
        }
        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (map.containsKey(i) && map.containsKey(j))
                    ret = Math.max(map.get(i) + map.get(j) - (adj[i][j] == 1 ? 1 : 0), ret);
            }
        }


        return ret;
    }

    public int maxDepth(String s) {
        int dep = 0;
        int max = 0;
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] == '(') {
                dep += 1;
                max = Math.max(max, dep);
            } else if (ss[i] == ')') {
                dep -= 1;
            }
        }
        return max;
    }


    public static int transType(int type, double[][] transMatrix) {
        int n = transMatrix[0].length;
        double[] prob = transMatrix[type];
        double culValue = 0;
        double rand = Math.random();
        for (int i = 0; i < n; i++) {
            culValue += prob[i];
            if (rand <= culValue) {
                return i;
            }
        }
        return 1;
    }

    public static String plusDate(String strDate, int days) {
        LocalDate date = LocalDate.parse(strDate, dft);
        return date.plusDays(days).format(dft);
    }


    public static long getDiffDays(String s1, String s2) {
        LocalDate t1 = LocalDate.parse(s1, dft);
        LocalDate t2 = LocalDate.parse(s2, dft);
        System.out.println(t1.plusDays(1).format(dft));
        return ChronoUnit.DAYS.between(LocalDate.parse(s1, dft), LocalDate.parse(s2, dft));
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

        return 10;
    }

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        TreeNode pre = null;

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                q.offer(cur.left);
                map.put(cur.left, map.get(cur) + 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                map.put(cur.right, map.get(cur) + 1);
            }

            if (map.get(cur) % 2 == cur.val % 2)
                return false;
            if (pre != null && map.get(pre) == map.get(cur)) {
                if ((map.get(cur) % 2 == 0 && (pre.val >= cur.val || cur.val % 2 == 0)) || (map.get(cur) % 2 == 1 && (pre.val <= cur.val || cur.val % 2 == 1))) {
                    return false;
                }
            }
            pre = cur;
        }

        return true;
    }

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ret = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                if (nums[i] >= n) {
                    ret = n;
                }
            } else {
                if (nums[i] >= n - i && nums[i - 1] < n - i) {
                    ret = n - i;
                }
            }

        }
        return ret;

    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length;
        int m = colSum.length;
        int[][] ret = new int[n][m];
        int[] rowIndex = IntStream.range(0, rowSum.length).boxed().
                sorted(Comparator.comparingInt(i -> rowSum[i])).
                mapToInt(a -> a).toArray();
        int[] colIndex = IntStream.range(0, colSum.length).boxed().
                sorted(Comparator.comparingInt(i -> colSum[i])).
                mapToInt(a -> a).toArray();

        int i = 0, j = 0;
        while (i < n || j < m) {
            int min = Math.min(rowSum[rowIndex[i]], colSum[colIndex[j]]);
            ret[rowIndex[i]][colIndex[j]] = min;
            rowSum[rowIndex[i]] -= min;
            colSum[colIndex[j]] -= min;
            if (rowSum[rowIndex[i]] == 0) {
                i++;
            }
            if (colSum[colIndex[j]] == 0) {
                j++;
            }
        }


        return ret;
    }


    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(keyName[i], k -> new ArrayList<>()).add(keyTime[i]);
        }

        List<String> ret = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String k : map.keySet()) {
            List<String> times = map.get(k);
            Collections.sort(times);
            for (int i = 0; i < times.size() - 2; i++) {
                if (getTimeDiff(times.get(i), times.get(i + 2)) <= 60) {
                    set.add(k);
                }
            }

        }
        ret.addAll(set);
        Collections.sort(ret);
        return ret;
    }

    public int getTimeDiff(String a, String b) {
        String[] aa = a.split(":");
        String[] bb = b.split(":");
        return (Integer.parseInt(bb[0]) - Integer.parseInt(aa[0])) * 60 + Integer.parseInt(bb[1]) - Integer.parseInt(aa[1]);
    }

    public static List<String> getWeekDate(String date) {
        LocalDate curDate = LocalDate.parse(date, dft);
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ret.add(curDate.minusDays(6 - i).format(dft));
        }
        return ret;
    }

    public static <T> String join(List<T> list, String conjunction) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(conjunction));
    }

    public static void givenUsingPlainJava_whenGeneratingRandomLongBounded_thenCorrect() {
        System.out.println(String.valueOf(20000000000L + (long) (Math.random() * 10000000000L)));
    }

    public static String hash(String data) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
        digest.update(data.getBytes());
        byte[] hash = digest.digest();
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for (int i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10) {
                System.out.println("here");
                buf.append("0");
            }
            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        }
        String result = buf.toString();
        //return result.substring(result.length() - 15);
        return result;
    }


    public static String getMD5(String item) {
        if (item == null || item.isEmpty()) {
            return "";
        }
        try {
            byte[] buf = item.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buf);
            byte[] tmp = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : tmp) {
                System.out.println(Integer.toHexString(b & 0xff | 0xFFFFFF00));
                sb.append(Integer.toHexString(b & 0xff | 0xFFFFFF00).substring(6));
            }
            return sb.toString().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int profit = 0;
        int n = customers.length;
        int maxProfit = Integer.MIN_VALUE;
        int maxRotate = 0;
        int i;
        for (i = 0; i < customers.length; i++) {
            if (customers[i] <= 4) {
                profit += (boardingCost * customers[i] - runningCost);
            } else {
                profit += (boardingCost * 4 - runningCost);
                if (i != customers.length - 1) {
                    customers[i + 1] += customers[i] - 4;
                } else {
                    customers[i] -= 4;
                }
            }
            if (profit > maxProfit) {
                maxProfit = profit;
                maxRotate = i;
            }
        }

        while (customers[n - 1] > 0) {
            if (customers[n - 1] > 4) {
                profit += (boardingCost * 4 - runningCost);
                customers[n - 1] -= 4;
            } else {
                profit += (boardingCost * customers[n - 1] - runningCost);
                customers[n - 1] = 0;
            }
            if (profit > maxProfit) {
                maxProfit = profit;
                maxRotate = i++;
            }

        }
        return maxProfit > 0 ? maxRotate + 1 : -1;

    }

    public int minOperations(String[] logs) {
        int depth = 0;
        for (int i = 0; i < logs.length; i++) {
            if (logs[i].equals("../")) {
                depth = Math.max(0, depth - 1);
            } else if (logs[i].equals("./")) {

            } else {
                depth += 1;
            }
        }

        return depth;
    }

    public static long getDiff(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyyMMddhh");
        Date date1 = sdf1.parse(t1);
        Date date2 = sdf2.parse(t2);
        if (date1.getTime() > date2.getTime())
            return (date1.getTime() - date2.getTime()) / 86400000;
        else
            return -1;
    }

    public boolean topologicalSort(int n, List<Pair<Integer, Integer>> edges) {
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


    public boolean findMid(Integer[] minMax, int c2Cnt, List<Pair<Integer, Integer>> c2List) {
        int minKey = minMax[0];
        int minValue = minMax[2];
        int maxKey = minMax[1];
        int maxValue = minMax[3];
        for (int o = 0; o < c2Cnt; o++) {
            if (c2List.get(o).getKey() >= minKey && c2List.get(o).getKey() <= maxKey
                    && c2List.get(o).getValue() >= minValue && c2List.get(o).getValue() <= maxValue) {
                return true;
            }
        }

        return false;
    }


    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        int maxIdx = -1;
        Map<Integer, Integer[]> minMax = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(targetGrid[i][j], k -> new ArrayList<>()).add(new Pair(i, j));
                maxIdx = Math.max(maxIdx, targetGrid[i][j]);

                if (!minMax.containsKey(targetGrid[i][j])) {
                    Integer[] tmp = new Integer[4];
                    tmp[0] = Integer.MAX_VALUE;
                    tmp[1] = 0;
                    tmp[2] = Integer.MAX_VALUE;
                    tmp[3] = 0;
                    minMax.put(targetGrid[i][j], tmp);
                }
                minMax.get(targetGrid[i][j])[0] = Math.min(minMax.get(targetGrid[i][j])[0], i);
                minMax.get(targetGrid[i][j])[1] = Math.max(minMax.get(targetGrid[i][j])[1], i);
                minMax.get(targetGrid[i][j])[2] = Math.min(minMax.get(targetGrid[i][j])[2], j);
                minMax.get(targetGrid[i][j])[3] = Math.max(minMax.get(targetGrid[i][j])[3], j);
            }
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        List<Pair<Integer, Integer>> order = new ArrayList<>();
        int cntKey = keys.size();
        for (int i = 0; i < cntKey; i++) {
            int c1 = keys.get(i);
            int c1Cnt = map.get(c1).size();
            List<Pair<Integer, Integer>> c1List = map.get(c1);
            for (int j = i + 1; j < cntKey; j++) {
                int c2 = keys.get(j);
                int c2Cnt = map.get(c2).size();
                List<Pair<Integer, Integer>> c2List = map.get(c2);

                boolean f1 = findMid(minMax.get(c1), c2Cnt, c2List);
                boolean f2 = findMid(minMax.get(c2), c1Cnt, c1List);

                if (f1) {
                    order.add(new Pair<>(c1, c2));
                }

                if (f2) {
                    order.add(new Pair<>(c2, c1));
                }

                if (f1 && f2) {
                    return false;
                }
                if (!topologicalSort(maxIdx + 1, order)) {
                    return false;
                }
            }
        }
        if (topologicalSort(maxIdx + 1, order))
            return true;
        else
            return false;
    }

    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] % p;
            total += nums[i];
            total %= p;
        }
        int[] sumArr = new int[n];
        sumArr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sumArr[i] = (nums[i] + sumArr[i - 1]) % p;
        }

        if (total == 0) {
            return 0;
        }

        int minLen = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (sumArr[i] == total) {
                minLen = Math.min(minLen, i + 1);
            }
            if (sumArr[i] >= total) {
                if (map.containsKey(sumArr[i] - total)) {
                    minLen = Math.min(minLen, i - map.get(sumArr[i] - total));
                }
            } else {
                if (map.containsKey(sumArr[i] + p - total)) {
                    minLen = Math.min(minLen, i - map.get(sumArr[i] + p - total));
                }
            }
            map.put(sumArr[i], i);
        }

        return minLen == n ? -1 : minLen;
    }


    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < requests.length; i++) {
            arr[requests[i][0]] += 1;
            if (requests[i][1] < n - 1)
                arr[requests[i][1] + 1] -= 1;
        }
        int[] newArr = new int[n];
        newArr[0] = arr[0];
        for (int i = 1; i < n; i++) {
            newArr[i] = newArr[i - 1] + arr[i];
        }
        Arrays.sort(newArr);
        Arrays.sort(nums);

        int ret = 0;
        int module = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            ret += newArr[n - 1 - i] * nums[n - 1 - i];
            ret %= module;

        }

        return ret;

    }


    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();

        for (int i = 0; i < m; i++) {
            left.add(i);
        }
        for (int i = 0; i < n; i++) {
            right.add(i);
        }

        int[][] matrix = new int[m * n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = cost.get(i).get(j);
            }
        }

        Arrays.sort(matrix, (Comparator.comparingInt(o -> o[2])));
        int ret = 0;
        for (int i = 0; i < m * n; i++) {
            if (!left.contains(matrix[i][0]) || !right.contains(matrix[i][1])) {
                ret += matrix[i][2];
                left.remove(matrix[i][0]);
                right.remove(matrix[i][1]);
            }
        }


        return 0;
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] dpPos = new long[m][n];
        long[][] dpNeg = new long[m][n];

        dpPos[0][0] = grid[0][0];
        dpNeg[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                List<Long> tmp = new ArrayList<>();
                if (i > 0) {
                    tmp.add(grid[i][j] * dpPos[i - 1][j]);
                    tmp.add(grid[i][j] * dpNeg[i - 1][j]);
                }
                if (j > 0) {
                    tmp.add(grid[i][j] * dpPos[i][j - 1]);
                    tmp.add(grid[i][j] * dpNeg[i][j - 1]);
                }
                Collections.sort(tmp);
                dpPos[i][j] = tmp.get(tmp.size() - 1);
                dpNeg[i][j] = tmp.get(0);

            }
        }

//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(dpPos[i]));
//        }
//
//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(dpNeg[i]));
//        }

        return dpPos[m - 1][n - 1] >= 0 ? (int) (dpPos[m - 1][n - 1] % ((int) 1e9 + 7)) : -1;

    }

    public int maxUniqueSplit(String s) {
        char[] ss = s.toCharArray();
        return helper(new HashSet<>(), ss, 0);
    }

    public int helper(Set<String> pre, char[] s, int start) {
        if (start == s.length) {
            //System.out.println(pre);
            return pre.size();
        }

        StringBuffer sb = new StringBuffer();
        int max = 0;
        for (int i = start; i < s.length; i++) {

            sb.append(s[i]);
            String tmp = sb.toString();
            if (!pre.contains(tmp)) {
                pre.add(tmp);
                max = Math.max(max, helper(pre, s, i + 1));
                pre.remove(tmp);
            } else {
                max = Math.max(max, helper(pre, s, i + 1));
            }

        }
        return max;
    }

    public String reorderSpaces(String text) {
        int numBlank = 0;
        int numWord = 0;
        List<String> list = new ArrayList<>();
        char[] ss = text.toCharArray();
        char pre = ' ';
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (ss[i] == ' ') {
                numBlank++;
            } else {
                if (pre == ' ') {
                    sb.setLength(0);
                    numWord++;
                }
                sb.append(ss[i]);
                if (i == text.length() - 1 || ss[i + 1] == ' ') {
                    list.add(sb.toString());
                }
            }
            pre = ss[i];
        }
        int each;
        if (numWord > 1) {
            each = numBlank / (numWord - 1);
        } else {
            each = 0;
        }
        String ret = "";


        for (int i = 0; i < list.size(); i++) {
            ret += list.get(i);
            if (i == list.size() - 1)
                continue;
            for (int j = 0; j < each; j++) {
                ret += " ";
            }
        }
        for (int j = 0; j < numBlank - each * (numWord - 1); j++) {
            ret += " ";
        }
        return ret;

    }

    public static String getHour() {
        long timestamp = 1600416297000L;
        LocalDateTime localTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHH");
        return dtf.format(localTime);
    }


    public static int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] inDegree = new int[n + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> length = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }

        for (int i = 0; i < dependencies.length; i++) {
            inDegree[dependencies[i][1]]++;
            map.computeIfAbsent(dependencies[i][0], x -> new ArrayList<Integer>()).add(dependencies[i][1]);
        }

        return 0;
    }


    public int minDistance(int[] houses, int k) {
        int[] mailBox = new int[k];
        for (int i = 0; i < k; i++) {
            mailBox[i] = houses[i];
        }
        int totalDis = calcTotalDis(houses, mailBox);
        do {
            List<List<Integer>> centers = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                centers.add(new ArrayList<>());
            }
            for (int i = 0; i < houses.length; i++) {
                int minValue = Integer.MAX_VALUE;
                int minIndex = -1;
                for (int j = 0; j < k; j++) {
                    if (Math.abs(houses[i] - mailBox[j]) < minValue) {
                        minValue = Math.abs(houses[i] - mailBox[j]);
                        minIndex = j;
                    }
                }
                centers.get(minIndex).add(i);
            }

            for (int i = 0; i < k; i++) {

            }


        } while (calcTotalDis(houses, mailBox) < totalDis);


        return 0;
    }

    public int calcTotalDis(int[] houses, int[] mailBox) {
        int ret = 0;
        for (int i = 0; i < houses.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < mailBox.length; j++) {
                min = Math.abs(houses[i] - mailBox[j]);
            }
            ret += min;
        }

        return ret;
    }
}