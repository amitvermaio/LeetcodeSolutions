use std::collections::{HashSet, VecDeque};

impl Solution {
    fn dfs(adj: &Vec<Vec<usize>>, vis: &mut Vec<bool>, node: usize) {
        if vis[node] {
            return;
        }

        vis[node] = true;

        for &nbr in &adj[node] {
            Self::dfs(adj, vis, nbr);
        }
    }

    pub fn remaining_methods(
        n: i32,
        k: i32,
        invocations: Vec<Vec<i32>>,
    ) -> Vec<i32> {
        let n = n as usize;
        let k = k as usize;

        let mut adj = vec![Vec::new(); n];

        for inv in invocations {
            adj[inv[0] as usize].push(inv[1] as usize);
        }

        let mut vis = vec![false; n];
        Self::dfs(&adj, &mut vis, k);

        let mut q = VecDeque::new();
        for i in 0..n {
            if !vis[i] {
                q.push_back(i);
            }
        }

        let mut set = HashSet::new();
        let mut flag = false;

        while let Some(node) = q.pop_front() {
            if !set.insert(node) {
                continue;
            }

            if vis[node] {
                flag = true;
            }

            for &nbr in &adj[node] {
                if !set.contains(&nbr) {
                    q.push_back(nbr);
                }
            }
        }

        if flag {
            for i in 0..n {
                if vis[i] {
                    set.insert(i);
                }
            }
        }

        set.into_iter().map(|x| x as i32).collect()
    }
}