package tiendhph30203.poly.duan1.SanPham;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.Database.Database;

public class SanPhamDAO {


    private SQLiteDatabase db;
    private Context context;


    public SanPhamDAO(Context context) {
        this.context = context;
        Database dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("anhsanpham", sanPham.getAnhsanpham());
        values.put("linkanhsanpham", sanPham.getLinkanhsanpham());
        values.put("tensanpham", sanPham.getTensanpham());
        values.put("giasanpham", sanPham.getGiasanpham());
        values.put("giamgia", sanPham.getGiamgia());
        values.put("soluongtrongkho", sanPham.getSoluongtrongkho());
        values.put("maloai", sanPham.getMaloai());
        values.put("ngaysanxuat", sanPham.getNgaysanxuat());
        values.put("hansudung", sanPham.getHansudung());
        return db.insert("sanpham", null, values);
    }
    public long addRating(float rating) {
        ContentValues values = new ContentValues();
        values.put("danhgia", rating);
        long id = db.insert("danhgia", null, values);
        return id;
    }

    public Cursor getAllRatings() {

        return db.query("danhgia", null, null, null, null, null, null);
    }

    public int update(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("anhsanpham", sanPham.getAnhsanpham());
        values.put("linkanhsanpham", sanPham.getLinkanhsanpham());
        values.put("tensanpham", sanPham.getTensanpham());
        values.put("giasanpham", sanPham.getGiasanpham());
        values.put("giamgia", sanPham.getGiamgia());
        values.put("soluongtrongkho", sanPham.getSoluongtrongkho());
        values.put("maloai", sanPham.getMaloai());
        values.put("ngaysanxuat", sanPham.getNgaysanxuat());
        values.put("hansudung", sanPham.getHansudung());
        return db.update("sanpham", values, "masanpham=?", new String[]{String.valueOf(sanPham.getMasanpham())});
    }






    public int delete(int masanpham) {
        Cursor cursor = db.rawQuery("SELECT * FROM hoadon WHERE masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("sanpham", "masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (check == -1)
            return 0;
        return 1;

    }


    public ArrayList<SanPham> getData(String sql, String... SelectArgt) {
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, SelectArgt);
        while (cursor.moveToNext()) {
            SanPham sanPham = new SanPham();
            sanPham.setMasanpham(cursor.getInt(0));
            sanPham.setAnhsanpham(cursor.getString(1));
            sanPham.setLinkanhsanpham(cursor.getString(2));
            sanPham.setTensanpham(cursor.getString(3));
            sanPham.setGiasanpham(cursor.getString(4));
            sanPham.setGiamgia(cursor.getString(5));
            sanPham.setSoluongtrongkho(cursor.getInt(6));
            sanPham.setMaloai(cursor.getInt(7));
            sanPham.setNgaysanxuat(cursor.getString(8));
            sanPham.setHansudung(cursor.getString(9));
            list.add(sanPham);
        }
        return list;
    }

    public List<SanPham> getAll() {
        String sql = "SELECT * FROM sanpham";
        return getData(sql);
    }


