import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    // Constructor to set up the main frame
    public MainFrame() {
        super("Cookie Monsta Inventory System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Setting up layout
        setLayout(new BorderLayout());

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Cookie Monsta Inventory System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        // Image icon
        ImageIcon icon = new ImageIcon("C:/Users/user/Downloads/");
        JLabel iconLabel = new JLabel(icon, JLabel.CENTER);
        add(iconLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Buttons
        JButton inventoryButton = new JButton("Manage Inventory");
        JButton suppliersButton = new JButton("Manage Suppliers");
        JButton reportsButton = new JButton("View Reports");

        // Adding action listeners
        inventoryButton.addActionListener(e -> new InventoryManagementFrame());
        suppliersButton.addActionListener(e -> new SupplierManagementFrame());
        reportsButton.addActionListener(e -> new ReportsFrame());

        // Adding buttons to panel
        buttonPanel.add(inventoryButton);
        buttonPanel.add(suppliersButton);
        buttonPanel.add(reportsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}