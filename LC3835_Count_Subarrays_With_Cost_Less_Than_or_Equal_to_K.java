class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;

        // Deques to maintain indices of max and min elements in current window
        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();
        ArrayDeque<Integer> minDeque = new ArrayDeque<>();

        long totalSubarrays = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {

            // Maintain decreasing deque for maximum values
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // Maintain increasing deque for minimum values
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // Shrink window if cost exceeds k
            while (left <= right) {
                long length = right - left + 1;
                long maxVal = nums[maxDeque.peekFirst()];
                long minVal = nums[minDeque.peekFirst()];
                long cost = length * (maxVal - minVal);

                if (cost <= k) break;

                // Remove elements going out of the window
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }

            // Count all valid subarrays ending at 'right'
            totalSubarrays += (right - left + 1);
        }

        return totalSubarrays;
    }
}
