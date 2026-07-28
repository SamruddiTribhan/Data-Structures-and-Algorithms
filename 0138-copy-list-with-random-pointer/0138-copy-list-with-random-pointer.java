/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Step 1: Create clone nodes and interweave them with original nodes
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            Node copyNode = new Node(curr.val);
            curr.next = copyNode;
            copyNode.next = nextNode;
            curr = nextNode;
        }
        
        // Step 2: Assign random pointers to the cloned nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Step 3: Separate the original list and cloned list
        curr = head;
        Node dummyHead = new Node(0);
        Node copyCurr = dummyHead;
        
        while (curr != null) {
            Node nextOriginalNode = curr.next.next;
            
            // Extract the copy node
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;
            
            // Restore the original list structure
            curr.next = nextOriginalNode;
            
            curr = nextOriginalNode;
        }
        
        return dummyHead.next;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna