package com.noob.algorithm.daily.codeTop;

import java.util.HashSet;

/**
 * 🟡003 无重复的最长子串
 */
public class Solution003_01 {

    /**
     * 滑动窗口：定义滑动窗口存储不含重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        // 定义最长不重复子串长度
        int max = 0;
        // 构建集合存储不重复字符子串:left,right分别指向这个子串的左右边界（left缩边、right扩边）
        HashSet<Character> set = new HashSet<>();
        // 定义双指针
        int left = 0, right = 0;
        // 当right指针滑动到字符串尾部则结束（此处求的是最长子串，当right移动到字符串尾部遍历结束，因为剩下的情况就是left缩边，长度只会越来越小）
        while (right < s.length()) {
            // right用于扩展搜索，指向当前遍历元素位置，判断是否已经出现在set中
            char cur = s.charAt(right); // 注意用字符类型处理字符串中的每个字符
            if (!set.contains(cur)) {
                // 如果指向元素不在集合中则加入集合并更新最长不重复子串的长度
                set.add(s.charAt(right));
                right++;
                // 更新集合大小
                max = Math.max(max, set.size());
            } else {
                /**
                 * 如果指向元素已在集合中则需逐步移出前面出现这个重复元素及在其前面的元素，此处有两种思路处理
                 * ① 逐步移除：当出现重复元素时，每次从左侧移除一个元素（这个思路处理的核心在于每次校验只要set存在重复元素，就逐步从左侧开始缩边移除一位直到把满足加入新的元素并更新）
                 * ② 遍历移除：当出现重复元素时，从左侧开始搜索到这个重复的元素并移除，在遍历的过程中需要将搜索过的元素也一并移除
                 */
                while (s.charAt(left) != cur) { // 定位重复元素所在位置，并逐步移除前面的元素
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(cur);  // 此处等价于 set.remove(s.charAt(left));
                left++;
            }
        }
        // 返回结果
        return max;
    }

    public static void main(String[] args) {
        Solution003_01 s = new Solution003_01();
        System.out.println(s.lengthOfLongestSubstring("pwwkew"));
    }
}
