import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Extended Testing class for Parking System Logic and GUI.
 */
public class Tests
{
    public static void main(String [] args)
    {
        Graph graph = new Graph();
        
        // ----------------------------------------------------
        // 1. יצירת צמתים רבים (כניסה, דרכים וחניות)
        // ----------------------------------------------------
        
        // כניסה לחניון
        Node enter = new Node(100, 1, 4, Node.NodeType.ENTRANCE);
        
        // צומתי דרכים (Roads)
        Node r1 = new Node(201, 3, 4, Node.NodeType.ROAD);
        Node r2 = new Node(202, 6, 2, Node.NodeType.ROAD);
        Node r3 = new Node(203, 6, 6, Node.NodeType.ROAD);
        
        // חניות באגף הצפוני (חלקן תפוסות, חלקן פנויות)
        Node p1 = new Node(301, 5, 1, Node.NodeType.PARKING); // תפוסה
        p1.setIsOccupied(true);
        
        Node p2 = new Node(302, 8, 1, Node.NodeType.PARKING); // פנויה (אבל רחוקה)
        p2.setIsOccupied(false);
        
        Node p3 = new Node(303, 8, 3, Node.NodeType.PARKING); // תפוסה
        p3.setIsOccupied(true);

        // חניות באגף הדרומי
        Node p4 = new Node(304, 5, 7, Node.NodeType.PARKING); // פנויה! (קרובה יחסית)
        p4.setIsOccupied(false);
        
        Node p5 = new Node(305, 8, 5, Node.NodeType.PARKING); // תפוסה
        p5.setIsOccupied(true);
        
        Node p6 = new Node(306, 8, 7, Node.NodeType.PARKING); // פנויה
        p6.setIsOccupied(false);

        // הוספת כל הצמתים לגרף
        graph.addNode(enter);
        graph.addNode(r1);
        graph.addNode(r2);
        graph.addNode(r3);
        graph.addNode(p1);
        graph.addNode(p2);
        graph.addNode(p3);
        graph.addNode(p4);
        graph.addNode(p5);
        graph.addNode(p6);

        // ----------------------------------------------------
        // 2. חיבור הקשתות (Edges) - יצירת רשת כבישים
        // ----------------------------------------------------
        
        // מהכניסה לדרכים
        graph.addEdge(enter, r1, 4.0, false);
        graph.addEdge(r1, r2, 5.0, false);
        graph.addEdge(r1, r3, 5.0, false);

        // חיבורי האגף הצפוני (מ-r2)
        graph.addEdge(r2, p1, 3.0, false);
        graph.addEdge(r2, p2, 4.0, false);
        graph.addEdge(r2, p3, 3.5, false);

        // חיבורי האגף הדרומי (מ-r3)
        graph.addEdge(r3, p4, 2.5, false); // הדרך לחנייה הפנויה 304
        graph.addEdge(r3, p5, 4.0, false);
        graph.addEdge(r3, p6, 4.5, false);

        // ----------------------------------------------------
        // 3. הדפסה ובדיקת אלגוריתם Router
        // ----------------------------------------------------
        System.out.println("--- Graph Structure ---");
        graph.printGraph();

        System.out.println("\n--- Testing Router (Dijkstra) ---");
        List<Node> path = Router.findPathToNearestParking(graph, enter);

        if (path == null || path.isEmpty()) {
            System.out.println("No available parking spot found!");
        } else {
            System.out.print("Shortest path to nearest FREE parking: ");
            for (int i = 0; i < path.size(); i++) {
                Node n = path.get(i);
                System.out.print(n.getId() + " (" + n.getType() + ")");
                if (i < path.size() - 1) {
                    System.out.print(" --> ");
                }
            }
            System.out.println();
        }

        // ----------------------------------------------------
        // 4. הרצת ה-GUI
        // ----------------------------------------------------
        SwingUtilities.invokeLater(() -> {
            ParkingFrame frame = new ParkingFrame(graph, enter);
            frame.setVisible(true);
        });
    }
}