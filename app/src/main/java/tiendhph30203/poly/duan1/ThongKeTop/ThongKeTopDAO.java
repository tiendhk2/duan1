package tiendhph30203.poly.duan1.ThongKeTop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tiendhph30203.poly.duan1.Database.Database;
import tiendhph30203.poly.duan1.SanPham.SanPham;

public class ThongKeTopDAO {
    Database database;

    public ThongKeTopDAO(Context context) {
        database = new Database(context);
    }

    public ArrayList<SanPham> getTop10() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT hd.masanpham,sp.tensanpham, COUNT(hd.masanpham) FROM hoadon hd, sanpham sp WHERE hd.masanpham = sp.masanpham GROUP BY hd.masanpham,sp.tensanpham ORDER BY COUNT(hd.masanpham) DESC LIMIT 10", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
