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


 
public class WindowListenerExample {
	 public static void main(String[] args) {
		// Membuat Farme
		JFrame frame = new JFrame("MouseListener Example");
			
		// Membuat Label untuk menampilkan pesan
		JLabel label = new JLabel("Arahkan dan klik mouse pada area ini.");
		label.setBounds(50, 50, 300, 30);
		
		// menambahkan WindowListener
		frame.addWindowListener(new WindowListener() {
			
			// dijalankan ketika jendela dibuka
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Openned.");
			}
			
			// dijalankan ketika jendela di minimize
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Minimized.");
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Restored.");
			}
			
			// dijalankan ketika jendela dipulihkan dari minimize
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Deactivate.");
			}
			
			// dijalankan ktika jendela sedang dalam proses penutupan
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Closing.");
			}
			
			// dijalankan ketika jendela benar benar ditutup
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Window Clossed.");
			}
			
			// dijalankan ketika jendela menjadi aktif (berfokus)
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				label.setText("Window Activated.");
			}
		});
		
		// menambakan komponen ke frame
		frame.add(label);
		
		// setting frame
		frame.setSize(400, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // agar jendela benar benar tertutup
	}
}