class Solution {
    public int dominantIndices(int[] nums) {
        int length = nums.length;
        int dominantCount = 0;

        // Traverse from second-last element to the start
        for (int index = length - 2; index >= 0; index--) {
            int rightSideCount = length - index - 1;

            // nums[index + 1] already stores the sum of all elements to the right
            int rightSideSum = nums[index + 1];

            if (nums[index] > rightSideSum / rightSideCount) {
                dominantCount++;
            }

            // Convert nums[index] into prefix sum from this index to the end
            nums[index] += nums[index + 1];
        }

        return dominantCount;
    }
}
