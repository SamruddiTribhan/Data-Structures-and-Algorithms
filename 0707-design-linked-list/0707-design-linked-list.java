class MyLinkedList {
    
    // Internal Node class to represent each element
    private class Node {
        int val;
        Node next;
        
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
    
    private int size;
    private Node dummyHead;

    /** Initializes the MyLinkedList object. */
    public MyLinkedList() {
        this.size = 0;
        this.dummyHead = new Node(0); // Sentinel node to simplify insertions/deletions
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    /** Append a node of value val as the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node. If index equals the length, 
        the node will be appended to the end of the list. If index is greater than the length, 
        the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        
        size++;
        Node pred = dummyHead;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        
        Node toAdd = new Node(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        
        size--;
        Node pred = dummyHead;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        
        pred.next = pred.next.next;
    }
}
