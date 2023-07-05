package net.fpl.androidduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.ThuThu;
import net.fpl.androidduanmau.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //them
    public long insert(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put(ThuThu.COL_NAME_MATT_PK, thuThu.getMaTT());
        values.put(ThuThu.COL_NAME_HOTEN, thuThu.getHoTen());
        values.put(ThuThu.COL_NAME_MATKHAU, thuThu.getMatKhau());
        long result = db.insert(ThuThu.TB_NAME, null, values);
        return result;
    }


    //sua
    public int updatePass(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put(ThuThu.COL_NAME_HOTEN, thuThu.getHoTen());
        values.put(ThuThu.COL_NAME_MATKHAU, thuThu.getMatKhau());
        int result = db.update(ThuThu.TB_NAME, values, "maTT =?", new String[]{thuThu.getMaTT()});
        return result;
    }

    //get data nhieu tham so
    private List<ThuThu> getData(String sql, String... paramater) {
//        Nó có nghĩa là bạn có thể truyền một số lượng đối số tùy ý cho phương thức (thậm chí bằng không).
//        Trong phương thức, các đối số sẽ tự động được đưa vào một mảng của kiểu được
//        chỉ định, mà bạn sử dụng để truy cập các đối số riêng lẻ.
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, paramater);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String ma = cursor.getString(cursor.getColumnIndex(ThuThu.COL_NAME_MATT_PK));
            String ten = cursor.getString(cursor.getColumnIndex(ThuThu.COL_NAME_HOTEN));
            String matKhau = cursor.getString(cursor.getColumnIndex(ThuThu.COL_NAME_MATKHAU));
            ThuThu thuThu = new ThuThu(ma, ten, matKhau);
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //get data theo id
    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    //check login
    public int checkLogin(String id, String pass) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, pass);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }


}
