package tiendhph30203.poly.duan1.LoaiSanPham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.Database.Database;

public class LoaiSanPhamDAO {
    private Context context;
    private SQLiteDatabase db;


    public LoaiSanPhamDAO(Context context) {
        this.context = context;
        Database dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSanPham loaiSanPham) {
        ContentValues values = new ContentValues();
        values.put("tenloai", loaiSanPham.getTenLoaiSanPham());
        return db.insert("loaisanpham", null, values);
    }

    public int update(LoaiSanPham loaiSanPham) {
        ContentValues values = new ContentValues();
        values.put("tenloai", loaiSanPham.getTenLoaiSanPham());
        return db.update("loaisanpham", values, "maloai=?", new String[]{String.valueOf(loaiSanPham.getMaLoaiSanPham())});
    }

    public int delete(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM sanpham WHERE maloai = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("loaisanpham", "maloai=?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;


//
//        ContentValues values = new ContentValues();
//        values.put("tenloai", id);
//        return db.delete("loaisanpham", "maloai=?", new String[]{String.valueOf(id)});
    }



    public List<LoaiSanPham> getData(String sql, String... SelectArgt) {
        List<LoaiSanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, SelectArgt);
        while (cursor.moveToNext()) {
            LoaiSanPham loaiSanPham = new LoaiSanPham();
            loaiSanPham.setMaLoaiSanPham(cursor.getInt(0));
            loaiSanPham.setTenLoaiSanPham(cursor.getString(1));
            list.add(loaiSanPham);
        }
        return list;
    }


//    @SuppressLint("Range")
//    public ArrayList<Sach> TimKiemLoaiSach(int ten) {
//        tiendhph30203.poly.duanmau.database.database dbHelper = new database(context);
//        SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
//        ArrayList<Sach> list = new ArrayList<>();
//        Cursor cursor = sqLite.rawQuery("SELECT  sc.masach, sc.tensach, sc.soluongtrangsach, sc.maloai, lo.tenloai  FROM sach sc, loaisach lo WHERE sc.maloai = lo.maloai AND sc.soluongtrangsach <= " + ten, null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                Sach ls = new Sach();
//                ls.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
//                ls.setTenSach(cursor.getString(cursor.getColumnIndex("tensach")));
//                ls.setSoluongtrangsach(cursor.getColumnIndex("soluongtrangsach"));
//                list.add(ls);
//
//            }
//            while (cursor.moveToNext());
//        }
//        return list;
//    }

    public List<LoaiSanPham> getAll() {
        String sql = "SELECT * FROM loaisanpham";
        return getData(sql);
    }
}
