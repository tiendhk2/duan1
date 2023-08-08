package tiendhph30203.poly.duan1.DonMua;

import java.io.Serializable;

public class GioHang implements Serializable {
    int masanpham;
    String anhsanpham;
    String linkanhsanpham;
    String tensanpham;
    int soluong;
    int giasanpham;
    int manguoidung;

    public GioHang(int masanpham, String anhsanpham, String linkanhsanpham, String tensanpham, int soluong, int giasanpham, int manguoidung) {
        this.masanpham = masanpham;
        this.anhsanpham = anhsanpham;
        this.linkanhsanpham = linkanhsanpham;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.giasanpham = giasanpham;
        this.manguoidung = manguoidung;
    }

    public GioHang() {
    }

    public GioHang( int masanpham, String anhsanpham, String tensanpham, int soluong, int giasanpham, int manguoidung, String linkanhsanpham) {

        this.masanpham = masanpham;
        this.anhsanpham = anhsanpham;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.giasanpham = giasanpham;
        this.manguoidung = manguoidung;
        this.linkanhsanpham = linkanhsanpham;
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

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getManguoidung() {
        return manguoidung;
    }

    public void setManguoidung(int manguoidung) {
        this.manguoidung = manguoidung;
    }

    public String getLinkanhsanpham() {
        return linkanhsanpham;
    }

    public void setLinkanhsanpham(String linkanhsanpham) {
        this.linkanhsanpham = linkanhsanpham;
    }
}
