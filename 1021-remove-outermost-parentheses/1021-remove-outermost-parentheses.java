class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder answer= new StringBuilder();
        int depth=0;
        // String result="";
        for(char ch: s.toCharArray())   {
            if(ch =='('){
        
                if(depth !=0)
                {
                    answer.append('(');
                  
                }
                 depth++;
            }
            else{
                depth--;
                if(depth !=0){
                    answer.append(')');

                }

            }
        }
        return answer.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna