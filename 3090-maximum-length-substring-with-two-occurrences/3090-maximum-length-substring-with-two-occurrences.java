class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLength = 0;
        int[] count = new int[26];
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            // Include the current character in the window
            char rightChar = s.charAt(right);
            count[rightChar - 'a']++;

            // Shrink the window from the left if any character occurs more than twice
            while (count[rightChar - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }

            // Update the maximum length of a valid substring found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna