package tiendhph30203.poly.duan1.DonMua;

public class HoaDon {
    private int id;
    private int mahoadon;
    private int manguoidung;
    private int masanpham;
    private String hoten;
    private String ngaymua;
    private int tongtien;
    private int trangthai;
    private int soluongdamua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HoaDon(int id, int mahoadon, int manguoidung, int masanpham, String hoten, String ngaymua, int tongtien, int trangthai, int soluongdamua) {
        this.id = id;
        this.mahoadon = mahoadon;
        this.manguoidung = manguoidung;
        this.masanpham = masanpham;
        this.hoten = hoten;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.soluongdamua = soluongdamua;
    }

    public HoaDon(int mahoadon, int manguoidung, int masanpham, String hoten, String ngaymua, int tongtien, int trangthai) {
        this.mahoadon = mahoadon;
        this.manguoidung = manguoidung;
        this.masanpham = masanpham;
        this.hoten = hoten;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public HoaDon() {
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getManguoidung() {
        return manguoidung;
    }

    public void setManguoidung(int manguoidung) {
        this.manguoidung = manguoidung;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getSoluongdamua() {
        return soluongdamua;
    }

    public void setSoluongdamua(int soluongdamua) {
        this.soluongdamua = soluongdamua;
    }

    public HoaDon(int mahoadon, int manguoidung, int masanpham, String hoten, String ngaymua, int tongtien, int trangthai, int soluongdamua) {
        this.mahoadon = mahoadon;
        this.manguoidung = manguoidung;
        this.masanpham = masanpham;
        this.hoten = hoten;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.soluongdamua = soluongdamua;
    }
}
