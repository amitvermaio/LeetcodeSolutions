class Solution {
    public Node connect(Node root) {
        if (root == null)
            return root;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int sz = q.size();

            Node prev = null;
            for (int i=0; i<sz; i++) {
                Node curr = q.poll();

                if (prev != null) {
                    prev.next = curr;    
                } 

                prev = curr;

                if (curr.left != null) 
                    q.offer(curr.left);
                
                if (curr.right != null)
                    q.offer(curr.right);
            }
        }        

        return root;
    }
}