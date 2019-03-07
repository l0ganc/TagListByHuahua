package Graph.Queue_Hashtable;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph,
 * return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * time : O(m + n) m : nodes n : edges
 * space : O(m)
 */
public class LC133CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    public Node cloneGraph (Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(node, null);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node copy = new Node(node.val, new ArrayList<>());
            map.put(cur, copy);
            for (Node nei : node.neighbors) {
                if (!map.containsKey(nei)) {
                    map.put(nei, null);
                    queue.offer(nei);
                }
            }
        }

        for (Node original : map.keySet()) {
            Node copy = map.get(original);
            for (Node nei : original.neighbors) {
                copy.neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }

}
