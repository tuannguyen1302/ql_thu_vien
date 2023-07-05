package net.fpl.androidduanmau.DTO;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private Date ngay;
    private int tienThue;
    private int traSach;


    public static final String TB_NAME = "PhieuMuon";
    public static final String COL_NAME_MAPM_PK = "maPM";
    public static final String COL_NAME_MATT_FK = "maTT";
    public static final String COL_NAME_MATV_FK = "maTV";
    public static final String COL_NAME_MASACH_FK = "maSach";
    public static final String COL_NAME_NGAY = "ngay";
    public static final String COL_NAME_TIENTHUE = "tienThue";
    public static final String COL_NAME_TRASACH = "traSach";


    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, Date ngay, int tienThue, int traSach) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }

    public PhieuMuon() {
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }
}
