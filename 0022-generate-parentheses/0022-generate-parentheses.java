import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String s,
                          int open, int close, int n) {

        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        if (open < n) {
            backtrack(ans, s + "(", open + 1, close, n);
        }

        if (close < open) {
            backtrack(ans, s + ")", open, close + 1, n);
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna