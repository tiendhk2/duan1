package tiendhph30203.poly.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class manhinhlogin extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private Button btnDangNhap;
    private CheckBox cbLuuMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhlogin);
        edtUser = findViewById(R.id.edtTenDangNhap);
        edtPass = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        cbLuuMatKhau = findViewById(R.id.cbNhoMatKhau);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
//                if(thuThuDao.checkDangNhap(user,pass)){

                    startActivity(new Intent(manhinhlogin.this,MainActivity.class));
//                }else {
//                    Toast.makeText(manhinhlogin.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//
//                }

            }
        });

    }
}