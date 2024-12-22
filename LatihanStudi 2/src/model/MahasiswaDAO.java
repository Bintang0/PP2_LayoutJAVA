package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    private Connection koneksi;

    public MahasiswaDAO() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_mahasiswa";
        String username = "root";
        String password = "";
        koneksi = DriverManager.getConnection(url, username, password);
    }

    public List<Mahasiswa> getAllMahasiswa() throws SQLException {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String query = "SELECT * FROM mahasiswa";
        Statement stmt = koneksi.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Mahasiswa m = new Mahasiswa(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("jurusan"),
                rs.getInt("umur"),
                rs.getString("alamat")
            );
            mahasiswaList.add(m);
        }

        return mahasiswaList;
    }

    public void addMahasiswa(Mahasiswa m) throws SQLException {
        String query = "INSERT INTO mahasiswa (nama, jurusan, umur, alamat) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = koneksi.prepareStatement(query);
        pstmt.setString(1, m.getNama());
        pstmt.setString(2, m.getJurusan());
        pstmt.setInt(3, m.getUmur());
        pstmt.setString(4, m.getAlamat());
        pstmt.executeUpdate();
    }

    public void updateMahasiswa(Mahasiswa m) throws SQLException {
        String query = "UPDATE mahasiswa SET nama = ?, jurusan = ?, umur = ?, alamat = ? WHERE id = ?";
        PreparedStatement pstmt = koneksi.prepareStatement(query);
        pstmt.setString(1, m.getNama());
        pstmt.setString(2, m.getJurusan());
        pstmt.setInt(3, m.getUmur());
        pstmt.setString(4, m.getAlamat());
        pstmt.setInt(5, m.getId());
        pstmt.executeUpdate();
    }

    public void deleteMahasiswa(int id) throws SQLException {
        String query = "DELETE FROM mahasiswa WHERE id = ?";
        PreparedStatement pstmt = koneksi.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
