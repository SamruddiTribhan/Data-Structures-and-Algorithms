class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        long curr = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            switch (ch) {
                case '*':
                    if (curr > 0) curr--;
                    break;
                case '#':
                    curr = Math.min(curr * 2, Long.MAX_VALUE);
                    break;
                case '%':
                    break;
                default:
                    curr++;
            }

            len[i] = curr;
        }

        if (k >= curr) return '.';

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long currLen = len[i];
            long prevLen = (i == 0) ? 0 : len[i - 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == currLen - 1) return ch;
            } else if (ch == '#') {
                if (prevLen > 0) k %= prevLen;
            } else if (ch == '%') {
                k = currLen - 1 - k;
            }
        }

        return '.';
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna