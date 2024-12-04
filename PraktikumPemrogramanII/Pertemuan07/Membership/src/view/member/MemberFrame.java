package Pertemuan07.Membership.src.view.member;


import javax.swing.*;
import java.util.*;
import Pertemuan07.Membership.src.model.*;
import Pertemuan07.Membership.src.dao.MemberDao;
import Pertemuan07.Membership.src.dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    @SuppressWarnings("rawtypes")
    private JComboBox comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    
    @SuppressWarnings("rawtypes")
    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();
        
        // Create and setup name label and field
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        
        // Create and setup member type label and combo box
        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);
        
        comboJenis = new JComboBox();
        comboJenis.setBounds(15, 120, 350, 30);
        
        // Create and setup save button
        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);
        
        // Create and setup table with scrollpane
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);
        
        // Setup table model
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);
        
        // Setup action listener
        MemberButtonSimpanActionListener actionListener = 
            new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);
        
        // Add all components to frame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);
        
        // Set frame size and layout
        this.setSize(400, 500);
        this.setLayout(null);
        
        // Populate combo box
        populateComboJenis();
    }
    
    @SuppressWarnings("unchecked")
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }
    
    public String getNama() {
        return textFieldNama.getText();
    }
    
    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }
    
    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }
    
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}