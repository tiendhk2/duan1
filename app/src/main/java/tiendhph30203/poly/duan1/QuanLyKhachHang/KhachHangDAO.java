package tiendhph30203.poly.duan1.QuanLyKhachHang;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.Database.Database;

public class KhachHangDAO {
    Database database;
    SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<KhachHang> getDSKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nguoidung", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {

                KhachHang khachHang = new KhachHang();
                khachHang.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                khachHang.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                khachHang.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                khachHang.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                khachHang.setSodienthoai(cursor.getString(cursor.getColumnIndex("sodienthoai")));
                khachHang.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                khachHang.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
                khachHang.setLoaitaikhoan(cursor.getString(cursor.getColumnIndex("loaitaikhoan")));
                list.add(khachHang);


            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themkhachhang(String hoten, String username, String password, String sodienthoai, String email, String diachi, String loaitaikhoan) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("username", username);
        values.put("password", password);
        values.put("sodienthoai", sodienthoai);
        values.put("email", email);
        values.put("diachi", diachi);
        values.put("loaitaikhoan", loaitaikhoan);
        long check = sqLiteDatabase.insert("nguoidung", null, values);
        if (check == -1)
            return false;
        return true;
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM nguoidung WHERE manguoidung=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(2);
    }

    public int update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoten", obj.getHoten());
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("sodienthoai", obj.getSodienthoai());
        values.put("email", obj.getEmail());
        values.put("diachi", obj.getDiachi());
        values.put("loaitaikhoan", obj.getDiachi());
        return db.update("nguoidung", values, "manguoidung=?", new String[]{String.valueOf(obj.getManguoidung())});
    }

    //get data nhieu tham so
    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String... selectionArgs) {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nguoidung", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                KhachHang khachHang = new KhachHang();
                khachHang.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                khachHang.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                khachHang.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                khachHang.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                khachHang.setSodienthoai(cursor.getString(cursor.getColumnIndex("sodienthoai")));
                khachHang.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                khachHang.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
                khachHang.setLoaitaikhoan(cursor.getString(cursor.getColumnIndex("loaitaikhoan")));
                list.add(khachHang);
            } while (cursor.moveToNext());

        }

        return list;
    }


    public KhachHang getUserName(String id) {
        String sql = "SELECT * FROM nguoidung WHERE username=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(2);
    }

    public KhachHang getUserName2(String id) {
        String sql = "SELECT * FROM nguoidung WHERE username=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(2);
    }


    public boolean capNhapThongTin(int manguoidung, String hoten, String username, String email, String diachi) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("username", username);
        values.put("email", email);
        values.put("diachi", diachi);
        long check = sqLiteDatabase.update("nguoidung", values, "manguoidung = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1)
            return false;
        return true;

    }


    public int xoaThongTinThanhVien(int manguoidung) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM hoadon WHERE manguoidung = ?", new String[]{String.valueOf(manguoidung)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("nguoidung", "manguoidung = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1)
            return 0;
        return 1;
    }

}
