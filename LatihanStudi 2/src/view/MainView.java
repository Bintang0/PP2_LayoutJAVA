package view;

import controller.MahasiswaController;
import model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNama, txtJurusan, txtUmur, txtAlamat;
    private MahasiswaController controller;

    public MainView() {
        setTitle("Biodata Mahasiswa");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        controller = new MahasiswaController(this);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(4, 2));
        txtNama = new JTextField();
        txtJurusan = new JTextField();
        txtUmur = new JTextField();
        txtAlamat = new JTextField();
        panelInput.add(new JLabel("Nama:"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Jurusan:"));
        panelInput.add(txtJurusan);
        panelInput.add(new JLabel("Umur:"));
        panelInput.add(txtUmur);
        panelInput.add(new JLabel("Alamat:"));
        panelInput.add(txtAlamat);
        add(panelInput, BorderLayout.NORTH);

         // Tabel
         tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Jurusan", "Umur", "Alamat"}, 0);
         table = new JTable(tableModel);
         add(new JScrollPane(table), BorderLayout.CENTER);
 
         // Panel Tombol
         JPanel panelButton = new JPanel();
         JButton btnAdd = new JButton("Tambah");
         JButton btnUpdate = new JButton("Update");
         JButton btnDelete = new JButton("Hapus");
         JButton btnRefresh = new JButton("Refresh");
         panelButton.add(btnAdd);
         panelButton.add(btnUpdate);
         panelButton.add(btnDelete);
         panelButton.add(btnRefresh);
         add(panelButton, BorderLayout.SOUTH);


         // ActionListener untuk tombol Refresh
        btnRefresh.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        controller.loadTable(); // Panggil fungsi loadTable() dari controller
            }
        });

         btnAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String nama = txtNama.getText();
                 String jurusan = txtJurusan.getText();
                 int umur = Integer.parseInt(txtUmur.getText());
                 String alamat = txtAlamat.getText();
                 controller.addMahasiswa(nama, jurusan, umur, alamat);
             }
         });
 
         btnUpdate.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int selectedRow = table.getSelectedRow();
                 if (selectedRow != -1) {
                     int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                     String nama = txtNama.getText();
                     String jurusan = txtJurusan.getText();
                     int umur = Integer.parseInt(txtUmur.getText());
                     String alamat = txtAlamat.getText();
                     controller.updateMahasiswa(id, nama, jurusan, umur, alamat);
                 }
             }
         });

         btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    controller.deleteMahasiswa(id);
                }
            }
        });

        controller.loadTable();
    }

    public void refreshTable(List<Mahasiswa> mahasiswaList) {
        tableModel.setRowCount(0);
        for (Mahasiswa m : mahasiswaList) {
            tableModel.addRow(new Object[]{m.getId(), m.getNama(), m.getJurusan(), m.getUmur(), m.getAlamat()});
        }
    }
}