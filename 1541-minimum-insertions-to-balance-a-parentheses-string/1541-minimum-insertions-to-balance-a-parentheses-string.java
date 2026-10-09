class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertion = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertion++;
                }

                if (open > 0) {
                    open--;
                } else {
                    insertion++;
                }
            }
        }

        insertion += open * 2;
        return insertion;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna