import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] parent, depth, size, heavy, head, pos, base;
    int timer;
    char[] chars;
    SegmentTree seg;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        base = new int[n];

        Arrays.fill(heavy, -1);

        dfs(0, -1);
        timer = 0;
        decompose(0, 0);

        seg = new SegmentTree(base);

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);
                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));
            } else {
                int v = Integer.parseInt(parts[2]);
                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= seg.query(pos[u], pos[v]);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int maxSize = 0;

        for (int v : adj[u]) {
            if (v == p) continue;

            depth[v] = depth[u] + 1;
            int sub = dfs(v, u);
            size[u] += sub;

            if (sub > maxSize) {
                maxSize = sub;
                heavy[u] = v;
            }
        }

        return size[u];
    }

    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = timer;
        base[timer++] = 1 << (chars[u] - 'a');

        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }

        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int l, int r, int[] arr) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }

            int mid = (l + r) / 2;
            build(node * 2, l, mid, arr);
            build(node * 2 + 1, mid + 1, r, arr);
            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        void update(int node, int l, int r, int index, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = (l + r) / 2;
            if (index <= mid) {
                update(node * 2, l, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, r, index, value);
            }

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[node];
            if (r < ql || l > qr) return 0;

            int mid = (l + r) / 2;
            return query(node * 2, l, mid, ql, qr)
                 ^ query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna