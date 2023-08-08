package tiendhph30203.poly.duan1.ManHinhChinh;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPham;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManHinhChinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManHinhChinhFragment extends Fragment {
    ArrayList<LoaiSanPham> listLoaiSanPham;
    ArrayList<SanPham> listSanPham = new ArrayList<>();
    Spinner spSXTheoLoai;
    int maLoai, maLoaiCheckSpiner = 0;
    LoaiSanPhamDAO loaiSanPhamDAO;
    SapXepSpinnerSanPhamAdapter sapXepSpinnerSanPhamAdapter;
    RecyclerView recycle_sanphammanhinhchinh;


    private InputMethodManager inputMethodManager;


    public ManHinhChinhFragment() {

    }

    public static ManHinhChinhFragment newInstance() {
        ManHinhChinhFragment fragment = new ManHinhChinhFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_man_hinh_chinh, container, false);
        ImageSlider slider = view.findViewById(R.id.slider);
        EditText timkiemsanpham = view.findViewById(R.id.timkiemsanpham);
        ImageView btntimkiem = view.findViewById(R.id.btntimkiem);
        recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
        spSXTheoLoai = view.findViewById(R.id.spnSXLoai);
        inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        List<SlideModel> listSlide = new ArrayList<>();
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/banh-cuon-hinh-anh-mon-an-dac-san-viet-nam.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-5.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-8.jpg", ScaleTypes.FIT));
        slider.setImageList(listSlide);
//        TextView textView = view.findViewById(R.id.dichuyen);
//        Animation animation11 = AnimationUtils.loadAnimation(getContext(), R.anim.chuchay);
//        textView.startAnimation(animation11);


//        RecyclerView recyclerViewManHinhChinh = view.findViewById(R.id.recycle_loaisanphammanhinhchinh);
//        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
//        listLoaiSanPham = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
//        Adapter_LoaiSanPhamManHinhChinh adapter_loaiSanPhamManHinhChinh = new Adapter_LoaiSanPhamManHinhChinh(listLoaiSanPham, getContext(),loaiSanPhamDAO);
//        recyclerViewManHinhChinh.setAdapter(adapter_loaiSanPhamManHinhChinh);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
        SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
        listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
        Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        listLoaiSanPham = new ArrayList<>();
        listLoaiSanPham = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        listLoaiSanPham = new ArrayList<>();
        LoaiSanPham loaiSanPhamNull = new LoaiSanPham();
        loaiSanPhamNull.setTenLoaiSanPham("Tất cả các sản phẩm");
        listLoaiSanPham = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
        listLoaiSanPham.add(0, loaiSanPhamNull);
        sapXepSpinnerSanPhamAdapter = new SapXepSpinnerSanPhamAdapter(getContext(), listLoaiSanPham);
        spSXTheoLoai.setAdapter(sapXepSpinnerSanPhamAdapter);
        spSXTheoLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
                    listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
                    Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);

                } else {
                    SanPhamDAO sanPhamDAO2 = new SanPhamDAO(getContext());
                    maLoai = listLoaiSanPham.get(position).getMaLoaiSanPham();
                    ArrayList<SanPham> listsx = (ArrayList<SanPham>) sanPhamDAO2.LocSanPham(Integer.parseInt(String.valueOf(maLoai)));
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listsx, getContext(), sanPhamDAO2);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                maLoaiCheckSpiner = 1;
            }
        });


        timkiemsanpham.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Thực hiện thao tác ở đây khi người dùng nhấn phím "OK"
                    listSanPham.clear();

                    RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
                    if (timkiemsanpham.length() > 0) {
                        String ten = timkiemsanpham.getText().toString().trim();
                        listSanPham = sanPhamDAO.TimKiemSanPham(ten);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                    return true;
                }
                return false;
            }
        });


        timkiemsanpham.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    listSanPham.clear();

                    RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
                    String ten = timkiemsanpham.getText().toString().trim();

                    if (ten.equals("")) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
                        listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
                        Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                    } else {
                        listSanPham.clear();
                        recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
                        if (timkiemsanpham.length() > 0) {
                            listSanPham = sanPhamDAO.TimKiemSanPham(ten);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                            Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                            recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        }
                    }

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSanPham.clear();
                RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
                String ten = timkiemsanpham.getText().toString().trim();
                if (ten.equals("")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                    SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
                    listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
                    Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                    recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                } else {
                    listSanPham.clear();
                    recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
                    if (timkiemsanpham.length() > 0) {
                        listSanPham = sanPhamDAO.TimKiemSanPham(ten);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
                        Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(), sanPhamDAO);
                        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                }
            }
        });


        return view;
    }


}