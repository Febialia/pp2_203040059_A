/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan6;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ASUS
 */
public class BiodataApp extends JFrame {

    private JTextField namaField;
    private JTextArea alamatArea;
    private JRadioButton JKLakiLaki, JKPerempuan;
    private JCheckBox Membacacheck, Olahragacheck;
    private JComboBox<String> PekerjaancomboBox;
    private JList<String> NegaraFavlist;
    private JSlider Skorslider;
    private JSpinner Umurspinner;
    private JTable BiodataTable;
    private DefaultTableModel tableModel;

    public BiodataApp() {
        setTitle("Aplikasi Biodata Java");
        setSize(800, 800); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigate");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        menu.add(mainMenu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // membuat form panel
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        // Nama input JTextField
        formPanel.add(new JLabel("Nama:"));
        namaField = new JTextField(50);
        formPanel.add(namaField);

        // alamat JTextArea
        formPanel.add(new JLabel("Alamat:"));
        alamatArea = new JTextArea(3, 20);  //
        formPanel.add(new JScrollPane(alamatArea));

        //  jenis kelamin JRadioButton
        formPanel.add(new JLabel("Jenis Kelamin:"));
        JKLakiLaki = new JRadioButton("Laki-laki");
        JKPerempuan = new JRadioButton("Perempuan");
        ButtonGroup genreGroup = new ButtonGroup();
        genreGroup.add(JKLakiLaki);
        genreGroup.add(JKPerempuan);
        JPanel genrePanel = new JPanel();
        genrePanel.add(JKLakiLaki);
        genrePanel.add(JKPerempuan);
        formPanel.add(genrePanel);

        // hobi JCheckBox 
        formPanel.add(new JLabel("Hobi:"));
        Membacacheck = new JCheckBox("Membaca");
        Olahragacheck = new JCheckBox("Olahraga");
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(Membacacheck);
        optionsPanel.add(Olahragacheck);
        formPanel.add(optionsPanel);

        // JComboBox pekerjaan
        formPanel.add(new JLabel("Pekerjaan:"));
        PekerjaancomboBox = new JComboBox<>(new String[]{"Pilih", "Mahasiswa", "Karyawan", "Freelancer", "IRT"});
        formPanel.add(PekerjaancomboBox);

        // negara JList 
        formPanel.add(new JLabel("Negara Favorit:"));
        NegaraFavlist = new JList<>(new String[]{"Indonesia", "Malaysia", "Singapura", "Thailand", "Philipina"});
        NegaraFavlist.setVisibleRowCount(3); 
        NegaraFavlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        formPanel.add(new JScrollPane(NegaraFavlist));

        // skor
        formPanel.add(new JLabel("Skor kepuasan:"));
        Skorslider = new JSlider(0, 100);
        Skorslider.setMajorTickSpacing(60);
        Skorslider.setPaintTicks(true);
        formPanel.add(Skorslider);

        // JSpinner umur
        formPanel.add(new JLabel("Umur:"));
        Umurspinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        formPanel.add(Umurspinner);

        // Submit button
        JButton submitButton = new JButton("Submit");
        formPanel.add(submitButton);

        // Table biodata data
        String[] columnNames = {"Nama", "Alamat", "Jenis Kelamin", "Hobi", "Pekerjaan", "Negara Favorit", "Kepuasan", "Umur"};
        tableModel = new DefaultTableModel(columnNames, 0);
        BiodataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(BiodataTable);

        //menambahkan komponen untuk frame
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Event Listener untuk submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather data from form
                String Nama = namaField.getText();
                String Alamat = alamatArea.getText();
                String JK = JKLakiLaki.isSelected() ? "Laki-laki" : JKPerempuan.isSelected() ? "Perempuan" : "N/A";
                String Hobi = Membacacheck.isSelected() ? "Membaca" : "Olahraga";
                String Pekerjaan = (String) PekerjaancomboBox.getSelectedItem();
                java.util.List<String> NegaraFav = NegaraFavlist.getSelectedValuesList();
                String Kepuasan = String.valueOf(Skorslider.getValue());
                String Umur = Umurspinner.getValue().toString();

                tableModel.addRow(new Object[]{Nama, Alamat, JK, Hobi, Pekerjaan, NegaraFav, Kepuasan, Umur});
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BiodataApp app = new BiodataApp();
            app.setVisible(true);
        });
    }
}