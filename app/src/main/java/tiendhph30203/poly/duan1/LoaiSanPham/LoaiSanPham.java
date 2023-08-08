package tiendhph30203.poly.duan1.LoaiSanPham;

public class LoaiSanPham {

    private int id;
    private int maLoaiSanPham;
    private String tenLoaiSanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, int maLoaiSanPham, String tenLoaiSanPham) {
        this.id = id;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }
}
