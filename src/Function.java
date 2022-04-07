package src;

import java.io.*;
import java.math.BigInteger;
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

import javax.sound.sampled.SourceDataLine;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    List<NestedInteger> nestedList;
    List<Integer> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.list = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            this.list.addAll(flattenList(ni));
        }
    }

    public List<Integer> flattenList(NestedInteger ni) {
        List<Integer> ret = new ArrayList<>();
        if (ni.isInteger()) {
            ret.add(ni.getInteger());
        } else {
            for (NestedInteger child : ni.getList()) {
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
        if (list.isEmpty() || val < list.get(list.size() - 1)) {
            list.add(val);
        } else {
            list.add(list.get(list.size() - 1));
        }
    }

    public void pop() {
        stack.pop();
        list.remove(list.size() - 1);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.get(list.size() - 1);
    }
}

class Solution {
    int[] origin;
    int n;

    public Solution(int[] nums) {
        this.origin = nums;
        this.n = nums.length;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int[] ret = Arrays.copyOf(origin, n);
        for (int i = n - 1; i > 0; i--) {
            int cur = new Random().nextInt(i + 1);
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
            list.remove(list.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    public int getFront() {
        return list.isEmpty() ? -1 : list.get(0);
    }

    public int getRear() {
        return list.isEmpty() ? -1 : list.get(list.size() - 1);
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
            map.put(val, list.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            if (map.get(val) == list.size() - 1) {
                list.remove(list.size() - 1);
                map.remove(val);
            } else {
                list.set(map.get(val), list.get(list.size() - 1));
                list.remove(list.size() - 1);
                map.put(list.get(map.get(val)), map.get(val));
                map.remove(val);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        int r = (int) (Math.random() * list.size());
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

class MyCircularQueue {
    int[] queue;
    int header;
    int tail;
    int size;
    int capacity;

    public MyCircularQueue(int k) {
        queue = new int[k];
        header = 0;
        tail = 0;
        size = 0;
        capacity = k;
    }

    public boolean enQueue(int value) {
        if (size < capacity) {
            queue[(tail + capacity) % capacity] = value;
            size++;
            tail++;
            return true;
        } else {
            return false;
        }
    }

    public boolean deQueue() {
        if (size > 0) {
            header = (header + 1) % capacity;
            size--;
            return true;
        } else {
            return false;
        }
    }

    public int Front() {
        if (size > 0) {
            return queue[header];
        } else {
            return -1;
        }
    }

    public int Rear() {
        if (size > 0) {
            return queue[(tail + capacity - 1) % capacity];
        } else {
            return -1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return capacity == size;
    }
}

class LockingTree {
    private int[] parent;
    private int[] status;
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
                if (children.containsKey(x)) {
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
        while (tmp != -1 && status[tmp] == 0) {
            tmp = parent[tmp];
        }
        if (tmp != -1) {
            return false;
        }

        boolean hasLockedChildren = false;
        for (int x : children.get(num)) {
            if (status[x] != 0) {
                hasLockedChildren = true;
                break;
            }
        }
        if (hasLockedChildren) {
            status[num] = user;
            for (int x : children.get(num)) {
                status[x] = 0;
            }
            return true;
        }

        return false;
    }
}

class Test {
    public int a;

    public Test(int a) {
        this.a = a;
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
            while (!s1.isEmpty()) {
                s2.add(s1.pop());
            }
            int ret = s2.pop();
            while (!s2.isEmpty()) {
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
        for (int x : nums1) {
            nums1Map.put(x, nums1Map.getOrDefault(x, 0) + 1);
        }
        for (int x : nums2) {
            nums2Map.put(x, nums2Map.getOrDefault(x, 0) + 1);
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
        for (int x : nums1Map.keySet()) {
            ret += nums1Map.get(x) * nums2Map.getOrDefault(tot - x, 0);
        }
        return ret;
    }
}
class AA {
    float a;

    AA(float a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "AA{" +
                "a=" + a +
                '}';
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      void print() {
          StringBuffer sb = new StringBuffer();
          sb.append(val + ",");
          ListNode it = next;
          while(it != null) {
            sb.append(it.val + ",");
            it = it.next;
          }
          System.out.println(sb.toString());
      }
  }
 

class ASolution {
    ListNode head;

    public ASolution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode h = head;
        int ret = -1;
        int idx = 1;
        while(h!=null) {
            if (Math.random() * idx < 1) {
                ret = h.val;
            }
            idx ++;
            h = h.next;
        }
        return ret;
    }
}

class BSolution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    Map<Integer, Integer> Idxmap = new HashMap<>();
    public BSolution(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            map.computeIfAbsent(nums[i], x->new ArrayList<>()).add(i);
        }
    }

    
    public int pick(int target) {
        int len = map.get(target).size();
        int r = (int)(Math.random() * len);
        return map.get(target).get(r);
        // if (map.get(target).size() == 1) {
        //     return map.get(target).get(0);
        // } else {
        //     if (Idxmap.containsKey(target)) {
        //         return map.get(target).get((Idxmap.get(target) + 1)%map.get(target).size());
        //     } else {
        //         Idxmap.put(target, 0);
        //         return map.get(target).get(0);
        //     }
        // }
    }
}


public class Function {
    static DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Map<String, Set<Integer>> map = new HashMap<>();
    static int x = 0;
    static int y = 0;

    public static void main(String[] args){ 
        Function f = new Function();
        
        System.out.println(f.minNonZeroProduct(30));
        System.out.println((long)Math.pow(2, 60));
    }


    public int minNonZeroProduct(int p) {
        int modulo = (int)1e9+7;
        long ret = fastPowerLong(2, p, modulo) - 1;
        long left = fastPowerLong(ret-1, (long)Math.pow(2, p)/2-1, modulo);

        return (int)(ret*left%modulo);
    }
    public static long fastPowerLong(long a, long b, int module) {
        long ans = 1;
        long base = a % module;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = (ans * base) % module;
            }
            base = (base * base) % module;
            b >>= 1;
        }
        return ans;
    }
    
    public static int fastPower(int a, int b, int module) {
        long ans = 1;
        long base = a % module;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = (ans * base) % module;
            }
            base = (base * base) % module;
            b >>= 1;
        }
        return (int) ans;
    }


    public String defangIPaddr(String address) {
        
        return address.replace(".", "[.]");
    }

    public int minimizeTheDifference(int[][] mat, int target) {

        Set<Integer> set = new HashSet<>();
        int m = mat.length;
        int n = mat[0].length;
        set.add(0);
        for (int i = 0; i < m; i++) {
            Set<Integer> cur = new HashSet<>();
            int upperBound = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                for (int x : set) {
                    int curValue = x + mat[i][j];
                    if (curValue >= target && curValue < upperBound) {
                        upperBound = curValue;
                    }
                    if (curValue <= upperBound) {
                        cur.add(x+mat[i][j]); 
                    }
                }
                System.out.println(i+"\t"+cur+"\t"+upperBound);
            }
            set = cur;
        }
        int min = Integer.MAX_VALUE;
        for (int x : set) {
            min = Math.min(min, Math.abs(target -x));
        }

        return min;
    }

    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                list.add(nums[i]);
            }
        }

        int [] numsNew = new int[list.size()];
        for (int i = 0; i < numsNew.length; i++) {
            numsNew[i] = list.get(i);
        }

        int max = 0;
        for (int i = 0; i < numsNew.length; i++) {
            int idx = Arrays.binarySearch(numsNew, numsNew[i]+n-1);
            if (idx < 0) {
                idx = -1 - idx -1;
            } 
            max = Math.max(max, idx-i+1);
        }
        return n-max;
    }


    public int minFlips(String s) {
        int n = s.length();
        int[][] count = new int[2][3];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)-'0'][2] ++;
            count[s.charAt(i)-'0'][i%2] ++;
        }

        int[][] cur = new int[2][3];
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tail = n - i;

            int[][] suf = new int[2][3];
            int[][] pre = new int[2][3];

            for (int j = 0; j < 2; j ++) {
                pre[j][2] = cur[j][2];
                pre[j][0] = (tail%2 == 0 ? cur[j][0]: cur[j][1]);
                pre[j][1] = (tail%2 == 0 ? cur[j][1]: cur[j][0]);

                suf[j][2] = count[j][2] - cur[j][2];
                suf[j][0] = (i%2 == 0 ? count[j][0]- cur[j][0]: count[j][1]- cur[j][1]);
                suf[j][1] = (i%2 == 0 ? count[j][1]- cur[j][1]: count[j][0]- cur[j][0]);
            }

            ret = Math.min(ret, Math.min(pre[0][0]+suf[0][0]+pre[1][1]+suf[1][1], pre[0][1]+suf[0][1]+pre[1][0]+suf[1][0]));

            cur[s.charAt(i)-'0'][2] ++;
            cur[s.charAt(i)-'0'][i%2] ++;
        }
        
        return ret;
    }


    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> depth = new HashMap<>();
        Depth(root, depth);
        while (!depth.get(root.left).equals(depth.get(root.right))) {
            if (depth.get(root.left) > depth.get(root.right)) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public int Depth(TreeNode root, Map<TreeNode, Integer> depth) {
        int curDep = 0;
        if (root != null) {
            curDep = 1 + Math.max(Depth(root.left, depth), Depth(root.right, depth));
        }
        depth.put(root, curDep);
        return curDep;
    }


    int[] getSortedIndices(int[] arr) {
        return IntStream.range(0, arr.length).boxed().
                sorted(Comparator.comparingInt(i -> arr[i])).
                //sorted((i, j) -> arr[i] - arr[j]).
                        mapToInt(a -> a).toArray();
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int []si = getSortedIndices(nums2);
        Arrays.sort(nums2);
        int i=0,j =0;
        int n = nums1.length;
        List<Integer> selected = new ArrayList<>();
        List<Integer> other = new ArrayList<>();
        while (i < n && j < n) {
            while (i < n && nums1[i] <= nums2[j]) {
                other.add(nums1[i]);
                i++;
            }
            if (i < n) {
                selected.add(nums1[i]);
            }
            i++;
            j++;
        }
        selected.addAll(other);
        int [] ret = new int[n];
        for (int k = 0; k < n; k++) {
            ret[si[k]] = selected.get(k);
        }


        return ret;
    }

    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length();
        int[][]next = new int[26][n];
        for (int i = n-1; i >= 0; i--) {
            if (i != n-1) {
                for (int j = 0; j < 26; j++) {
                    next[j][i] = next[j][i+1];
                }
            } else {
                for (int j = 0; j < 26; j++) {
                    next[j][i] = -1;
                }
            }
            next[s.charAt(i)-'a'][i] = i;
        }

        // for (int i = 0; i < 26; i++) {
        //     System.out.println((char)('a'+i) + "\t" + Arrays.toString(next[i]));
        // }

        int m = dictionary.size();
        String ret = "";
        for (int i = 0; i < m; i++) {
            int p = 0;
            String cur = dictionary.get(i);
            for (int j = 0; j < cur.length(); j++) {
                if (p <n && next[cur.charAt(j)-'a'][p] >= 0) {
                    p = next[cur.charAt(j)-'a'][p]+1;
                    if (j == cur.length() -1) {
                        if (cur.length() > ret.length()) {
                            ret = cur;
                        } else if (cur.length() == ret.length() && cur.compareTo(ret) < 0) {
                            ret = cur;
                        }
                    }
                } else {
                    break;
                }
            }
        }



        return ret;
    }


    public int diameterOfBinaryTree(TreeNode root) {
        Map<TreeNode, Integer> depth = new HashMap<>();
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        int n = list.size();
        for (int i = 0; i < n; i++) {
            int d = 0;
            TreeNode cur = list.get(n-i-1);
            if (cur.left != null) {
                d = Math.max(d, depth.get(cur.left)+1);
            }
            if (cur.right != null) {
                d = Math.max(d, depth.get(cur.right)+1);
            }
            depth.put(cur, d);
            System.out.println(cur.val + "\t" + d);
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            TreeNode cur = list.get(n-i-1);
            int length = 0;
            if (cur.left != null) {
                length += (depth.get(cur.left) + 1);
            }
            if (cur.right != null) {
                length += (depth.get(cur.right) + 1);
            }
            ret = Math.max(ret, length);
        }




        return ret;
    }


    public ListNode swapNodes(ListNode head, int k) {
        Map<Integer, ListNode> map = new HashMap<>();
        int idx = 0;
        ListNode p = head;
        while (p!=null) {
            map.put(idx++, p);
            p = p.next;
        }
        if (idx < k) {
            return head;
        }

        int kValue = map.get(k-1).val;
        map.get(k-1).val = map.get(idx-k).val;
        map.get(idx-k).val = kValue;

        
        return head;
    }


    // public int connectTwoGroups(List<List<Integer>> cost) {
     
        
    //     return 0;
    // }



    public String stoneGameIII(int[] stoneValue) {
        Map<Integer, Integer> map = new HashMap<>();
     
        int val = stoneGameIIIHelper(stoneValue, stoneValue.length, 0, map);
        if (val > 0) {
            return "Alice";
        } else if (val < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    public int stoneGameIIIHelper(int[] stoneValue, int n, int startIndex, Map<Integer, Integer> map) {
        if (map.containsKey(startIndex)) {
            return map.get(startIndex);
        }
        if (startIndex == n) {
            return 0;
        }
        if (startIndex == n - 1) {
            return stoneValue[n-1];
        } 
        if (startIndex == n - 2) {
            if (stoneValue[n-1] > 0) {
                return stoneValue[n-2] + stoneValue[n-1];
            } else {
                return stoneValue[n-2] - stoneValue[n-1];
            }
        }
        int preSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = startIndex; i < Math.min(n, startIndex + 3); i++) {
            preSum += stoneValue[i];
            int cur = preSum - stoneGameIIIHelper(stoneValue, n, i+1, map);
            max = Math.max(max, cur);
            
        }
        map.put(startIndex, max);
        return max;
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ret = 0;
        long total = 0;
        for (int i = 0; i < costs.length; i++) {
            total += costs[i];
            if (total <= coins) {
                ret += 1;
            } else {
                break;
            }
        }
        return ret;
    }

    public double averageWaitingTime(int[][] customers) {
        long cur = 0;
        long totalWait = 0;
        for (int i = 0; i < customers.length; i++) {
            cur = Math.max(cur, customers[i][0]) + customers[i][1];
            totalWait += (cur - customers[i][0]);
        }
        return totalWait/(double)customers.length;
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        TreeMap<Character, Integer> tm = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            tm.put(s.charAt(i), tm.getOrDefault(s.charAt(i), 0) + 1);
        }
        char last = tm.lastKey();
        StringBuffer sb = new StringBuffer();
        char lastInsert = 0;
        System.out.println(tm);
        while (tm.get(last) > 0) {
            int curLimit = repeatLimit;
            if (last == lastInsert) {
                curLimit -= 1;
            }
            int curLen = Math.min(curLimit, tm.get(last));
            for (int i = 0; i < curLen; i++) {
                sb.append(last);
                tm.put(last, tm.get(last)-1);
                lastInsert = last;
            }
            if (tm.lowerEntry(last) != null) {
                sb.append(tm.lowerKey(last));
                tm.put(tm.lowerKey(last), tm.get(tm.lowerKey(last))-1);
                lastInsert = tm.lowerKey(last);
            } else {
                return sb.toString();
            }
            if (tm.get(tm.lowerKey(last)) == 0) {
                tm.remove(tm.lowerKey(last));
            }

            if (tm.get(last) == 0) {
                if (tm.lowerEntry(last) != null) {
                    last = tm.lowerKey(last);
                } else {
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    /*
    Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
    Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
    */

    public List<Integer> partitionLabels(String s) {
        int[] high = new int[26];
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            high[ss[i]-'a'] = Math.max(high[ss[i]-'a'], i);
        }
        int far = 0;
        int pre = 0;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < ss.length; i++) {
            far = Math.max(far, high[ss[i]-'a']);
            if (far == i) {
                ret.add(far-pre+1);
                pre = far+1;
            }
        }
        return ret;
    }



    public int countPaths(int n, int[][] roads) {
     
        return 0;
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o2[0], o1[0]);
            }
        });

        List<int[]> list = new ArrayList<>();
        int cur = people[0][0];
        for(int[] x : people) {
            if (x[0] == cur) {
                list.add(x);
            } else {
                list.add(x[1],x);
            }
        }
        int[][]ret = new int[people.length][2];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int m = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int pi = 0;
        for (int i = 0; i < m; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[pi]) {
                stack.pop();
                pi ++;
            }
        }
        return stack.isEmpty() && pi == m;
    }


    public int maxTurbulenceSize1(int[] A) {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
            if (result == 15) {
                System.out.println(i);
            }
        }
        return result;
    }

    public int maxTurbulenceSize(int[] arr) {

        int n = arr.length;
        if (n <= 1) {
            return n;
        }
        int[] maxLen = new int[n];
        int max = 1;
        maxLen[0] = 1;
        if (arr[0] != arr[1]) {
            maxLen[1] = 2;
            max = Math.max(max, 2);
        } else {
            maxLen[1] = 1;
        }

        for (int i = 2; i < n; i++) {
            if (arr[i] == arr[i-1]) {
                maxLen[i] = 1;
            } else {
                if ((long)(arr[i] - arr[i-1]) * (long)(arr[i-1] - arr[i-2]) < 0 ) {
                    maxLen[i] = maxLen[i-1] + 1;
                    max = Math.max(max, maxLen[i]);
                } else {
                    maxLen[i] = 2;
                }
            }
            System.out.println(i + "\t" + arr[i] + "\t"+maxLen[i]);
        }


     
        return max;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }

        TreeNode rightMin = root.right;
        while (rightMin.left != null) {
            rightMin = rightMin.left;
        }
        root.val = rightMin.val;

        if (root.right.val == root.val) {
            root.right = root.right.right;
            return root;
        }
        rightMin = root.right;
        while (rightMin.left.val != root.val) {
            rightMin = rightMin.left;
        }
        rightMin.left = rightMin.left.right;


        return root;
    }


    public String makeFancyString(String s) {
        char[] ss = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss.length; i++) {
            if (i >= 2 && ss[i] == ss[i-1] && ss[i] == ss[i-2]) {
                continue;
            } else {
                sb.append(ss[i]);
            }
        }

        
        return sb.toString();
    }


    public int maxChunksToSorted(int[] arr) {

        int i = 0;
        int curMax = -1;
        int ret = 0;
        for (; i < arr.length; i++) {
            curMax = Math.max(curMax, arr[i]);
            if (curMax == i) {
                ret += 1;
            }   
        }
        return ret;
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ret = new ArrayList<>();


        return ret;
    }




    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i-1] + stones[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        stoneGameVIIIHelper(stones, 1, preSum, map);
        return map.get(1);
    }

    public int stoneGameVIIIHelper(int[] stones, int start, int[] preSum, Map<Integer, Integer> map) {
        if (start == stones.length - 1) {
            map.put(start, preSum[stones.length -1]);
            return preSum[stones.length -1];
        }
        if (map.containsKey(start)) {
            return map.get(start);
        }
        int curMax = Integer.MIN_VALUE;
        for (int i = start; i < stones.length - 1; i++) {
            curMax = Math.max(curMax, preSum[i]-stoneGameVIIIHelper(stones, i+1, preSum, map));
        }
        curMax = Math.max(curMax, preSum[stones.length-1]);
        map.put(start, curMax);
        return curMax;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        int ridesLen = rides.length;
        Arrays.sort(rides, Comparator.comparingInt(o -> o[1]));
        int max = rides[ridesLen-1][1];
        long [] dp = new long[max+1];
        for (int i = 0; i < ridesLen; i++) {
            if (i > 0) {
                for (int j = rides[i-1][1]; j <= rides[i][1]; j++) {
                    dp[j] = dp[rides[i-1][1]];
                }
            }
            dp[rides[i][1]] = Math.max(dp[rides[i][1]], rides[i][1]-rides[i][0]+rides[i][2]+dp[rides[i][0]]);
        }
        return dp[max];
    }



    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');

        Stack<Character> stack = new Stack<>();
        char[] ss = s.toCharArray();
        for(char c: ss) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
     
        return 0;
    }

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ret = 1;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, root.val));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            if (p.getKey().left != null) {
                if (p.getKey().left.val >= (int)p.getValue()) {
                    ret += 1;
                }
                queue.add(new Pair<>(p.getKey().left, Math.max(p.getValue(), p.getKey().left.val)));
            }
            if (p.getKey().right != null) {
                if (p.getKey().right.val >= (int)p.getValue()) {
                    ret += 1;
                }
                queue.add(new Pair<>(p.getKey().right, Math.max(p.getValue(), p.getKey().right.val)));
            }
        }
        return ret;
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode root = new ListNode(-1);
        root.next = head;
        int idx = 1;
        ListNode pre = root;
        int preIdx = 0;
        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                if (idx == preIdx + 1) {
                    pre = head;
                } else {
                    pre.next = head.next;
                }
                preIdx = idx;
            }
            idx ++;
            head = head.next;
        }
        return root.next;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode tortoise = head;
        ListNode hare = head.next;
        while (tortoise != hare) {
            tortoise = (tortoise == null ? null : tortoise.next);
            if (hare != null && hare.next != null) {
                hare = hare.next.next;
            } else {
                hare = null;
            }
        }
        if (hare == null) {
            return false;
        }
        return true;
    }

    public int minimizedMaximum(int n, int[] quantities) {
        int l = 1, r = 100000;
        while (l < r) {
            int mid = (l + r) / 2;
            if (minimizedMaximumHelper(n, quantities, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean minimizedMaximumHelper(int n, int[]quantities, int max) {
        int total = 0;
        for (int i = 0; i < quantities.length; i++) {
            total += (int)Math.ceil(quantities[i]/(double)max);
        }
        return total <= n;
    }


    public Node connect1(Node root) {
        if (root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Node> list = new ArrayList<>();
            while(!queue.isEmpty()) {
                Node n = queue.poll();
                if (n.left != null) {
                    list.add(n.left);
                }
                if (n.right != null) {
                    list.add(n.right);
                }
                if (!queue.isEmpty()) {
                    n.next = queue.peek();
                }
            }
            queue.addAll(list);
        }
        return root;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] cups = new double[100][100];
        cups[0][0] = (double)poured;
        for (int i = 1; i < 100; i++) {
            if (i > query_row) {
                break;
            }
            for (int j = 0; j < 100; j++) {
                if (j > 0) {
                    cups[i][j] += Math.max(cups[i-1][j-1]-1,0)/2;
                }
                cups[i][j] += Math.max(cups[i-1][j]-1,0)/2;
            }
        }
        return Math.min(cups[query_row][query_glass], 1);
    }



    public boolean isSubsequence(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int j = 0;
        for (int i = 0; i < ss.length; i++) {
            char cur = ss[i];
            boolean found = false;
            for (; j < tt.length; j++) {
                if (tt[j] == cur) {
                    j++;
                    found = true;
                    break;
                }
            }
            if (!found && j == tt.length) {
                return false;
            }
        }


        return true;
    }

    public String longestDupSubstring(String s) {
        int n = s.length();
        int l = 1, r = n;

        String ret = "";
        while (l <= r) {
            int mid = (l+r)/2;
            String cur = longestDupSubstringHelper(s, mid);
            if (cur.length() > 0) {
                l = mid + 1;
                ret = cur;
            } else {
                r = mid - 1;
            }
        }
        return ret;
    }

    public String longestDupSubstringHelper(String s, int n) {
        long module = (long)1e9+7;
        long hash = 0;
        for (int i = 0; i < n; i++) {
            hash = (hash * 27  + (s.charAt(i) - 'a' + 1))%module;
        }
        long multi = 1;
        for (int i = 0; i < n - 1; i++) {
            multi = multi * 27 % module;
        }
        Map<Long, Set<String>> map = new HashMap<>();
        map.computeIfAbsent(hash, x -> new HashSet<>()).add(s.substring(0, n));
        int m = s.length();
        for (int i = n; i < m; i++) {
            long curIdx = s.charAt(i-n) - 'a' + 1;
            hash = ((hash + module * 27 - curIdx * multi) * 27 + (s.charAt(i)-'a')+1) % module;
            if (map.containsKey(hash) && map.get(hash).contains(s.substring(i-n+1, i+1))) {
                return s.substring(i-n+1, i+1);
            } else {
                map.computeIfAbsent(hash, x -> new HashSet<>()).add(s.substring(i-n+1, i+1));
            }
        }
        return "";
    }


    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int n = beans.length;
        long sum = 0;
        for (int x: beans) {
            sum += x;
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long cur = sum - (long)beans[i] * (long)(n-i);
            min = Math.min(min, cur);
            if (i > 0 && beans[i] == beans[i-1]) {
                continue;
            }
        }

        return min;
    }

    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        int base = 2;
        for (int i = 1; i <= n; i++) {
            if (i == base) {
                ans[i] = 1;
                base *= 2;
            } else if (i%2 == 1) {
                ans[i] = ans[i-1] + 1;
            } else {
                ans[i] = ans[i-base/2] + 1;
            }
        }
        return ans;
    }

    public int racecar(int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        racecarHelper(target, map);
        System.out.println(map);
        return map.get(target);
    }

    public int racecarHelper(int target, Map<Integer, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }

        int speed = 1;
        int pos = 0;
        int cnt = 0;
        while(pos < target) {
            pos += speed;
            speed *= 2;
            cnt += 1;
        }
        if (pos == target) {
            map.put(target, cnt);
            return cnt;
        }
        System.out.println(target + "\t" + pos + "\t" + (target - pos + speed/2) + "\t" + cnt + "\t" + speed);
        // int left = target - pos + speed/2;
        // int right = pos;
        // int steps = Integer.MAX_VALUE;
        // for (int i = left; i <= right; i++) {
            
        // }

        int steps = Math.min(cnt + 1 + racecarHelper(pos-target, map), 
                             cnt + 1 + racecarHelper(target - pos + speed/2, map));
        map.put(target, steps);
        return steps;
    }


    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return ret;
        }
        String begin = nums[0] + "->";
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i-1] != 1) {
                ret.add(begin+nums[i-1]);
                begin = nums[i] + "->";
            }
        }
        ret.add(begin+nums[n-1]);
        for (int i = 0; i < ret.size(); i++) {
            String[] arr = ret.get(i).split("->");
            if(Integer.parseInt(arr[0]) == Integer.parseInt(arr[1])) {
                ret.set(i, arr[0]);
            }
        }

        return ret;
    }

    public ListNode sortList(ListNode head) {
        
        return null;
    }

    public void test(Set<Integer> set, int n, int status) {
        if (set.contains(status)) {
            y+=1;
            return;
        }
        x += 1;
        for (int i = 1; i <= n; i++) {
            if (((status >> i) & 1) == 0) {
                test(set, n, status+(1<<i));
            }
        }
        set.add(status);
    }


    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n];
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? nums[i] : nums[i] + sum[i-1]);
        }
        Arrays.fill(ret, -1);
        for (int i = 0; i < n; i++) {
            if (i >= 2*k) {
                ret[i-k] = (int)((sum[i]-sum[i-2*k]+nums[i-2*k])/(2*k+1));
            }
        }
        return ret;
    }

    public int consecutiveNumbersSum(int n) {
        int ret = 0;
        for (int i = 1; i < 50000; i++) {
            int sum = (1+i)*i/2;
            if (sum > n) {
                break;
            }
            if (n >= sum && (n - sum)%i==0) {
                ret += 1;
            }
        }
        return ret;
    }
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    */
    // public List<List<Integer>> levelOrder(Node root) {
    //     Queue<Node> queue = new LinkedList<>();
    //     queue.add(root);
    //     List<List<Integer>> ret = new ArrayList<>();
    //     if (root == null) {
    //         return ret;
    //     }

    //     while (!queue.isEmpty()) {
    //         Queue<Node> tmp = new LinkedList<>();
    //         List<Integer> level = new ArrayList<>();
    //         while (!queue.isEmpty()) {
    //             Node cur = queue.poll();
    //             level.add(cur.val);
    //             for (Node c: cur.children) {
    //                 tmp.add(c);
    //             }
    //         }
    //         ret.add(level);
    //         queue = tmp;
    //     }

    //     return ret;
    // }

    public static int getAdjustCnt (int cnt) {
		float floatCnt = cnt/10f;
		int floorCnt = (int)floatCnt;

		double r = Math.random();
		if (floatCnt - floorCnt > r) {
			floorCnt ++;
		} 
		return floorCnt;
	}

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        int i1 = 0, i2 = 0;
        while (i1 < m || i2 < n) {
            int n1 = i1 < m ? nums1[i1] : Integer.MIN_VALUE;
            int n2 = i2 < n ? nums2[i2] : Integer.MIN_VALUE;
            Stack<Pair<Integer, Integer>> tmp = new Stack<>();
            if (n1 >= n2) {
                while (!stack.isEmpty() && stack.peek().getKey() < n1 
                 && (stack.size() + m - i1 + n - i2 > k || stack.peek().getValue() == 2)) {
                    Pair<Integer, Integer> p = stack.pop();
                    if(p.getValue() == 2) {
                        tmp.push(p);
                    }
                }
                stack.push(new Pair<>(n1, 1));
                i1 ++;
            } else {
                while (!stack.isEmpty() && stack.peek().getKey() < n2
                 && (stack.size() + m - i1 + n - i2 > k || stack.peek().getValue() == 1)) {
                    Pair<Integer, Integer> p = stack.pop();
                    if(p.getValue() == 1) {
                        tmp.push(p);
                    }        
                }
                stack.push(new Pair<>(n2, 2));
                i2 ++;
            }
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }
        }
        Object[] arr = stack.toArray();
        int[] ret = new int[arr.length];
        for (int i = 0; i < k; i++) {
            // ret[i] = Integer.parseInt(arr[i].toString());
            ret[i] = ((Pair<Integer, Integer>)arr[i]).getKey();
        }
        return ret;
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if ((1+maxChoosableInteger) * maxChoosableInteger/2 < desiredTotal) {
            return false;
        }

        Map<Integer, Boolean> map = new HashMap<>();
        canIWinHelper(maxChoosableInteger, desiredTotal, 0, 0, map);
        return map.get(0);
    }
    public boolean canIWinHelper(int maxChoosableInteger, int desiredTotal, int curStatus, int curSum,  Map<Integer, Boolean> map) {
        if (map.containsKey(curStatus)) {
            return map.get(curStatus);
        }
        for (int i = 1; i <= maxChoosableInteger; i ++) {
            if (((curStatus >> i) & 1) == 0) {
                if (curSum +i >= desiredTotal || !canIWinHelper(maxChoosableInteger, desiredTotal, curStatus+(1<<i), curSum+i, map)) {
                    map.put(curStatus, true);
                    return true;
                }
            }
        }
        map.put(curStatus, false);
        return false;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] sum = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                sum[i] = nums[i];
            } else {
                sum[i] = nums[i] + sum[i-1];
            }
            ret += map.getOrDefault(sum[i] - goal, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
        }
     
        return ret;
    }

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> ret = new ArrayList<>();
        UnionFind uf = new UnionFind(n+1);
        for (int i = threshold+1; i <= n; i++) {
            int j = 1;
            while (i * j <= n) {
                uf.union(i, i*j);
                j++;
            }
        }
        for(int [] x: queries){
            if (uf.find(x[0]) == uf.find(x[1])){
                ret.add(true);
            } else {
                ret.add(false);
            }
        } 
        return ret;
    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int x : nums) {
            map.put(x, map.getOrDefault(x, 0)+1);
        }
        int max = 0;
        for(int x: map.keySet()) {
            if (map.containsKey(x+1)) {
                max = Math.max(max, map.get(x) + map.get(x+1));
            }
            if (map.containsKey(x-1)) {
                max = Math.max(max, map.get(x) + map.get(x-1));
            }
        }
     
        return max;
    }

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        long modulo = (long)1e9+7;
        long i1 = findGoodStringsHelper(s1);
        long i2 = findGoodStringsHelper(s2);
        if (evil.length() > s1.length()){
            return (int)((i2-i1+1+modulo)%modulo);
        } 

        if (evil.length() == s1.length()) {
            if (s1.compareTo(evil) <= 0 && evil.compareTo(s2) <= 0) {
                return (int)((i2-i1+modulo)%modulo);
            } else {
                return (int)((i2-i1+1+modulo)%modulo);
            }
        }
        long total = (i2 + modulo - i1) % modulo;
        int sLen = s1.length();
        int eLen = evil.length();


        for (int i = 0; i <= sLen - eLen; i++) {

        }

        
        return (int)total;
    }

    public long findGoodStringsHelper(String s) {
        long modulo = (long)1e9+7;
        int n = s.length();
        char[] ss = s.toCharArray();
        long mul = 1;
        long ret = 0;
        for (int i = n-1; i >= 0; i--) {
            long cur = ss[i] - 'a';
            ret = (ret + cur * mul) % modulo;
            mul *= 26;
            mul %= modulo;
        }

        return ret;
    }

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int ret = 0;
        int rowDiff = 0;
        int colDiff = 0;
        if (homePos[0] > startPos[0]) {
            rowDiff = -1;
        } else {
            rowDiff = 1;
        }

        if (homePos[1] > startPos[1]) {
            colDiff = -1;
        } else {
            colDiff = 1;
        }

        while (homePos[0] != startPos[0]) {
            ret += rowCosts[homePos[0]];
            homePos[0] += rowDiff;
        }
        while (homePos[1] != startPos[1]) {
            ret += colCosts[homePos[1]];
            homePos[1] += colDiff;
        }
        return ret;
    }


    public boolean checkInclusion(Map<Character, Integer> m1, Map<Character, Integer> m2){
        if (m1.size() != m2.size()) {
            return false;
        }
        for (char key: m1.keySet()) {
            if (!m1.get(key).equals(m2.getOrDefault(key, 0))) {
                return false;
            }
        }
        return true;
    }
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s2Len < s1Len) {
            return false;
        }
        Map<Character, Integer> m1 = new HashMap<>();
        for (int i = 0; i < s1Len; i++) {
            char c = s1.charAt(i);
            m1.put(c, m1.getOrDefault(c, 0)+1);
        }
        Map<Character, Integer> m2 = new HashMap<>();
        for (int i = 0; i < s1Len; i++) {
            char c = s2.charAt(i);
            m2.put(c, m2.getOrDefault(c, 0)+1);
        }
        if (checkInclusion(m1, m2)) {
            return true;
        }
        System.out.println(m1);
        System.out.println(m2);
        for (int i = s1Len; i < s2Len; i++) {
            char c = s2.charAt(i);
            m2.put(c, m2.getOrDefault(c, 0)+1);
            char before = s2.charAt(i-s1Len);
            m2.put(before, m2.get(before)-1);
            if (m2.get(before) == 0) {
                m2.remove(before);
            }
            if (checkInclusion(m1, m2)) {
                return true;
            }
        }


        return false;
    }

    public int countPyramids(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    rowSum[i][j] = grid[i][j];
                } else {
                    rowSum[i][j] = grid[i][j] + rowSum[i][j-1];
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int k = i+1; k < m; k++) {
                    int left = j-(k-i);
                    int right = j+(k-i);
                    if (left >= 0 && right < n) {
                        if (rowSum[k][right] - (left > 0 ? rowSum[k][left-1] : 0) == 2*(k-i) + 1) {
                            ret += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                for (int k = i-1; k >= 0; k--) {
                    int left = j-(i-k);
                    int right = j+(i-k);
                    if (left >= 0 && right < n) {
                        if (rowSum[k][right] - (left > 0 ? rowSum[k][left-1] : 0) == 2*(i-k) + 1) {
                            ret += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        
        return ret;
    }
    

    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Set<Integer> n = new HashSet<>();
        Set<Integer> mid = new HashSet<>();

        for (int x : nums) {
            if (n.contains(x-k)) {
                mid.add(2*x-k);
            } 
            if (n.contains(x+k)) {
                mid.add(2*x+k);
            }
            n.add(x);
        }
        return mid.size();
    }

    public int catMouseGame(int[][] graph) {
        Set<Integer> catZone = new HashSet<>();
        catZone.add(2);
        for (int i = 0; i < graph[2].length; i++) {
            catZone.add(graph[2][i]);
        }

        Set<Integer> mouseZone = new HashSet<>();
        Set<Integer> mouseWin = new HashSet<>();
        for (int i = 0; i < graph[1].length; i++) {
            mouseZone.add(graph[1][i]);
            for (int j = 0; j < graph[graph[1][i]].length; j++) {
                if (graph[graph[1][i]][j] == 0) {
                    mouseWin.add(graph[1][i]);
                    break;
                }
            }
        }
        // 直接进洞
        if (mouseZone.contains(0)) {
            return 1;
        }
        // 两步进洞
        for (int x: mouseWin) {
            if (!catZone.contains(x)) {
                return 1;
            }
        }


        return 0;
    }

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x: nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        List<Integer> ret = new ArrayList<>();
        int n3 = nums.length/3;
        for(int key : map.keySet()) {
            if (map.get(key) > n3) {
                ret.add(key);
            }
        }
        return ret;
    }

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x: deck) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        for (int key: map.keySet()) {
            min = Math.min(min, map.get(key));
        }
        
        if (min == 1) {
            return false;
        }
        System.out.println(map);
        System.out.println(min);
        for (int i = 2; i <= min; i++) {
            boolean flag = true;
            for (int key: map.keySet()) {
                if (map.get(key) % i != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
     
        return false;
    }


    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        TreeMap<Integer, Integer> rMap = new TreeMap<>((x1, x2) -> (x2-x1));
        int max = 1;
        map.put(nums[0], 1);
        rMap.put(nums[0], 1);
        int preIdx = 0;
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            cnt ++;
            rMap.put(nums[i], rMap.getOrDefault(nums[i], 0) + 1);
            while (rMap.firstKey() - map.firstKey() > limit) {
                map.put(nums[preIdx], map.get(nums[preIdx]) - 1);
                cnt --;
                rMap.put(nums[preIdx], rMap.get(nums[preIdx]) - 1);
                if (map.get(nums[preIdx]) == 0) {
                    map.remove(nums[preIdx]);
                }
                if (rMap.get(nums[preIdx]) == 0) {
                    rMap.remove(nums[preIdx]);
                }
                preIdx ++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

    public boolean xorGame(int[] nums) {
        int len = nums.length;
        int xor = 0;
        for (int x : nums) {
            xor ^= x;
        }
        return xor == 0 || len % 2 == 0;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        
        ListNode root = new ListNode();
        ListNode p = root;
        boolean hasValue = true;
        int len = lists.length;

        while (hasValue) {
            int cnt = 0;
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < len; i++) {
                if(lists[i] != null) {
                    cnt += 1;
                    if (lists[i].val < min) {
                        min = lists[i].val;
                        minIdx = i;
                    }
                }
            }
            if (cnt == 0) {
                hasValue = false;
            } else {
                p.next = new ListNode(min);
                p = p.next;
                lists[minIdx] = lists[minIdx].next;
            }
        }

        return root.next;
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        TreeMap<Integer, List<Pair<Integer, Integer>>> tm = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tm.computeIfAbsent(matrix[i][j], x -> new ArrayList<>()).add(new Pair<>(i, j));
            }
        }
        int[][] ret = new int[m][n];
        int[] rank = new int[m + n]; // 这一步很巧妙

        for (int k : tm.keySet()) {
            UnionFind uf = new UnionFind(m + n);
            for (Pair<Integer, Integer> p : tm.get(k)) {
                uf.union(p.getKey(), p.getValue() + m);
            }
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (Pair<Integer, Integer> p : tm.get(k)) {
                int root = uf.find(p.getKey());
                groups.computeIfAbsent(root, x -> new ArrayList<>()).add(p.getKey());
                groups.computeIfAbsent(root, x -> new ArrayList<>()).add(m+p.getValue());
            }
            for (int root : groups.keySet()) {
                int curMax = 0;
                for (int x : groups.get(root)) {
                    curMax = Math.max(curMax, rank[x]);
                }
                for (int x : groups.get(root)) {
                    rank[x] = curMax + 1;
                }
            }
            for (Pair<Integer, Integer> p : tm.get(k)) {
                ret[p.getKey()][p.getValue()] = rank[p.getKey()];
            }
        }
        return ret;
    }


    // public int findMaxLength(int[] nums) {
    //     int n = nums.length;
    //     for (int i = 0; i < n; i++) {
    //         if (nums[i] == 0) {
    //             nums[i] = -1;
    //         }
    //     }
    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(0, -1);
    //     int maxLen = 0;
    //     int curSum = 0;
    //     for (int i = 0; i < n; i++) {
    //         curSum += nums[i];
    //         if (!map.containsKey(curSum)) {
    //             map.put(curSum, i);
    //         } else {
    //             maxLen = Math.max(maxLen, i - map.get(curSum));
    //         }
    //     }
    //     return maxLen;
    // }

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        double [][][] prob = new double[k][n][n];
        int [][] adj = {{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1}};
        double eight = 1/8d;
        for (int i = 0; i < k; i++) {
            for (int m = 0; m < n; m++) {
                for (int j = 0; j < n; j++) {
                    double inProb = 0;
                    for(int[] a: adj) {
                        int nm = m + a[0];
                        int nj = j + a[1];
                        if (nm < n && nm >=0 && nj < n && nj >=0) {
                            if (i > 0) {
                                inProb += eight * prob[i-1][nm][nj];
                            } else {
                                inProb += eight;
                            }
                        }
                    }
                    prob[i][m][j] = inProb;
                }
            }
        }
        return prob[k-1][row][column];
    }

    public int maximumDetonation(int[][] bombs) {
        int len = bombs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                long x = bombs[i][0] - bombs[j][0];
                long y = bombs[i][1] - bombs[j][1];
                long r1 = (long)bombs[i][2];
                long r2 = (long)bombs[j][2];
                long dis = x*x + y*y;
                if (dis <= r1*r1) {
                    map.computeIfAbsent(i, xxx -> new ArrayList<>()).add(j);
                }
                if (dis <= r2*r2) {
                    map.computeIfAbsent(j, xxx -> new ArrayList<>()).add(i);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(i);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int cnt = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                cnt += 1;
                List<Integer> adj = map.getOrDefault(cur, new ArrayList<>());
                for(int x : adj) {
                    if(!set.contains(x)) {
                        queue.add(x);
                        set.add(x);
                    }
                }
                
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for(String s : dictionary) {
            trie.insert(s);
        }
 

        StringBuffer sb = new StringBuffer();
        String[] ss = sentence.split(" ");
        int i = 0;
        for (String s : ss) {
            if (i > 0) {
                sb.append(" ");
            }
            i ++ ;
            int idx = trie.findPrefix(s);
            System.out.println(s+"\t"+idx);
            if (idx >= 0) {
                sb.append(s.substring(0,idx+1));
            } else {
                sb.append(s);
            }
        }

        return sb.toString();
    }

    public int findInMountainArray(int target, List<Integer> mountainArr) {
        int n = mountainArr.size();
        // first find mountain peak
        int begin = 0, end = n - 1;
        while (begin < end) {
            int mid1 = (begin + end)/2;
            int mid2 = (mid1 + end)/2;

            int r1 = mountainArr.get(mid1);
            int r2 = mountainArr.get(mid2);
            if (r1 > r2) {
                end = mid2 - 1;
            } else if (r1 < r2) {
                begin = mid1 + 1;
            } else {
                int r3 = mountainArr.get(end);
                if (r3 > r2) {
                    begin = end;
                } else {
                    begin = mid1;
                    end = mid2;
                }
            }
        }
        // begin is the mountain peak
        int l = 0, r = begin;
        while (l <= r) {
            int m = (l + r)/2;
            int cur = mountainArr.get(m);
            if (cur < target) {
                l = m + 1;
            } else if (cur > target) {
                r = m -1;
            } else {
                return m;
            }
        }

        l = begin;
        r = n - 1;
        while (l <= r) {
            int m = (l+r)/2;
            int cur = mountainArr.get(m);
            if (cur < target) {
                r = m - 1;
            } else if (cur > target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public int minimumSwap(String s1, String s2) {
        char[]ss1 = s1.toCharArray();
        char[]ss2 = s2.toCharArray();
        int n = ss1.length;
        int xDiff = 0;
        int yDiff = 0;
        for (int i = 0; i < n; i++) {
            if (ss1[i] != ss2[i]) {
                if (ss1[i] == 'x') {
                    xDiff += 1;
                } else {
                    yDiff += 1;
                }
            }
        }
        int ret = 0;
        ret += xDiff/2;
        ret += yDiff/2;
        xDiff %= 2;
        yDiff %= 2;
        if (xDiff == 0 && yDiff == 0) {
            return ret;
        } else if (xDiff == 1 && yDiff == 1) {
            return ret + 2;
        } else {
            return -1;
        }
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = nums1[i] + nums2[j];
                map.put(cur, map.getOrDefault(cur, 0)+1);
            }
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = nums3[i] + nums4[j];
                ret += map.getOrDefault(-cur, 0);
            }
        }
     
        return ret;
    }

    public boolean isValidSerializationHelper(Stack<String> stack, String s) {
        if (s.equals("#") && !stack.isEmpty() && stack.peek().equals("#")) {
            stack.pop();
            if (stack.isEmpty() || stack.peek().equals("#")) {
                return false;
            } else {
                stack.pop();
                if (!isValidSerializationHelper(stack, "#")) {
                    return false;
                }
            }
        } else {
            stack.push(s);
        }
        return true;
    }

    public boolean isValidSerialization1(String preorder) {
        String[] pre = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (String s : pre) {
            if (!isValidSerializationHelper(stack, s)) {
                return false;
            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }

    public boolean isValidSerialization(String preorder) {
        String[] s = preorder.split(",");
        int len = s.length;
        int[][] isValid = new int[len][len]; // []闭区间, 0:未访问,1:true,2:false
        boolean ret = isValidSerialization(0, len-1, s, isValid);
        // for(int [] x: isValid) {
        //     System.out.println(Arrays.toString(x));
        // }
        return ret;
    }

    public boolean isValidSerialization(int s, int e, String[]pre, int[][] isValid) {
        if (isValid[s][e] != 0) {
            return isValid[s][e] == 1;
        }
        if (s==e) {
            if (pre[s].equals("#")) {
                isValid[s][e] = 1;
                return true;
            } else {
                isValid[s][e] = 2;
                return false;
            }
        } 
        if (e - s == 1 || pre[s].equals("#")) {
            isValid[s][e] = 2;
            return false;          
        }

        boolean ret = false;
        for (int i = s+1; i < e; i++) {
            if (isValidSerialization(s+1,i,pre,isValid) && isValidSerialization(i+1,e,pre,isValid)) {
                ret = true;
                break;
            }
        }
        isValid[s][e] = ret ? 1 : 2;
        return ret;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        long [] cnt = new long[10001];
        long [] sum = new long[10001];
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        map.put(root, 0);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cnt[map.get(cur)] ++;
            sum[map.get(cur)] += cur.val;

            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, map.get(cur)+1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, map.get(cur)+1);
            }
        }
        for (int i = 0; i < 10001; i++) {
            if (cnt[i] > 0) {
                ret.add(sum[i]/(double)cnt[i]);
            }
        }
        return ret;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int pLen = p.length();
        int sLen = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Integer> ret = new ArrayList<>();
        Map<Character, Integer> cur = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            cur.put(c, cur.getOrDefault(c, 0) + 1);
            if (i >= pLen) {
                char de = s.charAt(i-pLen);
                cur.put(de, cur.get(de)-1);
            }
            boolean isMatch = true;
            for (char cc: map.keySet()) {
                if (!map.get(cc).equals(cur.getOrDefault(cc, 0))) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                ret.add(i-pLen+1);
            }
        }
        return ret;
    }

    public int findMaximumXOR(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ret = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | 1 << i;
            set.clear();
            for (int x : nums) {
                set.add(x & mask);
            }
            int e = ret | 1 << i;
            for (int x : set) {
                if (set.contains(e ^ x)) {
                    ret = e;
                    break;
                }
            }
        }
        return ret;
    }

    public String shortestPalindrome(String s) {
    
    
        return "";
    }

    List<Integer> inOrder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = inOrder(root1);
        List<Integer> l2 = inOrder(root2);

        List<Integer> ret = new ArrayList<>();
        int i = 0; 
        int j = 0;
        int len1 = l1.size();
        int len2 = l2.size();
        while (i < len1 || j < len2) {
            if (i >= len1) {
                ret.add(l2.get(j));
                j++;
            } else if (j >= len2) {
                ret.add(l1.get(i));
                i++;
            } else {
                if (l1.get(i) <= l2.get(j)) {
                    ret.add(l1.get(i));
                    i++;
                } else {
                    ret.add(l2.get(j));
                    j++;
                }
            }
        }
        return ret;
    }

    public boolean validMountainArray(int[] arr) {
        int maxIdx = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }

        if (maxIdx == 0 || maxIdx == arr.length - 1) {
            return false;
        }
        for (int i = 0; i < maxIdx; i++) {
            if (arr[i] >= arr[i+1]) {
                return false;
            }
        }

        for (int i = maxIdx; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i+1]) {
                return false;
            } 
        }

        return true;
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ret = new ArrayList<>();
        int lowLen = String.valueOf(low).length();
        int hightLen = String.valueOf(high).length();

        for (int i = lowLen; i <= hightLen; i++) {
            for (int j = 1; j <= 10 - i; j++) {
                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < i; k++) {
                    sb.append(j+k);
                }
                if (sb.length() > 0){
                    int cur = Integer.parseInt(sb.toString());
                    if (cur <= high && cur >= low) {
                        ret.add(cur);
                    }
                }
            }
        }
        return ret;
    }
    
    public int findIndex1(int[] remain) {
        int n = remain.length;
        int curGas = 0;
        int[] minGasArr = new int[n];
        int minGas = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            curGas += remain[i];
            minGas = Math.min(minGas, curGas);
            minGasArr[i] = minGas;
        }
        if (minGas >= 0) {
            return 0;
        }

        int sumReamin = 0;
        for (int i = n - 1; i > 0; i--) {
            sumReamin += remain[i];
            if (sumReamin > 0 && sumReamin + minGasArr[i-1] >= 0) {
                return i;
            }
        }
        return -1;      
    }

    public int findIndex(int[]remain) {
        int n = remain.length;
        int[] preSum = new int[n];
        preSum[0] = remain[0];
        int min = preSum[0];
        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i-1] + remain[i];
            if (preSum[i] < min) {
                min = preSum[i];
                minIdx = i;
            }
        }
        if (preSum[n-1] < 0) {
            return -1;
        }
        return (minIdx + 1) % n;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            sumCost += cost[i];
        }
        for (int i = 0; i < n; i++) {
            sumGas += gas[i];
        }
        if (sumGas < sumCost) {
            return -1;
        }

        int[] remain = new int[n];
        for (int i = 0; i < n; i++) {
            remain[i] = gas[i] - cost[i];
        }

        // first from index 0 
        int curGas = 0;
        int[] minGasArr = new int[n];
        int minGas = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            curGas += remain[i];
            minGas = Math.min(minGas, curGas);
            minGasArr[i] = minGas;
        }
        if (minGas >= 0) {
            return 0;
        }

        int sumReamin = 0;
        for (int i = n - 1; i > 0; i--) {
            sumReamin += remain[i];
            if (sumReamin > 0 && sumReamin + minGasArr[i-1] >= 0) {
                return i;
            }
        }
     
        return -1;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1; 
        int r = Integer.MAX_VALUE;

        while (l < r) {
            int mid = l + (r-l)/2;
            if (minEatingSpeedHelper(piles, h, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } 
        return r;
    }

    public boolean minEatingSpeedHelper(int[] piles, int h, int k) {
        int total = 0;
        for (int i = 0; i < piles.length; i++) {
            total += (piles[i]/k + (piles[i]%k == 0 ? 0 : 1));
        }
        return total <= h;
    }

    public Pair<Integer, Integer> detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode tortoise = head;
        ListNode hare = head.next;
        while (tortoise != hare) {
            tortoise = (tortoise == null ? null : tortoise.next);
            if (hare != null && hare.next != null) {
                hare = hare.next.next;
            } else {
                hare = null;
            }
        }
        if (hare == null) {
            return null;
        }
        /* 
        * 这里做下基本解释
        * 对于torties， 停留在未知i， 则hare停留在1+2i
        * 由于两者相遇，所以1+2i=i+kλ => 1+i=kλ
        * 设环起始位置为μ, 则hare再多走1+μ, 即 1+2i+1+μ=2(1+i)+μ=2kλ+μ
        * 所以hare回到环开始未知, torties从0出发，走μ也到起始位置相遇
        * firstIdx 返回环起始位置idx(0-indexed)
        * lam 则返回环的长度
        */
        tortoise = head;
        hare = hare.next;
        int firIdx = 0;
        while (tortoise != hare) {
            tortoise = tortoise.next;
            hare = hare.next;
            firIdx += 1;
        }

        int lam = 1;
        hare = hare.next;
        while (tortoise != hare) {
            hare = hare.next;
            lam += 1;
        }
        return new Pair<>(firIdx, lam);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int ret = 0;
        int preIdx = -2;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                ret += ((i - preIdx - 2) / 2);
                preIdx = i;
            }
        }
        ret += (flowerbed.length - preIdx - 1) / 2;
        return ret >= n ;
    }

    public int canPlaceFlowers1(int[] flowerbed, int n) {
        int ret = 0;
        int l = flowerbed.length;
        for (int i = 0; i < l; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == l-1 || flowerbed[i+1] == 0)) {
                ret += 1;
                flowerbed[i] = 1;
            }
        }
        return ret;
    }

    public boolean wordPattern(String pattern, String s) {
        char[] p = pattern.toCharArray();
        String[] ss = s.split(" ");

        if (p.length != ss.length) {
            return false;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        Map<String, Integer> sMap = new HashMap<>();
        int [] idx = new int[300];
        for (int i = 0; i < p.length; i++) {
            if (!charMap.containsKey(p[i])) {
                idx[i] = i;
                charMap.put(p[i], i);
                if (sMap.containsKey(ss[i])) {
                    return false;
                }
                sMap.put(ss[i], i);
                
            } else {
                idx[i] = charMap.get(p[i]);
                if (!ss[i].equals(ss[idx[i]])) {
                    return false;
                }
            }
        }
        return true;
    }  

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int min = Integer.MAX_VALUE;
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] <= min) {
                min = Math.min(min, points[i][1]);
            } else {
                ret += 1;
                min = points[i][1];
            }
        }
        return ret + 1;
    }

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] minHeight = new int[n];
        for (int i = 0; i < n; i++) {            
            int cultiThick = books[i][0];
            int curMaxHeight = books[i][1];
            minHeight[i] = i > 0 ? (minHeight[i-1] + books[i][1]) : books[i][1];
            for (int j = i-1; j >= -1; j--) {
                cultiThick += (j > 0 ? books[j][0] : 0);
                if (cultiThick <= shelfWidth) {
                    curMaxHeight = Math.max(curMaxHeight, j > 0 ? books[j][1] : 0);
                    minHeight[i] = Math.min(minHeight[i], j >= 0 ? (minHeight[j] + curMaxHeight) : curMaxHeight);
                }
            }
        }
        return minHeight[n-1];
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                root.left =  insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                root.right =  insertIntoBST(root.right, val);
            }
        }
        return root;
    }

    /*
    Given two binary strings a and b, return their sum as a binary string.
    Example 1:

    Input: a = "11", b = "1"
    Output: "100"
    Example 2:

    Input: a = "1010", b = "1011"
    Output: "10101"
    
    */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int pre = 0;

        while(i >= 0 || j >= 0) {
            int cur = pre;
            if (i >= 0) {
                cur += (a.charAt(i) - '0');
            }
            if (j >= 0) {
                cur += (b.charAt(j) - '0');
            }
            sb.append(cur%2);
            if (cur >= 2) {
                pre = 1;
            } else {
                pre = 0;
            }
            i--;
            j--;
        }
        if (pre > 0) {
            sb.append(1);
        }
        return sb.reverse().toString();
    } 



    public int longestDecomposition(String text) {
        if (text.length() <= 1) {
            return text.length();
        }
        int n = text.length();
        int module = (int)1e9+7;
        char[] s = text.toCharArray();
        long h = 0;
        long t = 0;
        for (int i = 0; i < n/2; i++) {
            h = (h * 26 + (s[i]-'a')) % module;
            t = (t + (s[n-i-1]-'a') * (long)fastPower(26, i, module))%module;
            if (h == t) {
                return 2 + longestDecomposition(text.substring(i+1, n-i-1));
            }
        }
        return 1;
    }

    public int[] stringToArr(String s) {
        String [] x = s.substring(1,s.length()-1).split(", ");
        int [] ret = new int[x.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(x[i]);
        }
        return ret;
    }

    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        List<String> list = new ArrayList<>();
        String cellsString = Arrays.toString(cells);
        while(!map.containsKey(cellsString)) {
            map.put(cellsString, idx);
            list.add(cellsString);
            idx ++;
            int [] cur = new int[8];
            cur[0] = 0;
            cur[7] = 0;
            for (int i = 1; i < 7; i++) {
                if (cells[i-1] == cells[i+1]){
                    cur[i] = 1;
                } else {
                    cur[i] = 0;
                }
            }
            cells = cur;
            cellsString = Arrays.toString(cells);
        }
        int beforIdx = map.get(Arrays.toString(cells));
        int interval = idx - beforIdx;


        if (n < idx) {
            return stringToArr(list.get(n));
        } else {
            int c = (n-beforIdx)/interval;
            n -= interval*c;
            return stringToArr(list.get(n));
        }
    }

    public boolean isRobotBounded(String instructions) {
        int [][] adj = {{0,1}, {-1,0}, {0,-1}, {1,0}};
        int adjIdx = 0;
        int x = 0;
        int y = 0;
        for (char c : instructions.toCharArray()) {
            switch (c) {
                case 'G':
                    x += adj[adjIdx][0];
                    y += adj[adjIdx][1];
                    break;
                case 'L':
                    adjIdx = (adjIdx + 1) % 4;
                    break;
                case 'R':
                    adjIdx = (adjIdx + 4 - 1) % 4;
                    break;
            }
        }
        if ((x == 0 && y == 0) || adjIdx != 0) {
            return true;
        }     
        return false;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int [] arr = new int[1001];
        for(int[] x: trips) {
            arr[x[1]]+= x[0];
            arr[x[2]] -= x[0];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i > 0 ? arr[i-1] + arr[i] : arr[i];
            if (arr[i] > capacity) {
                return false;
            }
        }

        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        if (s.length() == 0) {
            List<String> cur = new ArrayList<>();
            ret.add(cur);
            return ret;
        }
        if (s.length() == 1) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            ret.add(cur);
            return ret;
        }

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (isPalindrome(prefix)) {
                List<List<String>> subRes = partition(s.substring(i));
                for (List<String> l : subRes) {
                    l.add(0, prefix);
                    ret.add(l);
                }
            }
        }
        return ret;
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

    public Map<Integer,Integer> smallestRepunitDivByKHelper(int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int cur = (k * i) % 10;
            if (!map.containsKey(cur)) {
                map.put(cur, i);
            }
        }
        return map;
    }

    public int smallestRepunitDivByK(int k) {
        if (k %2 == 0 || k % 10 == 5) {
            return -1;
        }
        int tail = k % 10;
        Map<Integer, Integer> map = smallestRepunitDivByKHelper(tail);
        List<Integer> kList = new ArrayList<>();
        int tmp = k;
        while(tmp > 0) {
            kList.add(tmp%10);
            tmp/=10;
        }
        int [] res = new int[1000000];
        int idx = 0;
        List<Integer> mult = new ArrayList<>();
        int len = 0;
        while (true) {
            int cur = map.get((11-res[idx])%10);
            int curRes = cur * k;
            int i = idx;
            int pre = 0;
            //System.out.println(curRes);
            while(curRes > 0) {
                len  = i;
                res[i] += (curRes % 10);
                res[i] += pre;
                if (res[i] >= 10) {
                    res[i]%=10;
                    pre = 1;
                } else {
                    pre = 0;
                }
                i++;
                curRes/=10;
            }
            if (pre > 0) {
                res[i] += 1;
                len = i;
            }
            mult.add(cur);
            // System.out.println(idx + "\n" + mult);
            // System.out.println(Arrays.toString(res));

            boolean f = true;
            for (int j = idx; j <= len; j++) {
                if (res[j]!=1){
                    f = false;
                }
            }
            if (f) {
                return len+1;
            }
            idx++;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            list.add(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        int idx = 0;
        int level = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (i == idx) {
                level += 1;
                idx += Math.pow(2, level);
            } else {
                list.get(i).next = list.get(i+1);
            }
        }
        return root;
    }

    public int findComplement(int num) {
        return num ^ ((Integer.highestOneBit(num) << 1) - 1);
    }

    public ListNode middleNode(ListNode head) {
        ListNode h = head;
        int len = 0;
        while(h != null) {
            len += 1;
            h = h.next;
        }
        int mid = len/2;
        while (mid > 0) {
            head = head.next;
            mid --;
        }
        return head;
    }

    public Set<Integer> scoreOfStudentsHelper1(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        Set<Integer> set = new HashSet<>();
        if (!s.contains("+") && !s.contains("*")) {
            set.add(Integer.parseInt(s));
        } else if (!s.contains("+") || !s.contains("*")) {
            set.add(scoreOfStudentsHelper(s, true));
        } else {
            List<Integer> idx = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '*') {
                    idx.add(i);
                }
            }
            for (int i = 0; i < idx.size(); i++) {
                String prefix;
                String sufix;
                int pre;
                int su;
                if (i == 0) {
                    prefix = "";
                    pre = -1;
                } else {
                    prefix = s.substring(0, idx.get(i-1)+1);
                    pre = idx.get(i-1);
                }
                if (i == idx.size() - 1) {
                    sufix = "";
                    su = s.length();
                } else {
                    sufix = s.substring(idx.get(i+1));
                    su = idx.get(i+1);
                }
                int cur;
                if (s.charAt(idx.get(i)) == '+') {
                    cur = Integer.parseInt(s.substring(pre+1, idx.get(i))) + Integer.parseInt(s.substring(idx.get(i)+1, su));
                } else {
                    cur = Integer.parseInt(s.substring(pre+1, idx.get(i))) * Integer.parseInt(s.substring(idx.get(i)+1, su));
                }

                set.addAll(scoreOfStudentsHelper1(prefix+cur+sufix));
            }
        }
        map.put(s, set);
        return set;
    }

    public int scoreOfStudentsHelper(String s, boolean isAdd) {

        if (!s.contains("+") && !s.contains("*")) {
            return Integer.parseInt(s);
        }

        if (isAdd) {
            int ret = 0;
            String[] sa = s.split("\\+");
            for (String n : sa) {
                ret += scoreOfStudentsHelper(n, !isAdd);
            }
            return ret;
        } else {
            int ret = 1;
            String[] sa = s.split("\\*");
            for (String n : sa) {
                ret *= scoreOfStudentsHelper(n, !isAdd);
            }
            return ret;
        }
    }

    public int scoreOfStudents(String s, int[] answers) {

        int rightAns = scoreOfStudentsHelper(s, true);
        Set<Integer> set = scoreOfStudentsHelper1(s);
        //System.out.println(set);
        int ret = 0;
        for(int x : answers) {
            if (x == rightAns) {
                ret += 5;
            } else if (set.contains(x)) {
                ret += 2;
            }
        }
     
        return ret;
    }

    public int sumOfUnique(int[] nums) {
        int ret = 0;
        int [] n = new int [101];
        for (int index = 0; index < nums.length; index++) {
            n[nums[index]]++;
        }
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 1) {
                ret += i;
            }
        }
     
        return ret;
    }

    public int[][] merge(int[][] intervals) {
        int [] arr = new int[20005];
        for (int [] x : intervals) {
            arr[x[0]*2] ++;
            arr[x[1]*2+1] --;
        }
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int s = -1;
        int e = -1;
        for (int i = 0; i < arr.length; i++) {
            // if(i < 10) {
            //     System.out.println(s+"\t"+i);
            // }
            if (i > 0) {
                arr[i] += arr[i-1];
            }
            if (arr[i] > 0) {
                if (s >= 0) {
                    e = i/2;
                } else {
                    s = i/2;
                    e = i/2;
                }
            } else {
                if (e >= 0) {
                    list.add(new Pair<Integer,Integer>(s, e));
                } 
                s = -1;
                e = -1;
            }
        }
        //System.out.println(Arrays.toString(arr));
        int [][] ret = new int[list.size()][2];
        for (int i = 0; i < ret.length; i++) {
            ret[i][0] = list.get(i).getKey();
            ret[i][1] = list.get(i).getValue();
        }
     
        return ret;
    }

    /*
    'M' represents an unrevealed mine,
    'E' represents an unrevealed empty square,
    'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
    digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
    'X' represents a revealed mine.
    You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
    */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int [] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]] ++;
            map.computeIfAbsent(prerequisites[i][1], x -> new ArrayList<>()).add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int [] ret = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ret[idx++] = cur;
            if (map.containsKey(cur)) {
                for (int x : map.get(cur)) {
                    inDegree[x] --;
                    if (inDegree[x] == 0) {
                        queue.add(x);
                    }
                }
            }
        }
        if (idx != numCourses) {
            return new int[0];
        }
        return ret;
    }

    public void reorderList(ListNode head) {
        ListNode h = head;
        List<ListNode> list = new ArrayList<>();
        while(h != null) {
            ListNode cur = h;
            list.add(cur);
            h = h.next;
        }
        int n = list.size()/2;
        for (int i = 0; i < n; i++) {
            list.get(i).next = list.get(list.size()-i-1);
            if (list.size() % 2 == 0 && i == n-1) {
                list.get(list.size()-i-1).next = null;
            } else {
                list.get(list.size()-i-1).next = list.get(i+1);
                if (list.size() %2 == 1 && i == n-1){
                    list.get(i+1).next = null;
                }
            }
        }
        head = list.get(0);
        // System.out.println("hello");
        // head = list.get(0);
        // while(head != null) {
        //     System.out.println(head.val);
        //     head = head.next;
        // }
        
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
    
        char c = board[click[0]][click[1]];
        switch (c) {
            case 'M':
                board[click[0]][click[1]] = 'X';
                return board;
            case 'E':
                int cnt = 0;
                for (int i = click[0] -1 ; i <= click[0]+1; i++) {
                    for (int j = click[1] -1 ; j <= click[1] + 1; j++) {
                        if (i >= 0 && i < m && j >= 0 && j < n && !(i == click[0] && j == click[1])) {
                            if (board[i][j] == 'M') {
                                cnt += 1;
                            }
                        }
                    }
                }
                if (cnt > 0) {
                    board[click[0]][click[1]] = (char)(cnt+'0');
                    return board;
                } else {
                    board[click[0]][click[1]] = 'B';
                    for (int i = click[0] -1 ; i <= click[0]+1; i++) {
                        for (int j = click[1] -1 ; j <= click[1] + 1; j++) {
                            if (i >= 0 && i < m && j >= 0 && j < n && !(i == click[0] && j == click[1])) {
                                if (board[i][j] == 'E') {
                                    board = updateBoard(board, new int[]{i,j});
                                }
                            }
                        }
                    } 
                }
        }

        return board;
    }

    public boolean validTicTacToe(String[] board) {
        int cntX = 0;
        int cntO = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    cntO ++;
                }
                if (board[i].charAt(j) == 'X') {
                    cntX ++;
                }
            }
        }
        System.out.println(cntX+"\t"+cntO);
        if (cntX < cntO || cntX - cntO > 1) {
            return false;
        } 

        List<String> list = new ArrayList<>();
        for(String s: board) {
            list.add(s);
        }

        List<String> vlist = new ArrayList<>();
        List<String> diList = new ArrayList<>();

        vlist.add(""+board[0].charAt(0)+board[1].charAt(0)+board[2].charAt(0));
        vlist.add(""+board[0].charAt(1)+board[1].charAt(1)+board[2].charAt(1));
        vlist.add(""+board[0].charAt(2)+board[1].charAt(2)+board[2].charAt(2));
        diList.add(""+board[0].charAt(0)+board[1].charAt(1)+board[2].charAt(2));
        diList.add(""+board[0].charAt(2)+board[1].charAt(1)+board[2].charAt(0));

        int sucCnt = 0;
        int hSuc = 0;
        int vSuc = 0;
        int diSuc = 0;
        int Xsuc = 0;
        int Osuc = 0;
        for (String s : list) {
            if (s.equals("XXX")||s.equals("OOO")){
                sucCnt += 1;
                hSuc += 1;
                if (s.equals("XXX")) {
                    Xsuc += 1;
                } else {
                    Osuc += 1;
                }
            }


        }
        for (String s : vlist) {
            if (s.equals("XXX")||s.equals("OOO")){
                sucCnt += 1;
                vSuc += 1;
                if (s.equals("XXX")) {
                    Xsuc += 1;
                } else {
                    Osuc += 1;
                }
            }
        }
        for (String s : diList) {
            if (s.equals("XXX")||s.equals("OOO")){
                sucCnt += 1;
                diSuc += 1;
                if (s.equals("XXX")) {
                    Xsuc += 1;
                } else {
                    Osuc += 1;
                }
            }
        }
        if ((vSuc > 1 || hSuc > 1)) {
            return false;
        }

        if ((Xsuc > 0 && cntX != cntO +1)||(Osuc > 0 && cntX != cntO)) {
            return false;
        }


        return true;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode ret = new ListNode(head.val);
        while (head.next != null) {
            int cur = head.next.val;
            ListNode h = ret;
            if (cur <= h.val) {
                ListNode n = new ListNode(cur, ret);
                ret = n;
            } else {
                while (h.next != null && cur > h.next.val) {
                    h = h.next;
                }
                ListNode tmp = h.next;
                h.next = new ListNode(cur, tmp);

            }
            head = head.next;
        }
        
        return ret;
    }

    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x: nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        
        List<Integer> cnt = new ArrayList<>(map.values());
        int n = cnt.size();

        Map<Integer, Integer> sum = new HashMap<>();
        int total = 1 << quantity.length;
        for (int i = 0; i < total; i++) {
            int cur = 0;
            for (int j = 0; j < quantity.length; j++) {
                if ((i & (1 << j)) > 0) {
                    cur += quantity[j];
                }
            }
            sum.put(i, cur);
        }
        // System.out.println(sum);
        // System.out.println(cnt);
        boolean [][] canDis = new boolean[n+1][total];
        for (int i = 0; i < n; i++) {
            canDis[i][0] = true;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < total; j++) {
                if (cnt.get(i-1) >= sum.get(j)) {
                    canDis[i][j] = true;
                    continue;
                }
                for(int s = j; s > 0 ; s = (s-1)&j) {
                    if (canDis[i-1][s] && cnt.get(i-1) >= sum.get(j-s)){
                        canDis[i][j] = true;
                        break;
                    }
                }
            }
        }

        // for (int i = 0; i < canDis.length; i++) {
        //     System.out.println(Arrays.toString(canDis[i]));
        // }

        return canDis[n][total-1];
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ret = 0;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val <= high && cur.val >= low) {
                ret += cur.val;
            }
            if (cur.val > low && cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.val < high && cur.right != null) {
                queue.add(cur.right);
            }
        }

        return ret;
    }

    public int maxPower(String s) {
        char pre = '-';
        char[] ss = s.toCharArray();
        int len = 0;
        int max = 1;
        for (char c : ss) {
            if (c == pre) {
                len += 1;
                max = Math.max(len, max);
            } else {
                len = 1;
                pre = c;
            }
        }
        return max;
    }

    public int numTilings(int n) {
        if (n <= 2) {
            return n;
        }
        int module = (int) 1e9 + 7;

        long[] cnt = new long[n + 1];
        long[] more = new long[n + 1];
        cnt[0] = 1;
        cnt[1] = 1;
        cnt[2] = 2;
        more[2] = 1;

        for (int i = 3; i < cnt.length; i++) {
            cnt[i] = (cnt[i] + cnt[i - 1]) % module;
            cnt[i] = (cnt[i] + cnt[i - 2]) % module;
            cnt[i] = (cnt[i] + 2 * more[i - 1]) % module;

            more[i] = (more[i] + cnt[i - 2]) % module;
            more[i] = (more[i] + more[i - 1]) % module;
        }

        // System.out.println(Arrays.toString(cnt));
        // System.out.println(Arrays.toString(more));

        return (int) cnt[n];
    }

    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        while (s.length() > 0 && i < words.length) {
            if (s.startsWith(words[i])) {
                s = s.substring(words[i++].length());
            } else {
                return false;
            }
        }

        return s.length() == 0;
    }

    public static int getLen(int[] p1, int[] p2) {
        return (int) (Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> edgeLen = new HashSet<>();
        edgeLen.add(getLen(p1, p2));
        edgeLen.add(getLen(p1, p3));
        edgeLen.add(getLen(p1, p4));
        edgeLen.add(getLen(p2, p3));
        edgeLen.add(getLen(p2, p4));
        edgeLen.add(getLen(p3, p4));
        List<Integer> list = new ArrayList<>(edgeLen);

        return list.size() == 2 && Math.max(list.get(0), list.get(1)) == Math.min(
                list.get(0), list.get(1)) * 2;
    }

    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // try {
            // TimeUnit.SECONDS.sleep(1);
            // } catch (InterruptedException e) {

            // }
            // System.out.println(i + "\t" + Arrays.toString(nums));
            if (nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
                i--;
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                ret.add(nums[i]);
            }
        }

        // System.out.println(Arrays.toString(nums));

        return ret;
    }

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.abs(sumTree(root.left) - sumTree(root.right)) + findTilt(root.left) + findTilt(root.right);

    }

    public int sumTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumTree(root.left) + sumTree(root.right) + root.val;
    }

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        int ret = 0;

        int[][] max = new int[n][n];
        for (int i = 0; i < n; i++) {
            max[0][i] = 2;
        }
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max[i][j] = 2;
                int pre = map.getOrDefault(arr[j] - arr[i], -1);
                if (pre >= 0) {
                    max[i][j] = Math.max(max[i][j], max[pre][i] + 1);
                    ret = Math.max(ret, max[i][j]);
                }
            }
        }
        // System.out.println(" \t"+Arrays.toString(arr));
        // for (int i = 0; i < n; i++) {
        // System.out.printf("%02d\t%s\n", arr[i] , Arrays.toString(max[i]));
        // }

        return ret >= 3 ? ret : 0;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] lenOne = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    lenOne[i][j] = (i > 0 ? lenOne[i - 1][j] : 0) + 1;
                } else {
                    lenOne[i][j] = 0;
                }
            }
        }
        // for(int [] x : lenOne) {
        // System.out.println(Arrays.toString(x));
        // }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (lenOne[i][j] > 0) {
                    int min = lenOne[i][j];
                    for (int k = j; k >= 0; k--) {
                        min = Math.min(min, lenOne[i][k]);
                        max = Math.max(min * (j - k + 1), max);
                    }
                }
            }
        }

        return max;
    }

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = nums[i] + (i > 0 ? sum[i - 1] : 0);
        }
        for (int i = 0; i < n; i++) {
            int left = sum[i] - nums[i];
            int right = sum[n - 1] - sum[i];
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
        for (String key : set.keySet()) {
            map.put(key, idx++);
        }

        UnionFind uf = new UnionFind(set.size());
        for (List<String> list : accounts) {
            for (int i = 1; i < list.size() - 1; i++) {
                uf.union(map.get(list.get(i)), map.get(list.get(i + 1)));
            }
        }

        Map<Integer, List<String>> tmp = new HashMap<>();
        for (String key : set.keySet()) {
            int p = uf.find(map.get(key));
            if (!tmp.containsKey(p)) {
                tmp.computeIfAbsent(p, x -> new ArrayList<>()).add(set.get(key));
            }
            tmp.get(p).add(key);
        }
        List<List<String>> ret = new ArrayList<>(tmp.values());
        return ret;
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets <= 1)
            return 0;
        int n = minutesToTest / minutesToDie + 1;
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
        int n = minutesToTest / minutesToDie + 1;
        int tmp = (int) (Math.log(buckets) / Math.log(n));
        if ((int) Math.pow(n, tmp) == buckets) {
            return tmp;
        } else {
            return tmp + 1;
        }
    }

    public int tupleSameProduct(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                map.computeIfAbsent(nums[i] * nums[j], x -> new ArrayList<>()).add(nums[i]);
            }
        }
        int ret = 0;
        for (int k : map.keySet()) {
            int s = map.get(k).size();
            if (s > 1) {
                ret += s * (s - 1) * 4;
            }
        }
        return ret;
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int m = firstList.length;
        int n = secondList.length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] f = firstList[i];
                int[] s = secondList[j];
                if (f[1] >= s[0] && s[1] >= f[0]) {
                    list.add(new int[] { Math.max(s[0], f[0]), Math.min(s[1], f[1]) });
                }

                if (s[0] > f[1]) {
                    break;
                }
            }
        }
        int[][] ret = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public String isMatchHelper(String p) {
        Stack<Character> stack = new Stack<>();
        char[] ss = p.toCharArray();
        for (char c : ss) {
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                switch (c) {
                    case '*':
                        while (!stack.isEmpty() && stack.peek() == '*') {
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
        while (itr.hasNext()) {
            sb.append(itr.next());
        }
        return sb.toString();
    }

    public boolean isMath1(String s, String p) {
        // System.out.printf("s:%s, p:%s\n", s, p);
        if (s.length() == 0 && p.length() == 0) {
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
        return isMath1(s, p);
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            tm.put(difficulty[i], Math.max(tm.getOrDefault(difficulty[i], 0), profit[i]));
        }
        int curMax = 0;
        for (int key : tm.keySet()) {
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

    public int largestComponentSize(int[] nums) {
        UnionFind uf = new UnionFind((int) 1e5 + 1);
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
            for (int i = 2; i <= (int) Math.sqrt(x); i++) {
                if (x % i == 0) {
                    uf.union(i, x);
                    uf.union(x / i, x);
                }
            }
        }
        int ret = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 1; i <= (int) 1e5; i++) {
            if (set.contains(i)) {
                int p = uf.find(i);
                cnt.put(p, cnt.getOrDefault(p, 0) + 1);
                ret = Math.max(ret, cnt.get(p));
            }
        }
        // for (int i = 1; i <= 40; i++) {
        // if (set.contains(i))
        // System.out.println(i + "\t"+uf.find(i));
        // }

        return ret;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
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
        return list.get(k - 1);
    }

    public static int test(Map<Integer, Integer> test) {
        test.put(1, 1);
        return 1;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] max = new int[n];
        int[] pre = new int[n];
        max[0] = 1;
        pre[0] = 0;
        int maxLen = 0;
        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            max[i] = 1;
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (max[j] + 1 > max[i]) {
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

        while (pre[maxIdx] != maxIdx) {
            ret.add(nums[maxIdx]);
            maxIdx = pre[maxIdx];
        }
        ret.add(nums[maxIdx]);
        return ret;
    }

    public int maxValueAfterReverse(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            res += Math.abs(nums[i] - nums[i + 1]);
        }

        int diff = 0;
        int maxMin = Integer.MIN_VALUE;
        int minMax = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            maxMin = Math.max(maxMin, Math.min(nums[i], nums[i + 1]));
            minMax = Math.min(minMax, Math.max(nums[i], nums[i + 1]));
        }
        diff = Math.max(diff, 2 * (maxMin - minMax));
        for (int i = 1; i < n - 1; i++) {
            diff = Math.max(diff, Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
            diff = Math.max(diff,
                    Math.abs(nums[n - i - 2] - nums[n - 1]) - Math.abs(nums[n - i - 2] - nums[n - i - 1]));
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

                for (int k = i + 1; k < m; k++) {
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
            for (char c : word.toCharArray()) {
                cur |= (1 << (c - 'a'));
            }
            ws.put(cur, ws.getOrDefault(cur, 0) + 1);
        }

        List<Integer> ret = new ArrayList<>();
        for (String p : puzzles) {
            Set<Integer> curSet = new HashSet<>();
            curSet.add(1 << (p.charAt(0) - 'a'));
            char[] ss = p.toCharArray();
            for (int i = 1; i < ss.length; i++) {
                Set<Integer> newSet = new HashSet<>();
                for (int x : curSet) {
                    newSet.add(x | (1 << (ss[i] - 'a')));
                }
                curSet.addAll(newSet);
            }

            int curCnt = 0;
            for (Integer x : curSet) {
                curCnt += ws.getOrDefault(x, 0);
            }
            ret.add(curCnt);
        }
        return ret;
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                uf.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        for (String s : equations) {
            if (s.charAt(1) != '=') {
                if (uf.find(s.charAt(0) - 'a') == uf.find(s.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    public int numTrees(int n) {
        if (n == 1)
            return 1;
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            int cur = 0;
            for (int j = 1; j <= i; j++) {
                cur += arr[j - 1] * arr[i - j];
            }
            arr[i] = cur;
        }

        return arr[n];
    }

    public int arrangeCoins(int n) {
        long pre = (long) Math.sqrt((long) n * 2) - 1;
        long sum = (1 + pre) * pre / 2;
        // System.out.println(pre);
        // System.out.println(sum);
        for (long i = pre + 1; i <= Integer.MAX_VALUE; i++) {
            sum += i;
            if (sum > n) {
                return (int) i - 1;
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
        Pair<Integer, Integer>[] parr = new Pair[n + 1];
        parr[0] = new Pair<>(0, 0);
        for (int i = 0; i < n; i++) {
            int continues = parr[i].getKey() + arr[i];
            int del = Math.max(parr[i].getKey(), parr[i].getValue() + arr[i]);
            max = Math.max(max, Math.max(parr[i].getValue() + arr[i], continues));
            parr[i + 1] = new Pair<>(continues > 0 ? continues : 0, del > 0 ? del : 0);
        }
        // System.out.println(Arrays.toString(parr));
        return max;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left != null) {
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
                if (i % j == 0 || j % i == 0) {
                    map.computeIfAbsent(i, x -> new ArrayList<>()).add(j);
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
                diff++;
            } else {
                diff--;
            }

            if (!map.containsKey(diff)) {
                map.put(diff, i);
            } else {
                max = Math.max(max, i - map.get(diff));
            }
        }

        return max;
    }

    public String removeOccurrences(String s, String part) {
        int m = s.length();
        int n = part.length();
        while (!s.replaceFirst(part, "").equals(s)) {
            s = s.replaceFirst(part, "");
        }

        return s;
    }

    // TODO: 不会
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int si = 0, sj = 0, ei = 0, ej = 0, empCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 1:
                        si = i;
                        sj = j;
                        break;
                    case 2:
                        ei = i;
                        ej = j;
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
        boolean[][] visited = new boolean[m][n];
        int[][] adj = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 && i != m - 1 && j != 0 && j != n - 1) {
                    continue;
                }
                if (board[i][j] == 'X') {
                    continue;
                }
                // System.out.printf("i=%d,j=%d\n", i, j);
                Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                queue.add(new Pair<>(i, j));
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    Pair<Integer, Integer> cur = queue.poll();
                    for (int[] x : adj) {
                        int ii = cur.getKey() + x[0];
                        int jj = cur.getValue() + x[1];

                        if (ii >= 0 && ii < m && jj >= 0 & jj < n && visited[ii][jj] == false && board[ii][jj] == 'O') {
                            queue.add(new Pair<>(ii, jj));
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
        while (i < j) {
            if (ss[i] != ss[j]) {
                isPal = false;
                s1 = s.substring(0, i) + s.substring(i + 1, n);
                s2 = s.substring(0, j) + s.substring(j + 1, n);
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
        for (int k = 0; k < (n - 1) / 2; k++) {
            if (s1.charAt(k) != s1.charAt(n - 2 - k)) {
                b1 = false;
                break;
            }
        }
        for (int k = 0; k < (n - 1) / 2; k++) {
            if (s2.charAt(k) != s2.charAt(n - 2 - k)) {
                b2 = false;
                break;
            }
        }

        return b1 || b2;
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
            if (root.left != null) {
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
        int[][] ret = new int[k][2];
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
        int[][] time = new int[m][n];
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

        int[][] adj = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int[][] visited = new int[m][n];
                    visited[i][j] = 1;
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.add(new Pair<>(i, j));
                    while (!queue.isEmpty()) {
                        Pair<Integer, Integer> cur = queue.poll();
                        // System.out.println(cur);
                        for (int[] x : adj) {
                            int ii = cur.getKey() + x[0];
                            int jj = cur.getValue() + x[1];
                            if (ii >= 0 && ii < m && jj >= 0 && jj < n && grid[ii][jj] != 0 && visited[ii][jj] == 0) {
                                time[ii][jj] = Math.min(time[ii][jj], time[cur.getKey()][cur.getValue()] + 1);
                                queue.add(new Pair<>(ii, jj));
                                visited[ii][jj] = 1;
                            }
                        }
                    }
                }
                // System.out.printf("i:%d, j:%d\n", i, j);
                // for (int [] t : time){
                // System.out.println(Arrays.toString(t));
                // }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
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
            for (Node n : cur.neighbors) {
                if (!map.containsKey(n.val)) {
                    queue.add(n);
                }
            }
        }
        Map<Integer, Node> mapClone = new HashMap<>();
        for (int k : map.keySet()) {
            mapClone.put(k, new Node(k));
        }

        for (int k : mapClone.keySet()) {
            List<Node> list = new ArrayList<>();
            for (Node n : map.get(k).neighbors) {
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
        for (char[] x : board) {
            for (char y : x) {
                set.add(y);
            }
        }
        for (String word : words) {
            boolean flag = false;
            for (char x : word.toCharArray()) {
                if (!set.contains(x)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            for (int idx = 0; idx < m * n; idx++) {
                int i = idx / n;
                int j = idx % n;
                if (board[i][j] == word.charAt(0)) {
                    int[][] visited = new int[m][n];
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
        int[][] adj = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        for (int[] x : adj) {
            int ii = i + x[0];
            int jj = j + x[1];
            if (ii >= 0 && ii < m && jj >= 0 && jj < n && board[ii][jj] == word.charAt(0) && visited[ii][jj] == 0) {
                visited[ii][jj] = 1;
                if (findWordsHelper(board, word.substring(1), ii, jj, visited)) {
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
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = original[i * n + j];
            }
        }
        return ret;
    }

    public String largestOddNumber(String num) {
        int n = num.length();
        for (int i = n - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }

    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[][] trans = new char[n][m];

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
                // System.out.println(i+"\t"+j);
                if ((board[i][j] == ' ' || (board[i][j] >= 'a' && board[i][j] <= 'z'))
                        && (j == 0 || board[i][j - 1] == '#')) {
                    for (int k = j; k < n; k++) {
                        if (board[i][k] == '#' || k == n - 1) {
                            if (board[i][k] != '#') {
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
            if (b.charAt(i) != ' ' && b.charAt(i) != a.charAt(i)) {
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
                Pair<Integer, Integer> l = robHelper(root.left, map);
                inclusive += l.getValue();
                exclusive += Math.max(l.getKey(), l.getValue());
            }

            if (root.right != null) {
                Pair<Integer, Integer> r = robHelper(root.right, map);
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
        return max - min > 2 * k ? max - min - 2 * k : 0;
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

            for (int j = i + 1; j < n; j++) {
                if (jSet.contains(nums[j])) {
                    continue;
                } else {
                    jSet.add(nums[j]);
                }
                int c = 0 - nums[i] - nums[j];

                if (map.containsKey(c)) {
                    if (map.get(c).get(map.get(c).size() - 1) > j) {
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

        // System.out.println(ret);
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
        // if (n == 0) {
        // return 0;
        // } else {
        // return (int) Math.pow(10, n - 1) + 10 * countDigitOneHelper(n - 1);
        // }
        // 与上面代码等价
        return n * (int) Math.pow(10, n - 1);
    }

    // 842
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Integer> tmp = checkFibonacci(num, 0, i, j);
                if (tmp.size() > 0) {
                    return tmp;
                }
            }
        }
        return new ArrayList<>();
    }

    public List<Integer> checkFibonacci(String num, int begin, int i, int j) {
        List<Integer> ret = new ArrayList<>();
        while (j < num.length()) {
            String a = num.substring(begin, i);
            String b = num.substring(i, j);
            // System.out.println(a+"\t"+b);

            if (a.length() > 10 || b.length() > 10) {
                return new ArrayList<>();
            }
            long la = Long.parseLong(a);
            long lb = Long.parseLong(b);

            // System.out.println(la+"\t"+lb);

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
            // System.out.println(a +"\t"+b+"\t"+num.substring(j, Math.min(j+sum.length(),
            // num.length())));
            if (num.substring(j, Math.min(j + sum.length(), num.length())).equals(sum)) {
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
        int module = (int) 1e9 + 7;

        return 0;
    }

    // 115
    public int numDistinct(String s, String t) {

        return 0;
    }

    // 1835
    public int getXORSum(int[] arr1, int[] arr2) {
        long[] arr1Bit = new long[32];
        long[] arr2Bit = new long[32];

        for (int x : arr1) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) > 0) {
                    arr1Bit[i]++;
                }
            }
        }
        for (int x : arr2) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) > 0) {
                    arr2Bit[i]++;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < 32; i++) {
            long oneCnt = arr1Bit[i] * arr2Bit[i];
            ret |= ((oneCnt % 2 == 0 ? 0 : 1) << i);
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
        int module = (int) 1e9 + 7;
        Set<Integer> set = new HashSet<>();
        for (int x : arr) {
            set.add(x);
        }
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long tmp = (long) arr[i] * (long) arr[j];
                if (tmp < Integer.MAX_VALUE && set.contains((int) tmp)) {
                    map.computeIfAbsent((int) tmp, x -> new ArrayList<>()).add(arr[i]);
                }
            }
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                long curSum = 1;
                for (int x : map.get(arr[i])) {
                    int leftCnt = cnt.get(x);
                    int rightCnt = cnt.get(arr[i] / x);
                    long product = (int) (((long) leftCnt * (long) rightCnt) % (long) (module));
                    if (x * x == arr[i]) {
                        curSum += product;
                        curSum %= module;
                    } else {
                        curSum += (int) product * 2 % module;
                    }
                }
                cnt.put(arr[i], (int) (curSum % module));
            } else {
                cnt.put(arr[i], 1);
            }
        }

        for (int x : cnt.keySet()) {
            ret += cnt.get(x);
            ret %= module;
        }

        // System.out.println(Arrays.toString(arr));
        // System.out.println(map);
        // System.out.println(cnt);
        return (int) ret;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cntRansom = new int[26];
        int[] cntMagazine = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            cntRansom[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            cntMagazine[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (cntRansom[i] > cntMagazine[i]) {
                return false;
            }
        }
        return true;
    }

    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || arr[i] <= arr[i - 1]) {
                left[i] = 1;
            } else {
                left[i] = left[i - 1] + 1;
            }
            int j = n - 1 - i;
            if (j == n - 1 || arr[j] <= arr[j + 1]) {
                right[j] = 1;
            } else {
                right[j] = right[j + 1] + 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > 1 & right[i] > 1)
                ret = Math.max(ret, left[i] + right[i] - 1);
        }
        return ret >= 3 ? ret : 0;
    }

    public void printMatrix(int[][] x) {
        System.out.println("================");
        for (int[] xx : x) {
            System.out.println(Arrays.toString(xx));
        }
    }

    public int getValue(int[][] x, int m, int n, int i, int j) {
        if (i >= 0 && i < m && j >= 0 && j < n) {
            return x[i][j];
        } else {
            return 0;
        }
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rowSum = new int[m][n];
        int[][] colSum = new int[m][n];
        int[][] digLeft = new int[m][n];
        int[][] digRight = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j] = getValue(rowSum, m, n, i, j - 1) + grid[i][j];
                colSum[i][j] = getValue(colSum, m, n, i - 1, j) + grid[i][j];
                digLeft[i][j] = getValue(digLeft, m, n, i - 1, j - 1) + grid[i][j];
                digRight[i][j] = getValue(digRight, m, n, i - 1, j + 1) + grid[i][j];
            }
        }

        int ret = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; i + k < m && j + k < n; k++) {
                    int s = rowSum[i][j + k] - rowSum[i][j] + grid[i][j];
                    boolean valid = true;
                    for (int l = 0; l <= k; l++) {
                        if (rowSum[i + l][j + k] - rowSum[i + l][j] + grid[i + l][j] != s) {
                            valid = false;
                            break;
                        }
                        if (colSum[i + k][j + l] - colSum[i][j + l] + grid[i][j + l] != s) {
                            valid = false;
                            break;
                        }
                    }
                    if (digLeft[i + k][j + k] - digLeft[i][j] + grid[i][j] != s ||
                            digRight[i + k][j] - digRight[i][j + k] + grid[i][j + k] != s) {
                        valid = false;
                    }

                    if (!valid) {
                        continue;
                    } else {
                        ret = Math.max(ret, k + 1);
                    }
                }
            }
        }
        return ret;
    }

    public String frequencySort(String s) {
        char[] ss = s.toCharArray();
        int[] cnt = new int[123];
        for (char c : ss) {
            cnt[(int) c]++;
        }
        int[] idx = IntStream.range(0, cnt.length).boxed().sorted(Comparator.comparingInt(i -> cnt[i])).mapToInt(a -> a)
                .toArray();
        StringBuffer sb = new StringBuffer();
        for (int i = idx.length - 1; i >= 0; i--) {
            if (cnt[idx[i]] > 0) {
                char c = (char) idx[i];
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
        int[] next = new int[n];
        int idx = 1;
        int now = 0;

        while (idx < n) {
            if (s.charAt(idx) == s.charAt(now)) {
                now += 1;
                next[idx] = now;
                idx += 1;
            } else {
                if (now > 0) {
                    now = next[now - 1];
                } else {
                    next[idx] = 0;
                    idx++;
                }
            }
        }
        return s.substring(0, next[n - 1]);
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nb = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int n = nums2.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                nb.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int m = nums1.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            ret[i] = nb.getOrDefault(nums1[i], -1);
        }
        return ret;
    }

    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        int n = changed.length;
        if (n % 2 != 0) {
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
                twice++;
            }
            if (changed[i] % 2 == 0 && map.containsKey(changed[i] / 2)) {
                half++;
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
            ret += map.getOrDefault(nums[i] + k, 0);
            ret += map.getOrDefault(nums[i] - k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ret;
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        Map<Integer, List<Integer>> children = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (parents[i] >= 0) {
                children.computeIfAbsent(parents[i], x -> new ArrayList<>()).add(i);
            }
        }
        Map<Integer, Integer> childBit = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int curValue = 0;
            for (int x : children.get(i)) {
                curValue |= x;
            }
        }

        return null;
    }

    public int maxProduct(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < (1 << s.length()); i++) {
            String curS = (new StringBuffer(Integer.toBinaryString(i)).reverse()).toString();
            List<Integer> oneIndex = new ArrayList<>();
            for (int j = 0; j < curS.length(); j++) {
                if (curS.charAt(j) == '1') {
                    oneIndex.add(j);
                }
            }
            boolean isPar = true;
            for (int j = 0; j < oneIndex.size() / 2; j++) {
                if (s.charAt(oneIndex.get(j)) != s.charAt(oneIndex.get(oneIndex.size() - 1 - j))) {
                    isPar = false;
                    break;
                }
            }
            if (isPar) {
                map.put(i, oneIndex.size());
            }
        }
        int max = 1;
        // System.out.println(map);
        List<Integer> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            for (int j = i + 1; j < keys.size(); j++) {
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
            StringBuffer sb = new StringBuffer(word.substring(0, index + 1));
            return sb.reverse() + word.substring(index + 1);
        }
    }

    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> map = new HashMap<>();
        for (int i = 0; i < rectangles.length; i++) {
            double cur = rectangles[i][0] / (double) rectangles[i][1];
            map.put(cur, map.getOrDefault(cur, (long) 0) + 1);
        }
        long ret = 0;
        for (double k : map.keySet()) {
            if (map.get(k) > 1) {
                ret += (map.get(k) * (map.get(k) - 1) / 2);
            }
        }
        return ret;
    }

    public static Map<String, String> getProductInfo(String s) {
        Map<String, String> ret = new HashMap<>();
        String[] sourceInfo = s.split("::");

        for (String si : sourceInfo) {
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
            return a;
        }
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

        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        return getGCD(min, max);

    }

    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();
        for (String s : nums) {
            set.add(Integer.parseInt(s, 2));
        }
        int n = nums.length;
        for (int i = 0; i < 65536; i++) {
            if (!set.contains(i)) {
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

        return nums[k - 1];
    }

    public int[][] findFarmland(int[][] land) {
        List<int[]> list = new ArrayList<>();
        int m = land.length;
        int n = land[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((land[i][j] == 1) && (i == 0 || land[i - 1][j] == 0) && (j == 0 || land[i][j - 1] == 0)) {
                    int ti = i;
                    int tj = j;
                    while (ti + 1 < m && land[ti + 1][tj] == 1) {
                        ti++;
                    }
                    while (tj + 1 < n && land[ti][tj + 1] == 1) {
                        tj++;
                    }
                    list.add(new int[] { i, j, ti, tj });
                }
            }
        }
        int[][] ret = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for (int x : nums) {
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
        int[] sum = new int[n + 1];
        int module = (int) 1e9 + 7;
        sum[1] = 2;
        for (int i = 1; i < n - 1; i++) {
            System.out.println(Arrays.toString(sum));
            int cur = (sum[i] + module - sum[nextVisit[i]] + 2) % module;
            sum[i + 1] = (sum[i] + cur) % module;
        }
        return sum[n - 1];
    }

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, Comparator.comparingInt(o -> o[0]));
        int n = properties.length;
        int[] max = new int[n];
        max[n - 1] = properties[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], properties[i][1]);
        }

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            if (properties[i][0] > properties[i - 1][0]) {
                tm.put(i, 1);
            }
        }
        System.out.println(tm);
        for (int[] x : properties) {
            System.out.println(Arrays.toString(x));
        }
        System.out.println(Arrays.toString(max));

        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (tm.higherKey(i) != null && max[tm.higherKey(i)] > properties[i][1]) {
                ret += 1;
            }
        }
        return ret;
    }

    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
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
        for (int x : chalk) {
            sum += x;
        }
        k -= ((k / sum) * sum);

        while (k >= 0) {
            k -= chalk[index % n];
            index++;

            System.out.println(k + "\t" + index);
        }
        return (index - 1 + n) % n;

    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] arr = new int[52];
        for (int[] x : ranges) {
            arr[x[0]]++;
            arr[x[1] + 1]--;
        }
        for (int i = 0; i < 52; i++) {
            arr[i] = i == 0 ? arr[i] : arr[i - 1] + arr[i];
        }

        for (int i = left; i <= right; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean exist(char[][] board, String word) {

        boolean ret = false;
        int m = board.length;
        int n = board[0].length;

        Set<String> sp = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sp.add(i + "_" + j);
                if (word.charAt(0) == board[i][j]) {
                    ret |= existHelper(board, word, i, j, 0, sp);
                }
                sp.remove(i + "_" + j);
            }
        }

        return ret;
    }

    public boolean existHelper(char[][] board, String word, int i, int j, int index, Set<String> sp) {
        // System.out.println(i+"\t"+j+"\t"+index+"\t"+sp);

        int m = board.length;
        int n = board[0].length;
        if (index == word.length() - 1) {
            return board[i][j] == word.charAt(index);
        }
        int[][] adj = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        boolean ret = false;
        for (int[] x : adj) {
            int newI = i + x[0];
            int newJ = j + x[1];

            String curKey = newI + "_" + newJ;

            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && board[newI][newJ] == word.charAt(index + 1)
                    && !sp.contains(curKey)) {
                sp.add(curKey);
                ret |= existHelper(board, word, newI, newJ, index + 1, sp);
                sp.remove(curKey);

            }
        }
        // System.out.println(ret);
        return ret;
    }

    public String maxValue(String n, int x) {

        if (n.startsWith("-")) {
            for (int i = 1; i < n.length(); i++) {
                if (x + '0' < n.charAt(i)) {
                    return n.substring(0, i) + x + n.substring(i);
                }
            }
            return n + x;
        } else {
            for (int i = 0; i < n.length(); i++) {
                if (x + '0' > n.charAt(i)) {
                    return n.substring(0, i) + x + n.substring(i);
                }
            }
            return n + x;
        }

    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();

        for (int i = 0; i < firstWord.length(); i++) {
            sb1.append(firstWord.charAt(i) - 'a');
        }
        for (int i = 0; i < secondWord.length(); i++) {
            sb2.append(secondWord.charAt(i) - 'a');
        }
        for (int i = 0; i < targetWord.length(); i++) {
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
                xorList.add(new Pair<>(new Pair<>(i, j), nums1[i] ^ nums2[j]));
            }
        }

        Collections.sort(xorList, Comparator.comparingInt(Pair::getValue));
        int ret = 0;
        for (Pair<Pair<Integer, Integer>, Integer> p : xorList) {
            System.out.println(p);
            if ((!visited1.contains(p.getKey().getKey())) && (!visited2.contains(p.getKey().getValue()))) {
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
        int[][] leftSum = new int[n][m];
        int[][] rightSum = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (i - 1 >= 0 && j + 1 < m) {
                    leftSum[i][j] = leftSum[i - 1][j + 1] + grid[i][j];
                } else {
                    leftSum[i][j] = grid[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    rightSum[i][j] = rightSum[i - 1][j - 1] + grid[i][j];
                } else {
                    rightSum[i][j] = grid[i][j];
                }
            }
        }
        int maxR = Math.min(m, n) / 2;
        Set<Integer> allSumSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k <= maxR; k++) {
                    if (i + k < n && i - k >= 0 && j + k < m && j - k >= 0) {
                        int curSum;
                        if (k == 0)
                            curSum = grid[i][j];
                        else
                            curSum = leftSum[i][j - k] - leftSum[i - k][j] + leftSum[i + k][j] - leftSum[i][j + k] +
                                    rightSum[i][j + k] - rightSum[i - k][j] + rightSum[i + k][j] - rightSum[i][j - k]
                                    - grid[i + k][j] + grid[i - k][j];
                        allSumSet.add(curSum);
                        // System.out.println(String.format("i=%d,j=%d,k=%d,sum=%d,m=%d,n=%d",
                        // i,j,k,curSum,m,n));
                    }
                }
            }
        }
        List<Integer> allSum = new ArrayList<>(allSumSet);
        Collections.sort(allSum);
        int l = Math.min(3, allSum.size());
        int[] ret = new int[l];

        for (int i = 0; i < l; i++) {
            ret[i] = allSum.get(allSum.size() - i - 1);
        }

        // for(int [] x: leftSum) {
        // System.out.println(Arrays.toString(x));
        // }
        // for(int [] x: rightSum) {
        // System.out.println(Arrays.toString(x));
        // }

        return ret;
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int min = 0;
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            min = Math.max(nums[i] + nums[n - i - 1], min);
        }
        return min;
    }

    public int countGoodSubstrings(String s) {
        char[] ss = s.toCharArray();
        int ret = 0;
        for (int i = 1; i < ss.length - 1; i++) {
            if (ss[i - 1] != ss[i] && ss[i] != ss[i + 1] && ss[i - 1] != ss[i + 1]) {
                ret += 1;
            }
        }
        return ret;
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        char[] ss = s.toCharArray();
        int n = ss.length;
        int[] canReach = new int[n]; // 0 未知， 1 can, 2 cannot

        return canReachHelper(ss, minJump, maxJump, 0, canReach);
    }

    private boolean canReachHelper(char[] ss, int minJump, int maxJump, int cur, int[] canReach) {
        if (cur == ss.length - 1)
            return true;
        boolean ret = false;
        for (int i = cur + minJump; i <= Math.min(cur + maxJump, ss.length - 1); i++) {
            if (ss[i] == '1') {
                continue;
            }
            if (canReach[i] == 1) {
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
        int r = (int) 1e7;

        if (dist.length - 1 + dist[dist.length - 1] / (double) r > hour) {
            return -1;
        }
        int l = 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (checkOnTime(dist, hour, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean checkOnTime(int[] dist, double hour, int mid) {
        double costTime = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            costTime += Math.ceil(dist[i] / (double) mid);
        }
        costTime += dist[dist.length - 1] / (double) mid;
        return costTime <= hour;
    }

    public boolean checkZeroOnes(String s) {
        String[] ones = s.split("0");
        String[] zeros = s.split("1");
        int maxOne = 0;
        int maxZero = 0;
        for (String cur : ones) {
            maxOne = Math.max(maxOne, cur.length());
        }
        for (String cur : zeros) {
            maxZero = Math.max(maxZero, cur.length());
        }
        return maxOne > maxZero;
    }

    public int rearrangeSticks(int n, int k) {
        int modulo = (int) 1e9 + 7;

        return 0;
    }

    public int minSwaps(String s) {
        char[] ss = s.toCharArray();
        int[] cnt = new int[2];
        for (char c : ss) {
            cnt[c - '0'] += 1;
        }
        if (Math.abs(cnt[0] - cnt[1]) > 1) {
            return -1;
        }
        int pre = '0';
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != (i % 2 + '0')) {
                s1 += 1;
            }
            if (ss[i] != ((-i % 2 + 1) + '0')) {
                s2 += 1;
            }
        }
        if (s1 % 2 != 0) {
            return s2 / 2;
        } else {
            if (s2 % 2 != 0) {
                return s1 / 2;
            } else {
                return Math.min(s1, s2) / 2;
            }
        }
    }

    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int allOr = 0;
        for (int x : nums) {
            allOr |= x;
        }
        int ret = 0;
        for (int i = 0; i < 5; i++) {
            if (((allOr >> i) & 1) == 1) {
                ret += Math.pow(2, i + n - 1);
            }
        }
        return ret;
    }

    public int sumOfFlooredPairs(int[] nums) {
        int modulo = (int) 1e9 + 7;
        Arrays.sort(nums);
        int n = nums.length;

        return 0;
    }

    public char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        for (int i = 0; i < n; i++) {
            int obs = m;
            for (int j = m - 1; j >= 0; j--) {
                switch (box[i][j]) {
                    case '#':
                        if (j < obs - 1) {
                            box[i][obs - 1] = '#';
                            box[i][j] = '.';
                        }
                        obs = obs - 1;
                        break;
                    case '*':
                        obs = j;
                        break;
                }
            }
        }
        char[][] result = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n - 1 - i] = box[i][j];
            }
        }

        return result;
    }

    public int[] memLeak(int memory1, int memory2) {

        int i = 1;
        while (true) {
            if (memory1 >= memory2) {
                if (memory1 >= i) {
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
            i++;
        }
        return new int[] { i, memory1, memory2 };
    }

    public String sortSentence(String s) {
        String[] list = s.split(" ");
        String[] result = new String[list.length];
        for (String cur : list) {
            int index = Integer.parseInt(cur.charAt(cur.length() - 1) + "");
            result[index - 1] = cur.substring(0, cur.length() - 1);
        }

        return String.join(" ", result);
    }

    public int largestPathValue(String colors, int[][] edges) {
        char[] colorArr = colors.toCharArray();
        int n = colorArr.length;
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        Set<Integer> zeroIndegree = new HashSet<>();
        int[] inDegree = new int[n];
        for (int[] x : edges) {
            edgeMap.computeIfAbsent(x[0], k -> new ArrayList<Integer>()).add(x[1]);
            inDegree[x[1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                zeroIndegree.add(i);
            }
        }
        Map<Integer, Map<Character, Integer>> colorLen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colorLen.computeIfAbsent(i, k -> new HashMap<>()).put(colorArr[i], 1);
        }

        int visited = 0;
        while (!zeroIndegree.isEmpty()) {
            Set<Integer> cur = new HashSet<>();
            for (int key : zeroIndegree) {
                visited += 1;
                List<Integer> targets = edgeMap.get(key);
                if (targets == null)
                    continue;
                for (int t : targets) {
                    if (--inDegree[t] == 0) {
                        cur.add(t);
                    }
                    for (char c : colorLen.get(key).keySet()) {
                        colorLen.get(t).put(c, Math.max(colorLen.get(key).get(c) + (c == colorArr[t] ? 1 : 0),
                                colorLen.get(t).getOrDefault(c, 0)));
                    }
                }

            }
            zeroIndegree = cur;

        }

        if (visited != n) {
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
        int modulo = (int) 1e9 + 7;
        int n = nums.length;
        long[] sumArr = new long[n];
        sumArr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sumArr[i] = sumArr[i - 1] + (long) nums[i];
        }
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        Map<Integer, Integer> nextSmaller = new HashMap<>();
        Map<Integer, Integer> beforeSmaller = new HashMap<>();
        stack.push(new Pair(nums[0], 0));
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().getKey() > nums[i]) {
                Pair<Integer, Integer> cur = stack.pop();
                nextSmaller.put(cur.getValue(), i);
            }
            stack.push(new Pair<>(nums[i], i));
        }

        stack.clear();
        stack.push(new Pair(nums[n - 1], n - 1));
        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().getKey() > nums[i]) {
                Pair<Integer, Integer> cur = stack.pop();
                beforeSmaller.put(cur.getValue(), i);
            }
            stack.push(new Pair<>(nums[i], i));
        }
        long maxValue = 0;
        for (int i = 0; i < n; i++) {
            int left = beforeSmaller.getOrDefault(i, -1);
            int right = nextSmaller.getOrDefault(i, n);
            maxValue = Math.max(maxValue, nums[i] * (sumArr[right - 1] - (left == -1 ? 0 : sumArr[left])));
        }

        // System.out.println(nextSmaller.toString());
        // System.out.println(beforeSmaller.toString());

        return (int) (maxValue % modulo);
    }

    int findInsertPosition(int[] arr, int l, int r, int target) {
        if (arr[r] >= target)
            return r;
        if (arr[l] < target) {
            return l;
        }

        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (arr[mid] >= target) {
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
        for (int i = 0; i < n1 && i < n2; i++) {
            maxDis = Math.max(maxDis, findInsertPosition(nums2, i, n2 - 1, nums1[i]) - i);
            System.out.println(i + "\t" + nums1[i] + "\t" + findInsertPosition(nums2, i, n2 - 1, nums1[i]));
        }
        return maxDis;
    }

    public int maximumPopulation(int[][] logs) {
        int[] list = new int[120];

        for (int[] x : logs) {
            list[x[0] - 1950] += 1;
            list[x[1] - 1950] -= 1;
        }
        int max = list[0];
        int maxYear = 0;
        for (int i = 1; i < 120; i++) {
            list[i] = list[i - 1] + list[i];
            if (list[i] > max) {
                max = list[i];
                maxYear = i;
            }
        }
        return maxYear + 1950;
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
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            tm.put(rooms[i][0], i);
        }

        for (int i = 0; i < k; i++) {
            int cur = queries[i][0];
            if (tm.containsKey(cur) && rooms[tm.get(cur)][1] >= queries[i][1]) {
                ret[i] = cur;
                continue;
            }

            int tmpH = tm.higherEntry(cur) != null ? tm.higherEntry(cur).getValue() : n;
            int tmpL = tmpH - 1;

            while (tmpH < n && rooms[tmpH][1] < queries[i][1]) {
                tmpH++;
            }
            while (tmpL >= 0 && rooms[tmpL][1] < queries[i][1]) {
                tmpL--;
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
                    ret[i] = (cur - rooms[tmpL][0]) <= (rooms[tmpH][0] - cur) ? rooms[tmpL][0] : rooms[tmpH][0];
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
            if (num.charAt(i) != newNum.charAt(i)) {
                System.out.println(num + "\t" + newNum + "\t" + i);
                for (int j = i + 1; j < n; j++) {
                    if (num.charAt(j) == newNum.charAt(i)) {
                        num = num.substring(0, i) + newNum.charAt(i) + num.substring(i, j) + num.substring(j + 1);
                        ret += (j - i);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public String nexPermutation(String num) {
        char[] ss = num.toCharArray();
        int n = num.length();
        for (int i = n - 1; i > 0; i--) {
            if (ss[i - 1] < ss[i]) {
                for (int j = i; j < (n - i) / 2 + i; j++) {
                    char temp = ss[j];
                    ss[j] = ss[n - 1 - (j - i)];
                    ss[n - 1 - (j - i)] = temp;
                }
                for (int j = i; j < n; j++) {
                    if (ss[j] > ss[i - 1]) {
                        char temp = ss[i - 1];
                        ss[i - 1] = ss[j];
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
        for (int i = 1; i < n; i++) {
            long cur = Long.parseLong(s.substring(0, i));
            String sub = s.substring(i);
            if (cur > 5e10) {
                return false;
            }
            while (findMinusOneValue(cur, sub) > 0) {
                sub = sub.substring(findMinusOneValue(cur, sub));
                if (sub.length() == 0) {
                    return true;
                }
                cur--;
            }
        }
        return false;
    }

    public static int findMinusOneValue(long n, String s) {
        int ret = -1;
        for (int i = 1; i <= s.length(); i++) {
            long cur = Long.parseLong(s.substring(0, i));
            if (cur > 5e10) {
                return -1;
            }
            if (cur == n - 1) {
                ret = i;
            }
        }
        return ret;
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                min = Math.min(min, Math.abs(i - start));
            }
        }
        return min;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        System.out.println(Arrays.toString(arr));

        return arr[n - 1];
    }

    public static long getDiffDates(String s1, String s2) {
        LocalDateTime d1 = Instant.ofEpochMilli(Long.parseLong(s1) * 1000).atZone(ZoneOffset.ofHours(8))
                .toLocalDateTime();
        LocalDateTime d2 = LocalDateTime.parse(s2, dft);
        return ChronoUnit.HOURS.between(d1, d2);
    }

    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, Comparator.comparingInt(o -> o[0]));
        int cnt = restrictions.length;
        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                restrictions[i][1] = Math.min(restrictions[i][0] - 1, restrictions[i][1]);
            } else {
                if (restrictions[i][1] >= restrictions[i - 1][1]) {
                    restrictions[i][1] = Math.min(restrictions[i][1],
                            restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0]);
                }
            }
        }
        for (int i = cnt - 2; i >= 0; i--) {
            if (restrictions[i][1] >= restrictions[i + 1][1]) {
                restrictions[i][1] = Math.min(restrictions[i][1],
                        restrictions[i + 1][1] + restrictions[i + 1][0] - restrictions[i][0]);
            }
        }
        // 这个特殊条件可以通过数组里增加[1,0],[n,Math.min(Integer.MAX_VALUE< maxHeight_n)]

        int max = cnt > 0 ? Math.max(restrictions[0][1], restrictions[cnt - 1][1] + n - restrictions[cnt - 1][0])
                : n - 1;
        max = cnt > 0 ? Math.max(max, (restrictions[0][0] - 1 - restrictions[0][1]) / 2 + restrictions[0][1]) : max;
        for (int i = 1; i < cnt; i++) {
            int curMax = (restrictions[i][0] - restrictions[i - 1][0] - (Math.abs(restrictions[i][1] -
                    restrictions[i - 1][1]))) / 2 + Math.max(restrictions[i][1], restrictions[i - 1][1]);
            max = Math.max(max, curMax);
        }
        return max;
    }

    public int maxFrequency(int[] A, long k) {
        int i = 0, j;
        Arrays.sort(A);
        for (j = 0; j < A.length; ++j) {
            k += A[j];
            if (k < (long) A[j] * (j - i + 1))
                k -= A[i++];
        }
        return j - i;
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int max = 1;
        int j = 0;
        for (int i = 1; i < n; i++) {
            for (; j < i; j++) {
                if (nums[i] * (i - j + 1) - (sum[i] - sum[j] + nums[j]) <= k) {
                    max = Math.max(i - j + 1, max);
                    break;
                }
            }
        }
        return max;
    }

    public static double[] normalize(int[] vector) {
        int sum = 0;
        int len = vector.length;
        for (int i = 0; i < len; i++) {
            sum += vector[i] * vector[i];
        }
        double sqrtSum = Math.sqrt(sum);
        double[] ret = new double[len];
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

    public static int getType(int[] vvCount, double[][] center) {
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
            startDate = endDate.minusDays((long) period - 1);
        } else if (strEnd.length() == 0) {
            startDate = LocalDate.parse(strStart, dft);
            endDate = startDate.plusDays((long) period - 1);
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
                if ((map.get(cur) % 2 == 0 && (pre.val >= cur.val || cur.val % 2 == 0))
                        || (map.get(cur) % 2 == 1 && (pre.val <= cur.val || cur.val % 2 == 1))) {
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
        int[] rowIndex = IntStream.range(0, rowSum.length).boxed().sorted(Comparator.comparingInt(i -> rowSum[i]))
                .mapToInt(a -> a).toArray();
        int[] colIndex = IntStream.range(0, colSum.length).boxed().sorted(Comparator.comparingInt(i -> colSum[i]))
                .mapToInt(a -> a).toArray();

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
        return (Integer.parseInt(bb[0]) - Integer.parseInt(aa[0])) * 60 + Integer.parseInt(bb[1])
                - Integer.parseInt(aa[1]);
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
        // return result.substring(result.length() - 15);
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
        int[] minG2 = new int[n];
        for (int i = 0; i < n; i++) {
            minG2[i] = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                minG2[i] = Math.min(minG2[i], cost.get(j).get(i));
            }
        }
        int[][] dp = new int[13][4096];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return connectTwoGroupsHelper(cost, 0, 0, m,n, minG2, dp);
    }

    public int connectTwoGroupsHelper(List<List<Integer>> cost, int i , 
    int mask, int m, int n, int[] minG2, int[][] dp) {
        if (dp[i][mask] >= 0) {
            return dp[i][mask];
        }
        int ret = (i >= m ? 0 : Integer.MAX_VALUE);
        if (i >= m) {
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0 ){
                    ret += minG2[j];
                }
            }
        } else {
            for (int j = 0; j < n; j++) {
                ret = Math.min(ret, cost.get(i).get(j) + connectTwoGroupsHelper(cost, i+1, 
                mask | (1 << j), m, n, minG2, dp));
            }
        }
        dp[i][mask] = ret;
        return ret;
    }


    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i ++) {
            map.put(arr[i], i);
            count.put(arr[i], count.getOrDefault(arr[i], 0) + 1);
        }

        int module = (int)1e9+7;
        int ret = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j ++) {
                int rem = target - arr[i] -arr[j];
                if (map.getOrDefault(rem, -1) > j) {
                    ret += Math.min(count.get(rem), map.get(rem) - j);
                    ret %= module;
                }
            }
        }
        return ret;
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

        // for (int i = 0; i < m; i++) {
        // System.out.println(Arrays.toString(dpPos[i]));
        // }
        //
        // for (int i = 0; i < m; i++) {
        // System.out.println(Arrays.toString(dpNeg[i]));
        // }

        return dpPos[m - 1][n - 1] >= 0 ? (int) (dpPos[m - 1][n - 1] % ((int) 1e9 + 7)) : -1;

    }

    public int maxUniqueSplit(String s) {
        char[] ss = s.toCharArray();
        return helper(new HashSet<>(), ss, 0);
    }

    public int helper(Set<String> pre, char[] s, int start) {
        if (start == s.length) {
            // System.out.println(pre);
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