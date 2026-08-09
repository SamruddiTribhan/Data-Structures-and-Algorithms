class Solution {
    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {
        // All piles are taken
        if (i >= n) {
            return 0;
        }

        // Can take all remaining piles
        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;
        int currentSum = 0;

        for (int X = 1; X <= 2 * M && i + X <= n; X++) {
            currentSum += piles[i + X - 1];

            int opponent = solve(i + X, Math.max(M, X), piles);

            // Total stones current player can eventually get
            int currentPlayer = currentSum + 
                                (suffix[i + X] - opponent);

            best = Math.max(best, currentPlayer);
        }

        return dp[i][M] = best;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna