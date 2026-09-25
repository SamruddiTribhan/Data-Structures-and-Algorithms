import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s, int left, int right) {

        Set<String> result = new HashSet<>();

        // Start with an empty string for concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = left;

        while (i <= right) {

            char ch = s.charAt(i);

            // Expression inside {...}
            if (ch == '{') {

                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;
                    j++;
                }

                // Parse inside braces
                Set<String> inside = parse(s, i + 1, j - 2);

                // Concatenate current × inside
                current = combine(current, inside);

                i = j;

            } else if (ch == ',') {

                // Union: save current part
                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;

            } else {

                // Normal lowercase letter
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = combine(current, letter);

                i++;
            }
        }

        // Add last part
        result.addAll(current);

        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna