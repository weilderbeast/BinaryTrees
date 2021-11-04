package com.company;

import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    private BinarySearchTreeNode<T> root;

    public void insert(T element) {
        if (!contains(element)) {
            insertRec(root, element);
        }
    }

    private void insertRec(BinaryTreeNode<T> node, T element) {
        if (node == null) {
            root = new BinarySearchTreeNode<>();
            root.setElement(element);
            setRoot(root);
            return;
        }

        if (node.getElement().compareTo(element) <= 0) {
            if (node.getRightChild() == null) {
                BinarySearchTreeNode<T> child = new BinarySearchTreeNode<>();
                child.setElement(element);
                node.addRightChild(child);
            } else {
                insertRec(node.getRightChild(), element);
            }
        } else {
            if (node.getLeftChild() == null) {
                BinarySearchTreeNode<T> child = new BinarySearchTreeNode<>();
                child.setElement(element);
                node.addLeftChild(child);
            } else {
                insertRec(node.getLeftChild(), element);
            }
        }
    }

    public void removeElement(T element) {
        if (contains(element)) {
            setRoot(removeElementRec(getRoot(), element));
        }
    }

    private BinaryTreeNode<T> removeElementRec(BinaryTreeNode<T> node, T element) {
        if (node == null) return null;
        int check = element.compareTo(node.getElement());
        if (check < 0) node.addLeftChild(removeElementRec(node.getLeftChild(), element));
        else if (check > 0) node.addRightChild(removeElementRec(node.getRightChild(), element));
        else {
            if (node.getLeftChild() == null && node.getRightChild() == null) return null;
            else if (node.getLeftChild() == null) return node.getRightChild();
            else if (node.getRightChild() == null) return node.getLeftChild();
            else {
                T minimum = findMinRec(node.getRightChild());
                node.setElement(minimum);
                node.addRightChild(removeElementRec(node.getRightChild(), minimum));
            }
        }
        return node;
    }

    public T findMin() {
        return findMinRec(getRoot());
    }

    private T findMinRec(BinaryTreeNode<T> node) {
        if (node.getLeftChild() == null) return node.getElement();
        return findMinRec(node.getLeftChild());
    }

    public T findMax() {
        return findMaxRec(getRoot());
    }

    private T findMaxRec(BinaryTreeNode<T> node) {
        if (node.getRightChild() == null) return node.getElement();
        return findMaxRec(node.getRightChild());
    }


    public boolean contains(T element) {
        return containsRec(getRoot(), element) != null;
    }

    private BinaryTreeNode<T> containsRec(BinaryTreeNode<T> node, T element) {
        if (node == null) return null;
        if (node.getElement().compareTo(element) == 0) {
            return node;
        }
        if (node.getElement().compareTo(element) < 0) {
            return containsRec(node.getRightChild(), element);
        } else {
            return containsRec(node.getLeftChild(), element);
        }
    }

    public void rebalance() {
        ArrayList<T> inOrder = inOrder();
        setRoot(reBalance(inOrder));
    }

    private BinaryTreeNode<T> reBalance(ArrayList<T> inOrder) {
        if(inOrder.size() == 0) return null;
        int midIndex = inOrder.size()/2;
        BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>();
        node.setElement(inOrder.get(midIndex));
        node.addLeftChild(reBalance(new ArrayList<>(inOrder.subList(0, midIndex))));
        node.addRightChild(reBalance(new ArrayList<>(inOrder.subList(midIndex + 1, inOrder.size()))));
        return node;
    }

}
