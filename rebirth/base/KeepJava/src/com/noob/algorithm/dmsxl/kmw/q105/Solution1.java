package com.noob.algorithm.dmsxl.kmw.q105;

import com.noob.algorithm.dmsxl.graph.GraphInputUtil;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.List;

/**
 * KMW105-有向图的完全可达性
 */
public class Solution1 {

    // dfs版本1：遍历当前节点
    public static void dfs(List<List<Integer>> graph, boolean[] visited, int key) {
        if (visited[key]) {
            return; // 如果节点已经遍历过则return
        }
        visited[key] = true; // 标记节点访问状态

        // 搜索节点
        for (int nextKey : graph.get(key)) {
            dfs(graph, visited, nextKey); // 遍历节点的邻接节点，检索路径
        }
    }

    public static void main(String[] args) {
        // 1.输入控制（输入邻接表）
        List<List<Integer>> graph = GraphInputUtil.getTableGraph(0); // 1 表示手动输入控制
        PrintUtil.printGraphTable(graph);

        // 2.dfs 检索有向图
        boolean[] visited = new boolean[graph.size()]; // 存储已遍历节点(graph存储处理范围为[0,n],即graph.size()为n个节点，此处正常处理即可)
        dfs(graph, visited, 1); // 判断从1号节点出发是否可以到达其他所有节点

        // 3.校验visited标记（判断节点1出发是否可以到达其他节点）
        for (int i = 1; i < graph.size(); i++) { // 节点编号范围[1,graph.size()) (graph.size()的取值为n+1)
            if (!visited[i]) {
                System.out.println("-1" + "不可达");
                return;
            }
        }
        System.out.println("1" + "可达");
    }
}