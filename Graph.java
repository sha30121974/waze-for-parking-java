import java.util.*;

/**
 * Manegment of the roads and the parkings.
 *
 * @author (Shoval Shema)
 * @version (24/8/2026)
 */
public class Graph
{
    private List<Node> nodes;
    private Map<Node, List<Edge>> connections;

    public Graph()
    {
        this.nodes = new ArrayList<>();
        this.connections = new HashMap<>();
    }

    /**
     * add a new node to the list and the map
     *
     * @param  node     the new node
     */
    public void addNode(Node node)
    {
        if(!nodes.contains(node))
            nodes.add(node);
        connections.putIfAbsent(node, new ArrayList<>());  
    }

    public void addEdge(Node source, Node target, double weight, boolean bidirectional)
    {
        addNode(source);
        addNode(target);
        connections.get(source).add(new Edge(target, weight));
        if(bidirectional == true)
        {
            connections.get(target).add(new Edge(source, weight));                      
        }
    }

    public List<Edge> getNeighbors(Node node)
    {
        return connections.getOrDefault(node, Collections.emptyList());
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public void printGraph()
    {
        for(Node node : nodes)
        {
            System.out.println("ID ---> "  + node.getId());
            System.out.println("Type ---> " + node.getType());
            if(node.getType() == Node.NodeType.PARKING)
                System.out.println("Status ---> " + (node.getIsOccupied() ? "Occupied" : "Free"));
            System.out.println("Connections:");
            for(Edge edge : connections.getOrDefault(node,Collections.emptyList()))
            {
                System.out.println("   --> Leads to: " + edge.getTarget().getId() + " | Weight: " + edge.getWeight());
            }
            System.out.println("-----------------------------------");
        }
    }
}
