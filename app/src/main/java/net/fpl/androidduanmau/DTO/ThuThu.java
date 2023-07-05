package net.fpl.androidduanmau.DTO;

public class ThuThu {
    private String maTT;
    private String hoTen;
    private String matKhau;


    public static final String TB_NAME = "ThuThu";
    public static final String COL_NAME_MATT_PK = "maTT";
    public static final String COL_NAME_HOTEN = "hoTen";
    public static final String COL_NAME_MATKHAU = "matKhau";


    public ThuThu(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public ThuThu() {
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
