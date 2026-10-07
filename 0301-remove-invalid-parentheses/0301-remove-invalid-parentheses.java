import java.util.*;

class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            } 
            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, 0, 0, leftRemove, rightRemove, "");

        return new ArrayList<>(result);
    }

    private void backtrack(
        String s,
        int index,
        int balance,
        int removed,
        int leftRemove,
        int rightRemove,
        String current
    ) {

        // Reached end
        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current);
            }

            return;
        }

        char c = s.charAt(index);

        // Case 1: Parentheses
        if (c == '(' || c == ')') {

            // Remove current parenthesis
            if (c == '(' && leftRemove > 0) {
                backtrack(
                    s,
                    index + 1,
                    balance,
                    removed + 1,
                    leftRemove - 1,
                    rightRemove,
                    current
                );
            }

            if (c == ')' && rightRemove > 0) {
                backtrack(
                    s,
                    index + 1,
                    balance,
                    removed + 1,
                    leftRemove,
                    rightRemove - 1,
                    current
                );
            }
        }

        // Keep current character
        if (c == '(') {

            backtrack(
                s,
                index + 1,
                balance + 1,
                removed,
                leftRemove,
                rightRemove,
                current + c
            );

        } else if (c == ')') {

            // Can't have negative balance
            if (balance > 0) {
                backtrack(
                    s,
                    index + 1,
                    balance - 1,
                    removed,
                    leftRemove,
                    rightRemove,
                    current + c
                );
            }

        } else {

            // Letter
            backtrack(
                s,
                index + 1,
                balance,
                removed,
                leftRemove,
                rightRemove,
                current + c
            );
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna