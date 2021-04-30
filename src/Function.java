package src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Function {
    static DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");
    Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {

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