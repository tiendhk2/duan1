package tiendhph30203.poly.duan1.ThongKeDoanhThu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

import tiendhph30203.poly.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeDoanhThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeDoanhThuFragment extends Fragment {


    public ThongKeDoanhThuFragment() {

    }


    public static ThongKeDoanhThuFragment newInstance() {
        ThongKeDoanhThuFragment fragment = new ThongKeDoanhThuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_doanh_thu, container, false);
        EditText edtBatDau = view.findViewById(R.id.edtBatDau);
        EditText edtKetThuc = view.findViewById(R.id.edtKetThuc);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtKetQua = view.findViewById(R.id.ketqua);
        Calendar calendar = Calendar.getInstance();
        edtBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String ngay = "";
                        String thang = "";
                        if (day < 10) {
                            ngay = "0" + day;
                        } else {
                            ngay = String.valueOf(day);
                        }

                        if ((month + 1) < 10) {
                            thang = "0" + (month + 1);
                        } else {
                            thang = String.valueOf(month + 1);
                        }


                        edtBatDau.setText(year + "/" + thang + "/" + ngay);
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        edtKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String ngay = "";
                        String thang = "";
                        if (day < 10) {
                            ngay = "0" + day;
                        } else {
                            ngay = String.valueOf(day);
                        }

                        if ((month + 1) < 10) {
                            thang = "0" + (month + 1);
                        } else {
                            thang = String.valueOf(month + 1);
                        }


                        edtKetThuc.setText(year + "/" + thang + "/" + ngay);
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKe = new ThongKeDAO(getContext());
                String ngaybatdau = edtBatDau.getText().toString();
                String ngayketthuc = edtKetThuc.getText().toString();
                int doanhthu = thongKe.getDoanhThu(ngaybatdau, ngayketthuc);
                txtKetQua.setText(doanhthu + " VNĐ");

            }
        });
        return view;
    }
}