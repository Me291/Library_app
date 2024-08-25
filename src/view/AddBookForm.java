package view;

import controller.BookController;

import javax.swing.*;
import java.awt.*;

public class AddBookForm extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField stockField;
    private JButton addButton;

    public AddBookForm() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Add Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        titleField = new JTextField(20);
        titleField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        authorField = new JTextField(20);
        authorField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        stockField = new JTextField(20);
        stockField.setFont(new Font("Arial", Font.PLAIN, 16));

        addButton = new JButton("Add Book");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        addButton.setIcon(new ImageIcon("path/to/icon.png")); // Add an icon to the button

        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            int stock = Integer.parseInt(stockField.getText());
            BookController.addBook(title, author, stock);
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addComponent(authorLabel)
                                .addComponent(stockLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleField)
                                .addComponent(authorField)
                                .addComponent(stockField)))
                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(titleLabel)
                        .addComponent(titleField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(authorLabel)
                        .addComponent(authorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(stockLabel)
                        .addComponent(stockField))
                .addComponent(addButton)
        );

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddBookForm().setVisible(true));
    }
}
