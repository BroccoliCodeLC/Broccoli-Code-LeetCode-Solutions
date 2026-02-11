class Solution {
    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            long currentValue = nums[i];

            // Keep merging while the last element equals currentValue
            while (!result.isEmpty() && result.get(result.size() - 1) == currentValue) {
                result.remove(result.size() - 1);
                currentValue *= 2;  // Merge into doubled value
            }

            result.add(currentValue);
        }

        return result;
    }
}
