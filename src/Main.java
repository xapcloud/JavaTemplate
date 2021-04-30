package src;

import java.util.*;

class Node {
    Node left;
    Node right;
    long l;
    long r;
    int flag = 0;
    int value = 0;

    Node(int x, long l, long r) {
        this.value = x;
        this.l = l;
        this.r = r;
    }

    void makeChildren() {
        if (this.l == this.r){
            return;
        }
        long m = (this.l + this.r)/2;
        if (this.left == null && this.l != this.r) {
            this.left = new Node(0, this.l, m);
        }
        if (this.right == null && this.l != this.r) {
            this.right = new Node(0,m+1, this.r);
        }
    }

    int get(int l, int r) {
        if (l < 0)
            return 0;

        //System.out.println(this.toString());
        if(l <= this.l && r >= this.r) {
            return this.value;
        }
        long m = (this.l + this.r) / 2;
        makeChildren();
        if (this.flag > 0) {
            this.left.value = Math.max(this.left.value, this.flag);
            this.right.value = Math.max(this.right.value, this.flag);
            this.left.flag = Math.max(this.left.flag, this.flag);
            this.right.flag = Math.max(this.right.flag, this.flag);
            this.flag = 0;
        }
        int max = 0;
        if (l <= m){
            //System.out.println(this.toString());
            max = Math.max(max, this.left.get(l, r));
        }
        if (r > m){
            max = Math.max(max, this.right.get(l, r));
        }
        return max;
    }

    void update(long l, long r, int value) {
        //System.out.println("update:" + this.toString());
        if (l <= this.l && r >= this.r) {
            this.value = Math.max(this.value, value);
            this.flag = Math.max(this.flag, value);
        } else {
            long m = (this.l + this.r)/2;
            makeChildren();

            if(this.flag > 0 && this.l != this.r) {
                this.left.value = Math.max(this.left.value, this.flag);
                this.right.value = Math.max(this.right.value, this.flag);
                this.left.flag = Math.max(this.left.flag, this.flag);
                this.right.flag = Math.max(this.right.flag, this.flag);
                this.flag = 0;
            }

            if (l <= m){
                this.left.update(l, r, Math.max(this.flag, value));
            }
            if (r > m){
                this.right.update(l, r, Math.max(this.flag, value));
            }
            
            this.value = Math.max(this.left.value, this.right.value);
        }
    }

    @Override
    public String toString(){
        return String.format("[%d,%d]:%d,%d", this.l, this.r, this.value, this.flag);
    }
}




public class Main {
    public static void main(String[] args) {
       //int [][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
       //int [][] buildings = {{0,2,3},{2,5,3}};
       //int [][] buildings = {{0,2147483647,2147483647}};
       //int [][] buildings = {{4,9,10},{4,9,15},{4,9,12},{10,12,10},{10,12,8}};
       int [][] buildings = {{1,38,219},{2,19,228},{2,64,106},{3,80,65},{3,84,8},{4,12,8},{4,25,14},{4,46,225},{4,67,187},{5,36,118},{5,48,211},{5,55,97},{6,42,92},{6,56,188},{7,37,42},{7,49,78},{7,84,163},{8,44,212},{9,42,125},{9,85,200},{9,100,74},{10,13,58},{11,30,179},{12,32,215},{12,33,161},{12,61,198},{13,38,48},{13,65,222},{14,22,1},{15,70,222},{16,19,196},{16,24,142},{16,25,176},{16,57,114},{18,45,1},{19,79,149},{20,33,53},{21,29,41},{23,77,43},{24,41,75},{24,94,20},{27,63,2},{31,69,58},{31,88,123},{31,88,146},{33,61,27},{35,62,190},{35,81,116},{37,97,81},{38,78,99},{39,51,125},{39,98,144},{40,95,4},{45,89,229},{47,49,10},{47,99,152},{48,67,69},{48,72,1},{49,73,204},{49,77,117},{50,61,174},{50,76,147},{52,64,4},{52,89,84},{54,70,201},{57,76,47},{58,61,215},{58,98,57},{61,95,190},{66,71,34},{66,99,53},{67,74,9},{68,97,175},{70,88,131},{74,77,155},{74,99,145},{76,88,26},{82,87,40},{83,84,132},{88,99,99}};
       System.out.println(getSkyline(buildings));
    }

    public static void printTree(Node root) {
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        Map<Integer, List<Node>> map = new HashMap<>();
        while(!queue.isEmpty()){
            Pair<Node, Integer> p = queue.poll();
            map.computeIfAbsent(p.getValue(), k->new ArrayList<>()).add(p.getKey());
            if(p.getKey().left != null) {
                queue.offer(new Pair<>(p.getKey().left, p.getValue()+1));
            }
            if(p.getKey().right != null) {
                queue.offer(new Pair<>(p.getKey().right, p.getValue()+1));
            }
        }

        for (int i = 0; i < 100; i++) {
            if (!map.containsKey(i)){
                break;
            }
            for(Node n: map.get(i)){
                System.out.print(n.toString()+"\t");
            }
            System.out.println();
        }

    }
    /*
    1 <= buildings.length <= 104
    0 <= lefti < righti <= 231 - 1
    1 <= heighti <= 231 - 1
    buildings is sorted by lefti in non-decreasing order.
    */

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        Node root = new Node(0, 0, Integer.MAX_VALUE);
        
        for(int[] building: buildings){
            root.update(building[0]+1, building[1], building[2]);
        }
        //printTree(root);
        // for (int i = 0; i < 25; i++) {
        //     System.out.println(i+"\t"+root.get(i,i));
        // }   

        //"[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]"
        List<List<Integer>> ret = new ArrayList<>();
        Map<Integer, Integer> history = new HashMap<>();
        for(int[] building: buildings){
            history.put(building[0], root.get(building[0],building[0]));
            history.put(building[0] + 1, root.get(building[0] + 1,building[0] + 1));
            history.put(building[1], root.get(building[1],building[1]));
            history.put(building[1] + 1, root.get(building[1] + 1,building[1] + 1));

            if(history.get(building[0]) < history.get(building[0]+1)) {
                List<Integer> list = new ArrayList<>();
                list.add(building[0]);
                list.add(history.get(building[0]+1));
                ret.add(list);
            }
            if(history.get(building[1]+1) < history.get(building[1])) {
                List<Integer> list = new ArrayList<>();
                list.add(building[1]);
                list.add(history.get(building[1]+1));
                ret.add(list);
            }
        }

        Collections.sort(ret, (x1,x2) -> x1.get(0) - x2.get(0));
        List<List<Integer>> omitDuplicated = new ArrayList<>();

        omitDuplicated.add(ret.get(0));
        for (int i = 1; i < ret.size(); i++) {
            if(!ret.get(i).get(0).equals(ret.get(i-1).get(0)) || !ret.get(i).get(1).equals(ret.get(i-1).get(1))){
                omitDuplicated.add(ret.get(i));
            } 
        }
        System.out.println(ret);
        System.out.println(omitDuplicated);


        return omitDuplicated;
    }
}
