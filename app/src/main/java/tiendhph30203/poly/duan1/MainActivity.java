package tiendhph30203.poly.duan1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    Toolbar toolbar;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toobar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.Nav);
        frameLayout = findViewById(R.id.LayoutConten);
        drawerLayout = findViewById(R.id.Drawer);


        // Thay đổi avatar
        View header = navigationView.getHeaderView(0);
        TextView txtTen = header.findViewById(R.id.txtTen);
        ImageView thaydoianh = header.findViewById(R.id.thaydoianh);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Phân quyền
        // .....
        //

        ActivityResultLauncher<Intent> chooseImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri selectedImageUri = data.getData();
                            if (null != selectedImageUri) {
                                thaydoianh.setImageURI(selectedImageUri);

                                BitmapDrawable bitmapDrawable = (BitmapDrawable) thaydoianh.getDrawable();
                                bitmap = bitmapDrawable.getBitmap();

                            }
                        }
                    }
                });

        thaydoianh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                chooseImage.launch(i);

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itManHinhChinh) {
            repLaceFragment(ManHinhChinhFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itLoaiSanPham) {
            repLaceFragment(LoaiSanPhamFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itSanPham) {
            repLaceFragment(SanPhamFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itQuanLyHoaDon) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = null;
            fragment = new Tablayoutactivity();
            drawerLayout.close();
            fragmentManager.beginTransaction().replace(R.id.LayoutConten, fragment).commit();
        } else if (id == R.id.itQuanLyDonMua) {
            repLaceFragment(DonMuaFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itQuanLyKhachHang) {
            repLaceFragment(QuanLyKhachHangFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itThongKeDT) {
            repLaceFragment(ThongKeDoanhThuFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itThongKeTop) {
            repLaceFragment(ThongKeTopFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itDoiMatKhau) {
            showDialogDoiMatKhau();
            drawerLayout.close();
        }
        // Tìm kiếm
//        else if (id == R.id.itTimKiem) {
//            repLaceFragment(timkiemsach.newInstance());
//            drawerLayout.close();
//        }
        else if (id == R.id.itDangXuat) {
            Intent intent = new Intent(MainActivity.this, manhinhlogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        toolbar.setTitle(item.getTitle());
        return true;
    }

    //Chuyển fragment
    public void repLaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.LayoutConten, fragment);
        fragmentTransaction.commit();
    }

    private void showDialogDoiMatKhau() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(TrangChuActivity.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
//        EditText edtPassCu = view.findViewById(R.id.edtPassCu);
//        EditText edtPassMoi = view.findViewById(R.id.edtPassMoi);
//        EditText edtPassMoi2 = view.findViewById(R.id.edtPassMoi2);
//
//        builder.setView(view);
//        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String passcu = edtPassCu.getText().toString().trim();
//                String passmoi = edtPassMoi.getText().toString().trim();
//                String passmoi2 = edtPassMoi2.getText().toString().trim();
//
//                if (passmoi.equals(passmoi2)) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
//                    String mathuthu = sharedPreferences.getString("mathuthu", "");
//                    ThuThuDao thuThuDao = new ThuThuDao(TrangChuActivity.this);
//                    boolean check = thuThuDao.capnhatmatkhau(mathuthu, passcu, passmoi);
//                    if (check) {
//                        Toast.makeText(TrangChuActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(TrangChuActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(TrangChuActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(TrangChuActivity.this, "Mật khẩu phải trùng với nhau", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

}