package com.ssjj.androidmvpdemo.datastructure.train;


import android.support.v7.widget.DecorToolbar;

import java.io.FileNotFoundException;

/**
 * 排序二叉树最小高度
 *
 * @author songyu
 * @date 2017/11/27
 */
public class Coding {
    public static void main(String[] args) {

    }


    public int triangleCount(int[] S) {
        // write your code here
        int num = 0;
        for (int i = 0; i < S.length - 2; i++) {
            for (int j = i + 1; j < S.length - 1; j++) {
                for (int k = j + 1; k < S.length; k++) {
                    if (isTriAngle(S[i], S[j], S[k])) {
                        num++;
                    }
                }
            }
        }

        return num;
    }

    private boolean isTriAngle(int a, int b, int c) {
        if (a + b > c || a + c > b || b + c > a) {
            return true;
        }
        return false;
    }


    public TreeNode sortedArrayToBST(int[] A) {
        // write your code here

        int len = A.length;
        TreeNode root = null;
        root = recursion(A, 0, len - 1);
        return root;

    }


    private TreeNode recursion(int[] array, int start, int end) {
        if (end < start) {
            return null;
        }
        int middle = (start + end) / 2;
        TreeNode treeNode = new TreeNode(array[middle]);
        treeNode.left = recursion(array, start, middle - 1);
        treeNode.right = recursion(array, middle + 1, end);

        return treeNode;
    }


    private TreeNode getMinNode(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        TreeNode treeNode = new TreeNode(A[middle]);
        treeNode.left = getMinNode(A, 0, middle - 1);
        treeNode.right = getMinNode(A, middle + 1, end);

        return treeNode;

    }


    public int aplusb(int x, int y) {
        if (y == 0) {
            return x;
        } //没有进位时完成运算
        int sum, carry;
        sum = x ^ y;
        carry = (x & y) << 1;
        return aplusb(sum, carry); //递归相加
    }

    public int digitCounts(int k, int n) {
        // write your code here
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += getNum(i, k);

        }
        if (k == 0) {
            return sum + 1;
        }
        return sum;
    }

    private int getNum(int num, int k) {
        if (num == 0) {
            return 0;
        }
        return getNum(num / 10, k) + (num % 10 == k ? 1 : 0);
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String result = String.valueOf(root.val) + serialize(root.left) + serialize(root.right);
        return result;
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        char[] a = data.toCharArray();
        TreeNode treeNode=new TreeNode(a[0]);
        for (int i = 0; i <a.length; i++) {
            if(i%3==0){
            }
        }
        return  null;
    }


    class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }


    private void test(){
        FileNotFoundException fileNotFoundException = new FileNotFoundException();
    }

    private int[] getResult(int[] a ,int k){
        int sum=0;
        for (int i = 0; i <a.length ; i++) {
            sum+=a[i];
        }
        if(sum==k){
            return a;
        }
       return getResult(a,k);
    }

}




