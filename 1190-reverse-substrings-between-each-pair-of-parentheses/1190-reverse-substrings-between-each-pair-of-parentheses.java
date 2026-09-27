class Solution {
    public String reverseParentheses(String s) {
        
        Stack<String> stack= new Stack<>();
        String current="";

        for(char ch: s.toCharArray())
        {
            if( ch=='(')
        {
            stack.push(current);
            current ="";

        }
        else if ( ch ==')'){
            current = new StringBuilder(current).reverse().toString();
            String previous = stack.pop();
            current = previous +  current;

        }
        else{
            current = current + ch;
        }
        }
        return current;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna