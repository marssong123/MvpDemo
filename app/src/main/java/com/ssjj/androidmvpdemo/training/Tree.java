package com.ssjj.androidmvpdemo.training;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyu on 2018/4/3.
 */

public class Tree {

    /**
     * 递归先序遍历
     */
    public void preOrderRecursion1(TreeNode node) {
        if (node == null) //如果结点为空则返回
            return;
//        visit(node);//访问根节点
        System.out.print(node.data);
        preOrderRecursion1(node.left);//访问左孩子
        preOrderRecursion1(node.right);//访问右孩子
    }

    /**
     * 递归中序遍历
     */
    public void preOrderRecursion2(TreeNode node) {
        if (node == null) //如果结点为空则返回
            return;
        preOrderRecursion2(node.left);//访问左孩子
//        visit(node);//访问根节点
        System.out.print(node.data);
        preOrderRecursion2(node.right);//访问右孩子
    }

    /**
     * 递归后续序遍历
     */
    public void preOrderRecursion3(TreeNode node) {
        if (node == null) //如果结点为空则返回
            return;
        preOrderRecursion3(node.left);//访问左孩子
        preOrderRecursion3(node.right);//访问右孩子
//        visit(node);//访问根节点
        System.out.print(node.data);
    }

    /**
     * 非递归先序遍历二叉树
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();
        if (root == null) //如果为空树则返回
            return resultList;
        treeStack.push(root);
        while (!treeStack.isEmpty()) {
            TreeNode tempNode = treeStack.pop();
            if (tempNode != null) {
                resultList.add((Integer) tempNode.data);//访问根节点
                treeStack.push(tempNode.right); //入栈右孩子
                treeStack.push(tempNode.left);//入栈左孩子
            }
        }
        return resultList;
    }


    private void sortFirst(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {

            TreeNode node = stack.pop();
            System.out.print(node);
            stack.push(node.right);
            stack.push(node.left);

        }

    }


    public class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }

        /**
         * 输出树
         */
        public void printTree() {
            int height = getHeight(this);
            String[][] array = new String[height][height * 2];

            TreeNode<T> left = this.left;
            TreeNode<T> right = this.right;

        }

        /**
         * 返回树root的高度
         *
         * @param root
         * @return
         */
        private int getHeight(TreeNode<T> root) {
            if (root == null) {
                return 0;
            }

            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
    }

}
