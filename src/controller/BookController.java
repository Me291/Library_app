package controller;

import model.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookController {
    public static void addBook(String title, String author, int stock) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO books (title, author, stock) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, stock);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to add book: " + e.getMessage());
        }
    }

    public static void viewBooks() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            StringBuilder books = new StringBuilder("Books in Library:\n");
            while (rs.next()) {
                books.append("ID: ").append(rs.getInt("id"))
                        .append(", Title: ").append(rs.getString("title"))
                        .append(", Author: ").append(rs.getString("author"))
                        .append(", Stock: ").append(rs.getInt("stock")).append("\n");
            }

            JOptionPane.showMessageDialog(null, books.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve books: " + e.getMessage());
        }
    }

    public static void borrowBook(int userId, int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkStockSql = "SELECT stock FROM books WHERE id = ?";
            PreparedStatement checkStockStmt = conn.prepareStatement(checkStockSql);
            checkStockStmt.setInt(1, bookId);
            ResultSet rs = checkStockStmt.executeQuery();

            if (rs.next() && rs.getInt("stock") > 0) {
                String borrowSql = "INSERT INTO loans (user_id, book_id, loan_date) VALUES (?, ?, CURDATE())";
                PreparedStatement borrowStmt = conn.prepareStatement(borrowSql);
                borrowStmt.setInt(1, userId);
                borrowStmt.setInt(2, bookId);
                borrowStmt.executeUpdate();

                String updateStockSql = "UPDATE books SET stock = stock - 1 WHERE id = ?";
                PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSql);
                updateStockStmt.setInt(1, bookId);
                updateStockStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Book borrowed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Book out of stock!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to borrow book: " + e.getMessage());
        }
    }

    public static void returnBook(int userId, int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkLoanSql = "SELECT id FROM loans WHERE user_id = ? AND book_id = ? AND return_date IS NULL";
            PreparedStatement checkLoanStmt = conn.prepareStatement(checkLoanSql);
            checkLoanStmt.setInt(1, userId);
            checkLoanStmt.setInt(2, bookId);
            ResultSet rs = checkLoanStmt.executeQuery();

            if (rs.next()) {
                String returnSql = "UPDATE loans SET return_date = CURDATE() WHERE user_id = ? AND book_id = ? AND return_date IS NULL";
                PreparedStatement returnStmt = conn.prepareStatement(returnSql);
                returnStmt.setInt(1, userId);
                returnStmt.setInt(2, bookId);
                returnStmt.executeUpdate();

                String updateStockSql = "UPDATE books SET stock = stock + 1 WHERE id = ?";
                PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSql);
                updateStockStmt.setInt(1, bookId);
                updateStockStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Book returned successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No such loan record found!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to return book: " + e.getMessage());
        }
    }
}
