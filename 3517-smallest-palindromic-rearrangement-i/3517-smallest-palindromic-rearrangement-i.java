class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        StringBuilder left = new StringBuilder();
        String middle="";
        // int len = s.length();

        for(char ch:s.toCharArray()){
            freq[ch-'a']++;
        }
        for(int i =0; i< 26;i++){
            if(freq[i]%2==1){
                middle = String.valueOf((char)('a' + i));

            }
            for(int j = 0; j< freq[i]/2;j++){
                left.append((char)('a'+i));
            }
           

    }
    String res =left.toString() + middle + left.reverse().toString();
    return res;
  
}}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna