package com.clsaa.algorithm.leetcode.offer.offer36;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    class Solution {

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }

            List<Node> nodeSorted = new ArrayList<>();

            traverse(nodeSorted, root);

            Node firstNode = nodeSorted.get(0);
            Node lastNode = nodeSorted.get(nodeSorted.size() - 1);

            for (int i = 0; i < nodeSorted.size(); i++) {
                Node currentNode = nodeSorted.get(i);
                Node nextNode = null;
                Node preNode = null;
                if (i == 0) {
                    preNode = lastNode;
                } else {
                    preNode = nodeSorted.get(i - 1);
                }

                if (i == nodeSorted.size() - 1) {
                    nextNode = firstNode;
                } else {
                    nextNode = nodeSorted.get(i + 1);
                }

                currentNode.left = preNode;
                currentNode.right = nextNode;
            }

            return firstNode;
        }

        void traverse(List<Node> result, Node node) {
            if (node == null) {
                return;
            }
            traverse(result, node.left);
            result.add(node);
            traverse(result, node.right);
        }
    }


}
