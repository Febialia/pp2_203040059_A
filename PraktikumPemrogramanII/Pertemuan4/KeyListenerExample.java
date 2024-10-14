/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author ASUS
 */



public class KeyListenerExample {
    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("KeyListener Example");

        // Membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Tekan tombol pada keyboard.");
        label.setBounds(50, 50, 300, 30);

        // Membuat text field untuk fokus keyboard
        JTextField textField = new JTextField();
        textField.setBounds(50, 100, 200, 30);

        // Menambahkan keyListener ke text field
        textField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                label.setText("keyPressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            public void keyReleased(KeyEvent e) {
                label.setText("keyReleased: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            public void keyTyped(KeyEvent e) {
                label.setText("keyTyped: " + e.getKeyChar());
            }
        });

        // Menambahkan komponen ke frame
        frame.add(label);
        frame.add(textField);

        // Setting frame
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}