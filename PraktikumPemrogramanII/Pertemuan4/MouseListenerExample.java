/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;
import javax.swing.*;
import java.awt.event.*;
/**
/**
 *
 * @author ASUS
 */


public class MouseListenerExample {
	public static void main(String[] args) {
		//Membuat frame
		JFrame frame = new JFrame("MouseListener Example");
		
		//Membuat label untuk menampilkan pesan
		JLabel label = new JLabel("Arahkan dan klik mouse pada area ini.");
		label.setBounds(50, 50, 300, 30);
		
		//Menambahkan MouseListener ke label
		label.addMouseListener(new MouseListener() {
			//dijalankan ketika mouse diklik (klik kiri, kanan, atau tengah)
			public void mouseClicked(MouseEvent e) {
				label.setText("Mouse Clicked at: (" + e.getX() + "," + e.getY() + ")");
			}
			
			//dijalankan ketika mouse ditekan (tanpa dilepaskan)
			public void mousePressed(MouseEvent e) {
				label.setText("Mouse Pressed at: (" + e.getX() + "," + e.getY() + ")");
			}
			
			//dijalnkan ketika mouse dilepaskan setelah ditekan
			public void mouseReleased(MouseEvent e) {
				label.setText("Mouse Released at: (" + e.getX() + "," + e.getY() + ")");
			}
			
			//dijalankan ketika mouse masuk ke area komponen
			public void mouseEntered(MouseEvent e) {
				label.setText("Mouse Entered the area.");
			}
			
			//dijalankan ketika mouse keluar dari area komponen
			public void mouseExited(MouseEvent e) {
				label.setText("Mouse Exited the area.");
			}
			
		});
		
		    //menambahkan label ke frame 
		    frame.add(label);
		    
		    //setting frame
		    frame.setSize(400, 200);
		    frame.setLayout(null);
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}