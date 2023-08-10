package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.Database.Database;
import tiendhph30203.poly.duan1.FragmentHoaDon.CountCart;
import tiendhph30203.poly.duan1.Interface.ChangeNumberItemCartList;
import tiendhph30203.poly.duan1.SanPham.SanPham;
import tiendhph30203.poly.duan1.SanPham.SanPhamDAO;

public class GioHangDAO2 {
    private SQLiteDatabase db;
    Database database;

    public GioHangDAO2(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();

    }

//    public ArrayList<GioHang> getGioHang(String sql, String... SelectArgt) {
//        ArrayList<GioHang> list = new ArrayList<>();
//        Cursor cursor = db.rawQuery(sql, SelectArgt);
//        while (cursor.moveToNext()) {
//            GioHang gioHang = new GioHang();
//            gioHang.setMasanpham(cursor.getInt(0));
//            gioHang.setAnhsanpham(cursor.getString(1));
//            gioHang.setLinkanhsanpham(cursor.getString(2));
//            gioHang.setTensanpham(cursor.getString(3));
//            gioHang.setSoluong(cursor.getInt(4));
//            gioHang.setGiasanpham(cursor.getInt(5));
//            gioHang.setManguoidung(cursor.getInt(6));
//
//            list.add(gioHang);
//        }
//        ;
//        return list;
//
//    }
//
//    public List<GioHang> getAll() {
//        String sql = "SELECT * FROM giohang";
//        return getGioHang(sql);
//    }


    public long insertGioHang(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("anhsanpham", gioHang.getAnhsanpham());
        values.put("giasanpham", gioHang.getGiasanpham());
        values.put("linkanhsanpham", gioHang.getLinkanhsanpham());
        values.put("manguoidung", gioHang.getManguoidung());
        values.put("masanpham", gioHang.getMasanpham());
        values.put("soluong", gioHang.getSoluong());
        values.put("tensanpham", gioHang.getTensanpham());
        long res = db.insert("giohang", null, values);
        return res;
    }

    public long updateGioHang(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("anhsanpham", gioHang.getAnhsanpham());
        values.put("giasanpham", gioHang.getGiasanpham());
        values.put("linkanhsanpham", gioHang.getLinkanhsanpham());
        values.put("manguoidung", gioHang.getManguoidung());
        values.put("masanpham", gioHang.getMasanpham());
        values.put("soluong", gioHang.getSoluong());
        values.put("tensanpham", gioHang.getTensanpham());
        return db.update("giohang", values, "masanpham=?", new String[]{String.valueOf(gioHang.getMasanpham())});
    }




    public List<GioHang> getData(String sql, String... selectionArgs) {
        List<GioHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            GioHang gioHang = new GioHang();
            gioHang.setMasanpham(cursor.getInt(0));
            gioHang.setAnhsanpham(cursor.getString(1));
            gioHang.setLinkanhsanpham(cursor.getString(2));
            gioHang.setTensanpham(cursor.getString(3));
            gioHang.setSoluong(cursor.getInt(4));
            gioHang.setGiasanpham(cursor.getInt(5));
            gioHang.setManguoidung(cursor.getInt(6));
            list.add(gioHang);
        }
        return list;
    }



    public int checkValue(String id, String manguoidung) {
        String sql = "SELECT * FROM giohang WHERE masanpham=? AND manguoidung=?";
        List<GioHang> list = getData(sql, id, manguoidung);
        if (list.size() == 0)
            return -1;
        return 1;
    }

    public int delete(GioHang gioHang) {
        return db.delete("giohang", "masanpham=?", new String[]{gioHang.getMasanpham() + ""});
    }


    public int deleteGioHang(String id, int maQuyen) {
        int kq = 0;
        if (maQuyen == 1) {
            kq = db.delete("giohang", "manguoidung=?", new String[]{id});
        }
        return kq;
    }


    public List<GioHang> getAll(String manguoidung, int quyen) {
        String sql = null;
        if (quyen == 1) {
            sql = "SELECT * FROM giohang WHERE manguoidung=?";
        }
        List<GioHang> list = getData(sql, manguoidung);
        return list;
    }





    public ArrayList<GioHang> getListCart(String maOrder, int quyen) {
        return (ArrayList<GioHang>) getAll(maOrder, quyen);
    }


    public void plusNumber(ArrayList<GioHang> list, int position, Context context, View view, ChangeNumberItemCartList changeNumberItemCartList) {
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        int masanpham = list.get(position).getMasanpham();
        SanPham sanPham = sanPhamDAO.getID(masanpham + "");
        if (sanPham.getSoluongtrongkho() <= list.get(position).getSoluong()) {
            Snackbar snackbar = Snackbar
                    .make(view, "Sản phẩm trong kho không đủ", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        list.get(position).setSoluong(list.get(position).getSoluong() + 1);
        updateGioHang(list.get(position));
        changeNumberItemCartList.changed();
    }


    public void minusNumber(ArrayList<GioHang> list, int position, Context context, ChangeNumberItemCartList changeNumberItemCartList) {
        if (list.get(position).getSoluong() == 1) {
            delete(list.get(position));
            list.remove(position);
        } else {
            list.get(position).setSoluong(list.get(position).getSoluong() - 1);
            updateGioHang(list.get(position));
        }

        changeNumberItemCartList.changed();
    }

    @SuppressLint("Range")
    public List<CountCart> getCountCart(String magiohang, int quyen) {
        String sqlCount = null;
        if (quyen == 1) {
            sqlCount = "SELECT masanpham,count(masanpham) as soluong FROM giohang WHERE manguoidung=?";
        }
        List<CountCart> list = new ArrayList<CountCart>();
        Cursor cursor = db.rawQuery(sqlCount, new String[]{magiohang});
        while (cursor.moveToNext()) {
            CountCart sl = new CountCart();
            sl.soluong = (Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(sl);
        }
        return list;
    }


    public int getTolalFee(String manguoidung, int quyen) {
        ArrayList<GioHang> list = getListCart(manguoidung, quyen);
        int fee = 0;
        for (int i = 0; i < list.size(); i++) {
            fee = fee + (list.get(i).getGiasanpham() * list.get(i).getSoluong());
        }
        return fee;
    }
}
