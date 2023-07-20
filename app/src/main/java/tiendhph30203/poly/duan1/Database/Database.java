package tiendhph30203.poly.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    static final String DBName = "FoodDelivery";
    static final int DBVersion = 1;

    public Database(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        // Create table Người Dùng
        String dbnguoidung = "CREATE TABLE nguoidung  (manguoidung INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "hoten TEXT NOT NULL," +
                "sodienthoai INTEGER NOT NULL UNIQUE," +
                "email TEXT," +
                "diachi TEXT," +
                "loaitaikhoan TEXT NOT NULL)";
        db.execSQL(dbnguoidung);



        //Tạo username và password
        db.execSQL("INSERT INTO nguoidung  VALUES(1,'duongtien','123','Dương Tiến',0332322764,'tiendhph30203@fpt.edu.vn','121 Mỹ Đình','Vip')" +
                ",(2,'duongyen','123abc123','Dương Yến',0384867860,'duongtienpkfr@gmail.com','123 Mỹ Đình','No Vip')");


        // Create table Loại Sản Phẩm
        String ctLoaiSanPham = "CREATE TABLE loaisanpham(maloai INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tenloai TEXT NOT NULL)";
        db.execSQL(ctLoaiSanPham);

        //Tạo loại sản phẩm
        db.execSQL("INSERT INTO loaisanpham VALUES (1, 'Gà'),(2,'Trà Sữa'),(3, 'Coffee')");
        // Create table Sản Phẩm
        String ctSanPham = "CREATE TABLE sanpham(masanpham INTEGER PRIMARY KEY AUTOINCREMENT," +
                "anhsanpham TEXT," +
                "linkanhsanpham TEXT," +
                "tensanpham TEXT NOT NULL ," +
                "giasanpham TEXT NOT NULL ," +
                "giamgia TEXT NOT NULL ," +
                "soluongtrongkho INTEGER NOT NULL ," +
                "maloai INTEGER REFERENCES loaisanpham(maloai)," +
                "ngaysanxuat TEXT NOT NULL," +
                "hansudung TEXT NOT NULL)";
        db.execSQL(ctSanPham);

        //Tạo sản phẩm
        db.execSQL("INSERT INTO sanpham VALUES (1, 'NONE', 'Link 1', 'KFC', '1000','10',20,1,'01/06/2023','30/06/2023')," +
                "(2, 'NONE', 'Link 2', 'Tocotoco', '2000','10',20,2,'01/06/2023','30/06/2023'), " +
                "(3, 'NONE', 'Link 3', 'Bạc xỉu', '3000','10',18,3,'01/06/2023','30/06/2023')");

        //Create table Hóa Đơn
        String ctHoaDon = "CREATE TABLE hoadon (" +
                "mahoadon INTEGER PRIMARY KEY," +
                "ngaymua TEXT NOT NULL," +
                "tongtien TEXT NOT NULL," +
                "trangthai INTEGER NOT NULL," +
                "soluongdamua INTEGER NOT NULL," +
                "masanpham INTEGER REFERENCES sanpham(masanpham)," +
                "manguoidung INTEGER REFERENCES nguoidung(manguoidung))";
        db.execSQL(ctHoaDon);

        //Tạo hóa đơn
        db.execSQL("INSERT INTO hoadon VALUES(1,'15/07/2023','3000','Đã giao',1,1,1),(2,'20/07/2023','3000','Chưa giao',2,2,2)");







    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableNguoiDung = "DROP TABLE IF EXISTS nguoidung";
        db.execSQL(dropTableNguoiDung);
        String dropTableLoaiSanPham = "DROP TABLE IF EXISTS loaisanpham";
        db.execSQL(dropTableLoaiSanPham);
        String dropTableSanPham = "DROP TABLE IF EXISTS sanpham";
        db.execSQL(dropTableSanPham);
        String dropHoaDon = "DROP TABLE IF EXISTS hoadon";
        db.execSQL(dropHoaDon);
        onCreate(db);
    }
}
