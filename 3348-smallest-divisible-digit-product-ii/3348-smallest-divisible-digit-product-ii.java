import java.util.Arrays;

public class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Validate if t contains prime factors other than 2, 3, 5, 7
        long tempT = t;
        int[] targetFactors = new int[10]; // index 2, 3, 5, 7
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (tempT % p == 0) {
                targetFactors[p]++;
                tempT /= p;
            }
        }
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();
        
        // Count factors present in the input string up to the first '0'
        int[][] prefixFactors = new int[n + 1][10];
        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '0') {
                firstZero = i;
                break;
            }
            // Copy previous counts
            for (int p : primes) {
                prefixFactors[i + 1][p] = prefixFactors[i][p];
            }
            int val = c - '0';
            for (int p : primes) {
                int temp = val;
                while (temp % p == 0 && temp > 0) {
                    prefixFactors[i + 1][p]++;
                    temp /= p;
                }
            }
        }

        // Case A: The original string contains no zeros and already satisfies divisibility by t
        if (firstZero == -1) {
            boolean satisfied = true;
            for (int p : primes) {
                if (prefixFactors[n][p] < targetFactors[p]) {
                    satisfied = false;
                    break;
                }
            }
            if (satisfied) {
                return num;
            }
        }

        // Limit scanning position up to the index of the first '0'
        int scanLimit = (firstZero == -1) ? n - 1 : firstZero;

        // Step 2: Backtrack from right to left to find where we can increment a digit
        for (int i = scanLimit; i >= 0; i--) {
            int currentDigit = num.charAt(i) - '0';
            
            // Try a larger digit at position i
            for (int d = currentDigit + 1; d <= 9; d++) {
                int[] remainingNeeded = new int[10];
                boolean possible = true;
                
                // Add factors of current digit 'd' to prefix factor pool
                int[] currentPrefix = new int[10];
                for (int p : primes) {
                    currentPrefix[p] = prefixFactors[i][p];
                    int temp = d;
                    while (temp % p == 0) {
                        currentPrefix[p]++;
                        temp /= p;
                    }
                    
                    remainingNeeded[p] = Math.max(0, targetFactors[p] - currentPrefix[p]);
                }

                int remSpace = n - 1 - i;
                if (canFit(remainingNeeded, remSpace)) {
                    // Reconstruct string prefix + digit d
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(d);
                    
                    // Greedily fill remaining suffix
                    fillSuffixMinimal(sb, remainingNeeded, remSpace);
                    return sb.toString();
                }
            }
        }

        // Step 3: If no valid configuration of length n works, build a number of length n + 1 (or longer)
        int nextLen = (firstZero == -1) ? n + 1 : n;
        while (true) {
            for (int d = 1; d <= 9; d++) {
                int[] remainingNeeded = new int[10];
                int temp = d;
                for (int p : primes) {
                    int count = 0;
                    while (temp % p == 0) {
                        count++;
                        temp /= p;
                    }
                    remainingNeeded[p] = Math.max(0, targetFactors[p] - count);
                }
                
                if (canFit(remainingNeeded, nextLen - 1)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(d);
                    fillSuffixMinimal(sb, remainingNeeded, nextLen - 1);
                    return sb.toString();
                }
            }
            nextLen++; // Fallback structural expansion block
        }
    }

    // Helper checks if required primes can compress into given remaining slots
    private boolean canFit(int[] needed, int spaces) {
        int n2 = needed[2];
        int n3 = needed[3];
        int n5 = needed[5];
        int n7 = needed[7];

        int totalDigits = 0;
        
        // Maximize compression into single digits
        // 9 uses up two 3s
        int count9 = n3 / 2;
        totalDigits += count9;
        n3 %= 2;

        // 8 uses up three 2s
        int count8 = n2 / 3;
        totalDigits += count8;
        n2 %= 3;

        // 6 uses up one 2 and one 3
        int count6 = Math.min(n2, n3);
        totalDigits += count6;
        n2 -= count6;
        n3 -= count6;

        // 4 uses up two 2s
        int count4 = n2 / 2;
        totalDigits += count4;
        n2 %= 2;

        // Remaining 2s, 3s, 5s, 7s map directly 1-to-1 to single digits
        totalDigits += n2 + n3 + n5 + n7;

        return totalDigits <= spaces;
    }

    // Fills empty string slots up to `spaces` capacity with minimal layout arrangement
    private void fillSuffixMinimal(StringBuilder sb, int[] needed, int spaces) {
        int[] counts = new int[10];
        counts[5] = needed[5];
        counts[7] = needed[7];

        int n2 = needed[2];
        int n3 = needed[3];

        counts[9] = n3 / 2;
        n3 %= 2;

        counts[8] = n2 / 3;
        n2 %= 3;

        int count6 = Math.min(n2, n3);
        counts[6] = count6;
        n2 -= count6;
        n3 -= count6;

        counts[4] = n2 / 2;
        n2 %= 2;

        counts[2] = n2;
        counts[3] = n3;

        int requiredSlots = 0;
        for (int i = 2; i <= 9; i++) {
            requiredSlots += counts[i];
        }

        // Fill non-factor places with 1 first to keep the numerical scale minimal
        int ones = spaces - requiredSlots;
        for (int i = 0; i < ones; i++) {
            sb.append('1');
        }

        // Append non-one digits cleanly in ascending sorted digit order
        for (int d = 2; d <= 9; d++) {
            for (int i = 0; i < counts[d]; i++) {
                sb.append(d);
            }
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna