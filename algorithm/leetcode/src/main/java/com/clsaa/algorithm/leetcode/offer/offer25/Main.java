package com.clsaa.algorithm.leetcode.offer.offer25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(2);
        ListNode la3 = new ListNode(4);

        la1.next = la2;
        la2.next = la3;

        ListNode lb1 = new ListNode(1);
        ListNode lb2 = new ListNode(3);
        ListNode lb3 = new ListNode(4);

        lb1.next = lb2;
        lb2.next = lb3;

        Solution solution = new Solution();
        ListNode result = solution.mergeTwoLists(la1, lb1);

        printList(result);

    }

    static void printList(ListNode l1) {
        ListNode cur = l1;
        do {
            System.out.print(cur.val + "->");
            cur = cur.next;
        } while (cur != null);
        System.out.println("\n-------");
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            ListNode result = l1.val < l2.val ? l1 : l2;
            ListNode resultCurrent = result;


            ListNode l1CurrentNode = l1.val < l2.val ? l1.next : l1;
            ListNode l2CurrentNode = l1.val < l2.val ? l2 : l2.next;

            while (true) {
                if (l1CurrentNode == null) {
                    resultCurrent.next = l2CurrentNode;
                    break;
                }
                if (l2CurrentNode == null) {
                    resultCurrent.next = l1CurrentNode;
                    break;
                }

                if (l1CurrentNode.val < l2CurrentNode.val) {
                    resultCurrent.next = l1CurrentNode;
                    l1CurrentNode = l1CurrentNode.next;
                    resultCurrent = resultCurrent.next;
                } else {
                    resultCurrent.next = l2CurrentNode;
                    l2CurrentNode = l2CurrentNode.next;
                    resultCurrent = resultCurrent.next;
                }
            }

            return result;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


