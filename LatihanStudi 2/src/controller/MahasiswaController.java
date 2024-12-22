package controller;

import model.Mahasiswa;
import model.MahasiswaDAO;
import view.MainView;

import java.sql.SQLException;
import java.util.List;

public class MahasiswaController {
    private MainView view;
    private MahasiswaDAO dao;

    public MahasiswaController(MainView view) {
        this.view = view;
        try {
            dao = new MahasiswaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {
        try {
            List<Mahasiswa> mahasiswaList = dao.getAllMahasiswa();
            view.refreshTable(mahasiswaList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMahasiswa(String nama, String jurusan, int umur, String alamat) {
        try {
            dao.addMahasiswa(new            Mahasiswa(0, nama, jurusan, umur, alamat));
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMahasiswa(int id, String nama, String jurusan, int umur, String alamat) {
        try {
            dao.updateMahasiswa(new Mahasiswa(id, nama, jurusan, umur, alamat));
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMahasiswa(int id) {
        try {
            dao.deleteMahasiswa(id);
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
