package Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InventoryManagement extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtProductCode, txtProductName, txtCategory, txtStock, txtPrice;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private Connection conn;
    
    public InventoryManagement() {
        initComponents();
        connectToDatabase();
        loadData();
    }
    
    private void initComponents() {
        // Set up the frame
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Create table model
        String[] columns = {"ID", "Kode Produk", "Nama Produk", "Kategori", "Stok", "Harga"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Kode Produk:"));
        txtProductCode = new JTextField();
        inputPanel.add(txtProductCode);
        
        inputPanel.add(new JLabel("Nama Produk:"));
        txtProductName = new JTextField();
        inputPanel.add(txtProductName);
        
        inputPanel.add(new JLabel("Kategori:"));
        txtCategory = new JTextField();
        inputPanel.add(txtCategory);
        
        inputPanel.add(new JLabel("Stok:"));
        txtStock = new JTextField();
        inputPanel.add(txtStock);
        
        inputPanel.add(new JLabel("Harga:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Bersihkan");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add event listeners
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnClear.addActionListener(e -> clearFields());
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    displaySelectedRow(row);
                }
            }
        });
    }
    
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/inventory_db",
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Koneksi database gagal: " + e.getMessage());
        }
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("product_code"),
                    rs.getString("product_name"),
                    rs.getString("category"),
                    rs.getInt("stock"),
                    rs.getDouble("price")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }
    
    private void addProduct() {
        try {
            String sql = "INSERT INTO products (product_code, product_name, category, stock, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtProductCode.getText());
            pstmt.setString(2, txtProductName.getText());
            pstmt.setString(3, txtCategory.getText());
            pstmt.setInt(4, Integer.parseInt(txtStock.getText()));
            pstmt.setDouble(5, Double.parseDouble(txtPrice.getText()));
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!");
            clearFields();
            loadData();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding product: " + e.getMessage());
        }
    }
    
    private void updateProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang akan diubah!");
            return;
        }
        
        try {
            int id = (int) table.getValueAt(selectedRow, 0);
            String sql = "UPDATE products SET product_code=?, product_name=?, category=?, stock=?, price=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtProductCode.getText());
            pstmt.setString(2, txtProductName.getText());
            pstmt.setString(3, txtCategory.getText());
            pstmt.setInt(4, Integer.parseInt(txtStock.getText()));
            pstmt.setDouble(5, Double.parseDouble(txtPrice.getText()));
            pstmt.setInt(6, id);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produk berhasil diubah!");
            clearFields();
            loadData();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating product: " + e.getMessage());
        }
    }
    
    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang akan dihapus!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus produk ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = (int) table.getValueAt(selectedRow, 0);
                String sql = "DELETE FROM products WHERE id=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!");
                clearFields();
                loadData();
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting product: " + e.getMessage());
            }
        }
    }
    
    private void clearFields() {
        txtProductCode.setText("");
        txtProductName.setText("");
        txtCategory.setText("");
        txtStock.setText("");
        txtPrice.setText("");
        table.clearSelection();
    }
    
    private void displaySelectedRow(int row) {
        txtProductCode.setText(table.getValueAt(row, 1).toString());
        txtProductName.setText(table.getValueAt(row, 2).toString());
        txtCategory.setText(table.getValueAt(row, 3).toString());
        txtStock.setText(table.getValueAt(row, 4).toString());
        txtPrice.setText(table.getValueAt(row, 5).toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryManagement().setVisible(true);
        });
    }
}