class Solution {
public:
    bool hasCycle(vector<vector<int>>& adj, int node, vector<bool>& vis, vector<bool>& rec) {
        if (vis[node] && rec[node])
            return true;
        if (vis[node])
            return false;
        
        vis[node] = true;
        rec[node] = true;

        for (auto &nbr : adj[node]) {
            if (hasCycle(adj, nbr, vis, rec)) {
                return true;
            }
        }

        rec[node] = false;

        return false;
    }

    bool canFinish(int courses, vector<vector<int>>& pre) {
        vector<vector<int>> adj(courses);

        for (auto &p : pre) {
            adj[p[0]].push_back(p[1]);
        }

        vector<bool> vis(courses, false);
        vector<bool> rec(courses, false);

        for (int i=0; i<courses; i++) {
            if (hasCycle(adj, i, vis, rec)) {
                return false;
            }
        }

        return true;
    }
};