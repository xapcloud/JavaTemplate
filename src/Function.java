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
        //System.out.println(f.minFlips("01001001101"));

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