    //    @SuppressLint("Range")
//    public ArrayList<LoaiSach> TimKiemLoaiSach(String ten) {
//        SQLiteDatabase sqLite = dt.getWritableDatabase();
//        ArrayList<LoaiSach> list = new ArrayList<>();
//        Cursor cursor = sqLite.rawQuery("SELECT * FROM loaisach WHERE tenloai LIKE '%"+ ten +"%' ", null);
//        if(cursor.getCount()>0) {
//            cursor.moveToFirst();
//            do {
//                LoaiSach ls = new LoaiSach();
//                ls.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
//                ls.setTenLoai(cursor.getString(cursor.getColumnIndex("tenloai")));
//                list.add(ls);
//
//            }
//            while (cursor.moveToNext());
//        }
//        return list;
//    }







//    public ArrayList<Sach> getSachne() {
//        ArrayList<Sach> list = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT * FROM sach", null);
//        while (cursor.moveToNext()) {
//            Sach sach = new Sach();
//            sach.setMaSach(cursor.getInt(0));
//            sach.setTenSach(cursor.getString(1));
//            sach.setGiaThue(cursor.getString(2));
//            sach.setMaLoai(cursor.getInt(3));
//            sach.setSoluongtrangsach(cursor.getInt(4));
//            list.add(sach);
//        }
//        return list;
//    }


//    @SuppressLint("Range")
//    public ArrayList<Sach> getDSSmach2(int soluongtrangsach) {
//
//        tiendhph30203.poly.duanmau.database.database dbHelper = new database(context);
//        SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
//        ArrayList<Sach> list = new ArrayList<>();
//        String query = "SELECT sc.masach, sc.tensach, sc.soluongtrangsach, sc.maloai, lo.tenloai FROM sach sc, loaisach lo WHERE sc.maloai = lo.maloai AND sc.soluongtrangsach <=20";
//        Cursor cursor = sqLite.rawQuery(query, new String[]{String.valueOf(soluongtrangsach)});
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                Sach ls = new Sach();
//                ls.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
//                ls.setTenSach(cursor.getString(Integer.parseInt("tensach")));
//                ls.setSoluongtrangsach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongtrangsach"))));
//                list.add(ls);
//
//            }
//            while (cursor.moveToNext());
//        }

//
//        return list;
//    }
@SuppressLint("Range")
public ArrayList<SanPham> TimKiemSanPham(String ten) {
    Database database = new Database(context);
    SQLiteDatabase sqLite = database.getWritableDatabase();
    ArrayList<SanPham> list = new ArrayList<>();

    Cursor cursor = sqLite.rawQuery("SELECT  * FROM sanpham  WHERE tensanpham LIKE '%" + ten + "%' ", null);
    if (cursor.getCount() > 0) {
        cursor.moveToFirst();
        do {
            SanPham sanPham = new SanPham();
            sanPham.setMasanpham(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masanpham"))));
            sanPham.setAnhsanpham(String.valueOf(cursor.getColumnIndex("anhsanpham")));
            sanPham.setLinkanhsanpham(cursor.getString(cursor.getColumnIndex("linkanhsanpham")));
            sanPham.setTensanpham(cursor.getString(cursor.getColumnIndex("tensanpham")));
            sanPham.setGiasanpham(cursor.getString(cursor.getColumnIndex("giasanpham")));
            sanPham.setGiamgia(cursor.getString(cursor.getColumnIndex("giamgia")));
            sanPham.setSoluongtrongkho(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongtrongkho"))));
            sanPham.setMaloai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
            sanPham.setNgaysanxuat(cursor.getString(cursor.getColumnIndex("ngaysanxuat")));
            sanPham.setHansudung(cursor.getString(cursor.getColumnIndex("hansudung")));
            list.add(sanPham);

        }
        while (cursor.moveToNext());
    }
    return list;
}


    @SuppressLint("Range")
    public ArrayList<SanPham> LocSanPham(int maloai) {
        Database database = new Database(context);
        SQLiteDatabase sqLite = database.getWritableDatabase();
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT  * FROM sanpham  WHERE maloai LIKE '%" + maloai + "%' ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                SanPham sanPham = new SanPham();
                sanPham.setMasanpham(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masanpham"))));
                sanPham.setAnhsanpham(String.valueOf(cursor.getColumnIndex("anhsanpham")));
                sanPham.setLinkanhsanpham(cursor.getString(cursor.getColumnIndex("linkanhsanpham")));
                sanPham.setTensanpham(cursor.getString(cursor.getColumnIndex("tensanpham")));
                sanPham.setGiasanpham(cursor.getString(cursor.getColumnIndex("giasanpham")));
                sanPham.setGiamgia(cursor.getString(cursor.getColumnIndex("giamgia")));
                sanPham.setSoluongtrongkho(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongtrongkho"))));
                sanPham.setMaloai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
                sanPham.setNgaysanxuat(cursor.getString(cursor.getColumnIndex("ngaysanxuat")));
                sanPham.setHansudung(cursor.getString(cursor.getColumnIndex("hansudung")));
                list.add(sanPham);

            }
            while (cursor.moveToNext());
        }
        return list;
    }

    //get data theo id
    public SanPham getID(String id){
        String sql="SELECT * FROM sanpham WHERE masanpham=?";
        List<SanPham> list =getData(sql,id);
        return list.get(0);
    }




}
