package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> node) {
        this.root = node;
    }

    public boolean isEmpty() {
        return root == null || root.getElement() == null;
    }

    public int size() {
        return recSize(root);
    }

    private int recSize(BinaryTreeNode<T> node) {
        if (node == null || node.getElement() == null) return 0;
        else {
            return (recSize(node.getLeftChild()) + 1 + recSize(node.getRightChild()));
        }
    }

    public boolean contains(T element) {
        return containsRec(root, element);
    }

    private boolean containsRec(BinaryTreeNode<T> node, T element) {
        if(node == null){
            return false;
        }

        if (node.getElement().equals(element)) {
            return true;
        }

        boolean leftTree = containsRec(node.getLeftChild(), element);

        if (leftTree) return true;

        boolean rightTree = containsRec(node.getRightChild(), element);

        return rightTree;
    }

    public ArrayList<T> inOrder() {
        if(isEmpty()) return null;
        ArrayList<T> list = new ArrayList();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(BinaryTreeNode<T> node, ArrayList<T> list) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeftChild(), list);
        list.add(node.getElement());
        inOrderRec(node.getRightChild(), list);
    }

    public ArrayList<T> preOrder() {
        if(isEmpty()) return null;
        ArrayList<T> list = new ArrayList();
        preOrderRec(root, list);
        return list;
    }

    private void preOrderRec(BinaryTreeNode<T> node, ArrayList<T> list) {
        if (node == null){
            return;
        }
        list.add(node.getElement());
        preOrderRec(node.getLeftChild(),list);
        preOrderRec(node.getRightChild(),list);
    }

    public ArrayList<T> postOrder() {
        if(isEmpty()) return null;
        ArrayList<T> list = new ArrayList();
        postOrderRec(root, list);
        return list;
    }

    private void postOrderRec(BinaryTreeNode<T> node, ArrayList<T> list) {
        if (node == null){
            return;
        }
        postOrderRec(node.getLeftChild(),list);
        postOrderRec(node.getRightChild(),list);
        list.add(node.getElement());
    }

    public ArrayList<T> levelOrder(){
        if(isEmpty()) return null;
        return levelOrderImpl();
    }

    private ArrayList<T> levelOrderImpl() {
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        ArrayList<T> list = new ArrayList();

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> temp = queue.poll();
            list.add(temp.getElement());

            if (temp.getLeftChild() != null) {
                queue.add(temp.getLeftChild());
            }

            if (temp.getRightChild() != null) {
                queue.add(temp.getRightChild());
            }
        }
        if(isEmpty()) return null;
        return list;
    }

    public int height() {
        if(isEmpty()) return -1;
        if (root.getLeftChild() == null && root.getRightChild() == null){
            return 0;
        }
        return recHeight(root);
    }

    private int recHeight(BinaryTreeNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = recHeight(node.getLeftChild());
            int rightHeight = recHeight(node.getRightChild());
            if (leftHeight > rightHeight) return leftHeight + 1;
            else return rightHeight + 1;
        }
    }

}
