package tiendhph30203.poly.duan1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import tiendhph30203.poly.duan1.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class manhinhdangky extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextFullName, editTextPhoneNumber, editTextEmail, editTextAddress;
    Button buttonSignUp;
    KhachHangDAO khachHangDAO;
    LinearLayout manhinhdangky;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangky);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        khachHangDAO = new KhachHangDAO(getApplicationContext());


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = editTextFullName.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String sodienthoai = editTextPhoneNumber.getText().toString();
                String email = editTextEmail.getText().toString();
                String diachi = editTextAddress.getText().toString();
                manhinhdangky = findViewById(R.id.manhinhdangki);
                String loaitaikhoan = "nguoidung";


                if (hoten.length() == 0 ||
                        username.length() == 0 ||
                        password.length() == 0 ||
                        sodienthoai.length() == 0 ||
                        email.length() == 0 ||
                        diachi.length() == 0) {
                    snackBar(R.layout.custom_snackbar_error2, "Không được bỏ trống");
                    return;
                } else {
                    if (hoten.isEmpty()) {
                        snackBar(R.layout.custom_snackbar_error2, "Không được bỏ trống");
                        return;

                    }
                    if (khachHangDAO.checkUserName(username) < 0) {
                        snackBar(R.layout.custom_snackbar_error2, "Tên tài khoản đã tồn tại trong hệ thống");
                        return;
                    }
                    if (!(password.length() > 7 && password.length() > 7)) {
                        snackBar(R.layout.custom_snackbar_error2, "Mật khẩu phải lớn hơn 7 kí tự");
                        return;
                    }
                    if (khachHangDAO.checkSoDienThoai(sodienthoai) < 0) {
                        snackBar(R.layout.custom_snackbar_error2, "Số điện thoại đã có trong hệ thống");
                        return;
                    }
                    if (!isValidPhoneNumber(sodienthoai)) {
                        snackBar(R.layout.custom_snackbar_error2, "Số điện thoại là số và đủ 10-12 kí tự");
                        return;
                    }
                    if (!checkemail(email)) {
                        snackBar(R.layout.custom_snackbar_error2, "Nhập sai cấu trúc gmail");
                        return;
                    }
                    if (khachHangDAO.checkGmail(email) < 0) {
                        snackBar(R.layout.custom_snackbar_error2, "Gmail đã tồn tại trên hệ thống");
                        return;
                    }


                    if (diachi.isEmpty()) {
                        snackBar(R.layout.custom_snackbar_error2, "Không được bỏ trống");
                        return;
                    }
                    if (diachi.length() < 10) {
                        snackBar(R.layout.custom_snackbar_error2, "Địa chỉ quá ngắn");
                        return;
                    }

                    boolean check = khachHangDAO.themkhachhang(hoten, username, password, sodienthoai, email, diachi, loaitaikhoan);
                    if (check) {
                        Toast.makeText(manhinhdangky.this, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(manhinhdangky.this, manhinhlogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(manhinhdangky.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void snackBar(int layout, String s) {
        Snackbar snackbar = Snackbar.make(manhinhdangky, "", Snackbar.LENGTH_LONG);
        View custom = getLayoutInflater().inflate(layout, null);
        TextView tvError = custom.findViewById(R.id.tvError);
        tvError.setText(s);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(25, 25, 25, 25);
        snackbarLayout.addView(custom, 0);
        snackbar.show();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})");
    }

    public boolean checkemail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}