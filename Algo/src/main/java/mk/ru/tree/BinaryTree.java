package mk.ru.tree;

import java.util.*;

public class BinaryTree {
    private TreeNode root;

    public void addNode(int value) {
        if (root == null) root = new TreeNode(value);
        else addRecursive(root, value);
    }

    private void addRecursive(TreeNode current, int value) {
        if (value < current.value) {
            if (current.left == null) current.left = new TreeNode(value);
            else addRecursive(current.left, value);
        } else if (value > current.value) {
            if (current.right == null) current.right = new TreeNode(value);
            else addRecursive(current.right, value);
        }
    }

    public boolean findNode(int value) {
        return findRecursive(root, value);
    }

    private boolean findRecursive(TreeNode current, int value) {
        if (current == null) return false;
        if (value == current.value) return true;
        return value < current.value
                ? findRecursive(current.left, value)
                : findRecursive(current.right, value);
    }

    public void printLevelOrder() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    public void removeNode(int value) {
        root = removeRecursive(root, value);
    }

    private TreeNode removeRecursive(TreeNode current, int value) {
        if (current == null) return null;

        if (value < current.value) current.left = removeRecursive(current.left, value);
        else if (value > current.value) current.right = removeRecursive(current.right, value);
        else {
            if (current.left == null && current.right == null) return null;
            else if (current.left == null || current.right == null) return (current.left != null) ? current.left : current.right;
            else {
                int minValue = findMinValue(current.right);
                current.value = minValue;
                current.right = removeRecursive(current.right, minValue);
            }
        }
        return current;
    }

    private int findMinValue(TreeNode node) {
        int minv = Integer.MAX_VALUE;
        while (node != null) {
            if (minv > node.value) minv = node.value;
            node = node.left;
        }
        return minv;
    }
}
