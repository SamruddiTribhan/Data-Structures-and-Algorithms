class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min =Integer.MAX_VALUE; 
        int max =Integer.MIN_VALUE; 
        HashSet<Integer> set= new HashSet<>();

        for(int n : nums){
            min = Math.min(min, n);
            max = Math.max(max, n);
            set.add(n);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = min+1; i< max; i++){
            if(!set.contains(i)){
                ans.add(i);
            }
        }
        return ans;


    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna