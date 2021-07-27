package com.clsaa.algorithm.leetcode.offer.offer35;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);


        Node node2 = new Node(2);

        node1.next = node2;
        node1.random = node2;

        node2.next = null;
        node2.random = node2;

        Solution solution = new Solution();
        solution.print(node1);
        Node node = solution.copyRandomList(node1);
    }


    static class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node[] nodeArray = getNodeIndexArray(head);

            Node headCopy = new Node(head.val);
            Node cur = head;
            Node curCopy = headCopy;
            while (cur != null) {
                curCopy.val = cur.val;
                if (cur.next != null) {
                    curCopy.next = new Node(cur.next.val);
                }
                curCopy = curCopy.next;
                cur = cur.next;
            }

            Node[] copyNodeArray = getNodeIndexArray(headCopy);
            for (int i = 0; i < copyNodeArray.length; i++) {
                Node node = nodeArray[i];
                if (node.random != null) {
                    Node copyNode = copyNodeArray[i];
                    //never return -1
                    int index = getRandomIndex(nodeArray, node.random);
                    copyNode.random = copyNodeArray[index];
                }
            }

            return headCopy;
        }

        Node[] getNodeIndexArray(Node node) {
            int count = 0;
            Node cur = node;
            while (cur != null) {
                count++;
                cur = cur.next;
            }

            Node[] nodes = new Node[count];
            int i = 0;
            cur = node;
            while (cur != null) {
                nodes[i] = cur;
                cur = cur.next;
                i++;
            }
            return nodes;
        }

        int getRandomIndex(Node[] nodes, Node random) {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] == random) {
                    return i;
                }
            }
            return -1;
        }

        void print(Node head) {
            Node[] nodeIndexArray = getNodeIndexArray(head);
            for (Node node : nodeIndexArray) {
                System.out.print("[" + node.val + "," + getRandomIndex(nodeIndexArray, node.random) + "],");
            }
            System.out.println();
        }
    }

}