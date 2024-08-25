package view;

import javax.swing.*;
import java.awt.*;

import controller.BookController;

public class UserDashboard extends JFrame {
    private JButton viewBooksButton;
    private JButton addBookButton;
    private JButton borrowBookButton;
    private JButton returnBookButton;
    private JButton logoutButton;

    public UserDashboard() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("User Dashboard");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("User Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        viewBooksButton = new JButton("View Books");
        viewBooksButton.setFont(new Font("Arial", Font.PLAIN, 16));
        viewBooksButton.setBackground(new Color(70, 130, 180));
        viewBooksButton.setForeground(Color.WHITE);
        viewBooksButton.setFocusPainted(false);

        addBookButton = new JButton("Add Book");
        addBookButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addBookButton.setBackground(new Color(70, 130, 180));
        addBookButton.setForeground(Color.WHITE);
        addBookButton.setFocusPainted(false);

        borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setFont(new Font("Arial", Font.PLAIN, 16));
        borrowBookButton.setBackground(new Color(70, 130, 180));
        borrowBookButton.setForeground(Color.WHITE);
        borrowBookButton.setFocusPainted(false);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnBookButton.setBackground(new Color(70, 130, 180));
        returnBookButton.setForeground(Color.WHITE);
        returnBookButton.setFocusPainted(false);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setBackground(new Color(70, 130, 180));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addComponent(viewBooksButton)
                .addComponent(addBookButton)
                .addComponent(borrowBookButton)
                .addComponent(returnBookButton)
                .addComponent(logoutButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(20)
                .addComponent(viewBooksButton)
                .addGap(10)
                .addComponent(addBookButton)
                .addGap(10)
                .addComponent(borrowBookButton)
                .addGap(10)
                .addComponent(returnBookButton)
                .addGap(10)
                .addComponent(logoutButton)
        );

        viewBooksButton.addActionListener(e -> BookController.viewBooks());
        addBookButton.addActionListener(e -> new BookForm().setVisible(true));
        borrowBookButton.addActionListener(e -> new BorrowBookForm().setVisible(true));
        returnBookButton.addActionListener(e -> new ReturnBookForm().setVisible(true));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserDashboard().setVisible(true));
    }
}
