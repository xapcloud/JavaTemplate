package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof TreeNode) {
            return ((TreeNode) o).val == this.val;
        }
        return false;
    }
}

public class Tree {

    TreeNode constructTree(String arr) {
        arr = arr.replace(" ", "");
        String[] ele = arr.substring(1, arr.length() - 1).split(",");
        TreeNode[] nodes = new TreeNode[ele.length];

        for (int i = 0; i < ele.length; i++) {
            if (!ele[i].equals("null")) {
                TreeNode cur = new TreeNode(Integer.parseInt(ele[i]));
                nodes[i] = cur;
                if (i > 0) {
                    if (i % 2 == 0) {
                        nodes[(i - 1) / 2].right = cur;
                    } else {
                        nodes[(i - 1) / 2].left = cur;
                    }
                }
            }
        }
        return nodes[0];
    }


    List<Integer> preOrder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            ret.add(curNode.val);
            if (curNode.right != null)
                stack.push(curNode.right);
            if (curNode.left != null)
                stack.push(curNode.left);
        }
        return ret;
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

    List<Integer> postOrder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode cur = stack.peek();
                if (cur.right != null && lastVisited != cur.right) {
                    root = cur.right;
                } else {
                    ret.add(cur.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return ret;
    }

    List<Integer> postOrder1(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode cur = stack.peek();
                if (cur.right != null) {
                    root = cur.right;
                    //会改变树的结构，一次遍历是可以用的
                    cur.right = null;
                } else {
                    ret.add(cur.val);
                    stack.pop();
                }
            }
        }
        return ret;
    }
     static void printTree(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        while(!queue.isEmpty()){
            Pair<TreeNode, Integer> p = queue.poll();
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
            for(TreeNode n: map.get(i)){
                System.out.print(n.toString()+"\t");
            }
            System.out.println();
        }
    }
}
