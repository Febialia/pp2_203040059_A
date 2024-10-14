/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author ASUS
 */
public class ButtonExample {
   public static void main(String[] args){
        JFrame frame = new JFrame("Button Example");
        JButton button = new JButton("Click Me");

        // Menambahkan ActionListener ke JButton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Button Clicked!");
            }
        });
        
        button.setBounds(50, 50, 150, 30);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}