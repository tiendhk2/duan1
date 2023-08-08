package tiendhph30203.poly.duan1.GioHang;

public class PhuongThucThanhToan {
    int id;
    String title;
    int img;

    public PhuongThucThanhToan(int id, String title, int img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public PhuongThucThanhToan() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
