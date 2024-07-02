import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

// Main frame for managing suppliers
public class SupplierManagementFrame extends MainFrame 
{
    private DefaultTableModel tableModel;
    private JTable supplierTable;

    // Constructor to set up the frame
    public SupplierManagementFrame() 
    {
        setTitle("Supplier Management");
        initializeFrame();
        createUIComponents();
        setVisible(true);
    }

    // Initialize the main settings for the frame
    private void initializeFrame() 
    {
        setSize(600, 400);  // Set the size of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close the window when the user closes it
        setLocationRelativeTo(null);  // Center the window on the screen
        setLayout(new BorderLayout());  // Use BorderLayout for organizing components
    }

    // Create and add UI components to the frame
    private void createUIComponents() 
    {
        addTitleLabel();  // Add the title label
        addSupplierTable();  // Add the table for displaying suppliers
        addButtonPanel();  // Add the panel with buttons
    }

    // Add the title label at the top
    private void addTitleLabel() 
    {
        JLabel titleLabel = new JLabel("Manage Suppliers", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }

    // Add the table for displaying suppliers in the center
    private void addSupplierTable() 
    {
        String[] columnNames = {"Supplier ID", "Name", "Contact", "Email"};  // Define the columns
        tableModel = new DefaultTableModel(columnNames, 0);  // Initialize the table model
        supplierTable = new JTable(tableModel);  // Create the table with the model
        JScrollPane scrollPane = new JScrollPane(supplierTable);  // Add scroll functionality
        add(scrollPane, BorderLayout.CENTER);  // Add the table to the center of the layout
    }

    // Add a panel with buttons at the bottom
    private void addButtonPanel() 
    {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Create buttons
        JButton addButton = new JButton("Add Supplier");
        JButton editButton = new JButton("Edit Supplier");
        JButton deleteButton = new JButton("Delete Supplier");

        // Attach action listeners to buttons
        addButton.addActionListener(e -> showAddSupplierDialog());
        editButton.addActionListener(e -> showEditSupplierDialog());
        deleteButton.addActionListener(e -> showDeleteSupplierDialog());

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Show the dialog for adding a new supplier
    private void showAddSupplierDialog() 
    {
        AddSupplierDialog dialog = new AddSupplierDialog(this);
        dialog.setVisible(true);
    }

    // Show the dialog for editing an existing supplier
    private void showEditSupplierDialog() 
    {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a supplier to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the data of the selected supplier
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = tableModel.getValueAt(selectedRow, 1).toString();
        String contact = tableModel.getValueAt(selectedRow, 2).toString();
        String email = tableModel.getValueAt(selectedRow, 3).toString();

        // Open the edit dialog with the selected supplier's data
        EditSupplierDialog dialog = new EditSupplierDialog(this, id, name, contact, email, selectedRow);
        dialog.setVisible(true);
    }

    // Show a confirmation dialog for deleting a supplier
    private void showDeleteSupplierDialog() 
    {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a supplier to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this supplier?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            deleteSupplier(selectedRow);
        }
    }

    // Delete the selected supplier from the table
    private void deleteSupplier(int row) 
    {
        tableModel.removeRow(row);
    }

    // Add a new supplier to the table
    public void addSupplier(String id, String name, String contact, String email) 
    {
        tableModel.addRow(new Object[]{id, name, contact, email});
    }

    // Update the data of an existing supplier in the table
    public void updateSupplier(String id, String name, String contact, String email, int row) 
    {
        tableModel.setValueAt(id, row, 0);
        tableModel.setValueAt(name, row, 1);
        tableModel.setValueAt(contact, row, 2);
        tableModel.setValueAt(email, row, 3);
    }

    // Main method to run the frame
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(SupplierManagementFrame::new);
    }
}

// Dialog for adding a new supplier
class AddSupplierDialog extends JDialog 
{
    private JTextField idField;
    private JTextField nameField;
    private JTextField contactField;
    private JTextField emailField;
    private SupplierManagementFrame parentFrame;

    // Constructor to set up the dialog
    public AddSupplierDialog(SupplierManagementFrame parent) 
    {
        super(parent, "Add Supplier", true);
        this.parentFrame = parent;
        initializeDialog();
        createDialogUI();
    }

    // Initialize dialog settings
    private void initializeDialog() 
    {
        setSize(300, 250);  // Set the size of the dialog
        setLayout(new BorderLayout());
        setLocationRelativeTo(parentFrame);  // Center the dialog relative to the parent frame
    }

    // Create and add UI components to the dialog
    private void createDialogUI() 
    {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        // Labels and text fields for supplier information
        inputPanel.add(new JLabel("Supplier ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        inputPanel.add(contactField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.CENTER);

        // Panel for the Add and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        // Attach action listeners to buttons
        addButton.addActionListener(e -> addSupplier());
        cancelButton.addActionListener(e -> dispose());

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the dialog
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Handle adding a supplier
    private void addSupplier() 
    {
        String id = idField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();

        // Basic validation
        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || email.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else 
        {
            // Add supplier to the table in the parent frame
            parentFrame.addSupplier(id, name, contact, email);
            dispose();  // Close the dialog
        }
    }
}

// Dialog for editing an existing supplier
class EditSupplierDialog extends JDialog 
{

    private JTextField idField;
    private JTextField nameField;
    private JTextField contactField;
    private JTextField emailField;
    private SupplierManagementFrame parentFrame;
    private int rowIndex;
    
    // Constructor to set up the dialog
    public EditSupplierDialog(SupplierManagementFrame parent, String id, String name, String contact, String email, int rowIndex) 
    {
        super(parent, "Edit Supplier", true);
        this.parentFrame = parent;
        this.rowIndex = rowIndex;
        initializeDialog();
        createDialogUI(id, name, contact, email);
    }

    // Initialize dialog settings
    private void initializeDialog() 
    {
        setSize(300, 250);  // Set the size of the dialog
        setLayout(new BorderLayout());
        setLocationRelativeTo(parentFrame);  // Center the dialog relative to the parent frame
    }

    // Create and add UI components to the dialog
    private void createDialogUI(String id, String name, String contact, String email) 
    {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        // Labels and text fields for supplier information
        inputPanel.add(new JLabel("Supplier ID:"));
        idField = new JTextField(id);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(name);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Contact:"));
        contactField = new JTextField(contact);
        inputPanel.add(contactField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField(email);
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.CENTER);

        // Panel for the Save and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Attach action listeners to buttons
        saveButton.addActionListener(e -> saveSupplier());
        cancelButton.addActionListener(e -> dispose());

        // Add buttons to the panel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the dialog
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Handle saving the edited supplier
    private void saveSupplier() 
    {
        String id = idField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();

        // Basic validation
        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || email.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else 
        {
            // Update supplier in the table in the parent frame
            parentFrame.updateSupplier(id, name, contact, email, rowIndex);
            dispose();  // Close the dialog
        }
    }
}