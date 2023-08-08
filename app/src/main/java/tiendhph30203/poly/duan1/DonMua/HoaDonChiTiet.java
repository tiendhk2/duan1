package tiendhph30203.poly.duan1.DonMua;

public class HoaDonChiTiet {

    int maHoaDon;
    int maSP;
    double donGia;
    int soLuong;


    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHoaDon, int maSP, double donGia, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maSP = maSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
