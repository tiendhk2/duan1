package tiendhph30203.poly.duan1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.TaiKhoan.TaiKhoanDAO;

public class manhinhlogin extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private Button btnDangNhap, btnDangKy;
    private CheckBox cbLuuMatKhau;
    TextView tvQuenMatKhau;
    private ProgressBar progressBar;
    private KhachHangDAO khachHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhlogin);
        edtUser = findViewById(R.id.edtTenDangNhap);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        edtPass = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        progressBar = findViewById(R.id.progressBar);
        cbLuuMatKhau = findViewById(R.id.cbNhoMatKhau);
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        khachHangDAO = new KhachHangDAO(this);
        // btnDangKy = findViewById(R.id.btnDangKy);

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onForgotPasswordClick(v);
            }
        });


// Lưu trạng thái của checkbox vào SharedPreferences khi người dùng thay đổi

        cbLuuMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("rememberPassword", isChecked);
                editor.apply();
            }
        });


        // Trong quá trình khởi động ứng dụng, kiểm tra nếu "Nhớ mật khẩu" đã được chọn trước đó
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean set = sharedPreferences.getBoolean("rememberPassword", false);
        if (set) {
            // Lấy thông tin đăng nhập từ SharedPreferences và điền vào các trường tương ứng
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");

            // Điền thông tin đăng nhập vào các trường tương ứng (ví dụ: EditText)
            edtUser.setText(savedUsername);
            edtPass.setText(savedPassword);
            cbLuuMatKhau.setChecked(true);
        }


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (user.equals("")) {
                    Toast.makeText(manhinhlogin.this, "Tên đăng nhập không được để rỗng", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(manhinhlogin.this, "Mật khẩu không được để rỗng", Toast.LENGTH_SHORT).show();
                } else if (taiKhoanDAO.checkDangNhap(user, pass)) {
                    // Trong quá trình đăng nhập, kiểm tra giá trị của checkbox "Nhớ mật khẩu"
                    boolean shouldRememberPassword = cbLuuMatKhau.isChecked();
                    if (shouldRememberPassword) {
                        // Lưu thông tin đăng nhập vào SharedPreferences khi người dùng chọn "Nhớ mật khẩu"
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user); // Thay "enteredUsername" bằng tên người dùng nhập vào
                        editor.putString("password", pass); // Thay "enteredPassword" bằng mật khẩu người dùng nhập vào
                        editor.apply();
                    } else {
                        // Xóa thông tin đăng nhập trong SharedPreferences nếu người dùng không chọn "Nhớ mật khẩu"
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.apply();
                    }

                    Toast.makeText(manhinhlogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    btnDangNhap.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                    // Chạy một Runnable sau 2 giây
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(manhinhlogin.this, MainActivity.class));
                            progressBar.setVisibility(View.GONE);
                            btnDangNhap.setVisibility(View.VISIBLE);
                        }

                    }, 2000);
                } else {
                    Toast.makeText(manhinhlogin.this, "Tài khoản chưa có trên hệ thống", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onForgotPasswordClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Khôi phục mật khẩu");
        builder.setMessage("Vui lòng nhập số điện thoại của bạn");

        final EditText editTextEmail = new EditText(this);
        editTextEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        editTextEmail.setText("Đang phát triển !!!!");
        builder.setView(editTextEmail);

        builder.setPositiveButton("Gửi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy email người dùng đã nhập
                String email = editTextEmail.getText().toString().trim();
//                sendResetPasswordLink(email);
                Toast.makeText(manhinhlogin.this, "Đang phát triển !!!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

//    private void sendResetPasswordLink(String email) {
//        // Thực hiện quá trình gửi email hoặc tin nhắn SMS ở đây
//    }


}