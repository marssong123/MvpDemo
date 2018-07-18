package com.ssjj.androidmvpdemo.training;


import com.ssjj.androidmvpdemo.datastructure.Node;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LeetCode2 {


    public static void main(String[] args) {
        int k = 6;
        int[] ins = new int[]{8, 6, 10, 9, 7, 2, 1, 20, 13};
//        heapSort(ins);
//        String[] str = {"sdf", "sdvvmkmkmsd", "sdrferfersdgbb", "sdfsdfsdfqqq", "sdccccsdcc", "sd3333sdcwwww", "sdqwewesdgg"};
//        System.out.print(commonString(str));

//        int n = 5, w = 10;                    //物品个数，背包容量
//        int[] value = {0, 6, 3, 5, 4, 6};     //各个物品的价值
//        int[] weight = {0, 2, 2, 6, 5, 4};    //各个物品的重量
//        System.out.println(getMaxValue(weight,value,w,n));


//        List list = new ArrayList<Integer>();
//        printList(21, 8, list);
        Observable.just(1,2,3,4).concatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                System.out.println(integer);
                return Observable.just(integer+"个");
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        }) ;



    }


    /**
     *
     * n个数取若干个数相加为m
     * @param m 递归向左校验
     * @param n
     * @param outList
     */
    private static void printList(int m, int n, List<Integer> outList) {
        if (m == 0) {
            System.out.println(outList);
            return;
        }

        if (m < 0 || n <= 0) {
            return;
        }
        printList(m - n, n - 1, outList);

        List<Integer> list = new ArrayList<Integer>(outList);
        list.add(n);
        printList(m, n - 1, list);
    }


    /**
     * 有序数组
     * 使用循环的方式实现二分查找
     *
     * @param array
     * @param value
     * @return
     */
    public static Integer searchCirculation(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int middle;

        while (low <= high) {
            middle = (low + high) / 2;
            if (value < array[middle]) {
                high = middle - 1;
            } else if (value > array[middle]) {
                low = middle + 1;
            } else {
                return array[middle];
            }
        }

        return null;
    }

    /**
     * 使用递归的方式实现二分查找
     *
     * @param array
     * @param value
     * @return
     */
    public static Integer searchRecursive(int[] array, int value) {
        return searchRecursive(array, value, 0, array.length - 1);
    }

    private static Integer searchRecursive(int[] array, int value, int low, int high) {
        if (high < low) {
            return null;
        }

        int middle = (low + high) / 2;

        if (value < array[middle]) {
            return searchRecursive(array, value, low, middle - 1);
        } else if (value > array[middle]) {
            return searchRecursive(array, value, middle + 1, high);
        } else {
            return array[middle];
        }
    }


    /**
     * 字符串index
     *
     * @param source
     * @param pattern
     * @return
     */
    public static int indexOf(String source, String pattern) {
        int i = 0, j = 0;
        int sLen = source.length(), pLen = pattern.length();
        char[] src = source.toCharArray();
        char[] ptn = pattern.toCharArray();
        while (i < sLen && j < pLen) {
            if (src[i] == ptn[j]) {
                // 如果当前字符匹配成功,则将两者各自增1,继续比较后面的字符
                i++;
                j++;
            } else {
                // 如果当前字符匹配不成功,则i回溯到此次匹配最开始的位置+1处,也就是i = i - j + 1
                // (因为i,j是同步增长的), j = 0;
                i = i - j + 1;
                j = 0;
            }
        }
        // 匹配成功,则返回模式字符串在原字符串中首次出现的位置;否则返回-1
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    /**
     * 最长公共字符串 遍历短的字符串 长度每次减一 ，判断长的字符串是否含有子字符串
     *
     * @param input1
     * @param input2
     * @return
     */
    private static String commonString(String input1, String input2) {

        if (input1 == null || input2 == null) {
            return "";
        }
        String max = input1.length() > input2.length() ? input1 : input2;
        String min = input1.length() < input2.length() ? input1 : input2;

        for (int i = 0; i < min.length(); i++) {
            for (int start = 0, end = min.length() - i; end < min.length(); start++, end++) {
                if (max.contains(min.substring(start, end))) {
                    return min.substring(start, end);
                }
            }
        }

        return "";

    }


    /**
     * 查找两个有序数组的中位数
     *
     * @param a
     * @param b
     * @param m
     * @param n
     * @return
     */
    int setMid(int a[], int b[], int m, int n) {
        int k = (m + n) / 2;
        return mid(a, b, m, n, k);
    }

    private int mid(int a[], int b[], int m, int n, int k) //假定m<=n
    {
        if (m > n)  //若不是m<=n,调换一下
            return mid(b, a, n, m, k);
        if (m == 0)
            return b[k - 1];
        if (k == 1)
            return Math.min(a[0], b[0]);

        int ia = Math.min(k / 2, m);
        int ib = k - ia;
        if (a[ia] == b[ib])  //第k个数找到
            return a[ia];
        if (a[ia] > b[ib])   //第k个数肯定不在b[0..ib]，所以，删除这些，找第k-ib个数
            return mid(a, b, m, n - ib, k - ib);
        else  //a[ia]<b[ib] 第k个数肯定不在a[0...ia],所以，删除这些，找k-ia个数
            return mid(a, b, m - ia, n, k - ia);
    }


    /**
     * 最长回文子串，动态规划
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLength = 0;
        String longest = null;

        int length = s.length();
        boolean[][] table = new boolean[length][length];

        // 单个字符都是回文
        for (int i = 0; i < length; i++) {
            table[i][i] = true;
            longest = s.substring(i, i + 1);
            maxLength = 1;
        }

        // 判断两个字符是否是回文
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                longest = s.substring(i, i + 2);
                maxLength = 2;
            }
        }


        // 求长度大于2的子串是否是回文串
        for (int len = 3; len <= length; len++) {
            for (int i = 0, j = 0; (j = i + len - 1) <= length - 1; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] && maxLength < len) {
                        longest = s.substring(i, j + 1);
                        maxLength = len;
                    }
                } else {
                    table[i][j] = false;
                }
            }
        }
        return longest;
    }


    /**
     * 求数组坐标，最小装水量
     * 贪心算法
     *
     * @param nums
     * @return
     */
    private int maxWater(int[] nums) {

        if (nums == null || nums.length < 2) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int result = 0;

        while (left < right) {
            result = Math.max(result, Math.max(nums[left], nums[right]) * (right - left));

            int k = 0;

            if (nums[left] < nums[right]) {
                k = left;

                while (k < right && nums[k] <= nums[left]) {
                    k++;
                }

                left = k;

            } else {

                k = right;
                while (k > left && nums[k] <= nums[right]) {
                    k--;
                }

                right = k;
            }

        }

        return result;
    }

    /**
     * 最长共同前缀
     *
     * @param inputs
     * @return
     */
    private static String commonString(String[] inputs) {

        if (inputs == null || inputs.length == 0) {
            return "";
        }

        int index = 0;
        int max = 0;


        while (index < inputs.length) {
            if (isCommonString(inputs, index)) {
                max = Math.max(max, index);
            } else {
                return inputs[index].substring(0, max);
            }

            index++;
        }
        return inputs[index].substring(0, max);

    }

    private static boolean isCommonString(String[] inputs, int index) {
        String common = inputs[0].substring(0, index);

        for (int i = 1; i < inputs.length; i++) {
            if (!inputs[i].substring(0, index).equals(common)) {
                return false;
            }

        }
        return true;
    }


    /**
     * 删除链表倒数第n个节点,双指针
     *
     * @param head
     * @param n
     * @return
     */
    private Node deleteNNode(Node head, int n) {
        if (n < 1 || head == null) {
            return null;
        }
        Node nodeA = head;
        Node nodeB = head;

        for (int i = 0; i < n; i++) {
            nodeA = nodeA.next;
        }

        if (nodeA.next == null) { //n 等于链表长度
            head = head.next;
            return head;
        }

        while (nodeA != null) {
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        nodeB.next = nodeB.next.next;

        return nodeB;

    }


    /**
     * 输出给定的n对括号对的所有合法序列
     *
     * @param l     剩下的左括号
     * @param r     剩下的右括号
     * @param str
     * @param index char str[] = new char[count*2];  printPar(count, count, str, 0);
     */
    public static void printPar(int l, int r, char[] str, int index) {
        if (l < 0 || r < l) return;
        if (l == 0 && r == 0) {
            System.out.println(str);
        } else {
            if (l > 0) {
                str[index] = '(';
                printPar(l - 1, r, str, index + 1);
            }
            if (r > l) {
                str[index] = ')';
                printPar(l, r - 1, str, index + 1);
            }
        }

    }

    /**
     * 合并n个有序链表
     *
     * @param nodes
     * @return
     */
    public Node mergeNode(Node[] nodes) {
        if (nodes.length < 2) {
            return null;
        }
        Node result = mergeAB(nodes[0], nodes[1]);

        for (int i = 2; i < nodes.length; i++) {

            result = mergeAB(nodes[i], result);

        }

        return result;

    }

    private Node mergeAB(Node node1, Node node2) {
        Node result = new Node(0);
        while (node1.next != null && node2.next != null) {
            if ((int) node1.data < (int) node2.data) {
                node1 = node1.next;
                result = node1;
            } else {
                node2 = node2.next;
                result = node2;
            }

        }

        return result;

    }


    private static void heapSort(int[] nums) {

        int index = nums.length - 1;
        for (int i = 0; i < index; i++) {
            buildHeap(nums, index - i);
            swap(nums, 0, index - i);
            System.out.print(nums[index - i] + " ");
        }
    }

    /**
     * 大顶堆
     *
     * @param nums
     * @param lastIndex
     */
    private static void buildHeap(int[] nums, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            int k = i;
            while (2 * k + 1 <= lastIndex) {

                int bigIndex = 2 * k + 1;
                if (bigIndex < lastIndex) {//右边节点存在
                    if (nums[bigIndex] < nums[bigIndex + 1]) {
                        bigIndex++;
                    }
                }

                if (nums[k] < nums[bigIndex]) {

                    swap(nums, k, bigIndex);

                    k = bigIndex;

                } else {
                    break;
                }

            }

        }


    }

    private static void swap(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;

    }


    private static boolean haveWays(Tree.TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (sum == 0 && root.left == null && root.right == null) {
            return true;
        }

        return haveWays(root.left, sum - (int) root.data) || haveWays(root.right, sum - (int) root.data);

    }


    public static int getMaxValue(int[] weight, int[] value, int w, int n) {
        int[][] table = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) { //物品
            for (int j = 1; j <= w; j++) {  //背包大小
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    table[i][j] = table[i - 1][j];
                    // System.out.print(table[i][j]+ " ");
                } else { //装得下，Max{装物品i， 不装物品i}
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - weight[i]] + value[i]);
                    //System.out.print(table[i][j]+ " ");
                }
            }
            // System.out.println();
        }
        return table[n][w];
    }


}
