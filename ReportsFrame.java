import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program Objective: To focus on Development of inventory tracking logic. 
 * Implementation of adding, updating, deleting inventory items. 
 * GUI frame for viewing current stock levels.
 * Programmer: MA
 * Date: 26th June 24
 */

public class ReportsFrame extends JFrame 
{
    // Data for inventory
    private static final int MAX_ITEMS = 70;
    private static String[] itemName = {
        "Choco Chip", "Matcha Strawberry", "Double Chocolate", "Matcha White Choc Chip", 
        "Choc And Nut", "Red Velvet Cheesecake", "Sea Salt Choc Chip", 
        "Chocolate Chip", "Matcha Strawberry", "Sea Salt Choc Chip", 
        "Gingerbread Cookies", "Lemon Cookies"
    };
    private static int[] itemQuantity = {50, 30, 20, 25, 15, 10, 40, 60, 45, 50, 35, 40};
    private static double[] itemPrice = {6.50, 8.50, 7.50, 7.50, 8.50, 8.50, 6.50, 2.99, 3.49, 2.79, 2.99, 2.49};
    
    private static String[] cakeName = {
        "Chocolate Cake", "Tiramisu Cake", "Red Velvet Cake", "Vanilla Cake", "Carrot Cake"
    };
    private static int[] cakeQuantity = {10, 8, 6, 12, 9};
    private static double[] cakePrice = {19.99, 24.99, 22.99, 16.99, 18.99};

    // Constructor to set up the reports frame
    public ReportsFrame() 
    {
        setTitle("Reports");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Label and buttons
        JLabel reportLabel = new JLabel("Generate Inventory Report", JLabel.CENTER);
        JButton generateButton = new JButton("Generate Report");
        JButton exportButton = new JButton("Export Report");

        // Adding components to panel
        panel.add(reportLabel, BorderLayout.NORTH);
        panel.add(generateButton, BorderLayout.CENTER);
        panel.add(exportButton, BorderLayout.SOUTH);

        // Adding action listener for generating report
        generateButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String report = generateReport();
                displayReport(report);
            }
        });

        // Adding action listener for exporting report
        exportButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String report = generateReport();
                exportReport(report);
            }
        });

        add(panel);
        setVisible(true);
    }

    // To generate inventory report
    private String generateReport() 
    {
        StringBuilder report = new StringBuilder();
        report.append("Inventory Report\n\n");
        report.append("Cookies:\n");
        report.append(String.format("%-25s%-15s%-10s\n", "Item Name", "Quantity", "Price (RM)"));
        report.append("------------------------------------------------------\n");
        for (int i = 0; i < itemName.length; i++) 
        {
            report.append(String.format("%-25s%-15d%-10.2f\n", itemName[i], itemQuantity[i], itemPrice[i]));
        }
        
        report.append("\nCakes:\n");
        report.append(String.format("%-25s%-15s%-10s\n", "Item Name", "Quantity", "Price (RM)"));
        report.append("------------------------------------------------------\n");
        for (int i = 0; i < cakeName.length; i++) 
        {
            report.append(String.format("%-25s%-15d%-10.2f\n", cakeName[i], cakeQuantity[i], cakePrice[i]));
        }
        return report.toString();
    }

    // To display the report in a dialog
    private void displayReport(String report) 
    {
        JTextArea textArea = new JTextArea(report);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(380, 180));
        JOptionPane.showMessageDialog(null, scrollPane, "Inventory Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // To export the report to a text file
    private void exportReport(String report) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("inventory_report.txt"))) 
        {
            writer.println(report);
            JOptionPane.showMessageDialog(null, "Report exported to inventory_report.txt");
        } catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error exporting report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) 
    {
        new ReportsFrame();
    }
}//end of main