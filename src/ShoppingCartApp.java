import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class ShoppingCartApp {
    private static ShoppingCart cart;

    public static void main(String[] args) {
        // Initialize the shopping cart
        cart = new ShoppingCart();

        // Create the main frame
        JFrame frame = new JFrame("Shopping Cart App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Text fields for user input
        JTextField itemNameField = new JTextField();
        JTextField itemPriceField = new JTextField();
        JTextField itemQuantityField = new JTextField();

        // Buttons for different actions
        JButton addItemButton = new JButton("Add Item");
        JButton removeItemButton = new JButton("Remove Item");
        JButton searchItemButton = new JButton("Search Item");
        JButton doneButton = new JButton("Done");

        // Action listener for adding an item
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String itemName = itemNameField.getText();
                double itemPrice = Double.parseDouble(itemPriceField.getText());
                int itemQuantity = Integer.parseInt(itemQuantityField.getText());

                // Create Item and ItemOrder objects and add to the cart
                Item item = new Item(itemName, itemPrice);
                ItemOrder itemOrder = new ItemOrder(item, itemQuantity);
                cart.addItemOrder(itemOrder);

                // Clear input fields
                itemNameField.setText("");
                itemPriceField.setText("");
                itemQuantityField.setText("");
            }
        });

        // Action listener for removing an item
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the item name to be removed
                String itemName = itemNameField.getText();

                // Search for the item in the cart
                ItemOrder itemOrder = cart.searchItemOrder(itemName);

                if (itemOrder != null) {
                    // Remove the item if found
                    cart.removeItemOrder(itemOrder);
                    JOptionPane.showMessageDialog(frame, "Item removed: " + itemName);
                } else {
                    // Display a message if the item is not found
                    JOptionPane.showMessageDialog(frame, "Item not found: " + itemName);
                }

                // Clear input field
                itemNameField.setText("");
            }
        });

        // Action listener for searching an item
        searchItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the item name to be searched
                String itemName = itemNameField.getText();

                // Search for the item in the cart
                ItemOrder itemOrder = cart.searchItemOrder(itemName);

                if (itemOrder != null) {
                    // Display a message if the item is found
                    JOptionPane.showMessageDialog(frame, "Item found: " + itemName + " - $" + itemOrder.getItem().getPrice());
                } else {
                    // Display a message if the item is not found
                    JOptionPane.showMessageDialog(frame, "Item not found: " + itemName);
                }

                // Clear input field
                itemNameField.setText("");
            }
        });

        // Action listener for completing the shopping and displaying results
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sort the cart based on item price in descending order
                Collections.sort(cart.getCart(), (o1, o2) -> Double.compare(o2.getItem().getPrice(), o1.getItem().getPrice()));

                // Display items in the cart along with the total price
                StringBuilder result = new StringBuilder("Items in Cart (Most Expensive to Least Expensive):\n");
                for (ItemOrder itemOrder : cart.getCart()) {
                    result.append(itemOrder.getItem().getName())
                            .append(" - Quantity: ").append(itemOrder.getQuantity())
                            .append(", Price per item: $").append(itemOrder.getItem().getPrice())
                            .append(", Total Price: $").append(itemOrder.calculateTotal())
                            .append("\n");
                }

                result.append("\nTotal Price: $").append(cart.calculateTotalPrice());

                // Show the result in a dialog
                JOptionPane.showMessageDialog(frame, result.toString());
            }
        });

        // Add components to the panel
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Item Price:"));
        panel.add(itemPriceField);
        panel.add(new JLabel("Item Quantity:"));
        panel.add(itemQuantityField);
        panel.add(addItemButton);
        panel.add(removeItemButton);
        panel.add(searchItemButton);
        panel.add(doneButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
