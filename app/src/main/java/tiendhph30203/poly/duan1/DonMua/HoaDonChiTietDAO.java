package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.Database.Database;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private Context context;

    public HoaDonChiTietDAO(Context context) {
        this.context = context;
        Database database = new Database(context);
        db = database.getWritableDatabase();
    }

    public long insert(HoaDonChiTiet obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", obj.getMaHoaDon());
        contentValues.put("masanpham", obj.getMaSP());
        contentValues.put("dongia", obj.getDonGia());
        contentValues.put("soluong", obj.getSoLuong());
        return db.insert("chitietdonhang", null, contentValues);
    }

    public int update(HoaDonChiTiet obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", obj.getMaHoaDon());
        contentValues.put("masanpham", obj.getMaSP());
        contentValues.put("dongia", obj.getDonGia());
        contentValues.put("soluong", obj.getSoLuong());
        return db.update("chitietdonhang", contentValues, "mahoadon = ?", new String[]{String.valueOf(obj.getMaHoaDon())});
    }

    public int delete(String id) {
        return db.delete("chitietdonhang", "mahoadon = ?", new String[]{id});
    }

    public HoaDonChiTiet getID(String id) {
        String sql = "SELECT * FROM hoadon WHERE mahoadon = ?";
        List<HoaDonChiTiet> list = getData(sql, id);
        return list.get(0);
    }

    public List<HoaDonChiTiet> getAll() {
        String sql = "SELECT * FROM chitietdonhang";
        return getData(sql);
    }

    @SuppressLint("Range")
    public List<HoaDonChiTiet> getData(String sql, String... selectionArgs) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HoaDonChiTiet hoaDonChiTietDAO = new HoaDonChiTiet();
                hoaDonChiTietDAO.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mahoadon"))));
                hoaDonChiTietDAO.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masanpham"))));
                hoaDonChiTietDAO.setDonGia(Double.parseDouble(cursor.getString(cursor.getColumnIndex("dongia"))));
                hoaDonChiTietDAO.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
                list.add(hoaDonChiTietDAO);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
