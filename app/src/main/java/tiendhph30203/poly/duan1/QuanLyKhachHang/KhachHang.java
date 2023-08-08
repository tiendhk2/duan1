package tiendhph30203.poly.duan1.QuanLyKhachHang;

public class KhachHang {
    int manguoidung;
    String hoten;
    String username;
    String password;
    String sodienthoai;
    String email;
    String diachi;
    String loaitaikhoan;

    public KhachHang() {
    }

    public KhachHang(int manguoidung, String hoten, String username, String password, String sodienthoai, String email, String diachi, String loaitaikhoan) {
        this.manguoidung = manguoidung;
        this.hoten = hoten;
        this.username = username;
        this.password = password;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachi = diachi;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getManguoidung() {
        return manguoidung;
    }

    public void setManguoidung(int manguoidung) {
        this.manguoidung = manguoidung;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
