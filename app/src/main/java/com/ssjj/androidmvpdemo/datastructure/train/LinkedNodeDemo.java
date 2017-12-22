package com.ssjj.androidmvpdemo.datastructure.train;

/**
 * @author songyu
 * @date 2017/12/20
 */
public class LinkedNodeDemo {
    public static void main(String[] args) {

        LinkedNodeDemo demo = new LinkedNodeDemo();
        demo.test();
    }

    public void test() {

        Node head = initLinkedList(10);

        printLinkedList(head);

        System.out.println("**************");

        //反转链表
//        Node node = reverseLinkedList(head);
        Node node = resort(head);
        printLinkedList(node);
    }

    /**
     * 反转链表
     **/
    private Node reverseLinkedList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node next = null;
        while (head.next != null) {
            next = head.next;   //保存下一个节点
            head.next = prev;   //重置next
            prev = head;    //保存当前节点
            head = next;
        }
        head.next = prev;
        return head;
    }

    /**
     * 初始化链表
     **/
    private Node initLinkedList(int num) {
        Node head = new Node(0, null);
        Node cur = head;
        for (int i = 1; i < num; i++) {
            cur.next = new Node(i, null);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 打印链表
     **/
    private void printLinkedList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    /**
     * 单向链表定义
     **/
    static class Node<T> {
        private T value;    //节点值
        private Node<T> next;   //后继节点

        public Node() {
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

//    private Node resort(Node head){
//        if(head == null || head.next== null){
//            return head;
//        }
//        Node p=null;
//        Node n=null;
//        while (head.next!=null){
//          n=head.next;
//          head.next = p;
//          p= head;
//          head= n;
//        }
//        head.next=p;
//        return head;
//
//    }


    private Node resort(Node head) {
        if(head==null || head.next==null){
            return head;
        }

        Node p = head.next;
        head.next=null;
        Node last=resort(p);
        p.next = head;
        return last;
    }


}
