package tiendhph30203.poly.duan1.ThongKeDoanhThu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tiendhph30203.poly.duan1.Database.Database;

public class ThongKeDAO {
    Database database;

    public ThongKeDAO(Context context) {
        database = new Database(context);
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau =ngaybatdau.replace("/","");
        ngayketthuc =ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongtien) \n" + "FROM hoadon \n" + "WHERE substr(ngaymua,7)||substr(ngaymua,4,2)||substr(ngaymua,1,2) between ? and ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
