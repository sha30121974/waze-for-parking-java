
/**
 * presents a connection between two points.
 *
 * @author (Shoval Shema)
 * @version (24/8/2026)
 */
public class Edge
{
    private final Node target; // The final destination
    private final double weight; // The traveling time

    /**
     * Constructor for objects of class Edge
     * @param   a target point and the traveling time
     */
    public Edge(Node target, double weight)
    {
        this.target = target;
        this.weight = weight;
    }

    /**
     * Getter methods
     * @return    the value of the fields
     */
    public Node getTarget(){ return this.target; }
    public double getWeight(){ return this.weight; }
    
}
