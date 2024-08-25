package view;

import controller.BookController;

import javax.swing.*;
import java.awt.*;

public class ReturnBookForm extends JFrame {
    private JTextField userIdField;
    private JTextField bookIdField;
    private JButton returnButton;

    public ReturnBookForm() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Return Book");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Return Book");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        userIdField = new JTextField(20);
        userIdField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        bookIdField = new JTextField(20);
        bookIdField.setFont(new Font("Arial", Font.PLAIN, 16));

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));
        returnButton.setBackground(new Color(70, 130, 180));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);

        returnButton.addActionListener(e -> {
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            BookController.returnBook(userId, bookId);
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userIdLabel)
                                .addComponent(bookIdLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userIdField)
                                .addComponent(bookIdField)))
                .addComponent(returnButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userIdLabel)
                        .addComponent(userIdField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bookIdLabel)
                        .addComponent(bookIdField))
                .addComponent(returnButton)
        );

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReturnBookForm().setVisible(true));
    }
}
