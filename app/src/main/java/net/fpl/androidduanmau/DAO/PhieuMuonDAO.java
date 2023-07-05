package net.fpl.androidduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.PhieuMuon;
import net.fpl.androidduanmau.database.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PhieuMuonDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //them
    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put(PhieuMuon.COL_NAME_MATT_FK, phieuMuon.getMaTT());
        values.put(PhieuMuon.COL_NAME_MATV_FK, phieuMuon.getMaTV());
        values.put(PhieuMuon.COL_NAME_MASACH_FK, phieuMuon.getMaSach());
        values.put(PhieuMuon.COL_NAME_TIENTHUE, phieuMuon.getTienThue());
        values.put(PhieuMuon.COL_NAME_TRASACH, phieuMuon.getTraSach());
        values.put(PhieuMuon.COL_NAME_NGAY, simpleDateFormat.format(phieuMuon.getNgay()));
        long result = db.insert(PhieuMuon.TB_NAME, null, values);
        return result;
    }

    //sua
    public int update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put(PhieuMuon.COL_NAME_MATT_FK, phieuMuon.getMaTT());
        values.put(PhieuMuon.COL_NAME_MATV_FK, phieuMuon.getMaTV());
        values.put(PhieuMuon.COL_NAME_MASACH_FK, phieuMuon.getMaSach());
        values.put(PhieuMuon.COL_NAME_TIENTHUE, phieuMuon.getTienThue());
        values.put(PhieuMuon.COL_NAME_TRASACH, phieuMuon.getTraSach());
        values.put(PhieuMuon.COL_NAME_NGAY, simpleDateFormat.format(phieuMuon.getNgay()));
        int result = db.update(PhieuMuon.TB_NAME, values, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
        return result;
    }

    //xoa
    public int delete(String id) {
        int result = db.delete(PhieuMuon.TB_NAME, "maPM=?", new String[]{id});
        return result;
    }

    //get data nhieu tham so
    private List<PhieuMuon> getData(String sql, String... paramater) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, paramater);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int maPM = cursor.getInt(cursor.getColumnIndex(PhieuMuon.COL_NAME_MAPM_PK));
            String maTT = cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MATT_FK));
            int maTV = cursor.getInt(cursor.getColumnIndex(PhieuMuon.COL_NAME_MATV_FK));
            int maSach = cursor.getInt(cursor.getColumnIndex(PhieuMuon.COL_NAME_MASACH_FK));
            Date ngay = null;
            try {
                ngay = simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_NGAY)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int tienThue = cursor.getInt(cursor.getColumnIndex(PhieuMuon.COL_NAME_TIENTHUE));
            int traSach = cursor.getInt(cursor.getColumnIndex(PhieuMuon.COL_NAME_TRASACH));

            PhieuMuon phieuMuon = new PhieuMuon(maPM, maTT, maTV, maSach, ngay, tienThue, traSach);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //get tat ca data
    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM " + PhieuMuon.TB_NAME;
        return getData(sql);
    }

    //get data theo id
//    public PhieuMuon getID(String id){
//        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
//        List<PhieuMuon> list = getData(sql,id);
//        return list.get(0);
//    }
}
