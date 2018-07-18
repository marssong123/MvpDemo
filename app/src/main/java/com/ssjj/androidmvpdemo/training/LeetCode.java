package com.ssjj.androidmvpdemo.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 第一步 异常判断
 * Created by songyu on 2018/4/3.
 */

public class LeetCode {


    /**
     * 单向链表定义
     **/
    static class Node<T> {
        public T value;    //节点值
        public Node<T> next;   //后继节点

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

//        System.out.print(replaceSpace("We are one  people"));
//        System.out.print(inDexOf("asabcd", "guwedubcdjw"));

        System.out.print(charMatch("({}[]]])"));

    }


    private static int inDexOf(String input1, String input2) {
        char[] chars1 = input1.toCharArray();
        char[] chars2 = input2.toCharArray();


        int i = 0;
        int j = 0;
        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }

        }

        if (j == chars2.length) {
            return i - j;
        } else {
            return -1;
        }

    }


    void getNext(String pattern, int next[]) {
        int j = 0;
        int k = -1;
        int len = pattern.length();
        next[0] = -1;

        while (j < len - 1) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {

                j++;
                k++;
                next[j] = k;
            } else {

                // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
                // p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
                k = next[k];
            }
        }

    }

    int kmp(String s, String pattern) {
        int i = 0;
        int j = 0;
        int slen = s.length();
        int plen = pattern.length();

        int[] next = new int[plen];

        getNext(pattern, next);

        while (i < slen && j < plen) {

            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (next[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = next[j];
                }

            }

            if (j == plen) {
                return i - j;
            }
        }
        return -1;
    }


    //用两个队列实现栈

    /**
     * 两个队列相互倒 有一个必为空
     */
    public class TwoQueue {
        private LinkedList<String> queue1;
        private LinkedList<String> queue2;

        public TwoQueue() {
            queue1 = new LinkedList<String>();
            queue2 = new LinkedList<String>();
        }

        public String pop() { //1为空把2倒到1中 反之
            String re = null;
            if (queue1.size() == 0 && queue2.size() == 0) {
                return null;
            }
            if (queue2.size() == 0) {
                while (queue1.size() > 0) {
                    re = queue1.removeFirst();
                    if (queue1.size() != 0) { //最后一个不存进去
                        queue2.addLast(re);
                    }
                }
            } else if (queue1.size() == 0) { //将q2 添加到q1
                while (queue2.size() > 0) {
                    re = queue2.removeFirst();
                    if (queue2.size() != 0) {
                        queue1.addLast(re);
                    }
                }
            }
            return re;
        }

        public String push(String str) {//加到不为空的队列中
            if (queue1.size() == 0 && queue2.size() == 0) {
                queue1.addLast(str);
            }
            if (queue1.size() != 0) {
                queue1.addLast(str);
            } else if (queue2.size() != 0) {
                queue2.addLast(str);
            }
            return str;
        }
    }

    /**
     * 一个栈用来存数据 ， 一个栈用来反转数据
     * 队列队尾插入 ，队头删除
     *
     * @param <T>
     */
    class MList<T> {
        // 插入栈,只用于插入的数据
        private Stack<T> stack1 = new Stack<T>(); // 弹出栈,只用于弹出数据
        private Stack<T> stack2 = new Stack<T>();


        public MList() {
        }

        // 添加操作,入队
        public void appendTail(T t) {
            stack1.push(t);
        }

        // 删除操作,出队
        public T deleteHead() {
            // 先判断弹出栈是否为空,如果为空就将插入栈的所有数据弹出栈, // 并且将弹出的数据压入弹出栈中
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            // 如果弹出栈中还没有数据就抛出异常
            if (stack2.isEmpty()) {
                throw new RuntimeException("No more element.");
            }
            // 返回弹出栈的栈顶元素,对应的就是队首元素。
            return stack2.pop();
        }
    }


    /**
     * @param nums
     * @param target
     * @return
     */
    //    两数求和  题目的意思是：找出两个数之和等于目标值的两个数的角标（无序）
    private int[] getSum(int[] nums, int target) {
        int lens = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int end = lens - 1;
            while (i < end) {
                if (nums[i] + nums[end] != target) {
                    end--;
                } else {
                    return new int[]{i, end};
                }
            }

        }
        return new int[]{0, 0};

    }

    /**
     * 使用hashmap实现
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] getSum2(int nums[], int target) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;

            } else {
                map.put(nums[i], i);
            }

        }

        return result;

    }


    /**
     * 将数组中三个数之和为零的所有组合找出放进集合中，不允许有重复的组合。采用通用的思想，时间复杂度为 O(n²)，每次遍历，都跳过已经遍历过得元素，从而避免重复。
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> getThree(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);


        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        for (int i = 0; i < len - 2 && nums[i] <= 0; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = len - 1;

            while (start < len) {

                int sum = nums[i] + nums[start] + nums[end];

                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    List<Integer> oneList = new ArrayList<>();
                    oneList.add(nums[i]);
                    oneList.add(nums[start]);
                    oneList.add(nums[end]);
                    start++;
                    end--;

                    //跳过之前的组合

                    while (start < end && nums[start] == nums[start - 1]) {
                        start++;
                    }

                    while (start < end && nums[end] == nums[end + 1]) ;

                    end--;
                }

            }
        }
        return lists;

    }


    /**
     * 有序定长数组去除相等元素
     *
     * @param nums
     * @return
     */
    private int removeEle(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int newLen = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                nums[newLen] = nums[i];
                newLen++;
            }
        }
        return newLen;

    }


    /**
     * Example: Given input array nums = [3,2,1，4,3], val = 3  return 3
     * <p>
     * 系统会给定一个数组及一个值，我们需要把这个数组中与这个值相等的元素去除并返回一个新的长度
     *
     * @param nums
     * @param target
     * @return 如果fron < tail：
     * 移动front，如果遇到了A[front] == val的，暂停
     * 移动tail，如果遇到了A[tail]!=val的，把值复制给A[front]
     */
    private int reRemoveE(int[] nums, int target) {
        if (nums.length < 2) {
            return nums.length;
        }

        int start = 1;
        int end = nums.length - 1;

        while (start <= end) {

            if (nums[start] == target && nums[end] != target) {

                nums[start] = nums[end];
                nums[end] = target;

            }

            if (nums[start] != target) {
                start++;
            }

            if (nums[end] == target) {
                end--;
            }

        }

        return end + 1;

    }


