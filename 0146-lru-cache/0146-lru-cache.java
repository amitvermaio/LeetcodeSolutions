class LRUCache {
    class Node {
        int key;
        int val;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    HashMap<Integer, Node> mp;
    int limit;

    public LRUCache(int capacity) {
        mp = new HashMap<>();
        limit = capacity;

        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        Node temp = head.next;

        head.next = node;
        node.prev = head;

        node.next = temp;
        temp.prev = node;
    }

    private void deleteNode(Node node) {
        Node prevv = node.prev;
        Node nextt = node.next;

        prevv.next = nextt;
        nextt.prev = prevv;
    }
    
    public int get(int key) {
        if (!mp.containsKey(key)) 
            return -1;
        
        Node node = mp.get(key);
        deleteNode(node);
        addNode(node);

        return node.val;
    }
    
    public void put(int key, int value) {
        if (mp.containsKey(key)) {
            deleteNode(mp.get(key));
            mp.remove(key);
        } 
        
        if (mp.size() == limit) {
            mp.remove(tail.prev.key);
            deleteNode(tail.prev);
        } 

        Node node = new Node(key, value);
        mp.put(key, node);
        addNode(node);
    }
}