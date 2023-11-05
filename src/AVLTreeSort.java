
/*
Description: This file contains the static method `sort` which
        runs AVL  Sort algorithm on the called array.

        Author: Sümeyye Üzüm
        Contact: smyyuzum@gmail.com


            */


import java.util.ArrayList;
import java.util.List;

class AVLNode<E> {
    E data;
    AVLNode<E> left;
    AVLNode<E> right;
    int height;

    AVLNode(E data) {
        this.data = data;
        this.height = 1;
    }
}

public class AVLTreeSort {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        List<E> sortedList = new ArrayList<>();
        AVLNode<E> root = null;

        for (E item : arr) {
            root = insert(root, item);
        }

        inOrderTraversal(root, sortedList);

      for (int i = 0; i < arr.length && i < sortedList.size(); i++) {
            arr[i] = sortedList.get(i);
        }
    }

    private static <E extends Comparable<E>> AVLNode<E> insert(AVLNode<E> node, E data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node; // Duplicate data is not allowed
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1) {
            if (data.compareTo(node.left.data) < 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (data.compareTo(node.right.data) > 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private static <E> int getHeight(AVLNode<E> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private static <E> int getBalance(AVLNode<E> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private static <E> AVLNode<E> rotateRight(AVLNode<E> y) {
        AVLNode<E> x = y.left;
        AVLNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private static <E> AVLNode<E> rotateLeft(AVLNode<E> x) {
        AVLNode<E> y = x.right;
        AVLNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private static <E extends Comparable<E>> void inOrderTraversal(AVLNode<E> node, List<E> sortedList) {
        if (node != null) {
            inOrderTraversal(node.left, sortedList);
            sortedList.add(node.data);
            inOrderTraversal(node.right, sortedList);
        }
    }
}
