package com.noob.algorithm.dmsxl.leetcode.q102;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102 二叉树的层序遍历
 */
public class Solution2 {

    /**
     * 层序遍历：分层遍历（辅助队列实现）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // root为null判断
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义遍历结果集
        List<List<Integer>> res = new ArrayList<>();

        // 构建辅助队列进行遍历
        Deque<TreeNode> queue = new LinkedList<>(); // 要使用队列的方法，此处用Deque接收
        queue.offer(root); // 初始化队列
        // 遍历队列元素
        while (!queue.isEmpty()) {
            // 定义当层序列
            List<Integer> curList = new LinkedList<>();
            // 记录当前队列元素个数（当层元素个数）
            int queueSize = queue.size();
            // 分层进行遍历
            for (int i = 0; i < queueSize; i++) {
                // 取出当前队列元素
                TreeNode cur = queue.poll();
                // 记录元素值
                curList.add(cur.val);

                // 如果当前节点存在左右节点，则分别入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历完成，封装结果集，随后进入下一层遍历
            res.add(curList);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        // 层序遍历调用
        Solution2 solution = new Solution2();
        System.out.println(solution.levelOrder(root));
    }


}
