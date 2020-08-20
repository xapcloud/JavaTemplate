package src;

public class Main {
    public static void main(String[] args) {
        Tree t = new Tree();
        String s = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        TreeNode root = t.constructTree(s);
        System.out.println(t.preOrder(root).toString());
        System.out.println(t.inOrder(root).toString());
        System.out.println(t.postOrder(root).toString());
        System.out.println(t.postOrder1(root).toString());
    }
}