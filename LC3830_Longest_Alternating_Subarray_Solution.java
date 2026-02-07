class Solution {
    public int longestAlternating(int[] nums) {
    int n = nums.length;

    // longest alternating subarray ending at each index (from left)
    int[] longestEndingHere = new int[n];
    for (int i = 0; i < n; i++) longestEndingHere[i] = 1;

    for (int i = 1; i < n; i++) {
        int currentDirection = compare(nums[i], nums[i - 1]);

        if (currentDirection != 0) {
            if (i > 1 && compare(nums[i - 1], nums[i - 2]) == -currentDirection) {
                longestEndingHere[i] = longestEndingHere[i - 1] + 1;
            } else {
                longestEndingHere[i] = 2;
            }
        }
    }

    // longest alternating subarray starting at each index (from right)
    int[] longestStartingHere = new int[n];
    for (int i = 0; i < n; i++) longestStartingHere[i] = 1;

    for (int i = n - 2; i >= 0; i--) {
        int currentDirection = compare(nums[i + 1], nums[i]);

        if (currentDirection != 0) {
            if (i < n - 2 && compare(nums[i + 2], nums[i + 1]) == -currentDirection) {
                longestStartingHere[i] = longestStartingHere[i + 1] + 1;
            } else {
                longestStartingHere[i] = 2;
            }
        }
    }

    int maxLength = 0;

    // case 1: no removal
    for (int i = 0; i < n; i++) {
        maxLength = Math.max(maxLength, longestEndingHere[i]);
    }

    // case 2: remove one element at index i and connect left & right parts
    for (int i = 1; i < n - 1; i++) {
        int newDirection = compare(nums[i + 1], nums[i - 1]);

        if (newDirection != 0) {
            int leftLength =
                (i > 1 && compare(nums[i - 1], nums[i - 2]) == -newDirection)
                    ? longestEndingHere[i - 1]
                    : 1;

            int rightLength =
                (i < n - 2 && compare(nums[i + 2], nums[i + 1]) == -newDirection)
                    ? longestStartingHere[i + 1]
                    : 1;

            maxLength = Math.max(maxLength, leftLength + rightLength);
        }
    }

    return maxLength;
}

private int compare(int a, int b) {
    return (a > b ? 1 : 0) - (a < b ? 1 : 0);
}

}
