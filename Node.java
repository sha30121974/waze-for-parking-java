
/**
 * Presentes a point.
 *
 * @author (Shoval Shema)
 * @version (24/8/2026)
 */

public class Node
{
    public enum NodeType{
        ENTRANCE,ROAD,PARKING
    }
    // instance variables - replace the example below with your own
    private int id;
    private int x;
    private int y;
    private NodeType type;
    private boolean isOccupied;

    /**
     * Constructor for objects of class Node
     */
    public Node(int id, int x, int y, NodeType type)
    {
        // initialise instance variables
        this.id = id;
        this.x=x;
        this.y=y;
        this.type = type;
        this.isOccupied = false;
    }

    /**
     *  Getter methods
     * @return    the value of the fields;
     */
    public int getId(){ return this.id;}
    public int getX(){ return this.x;}
    public int getY(){ return this.y;}
    public NodeType getType(){ return this.type;}
    public boolean getIsOccupied(){ return this.isOccupied;}
    /**
     * set's methodes
     * @param    the value of the field isOccupied;
     */
    public void setIsOccupied(boolean isOccupied){this.isOccupied = isOccupied;}
    
}
