import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ParkingFrame extends JFrame {
    private Graph graph;
    private Node entranceNode;
    private ParkingPanel parkingPanel;

    public ParkingFrame(Graph graph, Node entranceNode) {
        this.graph = graph;
        this.entranceNode = entranceNode;

        setTitle("Waze For Parking");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------------------------------------------
        // 1. כותרת עליונה סגנונית (Header)
        // ----------------------------------------------------
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 37, 41)); // אפור כהה מודרני
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("🚗 Waze For Parking - Realtime Navigation");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // ----------------------------------------------------
        // 2. פאנל הציור במרכז (ParkingPanel)
        // ----------------------------------------------------
        parkingPanel = new ParkingPanel(graph);
        parkingPanel.setBackground(new Color(245, 247, 250)); // רקע בהיר ונקי
        add(parkingPanel, BorderLayout.CENTER);

        // ----------------------------------------------------
        // 3. סרגל כלים תחתון (Control Bar)
        // ----------------------------------------------------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        // כפתור מציאת חנייה
        JButton btnFind = new JButton("🔍 Find Nearest Parking");
        btnFind.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnFind.setBackground(new Color(13, 110, 253)); // כחול Waze/Bootstrap
        btnFind.setForeground(Color.WHITE);
        btnFind.setFocusPainted(false);
        btnFind.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFind.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(13, 110, 253)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // כפתור איפוס מסלול
        JButton btnReset = new JButton("🔄 Reset View");
        btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnReset.setBackground(new Color(108, 117, 125)); // אפור ניטרלי
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(108, 117, 125)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // מאזין לכפתור חיפוש
        btnFind.addActionListener(e -> {
            List<Node> path = Router.findPathToNearestParking(graph, entranceNode);
            if (path == null || path.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No available parking space found!", 
                    "System Alert", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                parkingPanel.setPath(path);
            }
        });

        // מאזין לכפתור איפוס
        btnReset.addActionListener(e -> parkingPanel.setPath(null));

        bottomPanel.add(btnFind);
        bottomPanel.add(btnReset);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}