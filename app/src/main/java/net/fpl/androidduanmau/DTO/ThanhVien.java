package net.fpl.androidduanmau.DTO;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;


    public static final String TB_NAME = "ThanhVien";
    public static final String COL_NAME_MATV_PK = "maTV";
    public static final String COL_NAME_HOTEN = "hoTen";
    public static final String COL_NAME_NAMSINH = "namSinh";


    public ThanhVien(int maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
