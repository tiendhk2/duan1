package tiendhph30203.poly.duan1.DonMua;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    private ArrayList<GioHang> list;
    private int mahoadon;

    public Order(ArrayList<GioHang> list, int mahoadon, String tenKhachHang, Long soDienThoai, String diaChi, double tongTien, int phuongThucThanhToan, int maKH, int maNV, int maOrder, int maQuyen, int diemThuong) {
        this.list = list;
        this.mahoadon = mahoadon;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maOrder = maOrder;
        this.maQuyen = maQuyen;
        this.diemThuong = diemThuong;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    private String tenKhachHang;
    private Long soDienThoai;
    private String diaChi;
    private double tongTien;
    private int phuongThucThanhToan;
    private int maKH;
    private int maNV;
    private int maOrder;
    private int maQuyen;
    private int diemThuong;

    public Order(ArrayList<GioHang> list, String tenKhachHang, Long soDienThoai, String diaChi, double tongTien, int phuongThucThanhToan, int maKH,int maNV,int maOrder,int maQuyen) {
        this.list = list;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maOrder = maOrder;
        this.maQuyen = maQuyen;
        this.diemThuong = diemThuong;
    }


    @Override
    public String toString() {
        return "Order{" +
                "tongTien=" + tongTien +
                '}';
    }

    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(int maOrder) {
        this.maOrder = maOrder;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public ArrayList<GioHang> getList() {
        return list;
    }

    public void setList(ArrayList<GioHang> list) {
        this.list = list;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Long getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(Long soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(int phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
    public String getListProduct(){
        if(list == null || list.isEmpty()){
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(int i  = 0;i <list.size();i++){
            GioHang gioHang = list.get(i);
            if(stringBuilder.length()>0){
                stringBuilder.append("\n");

            }
            stringBuilder.append(gioHang.getTensanpham());
            stringBuilder.append(" 1x" +gioHang.getSoluong());
        }
        return stringBuilder.toString();
    }
}
