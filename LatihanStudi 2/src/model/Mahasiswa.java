package model;

public class Mahasiswa {
    private int id;
    private String nama;
    private String jurusan;
    private int umur;
    private String alamat;

    public Mahasiswa() {}

    public Mahasiswa(int id, String nama, String jurusan, int umur, String alamat) {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
        this.umur = umur;
        this.alamat = alamat;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    public int getUmur() { return umur; }
    public void setUmur(int umur) { this.umur = umur; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
}
