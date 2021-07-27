package com.clsaa.algorithm.leetcode.offer.offer22;

import java.util.ArrayList;
import java.util.List;

public class Main {

    class Solution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            if (head == null || k == 0) {
                return null;
            }

            ListNode cur = head;

            int nodeCount = 0;
            while (cur != null) {
                nodeCount++;
                cur = cur.next;
            }

            int fromIndex = nodeCount - k;
            int nodeIndex = 0;
            cur = head;
            while (cur != null) {
                if (nodeIndex == fromIndex) {
                    return cur;
                }
                nodeIndex++;
                cur = cur.next;
            }
            return null;
        }
    }
}
