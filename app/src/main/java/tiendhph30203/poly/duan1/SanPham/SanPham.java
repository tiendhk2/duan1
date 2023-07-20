package tiendhph30203.poly.duan1.SanPham;

public class SanPham {
    int masanpham;
    String anhsanpham;
    String linkanhsanpham;
    String tensanpham;
    String giasanpham;
    String giamgia;
    int soluongtrongkho;
    int maloai;
    String ngaysanxuat;
    String hansudung;
     int soLuongdamua;

    public int getSoLuongdamua() {
        return soLuongdamua;
    }

    public void setSoLuongdamua(int soLuongdamua) {
        this.soLuongdamua = soLuongdamua;
    }

    public SanPham(int masanpham, String tensanpham, int soLuongdamua) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.soLuongdamua = soLuongdamua;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public String getLinkanhsanpham() {
        return linkanhsanpham;
    }

    public void setLinkanhsanpham(String linkanhsanpham) {
        this.linkanhsanpham = linkanhsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(String giamgia) {
        this.giamgia = giamgia;
    }

    public int getSoluongtrongkho() {
        return soluongtrongkho;
    }

    public void setSoluongtrongkho(int soluongtrongkho) {
        this.soluongtrongkho = soluongtrongkho;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(String ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public String getHansudung() {
        return hansudung;
    }

    public void setHansudung(String hansudung) {
        this.hansudung = hansudung;
    }

    public SanPham() {
    }

    public SanPham(int masanpham, String anhsanpham, String linkanhsanpham, String tensanpham, String giasanpham, String giamgia, int soluongtrongkho, int maloai, String ngaysanxuat, String hansudung) {
        this.masanpham = masanpham;
        this.anhsanpham = anhsanpham;
        this.linkanhsanpham = linkanhsanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.giamgia = giamgia;
        this.soluongtrongkho = soluongtrongkho;
        this.maloai = maloai;
        this.ngaysanxuat = ngaysanxuat;
        this.hansudung = hansudung;
    }
}
