package leetcode.p001;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author hofer.bhf
 * created on 2018/11/28 8:13 PM
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum3(nums, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer rest = target - nums[i];
            if (numsMap.containsKey(rest)) {
                int j = numsMap.get(rest);
                if (i != j) {
                    return new int[]{i, j};

                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int i = 0;
        int j = 1;
        for (; i < nums.length - 1; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        return null;
    }

    public static int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = 1;
        OK:
        for (; i < nums.length - 1; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    break OK;
                }
            }

        }
        return new int[]{i, j};
    }
}
