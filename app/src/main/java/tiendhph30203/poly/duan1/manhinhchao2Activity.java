package tiendhph30203.poly.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tiendhph30203.poly.projectdatdoan.R;

public class manhinhchao2Activity extends AppCompatActivity {


    Button btnLogin;
    Button btnSigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao2);
        btnLogin = findViewById(R.id.btnLogin);
        btnSigin = findViewById(R.id.btnSigin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(manhinhchao2Activity.this, manhinhlogin.class));
            }
        });

        btnSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(manhinhchao2Activity.this, manhinhdangky.class));
            }
        });
    }
}