//    /**
//     * 最长子串长度HaspMap 或者两次循环
//     *
//     * @param target
//     */
//    private void getMaxString(String target) {
//        char[] res = target.toCharArray();
//        HashMap map = new HashMap();
//        int num = 0;
//        int max = 0;
//        for (int i = 0; i < res.length; i++) {
//            if (map.containsKey(res[i])) {
//                if (num > max) {
//                    max = num;
//                }
//                num = 0;
//                map = new HashMap();
//            } else {
//                map.put(res[i], i);
//                num++;
//            }
//        }
//
//
//    }


    /**
     * 大数相加 ，先转换成数组 ，再反序添加到两个单链表中
     * <p>
     * <p>
     * 链表相加
     * Input: (3 -> 2 -> 1) + (7 -> 3 -> 2 -> 1) Output: 0 -> 7 -> 3 ->1
     *
     * @param node1
     * @param node2
     * @return 当前位：(A[i]+B[i])%10
     * 进位：（A[i]+B[i]）/10
     */
    private Node addTwoNum(Node node1, Node node2) {

        Node c1 = node1;
        Node c2 = node2;
        Node result = new Node(0);
        Node last = new Node(0);
        int sum = 0;


        while (c1 != null || c2 != null) {
            sum = sum / 10;
            if (c1 != null) {
                sum += (int) c1.value;
                c1 = c1.next;
            }

            if (c2 != null) {
                sum += (int) c2.value;
                c2 = c2.next;

            }

            last.next = new Node(sum % 10);
            last = last.next;
        }

        if (sum / 10 == 1) {
            result.next = new Node(1);
        }

        return result.next;

    }

    /**
     * 获取倒数第N个节点
     *
     * @return
     */
    private Node getNode(Node node, int n) {
        Node p1 = node;
        Node p2 = node;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            if (n < 0) {
                p2 = p2.next;
            }
            n--;
        }

        return p2;

    }


    /**
     * 非递归  合并两个有序链表
     * 比较大小 并且赋值
     *
     * @param node1
     * @param node2
     * @return
     */
    private Node mergeNode(Node node1, Node node2) {

        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node result = new Node(0);
        while (node1 != null && node2 != null) {
            if ((int) node1.value < (int) node2.value) {
                result.next = node1;
                node1 = node1.next;
            } else {
                result.next = node2;
                node2 = node2.next;
            }
            result = result.next;
        }
        if (node1 != null) {
            result.next = node1;
        }
        if (node2 != null) {
            result.next = node2;
        }

        return result.next;
    }

    /**
     * 递归实现链表合并
     * 比较大小并且递归
     *
     * @param node1
     * @param node2
     * @return
     */
    private Node mergeNode2(Node node1, Node node2) {

        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        Node result = new Node(0);


        if ((int) node1.value < (int) node2.value) {
            result = node1;
            mergeNode2(node1.next, node2);
        } else {

            result = node2;
            mergeNode2(node1, node2.next);
        }

        return result;

    }


    /**
     * 头插法 反转单链表
     *
     * @param head
     * @return
     */
    private Node reverseNode(Node head) {
        Node result = new Node(0);
        result.next = null;

        Node tmp;

        while (head != null) {
            tmp = head.next; //保存 head.next
            head.next = result.next; //head.next重定向

            result.next = head; //result指向头结点
            head = tmp; //头结点赋值
        }

        return result.next;
    }


    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 从右上角开始遍历
     *
     * @param inputs
     * @param num
     * @return
     */
    private static boolean findNum(int[][] inputs, int num) {
        int width = inputs[1].length;
        int height = inputs.length;
        int i = 0;
        int j = width - 1;

        while (i >= 0 && i < height && j >= 0) {

            if (inputs[i][j] > num) {
                j--;
            } else if (inputs[i][j] < num) {
                i++;
            } else {
                return true;
            }

        }

        return false;

    }


    /**
     * 字符串替换
     * 从后向前替换，时间复杂度O(n)
     *
     * @param
     */


    private static String replaceSpace(String input) {

        int oldLength = input.length();
        if (input == null) {
            return "";
        }
        int whiteCount = 0;
        for (int i = 0; i < input.length(); i++) {
            whiteCount++;
        }
        int newLength = whiteCount * 2 + oldLength;

        char[] outputChar = new char[newLength];


        int indexOld = oldLength;
        int indexNew = newLength - 1;

        while (indexOld > 0) {

            char curr = input.charAt(indexOld - 1);

            if (curr == ' ') {
                outputChar[indexNew--] = '0';
                outputChar[indexNew--] = '2';
                outputChar[indexNew--] = '%';

            } else {

                outputChar[indexNew--] = curr;
            }

            indexOld--;

        }

        return String.valueOf(outputChar);

    }


    /**
     * 反序打印单链表 栈或者头插法实现
     *
     * @param head
     */
    private void printNode(Node head) {
        Stack<Node> stack = new Stack();
        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().value);
        }

    }

    private void printNode2(Node head) {
        Node result = new Node(0);
        result.next = null;

        Node tmp;
        while (head.next != null) {

            tmp = head.next;
            head.next = result.next;
            result.next = head;
            head = tmp;
        }

        while (result.next != null) {
            System.out.print(result.value);
            result = result.next;
        }

    }

    /**
     * 删除链表->判断头结点  尾节点
     *
     * @param head
     * @param toBeDeleted
     * @return
     */
    public static Node deleteNode(Node head, Node toBeDeleted) {
        // 如果输入参数有空值就返回表头结点
        if (head == null || toBeDeleted == null) {
            return head;
        }
        // 如果删除的是头结点，直接返回头结点的下一个结点
        if (head == toBeDeleted) {
            return head.next;
        }
        // 下面的情况链表至少有两个结点
        // 在多个节点的情况下，如果删除的是最后一个元素
        if (toBeDeleted.next == null) {
            // 找待删除元素的前驱
            Node tmp = head;
            while (tmp.next != toBeDeleted) {
                tmp = tmp.next;
            }
            // 删除待结点
            tmp.next = null;
        } else {
            // 将下一个结点的值输入当前待删除的结点
            toBeDeleted.value = toBeDeleted.next.value;
            // 待删除的结点的下一个指向原先待删除引号的下下个结点，即将待删除的下一个结点删除
            toBeDeleted.next = toBeDeleted.next.next;
        }
        // 返回删除节点后的链表头结点
        return head;
    }


    /**
     * 二叉树，该函数输出它的镜像
     *
     * @param node
     */
    public static void mirror(TreeNode node) {
        // 如果当前结点不为空则进行操作
        if (node != null) {
            // 下面是交换结点左右两个子树
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            // 对结点的左右两个子树进行处理
            mirror(node.left);
            mirror(node.right);
        }
    }


    /**
     * 利用队列性质 保存顺序
     * 从上往下打印二叉树
     *
     * @param root
     */
    private void printNode(TreeNode root) {

        if (root == null) {
            return;
        }

        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);
        TreeNode curr;
        while (!list.isEmpty()) {
            //删除队首元素
            curr = list.removeFirst();
            System.out.print(curr.value);
            if (curr.left != null) {
                list.add(curr.left);
            }
            if (curr.right != null) {
                list.add(curr.right);
            }
        }


    }


    static int start = 0;
    static boolean isSame = true;

    private boolean isSame(int[] input, TreeNode root) {
        if (input == null || root == null) {
            return false;
        }
        backSort(root, input);
        return isSame;
    }


    /**
     * 判断一个二叉树的后序遍历和一个数组是否相等
     *
     * @param root
     * @param input
     */
    private void backSort(TreeNode root, int[] input) {

        if (root == null) {
            return;
        }

        backSort(root.left, input);
        backSort(root.right, input);
        if (root.value == input[start]) {
            start++;
        } else {
            isSame = false;
        }

    }


    /**
     * 二叉树深度
     *
     * @param root
     * @return
     */
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left > right ? (left + 1) : (right + 1);
    }

    /**
     * 判断是否平衡二叉树
     *
     * @param treeRoot
     * @return
     */
    private boolean isBlanceTree(TreeNode treeRoot) {

        if (treeRoot == null) {
            return true;
        }

        int leftDeep = treeDepth(treeRoot.left);
        int rightDeep = treeDepth(treeRoot.right);

        if (Math.abs(leftDeep - rightDeep) > 1) {
            return false;
        }

        return isBlanceTree(treeRoot.left) && isBlanceTree(treeRoot.right);
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，请写程序找出这两个只出现一次的数字
     *
     * @param nums
     * @return
     */
    private HashMap<Integer, Integer> getNum(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], i);
            }
        }

        return map;
    }


    /**
     * 删除链表重复元素
     *
     * @param head
     */
    public static void delete(com.ssjj.androidmvpdemo.datastructure.Node<Integer> head) {
        if (head == null)
            return;

        com.ssjj.androidmvpdemo.datastructure.Node<Integer> current = head;
        while (current != null) {
            com.ssjj.androidmvpdemo.datastructure.Node<Integer> runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }
    }


    /**
     * 递归 跳过所有重复的节点 ，当不重复时才保留
     *
     * @param pHead
     * @return
     */
    public Node deleteDuplication(Node pHead) {

        //当前只有0或1个节点，之间返回
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        if (pHead.value == pHead.next.value) {//当节点与前下一个节点重复
            Node pNode = pHead.next;
            //跳过与当前节点重复的所有节点，寻找第一个不重复的节点
            while (pNode != null && pNode.value == pHead.value) {
                pNode = pNode.next;
            }
            return deleteDuplication(pNode);
        } else {//当前节点不是重复节点
            pHead.next = deleteDuplication(pHead.next);//保留当前节点，从下一个节点开始递归
            return pHead;
        }

    }


    /**
     * 判断一个链表是否存在环儿
     *
     * @param header
     * @return 是否存在环儿
     */
    public static boolean isExistLoop(com.ssjj.androidmvpdemo.datastructure.Node header) {
        // 定义两个指针fast和slow,fast移动步长为2，slow移动步长为1
        com.ssjj.androidmvpdemo.datastructure.Node fast = header;
        com.ssjj.androidmvpdemo.datastructure.Node slow = header;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            //如果相遇则存在环儿，跳出
            if (fast == slow) {
                break;
            }
        }

        // 根据跳出循环的条件return
        if (fast == null || fast.next == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 计算有环儿链表的环儿长度<br>
     * fast, slow从碰撞点出发再次碰撞就是环儿的长度
     *
     * @param header
     * @return 返回环儿的长度
     */
    public static int loopLength(com.ssjj.androidmvpdemo.datastructure.Node header) {
        // 如果不存在环儿，返回0
        if (!isExistLoop(header)) {
            return 0;
        }

        com.ssjj.androidmvpdemo.datastructure.Node fast = header;
        com.ssjj.androidmvpdemo.datastructure.Node slow = header;
        int length = 0;
        boolean begin = false;
        boolean again = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 超过两圈后停止计数，跳出循环
            if (fast == slow && again) {
                break;
            }

            // 超过一圈后开始计数
            if (fast == slow) {
                begin = true;
                again = true;
            }

            if (begin) {
                ++length;
            }
        }

        return length;
    }

    /**
     * 找出环儿的连接点<br>
     * 碰撞点到连接点的距离=头指针到连接点的距离<br>
     * 因此，分别从碰撞点、头指针开始走，相遇的那个点就是连接点<br>
     *
     * @param head
     * @return 环儿连接点
     */


    private com.ssjj.androidmvpdemo.datastructure.Node getNode(com.ssjj.androidmvpdemo.datastructure.Node head) {

        com.ssjj.androidmvpdemo.datastructure.Node slow = head;
        com.ssjj.androidmvpdemo.datastructure.Node fast = head;

        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        fast = head;
        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;

            if (slow == fast) {
                break;
            }
        }
        return slow;
    }

    /**
     * 一个数组，每次传入一个数字后的中位数
     *
     * @param A
     * @param n
     * @return
     */
    public int[] getMiddle(int[] A, int n) {
        // write code here
        int[] res = new int[A.length];

        // 构造最大堆
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(n, comparator);

        // 构造最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(n);

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // 存入最小堆前判断当前元素是否小于最大堆的堆顶元素
                if (!maxHeap.isEmpty() && A[i] < maxHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(A[i]);
                } else {
                    minHeap.offer(A[i]);
                }
                res[i] = minHeap.peek();
            } else {
                // 存入最大堆之前判断当前元素是否大于最小堆的堆顶元素
                if (!minHeap.isEmpty() && A[i] > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(A[i]);
                } else {
                    maxHeap.offer(A[i]);
                }
                res[i] = maxHeap.peek();
            }
        }

        return res;
    }


    private int topK(int[] input, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(k);

        for (int i = 0; i < input.length; i++) {

            if (input[i] > priorityQueue.peek() && !priorityQueue.isEmpty()) {
                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                    priorityQueue.offer(input[i]);
                }
            }

        }

        return priorityQueue.peek();

    }


    /**
     * 括号匹配
     * haspmap保存左右字符串 ，依次入栈，找到匹配的出栈，找不到return false
     *
     * @param input
     * @return
     */
    private static boolean charMatch(String input) {

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for (int i = 0; i < input.length(); i++) {
            if (map.containsValue(input.charAt(i))) {
                stack.push(input.charAt(i));
            }

            if (map.containsKey(input.charAt(i))) {
                if (stack.isEmpty()) {
                    return false; //对不上
                }
                if (map.get(input.charAt(i)) == stack.peek()) {

                    stack.pop();//没有
                } else {
                    return false;
                }
            }


        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;

    }


    public int smallestDifference(int[] a, int[] b) {
        int sizeA = a.length, sizeB = b.length;
        if (sizeA <= 0 || sizeB <= 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        Arrays.sort(b);

        for (int i = 0; i < sizeA; i++) {
            int low = 0, high = sizeB - 1, target = a[i], mid = 0;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (b[mid] == target) {
                    return 0;
                } else if (b[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            result = min(result, Math.abs(a[i] - b[mid]));
            if (mid > 0) {
                result = min(result, Math.abs(a[i] - b[mid - 1]));
            }
            if (mid < sizeB - 1) {
                result = min(result, Math.abs(a[i] - b[mid + 1]));
            }
        }
        return result;
    }


    public int min(int a, int b) {
        if (a <= b) {
            return a;

        }
        return b;
    }

    int mid(int a[], int b[], int m, int n, int k) //假定m<=n
    {
        if (m > n)  //若不是m<=n,调换一下
            return mid(b, a, n, m, k);
        if (m == 0)
            return b[k - 1];
        if (k == 1)
            return Math.min(a[0], b[0]);

        int low = Math.min(k / 2, m);
        int high = k - low;
        if (a[low] == b[high])  //第k个数找到
            return a[low];
        if (a[low] > b[high])   //第k个数肯定不在b[0..ib]，所以，删除这些，找第k-ib个数
            return mid(a, b, m, n - high, k - high);
        else  //a[ia]<b[ib] 第k个数肯定不在a[0...ia],所以，删除这些，找k-ia个数
            return mid(a, b, m - low, n, k - low);
    }

    int getMid(int a[], int b[], int m, int n) {
        int k = (m + n) / 2;
        return mid(a, b, m, n, k);
    }

}
