class Solution {
public:
    vector<int> findOrder(int c, vector<vector<int>>& pre) {
        vector<int> indegree(c, 0);
        vector<vector<int>> adj(c);
        for (auto &p : pre) {
            adj[p[1]].push_back(p[0]);
            indegree[p[0]]++;
        }

        queue<int> q;
        for (int i=0; i<c; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> res;
        int k = 0;

        // BFS
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            res.push_back(node);
            k++;
            for (auto &nbr : adj[node]) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    q.push(nbr);
                }
            }
        }

        return (k == c) ? res : vector<int>{};
    }
};