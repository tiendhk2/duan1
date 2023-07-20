package tiendhph30203.poly.duan1.DonMua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.Database.Database;

public class HoaDonDAO {
    private SQLiteDatabase db;
    Database database;

    public HoaDonDAO(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();
    }


    public List<HoaDon> getDSHoaDon(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai, hd.soluongdamua\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham\n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM hoadon";
        return getDSHoaDon(sql);
    }



    public List<HoaDon> getDSHoaDonTrangThai(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai, hd.soluongdamua\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham and hd.trangthai = 'chưa giao'\n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public List<HoaDon> getAll2() {
        String sql = "SELECT * FROM hoadon";
        return getDSHoaDonTrangThai(sql);
    }






    public int delete(HoaDon obj){
        return db.delete("hoadon", "mahoadon=?",new String[]{String.valueOf(obj.getMahoadon())});
    }



    public int update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("manguoidung", obj.getManguoidung());
        values.put("masanpham", obj.getMasanpham());
        values.put("ngaymua", obj.getNgaymua());
        values.put("tongtien", obj.getTongtien());
        values.put("trangthai", obj.getTrangthai());
        values.put("soluongdamua", obj.getSoluongdamua());
        return db.update("hoadon", values, "mahoadon=?", new String[]{String.valueOf(obj.getMahoadon())});
    }


    public List<HoaDon> getAllThongBao() {
        String sql = "SELECT * FROM hoadon WHERE trangthai=1";
        List<HoaDon> list = getDSHoaDon(sql);
        return list;
    }

    public List<HoaDon> getAllKH(String manguoidung) {
        String sql = "SELECT * FROM hoadon WHERE manguoidung=?";
        List<HoaDon> list = getDSHoaDon(sql, manguoidung);
        return list;
    }

    public boolean thayDoiTrangThai(int mahoadon) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai", 1);
        long check = sqLiteDatabase.update("hoadon", values, "mahoadon = ? ", new String[]{String.valueOf(mahoadon)});
        if (check == -1) {
            return false;
        }
        return true;
    }


    public boolean themHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDon.getMahoadon());
        contentValues.put("ngaymua", hoaDon.getNgaymua());
        contentValues.put("tongtien", hoaDon.getTongtien());
        contentValues.put("trangthai", hoaDon.getTrangthai());
        contentValues.put("soluongdamua", hoaDon.getSoluongdamua());
        contentValues.put("masanpham", hoaDon.getMasanpham());
//        contentValues.put("manguoidung", hoaDon.getManguoidung());
        long check = sqLiteDatabase.insert("hoadon", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;

    }


}
