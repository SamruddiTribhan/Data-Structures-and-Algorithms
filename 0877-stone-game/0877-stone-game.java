class Solution {

    public boolean stoneGame(int[] piles) {

        int n = piles.length;

        int[] dp = new int[n];

        for (int i = 0; i < n; i++)
            dp[i] = piles[i];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                int left = piles[i] - dp[j];
                int right = piles[j] - dp[j - 1];

                dp[j] = Math.max(left, right);
            }
        }

        return dp[n - 1] > 0;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna