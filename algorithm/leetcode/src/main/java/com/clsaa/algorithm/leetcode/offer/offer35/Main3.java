package com.clsaa.algorithm.leetcode.offer.offer35;

import java.util.HashMap;
import java.util.Map;

public class Main3 {
    public static void main(String[] args) {
        Node node1 = new Node(1);


        Node node2 = new Node(2);

        node1.next = node2;
        node1.random = node2;

        node2.next = null;
        node2.random = node2;

        Solution solution = new Solution();
//        solution.print(node1);
        Node node = solution.copyRandomList(node1);
    }


    static class Solution { //HashMap实现
        public Node copyRandomList(Node head) {
            HashMap<Node, Node> map = new HashMap<>(); //创建HashMap集合
            Node cur = head;
            //复制结点值
            while (cur != null) {
                //存储put:<key,value1>
                map.put(cur, new Node(cur.val)); //顺序遍历，存储老结点和新结点(先存储新创建的结点值)
                cur = cur.next;
            }
            //复制结点指向
            cur = head;
            while (cur != null) {
                //得到get:<key>.value2,3
                map.get(cur).next = map.get(cur.next); //新结点next指向同旧结点的next指向
                map.get(cur).random = map.get(cur.random); //新结点random指向同旧结点的random指向
                cur = cur.next;
            }
            //返回复制的链表
            return map.get(head);
        }
    }

}
