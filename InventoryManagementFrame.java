import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementFrame extends JFrame {

    // Constructor to set up the inventory management frame
    public InventoryManagementFrame() {
        setTitle("Manage Inventory");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Make the frame non-resizable

        // String arrays for item names
        String[] cookieItems = {"Chocolate Chip", "Matcha Strawberry", "Sea Salt Choc Chip", "Gingerbread Cookies", "Lemon Cookies"};
        String[] cakeItems = {"Chocolate Cake", "Tiramisu Cake", "Red Velvet Cake", "Vanilla Cake", "Carrot Cake"};

        // String arrays for the item price
        double[] cookiePrices = {2.99, 3.49, 2.79, 2.99, 2.49};
        double[] cakePrices = {19.99, 24.99, 22.99, 16.99, 18.99};

        // Panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 15)); // Increase the vertical spacing to 15
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Increase the border insets to 15

        // Labels and fields
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Cookies", "Cakes"});
        JLabel itemNameLabel = new JLabel("Item Name:");
        JComboBox<String> itemNameComboBox = new JComboBox<>();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price (Each):");
        JTextField priceField = new JTextField();
        priceField.setEditable(false); // Make the price field non-editable

        // Buttons
        JButton addButton = new JButton("Add Item");
        JButton updateButton = new JButton("Update Item");
        JButton backToMenuButton = new JButton("Back to Menu");

        // Set preferred size for the "Back to Menu" button
        backToMenuButton.setPreferredSize(new Dimension(200, 30));

        // Adding components to panel
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(itemNameLabel);
        panel.add(itemNameComboBox);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(backToMenuButton);

        categoryComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) categoryComboBox.getSelectedItem();
                    if (selectedCategory.equals("Cookies")) {
                        itemNameComboBox.setModel(new DefaultComboBoxModel<>(cookieItems));
                        priceField.setText(String.format("RM%.2f", cookiePrices[0]));
                    } else if (selectedCategory.equals("Cakes")) {
                        itemNameComboBox.setModel(new DefaultComboBoxModel<>(cakeItems));
                        priceField.setText(String.format("RM%.2f", cakePrices[0]));
                    }
                }
            });

        itemNameComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = (String) categoryComboBox.getSelectedItem();
                    int selectedIndex = itemNameComboBox.getSelectedIndex();
                    if (selectedCategory.equals("Cookies")) {
                        if (selectedIndex >= 0 && selectedIndex < cookieItems.length) {
                            priceField.setText(String.format("RM%.2f", cookiePrices[selectedIndex]));
                        }
                    } else if (selectedCategory.equals("Cakes")) {
                        if (selectedIndex >= 0 && selectedIndex < cakeItems.length) {
                            priceField.setText(String.format("RM%.2f", cakePrices[selectedIndex]));
                        }
                    }
                }
            });

        addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code to add item to inventory
                    // Placeholder for functionality
                    JOptionPane.showMessageDialog(null, "Item added to inventory!");
                }
            });

        updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code to update item in inventory
                    // Placeholder for functionality
                    JOptionPane.showMessageDialog(null, "Item has been updated to inventory");
                }
            });

        backToMenuButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code to go back to the main menu
                    // Placeholder for functionality
                    dispose(); // Close the current frame
                    // Open the main menu frame or perform any other desired action
                }
            });

        add(panel);
        setVisible(true);
    }
}