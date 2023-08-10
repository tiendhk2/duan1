package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.Database.Database;

public class HoaDonDAO {
    private SQLiteDatabase db;

    private Context context;

    public HoaDonDAO(Context context) {
        this.context = context;
        Database database = new Database(context);
        db = database.getWritableDatabase();
    }
    public long insert(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hoaDon.getMahoadon());
        values.put("ngaymua", hoaDon.getNgaymua());
        values.put("tongtien", hoaDon.getTongtien());
        values.put("trangthai", hoaDon.getTrangthai());
        values.put("masanpham", hoaDon.getMasanpham());
        values.put("manguoidung", hoaDon.getManguoidung());
        return db.insert("hoadon", null, values);
    }

    public int update(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hoaDon.getMahoadon());
        values.put("ngaymua", hoaDon.getNgaymua());
        values.put("tongtien", hoaDon.getTongtien());
        values.put("trangthai", hoaDon.getTrangthai());
        values.put("masanpham", hoaDon.getMasanpham());
        values.put("manguoidung", hoaDon.getManguoidung());
        return db.update("hoadon", values, "mahoadon=?", new String[]{String.valueOf(hoaDon.getMahoadon())});
    }


    @SuppressLint("Range")
    public List<HoaDon> getDSHoaDon(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham\n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMahoadon(cursor.getInt(cursor.getColumnIndex("mahoadon")));
                hoaDon.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setMasanpham(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                hoaDon.setNgaymua(cursor.getString(cursor.getColumnIndex("ngaymua")));
                hoaDon.setTongtien(cursor.getInt(cursor.getColumnIndex("tongtien")));
                hoaDon.setTrangthai(cursor.getInt(cursor.getColumnIndex("trangthai")));
                list.add(hoaDon);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM hoadon";
        return getDSHoaDon(sql);
    }


    public List<HoaDon> getDSTrangThai0(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham and trangthai = 0 \n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<HoaDon> getTrangThai0() {
        String sql = "SELECT * FROM hoadon";
        return getDSTrangThai0(sql);
    }


    public List<HoaDon> getDSTrangThai1(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        ContentValues values = new ContentValues();
        values.put("trangthai", 1);
        Cursor cursor = db.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham and trangthai = 1 \n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<HoaDon> getTrangThai1() {
        String sql = "SELECT * FROM hoadon";
        return getDSTrangThai1(sql);
    }


    public List<HoaDon> getDSTrangThai3(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        ContentValues values = new ContentValues();
        values.put("trangthai", 1);
        Cursor cursor = db.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham and trangthai = 3 \n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<HoaDon> getTrangThai3() {
        String sql = "SELECT * FROM hoadon";
        return getDSTrangThai3(sql);
    }

    public List<HoaDon> getDSTrangThai2(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        ContentValues values = new ContentValues();
        values.put("trangthai", 2);
        Cursor cursor = db.rawQuery("SELECT hd.mahoadon, nd.manguoidung, sp.masanpham,nd.hoten, hd.ngaymua, hd.tongtien, hd.trangthai\n" + "FROM hoadon hd, nguoidung nd, sanpham sp\n" + "WHERE hd.manguoidung = nd.manguoidung and hd.masanpham =sp.masanpham and trangthai = 2 \n", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<HoaDon> getTrangThai2() {
        String sql = "SELECT * FROM hoadon";
        return getDSTrangThai2(sql);
    }


    public int delete(HoaDon obj) {
        return db.delete("hoadon", "mahoadon=?", new String[]{String.valueOf(obj.getMahoadon())});
    }

    public int delete(int mahoadon) {
        return db.delete("hoadon", "mahoadon=?", new String[]{String.valueOf(mahoadon)});
    }




    public List<HoaDon> getAllKH(String manguoidung) {
        String sql = "SELECT * FROM hoadon WHERE manguoidung=?";
        List<HoaDon> list = getDSHoaDon(sql, manguoidung);
        return list;
    }

    public boolean thayDoiTrangThai(int mahoadon) {
        ContentValues values = new ContentValues();
        values.put("trangthai", 1);
        long check = db.update("hoadon", values, "mahoadon = ? ", new String[]{String.valueOf(mahoadon)});
        if (check == -1) {
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    public List<HoaDon> getDSHoaDon202(String sql, String... SelectArgt) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, SelectArgt);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMahoadon(cursor.getInt(cursor.getColumnIndex("mahoadon")));
                hoaDon.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setMasanpham(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                hoaDon.setNgaymua(cursor.getString(cursor.getColumnIndex("ngaymua")));
                hoaDon.setTongtien(cursor.getInt(cursor.getColumnIndex("tongtien")));
                hoaDon.setTrangthai(cursor.getInt(cursor.getColumnIndex("trangthai")));
                list.add(hoaDon);
            } while (cursor.moveToNext());
        }
        return list;
    }


    public int checkMaHoaDon(String id) {
        String sql = "SELECT * FROM hoadon WHERE mahoadon=?";
        List<HoaDon> list = getDSHoaDon202(sql, id);
        if (list.size() == 0)
            return -1;
        return 1;
    }

    //get tat ca id
    public HoaDon getID(String id) {
        String sql = "SELECT * FROM hoadon WHERE mahoadon=?";
        List<HoaDon> list = getDSHoaDon(sql, id);
        return list.get(0);
    }


    //get data nhieu tham so

    @SuppressLint("Range")
    public List<HoaDon> getDataThu(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMahoadon(cursor.getInt(cursor.getColumnIndex("mahoadon")));
                hoaDon.setManguoidung(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setMasanpham(cursor.getInt(cursor.getColumnIndex("manguoidung")));
                hoaDon.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
                hoaDon.setNgaymua(cursor.getString(cursor.getColumnIndex("ngaymua")));
                hoaDon.setTongtien(cursor.getInt(cursor.getColumnIndex("tongtien")));
                hoaDon.setTrangthai(cursor.getInt(cursor.getColumnIndex("trangthai")));
                list.add(hoaDon);
                cursor.moveToNext();
            }
        }
        return list;
    }


}
