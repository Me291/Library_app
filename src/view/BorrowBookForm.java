package view;

import controller.BookController;

import javax.swing.*;
import java.awt.*;

public class BorrowBookForm extends JFrame {
    private JTextField userIdField;
    private JTextField bookIdField;
    private JButton borrowButton;

    public BorrowBookForm() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Borrow Book");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        userIdField = new JTextField(20);
        userIdField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        bookIdField = new JTextField(20);
        bookIdField.setFont(new Font("Arial", Font.PLAIN, 16));

        borrowButton = new JButton("Borrow");
        borrowButton.setFont(new Font("Arial", Font.BOLD, 16));
        borrowButton.setBackground(new Color(70, 130, 180));
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setFocusPainted(false);

        borrowButton.addActionListener(e -> {
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            BookController.borrowBook(userId, bookId);
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userIdLabel)
                                .addComponent(bookIdLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userIdField)
                                .addComponent(bookIdField)))
                .addComponent(borrowButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userIdLabel)
                        .addComponent(userIdField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bookIdLabel)
                        .addComponent(bookIdField))
                .addComponent(borrowButton)
        );

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BorrowBookForm().setVisible(true));
    }
}
