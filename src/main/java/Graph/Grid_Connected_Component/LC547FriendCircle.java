package Graph.Grid_Connected_Component;

/**
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * 既可以用常规的DFS做，也可以用union find做
 * 时间复杂度O(N^2), 空间复杂度O(n)
 *
 */
public class LC547FriendCircle {
    public static int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }


    public static void main(String[] args) {
        int[][] M = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        };
        System.out.println(findCircleNum(M));
        System.out.println(findCircleNumMethod2(M));
    }


    // 以下是union find方法
    public static int findCircleNumMethod2(int[][] M) {
        int res = 0;
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }

        int m = M.length;
        int n = M[0].length;
        int[] roots = new int[m];
        for (int i = 0; i < m; i++) {
            roots[i] = i;
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    int p = findParent(roots, i);
                    int q = findParent(roots, j);
                    if (p != q) {
                        roots[p] = q;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (roots[i] == i) {
                res++;
            }
        }
        return res;
    }

    private static int findParent(int[] roots, int id) {
        while (id != roots[id]) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return roots[id];
    }

}
