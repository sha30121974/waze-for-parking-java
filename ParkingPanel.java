import javax.swing.JPanel;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;   
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Font;

/**
 * The draw of the parking area.
 *
 * @author (Shoval Shema)
 * @version (14/09/2026)
 */
public class ParkingPanel extends JPanel
{
    private Graph graph;
    private List<Node> currentPath;

    private final int SCALE = 40;
    private final int MARGIN = 50;
    private final int RADIUS = 35;

    /**
     * Constructor for objects of class ParkingPanel
     */
    public ParkingPanel(Graph graph)
    {
        this.graph = graph;
        setBackground(Color.LIGHT_GRAY);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  path  
     * @return    the sum of x and y
     */
    public void setPath(List<Node> path)
    {
        this.currentPath = path;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Node node : graph.getNodes())
        {
            for(Edge edge : graph.getNeighbors(node))
            {
                Node target = edge.getTarget();
                int x1 = (node.getX() * SCALE) + MARGIN;
                int y1 = (node.getY() * SCALE) + MARGIN;
                int x2 = (target.getX() * SCALE) + MARGIN;
                int y2 = (target.getY() * SCALE) + MARGIN;
                g.setColor(Color.DARK_GRAY);
                g.drawLine(x1,y1,x2,y2);
            }
        }
        if(currentPath != null && currentPath.size() > 1){ 
            g.setColor(Color.BLUE);
            for(int i = 0; i<currentPath.size() - 1;i++)
            {
                Node u = currentPath.get(i);
                Node v = currentPath.get(i+1);
                int x1 = (u.getX() * SCALE) + MARGIN;
                int y1 = (u.getY() * SCALE) + MARGIN;
                int x2 = (v.getX() * SCALE) + MARGIN;
                int y2 = (v.getY() * SCALE) + MARGIN;
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g.drawLine(x1,y1,x2,y2);
            }
        }
        for(Node node : graph.getNodes())
        {
            int centerX = (node.getX() * SCALE) + MARGIN;
            int centerY = (node.getY() * SCALE) + MARGIN;
            int drawX = centerX - (RADIUS / 2);
            int drawY = centerY - (RADIUS / 2);
            if(node.getType() == Node.NodeType.PARKING)
            {
                if(node.getIsOccupied())
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.GREEN);
            }
            else if(node.getType() == Node.NodeType.ENTRANCE)
                g.setColor(Color.ORANGE);
            else
                g.setColor(Color.GRAY); 
            g.fillOval(drawX, drawY, RADIUS, RADIUS);
            g.setColor(Color.BLACK);
            g.drawOval(drawX, drawY, RADIUS, RADIUS);
            g.drawString("ID: " + node.getId(), centerX - 12 ,centerY + 5);
        }
        // מקרא צבעים (Legend) בפינה השמאלית-על// 1. הגדרת רוחב וגובה המקרא
        int legendWidth = 150;
        int legendHeight = 110;
        int margin = 20; // מרחק מקצה המסך

        // 2. חישוב המיקום לפינה הימנית התחתונה
        int legendX = getWidth() - legendWidth - margin;
        int legendY = getHeight() - legendHeight - margin;

        // 3. ציור מלבן לבן חצי-שקוף עם פינות מעוגלות
        g.setColor(new Color(255, 255, 255, 230)); 
        g.fillRoundRect(legendX, legendY, legendWidth, legendHeight, 12, 12);

        // מסגרת אפורה עדינה למלבן
        g.setColor(new Color(200, 200, 200));
        g.drawRoundRect(legendX, legendY, legendWidth, legendHeight, 12, 12);

        // 4. הגדרת הפונט למקרא
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // --- פריטים בתוך המקרא ---

        // ירוק - חנייה פנויה
        g.setColor(Color.GREEN);
        g.fillOval(legendX + 12, legendY + 15, 12, 12);
        g.setColor(Color.BLACK);
        g.drawString("Available", legendX + 32, legendY + 26);

        // אדום - חנייה תפוסה
        g.setColor(Color.RED);
        g.fillOval(legendX + 12, legendY + 38, 12, 12);
        g.setColor(Color.BLACK);
        g.drawString("Occupied", legendX + 32, legendY + 49);

        // כתום - כניסה
        g.setColor(Color.ORANGE);
        g.fillOval(legendX + 12, legendY + 61, 12, 12);
        g.setColor(Color.BLACK);
        g.drawString("Entrance", legendX + 32, legendY + 72);

        // אפור - דרך
        g.setColor(Color.GRAY);
        g.fillOval(legendX + 12, legendY + 84, 12, 12);
        g.setColor(Color.BLACK);
        g.drawString("Road", legendX + 32, legendY + 95);
    }

}
