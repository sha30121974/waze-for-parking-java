import java.util.*;

/**
 * The negotiation to find the nearest free parking using DIJKSTRA.
 *
 * @author (Shoval Shema)
 * @version (31/8/2026)
 */
public class Router
{
    public static List<Node> findPathToNearestParking(Graph graph, Node start)
    {
        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();

        // אתחול מרחקים אינסופיים לכל הצמתים בגרף
        for (Node node : graph.getNodes()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        // תור עדיפויות הממיין לפי המרחק במפת המרחקים
        PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingDouble(n -> distances.getOrDefault(n, Double.POSITIVE_INFINITY))
        );
        pq.add(start);

        // הלולאה הראשית של Dijkstra
        while (!pq.isEmpty())
        {
            Node current = pq.poll();

            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.getTarget();
                double newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY))
                {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        // מציאת החנייה הפנויה הקרובה ביותר
        Node nearestParking = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Node node : graph.getNodes()) {
            if (node.getType() == Node.NodeType.PARKING && !node.getIsOccupied()) {
                double dist = distances.getOrDefault(node, Double.POSITIVE_INFINITY);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearestParking = node;
                }
            }
        }

        if (nearestParking == null || minDistance == Double.POSITIVE_INFINITY) {
            return new ArrayList<>(); // החזרת רשימה ריקה אם אין חנייה פנויה
        }

        // שחזור המסלול מהחנייה הקרובה בחזרה לכניסה
        List<Node> path = new ArrayList<>();
        Node curr = nearestParking;

        while (curr != null) {
            path.add(curr);
            curr = previous.get(curr);
        }

        Collections.reverse(path);
        return path;
    }
}