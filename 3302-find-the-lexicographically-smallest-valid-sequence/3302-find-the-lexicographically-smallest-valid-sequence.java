class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[j] = earliest index in word1 that can match
        // word2[j...] exactly.
        int[] suf = new int[m];
        Arrays.fill(suf, -1);

        int j = m - 1;

        for (int i = n - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                suf[j] = i;
                j--;
            }
        }

        int[] ans = new int[m];

        j = 0;
        boolean usedMismatch = false;
        int count = 0;

        for (int i = 0; i < n && j < m; i++) {

            // Characters match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[count++] = i;
                j++;
            }

            // Use the one allowed mismatch
            else if (!usedMismatch) {

                // If this is the last character, mismatch is always possible.
                boolean possible = (j == m - 1);

                // Otherwise, remaining characters must match exactly.
                if (!possible &&
                    suf[j + 1] != -1 &&
                    suf[j + 1] > i) {
                    possible = true;
                }

                if (possible) {
                    ans[count++] = i;
                    j++;
                    usedMismatch = true;
                }
            }
        }

        if (count != m) {
            return new int[0];
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna