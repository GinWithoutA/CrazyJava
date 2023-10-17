package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc :
 */
public class Problem_0045_JumpGameII {
    /**
     * 三个变量搞定一切：
     * step：当前需要移动的次数，初始值是0，因为一开始不需要移动，本身就在0位置
     * cur：当前不超过step步能到达的最右位置，如果当前位置超过了cur，说明当前的step步已经不能满足我到达这个位置了，则需要step++
     * next：当前不超过step + 1步，也就是多走一步的情况下，能到达的最右位置
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int curStep = 0, nextStepFar = nums[0], curFar = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i > curFar) {
                // 当前位置已经不能由当前的step达到了，需要多一个step
                curStep++;
                curFar = nextStepFar;
            }
            // 没有在if里面，说明当前的位置能由当前step走到，因为当前step最远能走到cur位置，当前位置小于cur位置
            nextStepFar = Math.max(nextStepFar, i + nums[i]);
        }
        return curStep;
    }
}
