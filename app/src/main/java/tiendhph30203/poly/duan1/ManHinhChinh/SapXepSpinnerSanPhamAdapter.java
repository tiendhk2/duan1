package tiendhph30203.poly.duan1.ManHinhChinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.R;

public class SapXepSpinnerSanPhamAdapter extends ArrayAdapter<LoaiSanPham> {
    private Context context;
    private ArrayList<LoaiSanPham> lists;
    private String[] array=new String[]{"Tăng dần","Giảm dần"};
    TextView tvMaLoai,tvTenLoaiSP;

    public SapXepSpinnerSanPhamAdapter(@NonNull Context context, ArrayList<LoaiSanPham> lists) {
        super(context,0,lists);
        this.context=context;
        this.lists=lists;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sap_xep_san_pham_spinner,null);
        }
        final LoaiSanPham item= lists.get(position);
        if (item!=null){

            tvTenLoaiSP=v.findViewById(R.id.tvTenLoaiSp);
            tvTenLoaiSP.setText(item.getTenLoaiSanPham());
        }

        return v;
    }

    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loai_san_pham_spinner_dropview,null);
        }
        final LoaiSanPham item= lists.get(position);
        if (item!=null){

            tvTenLoaiSP=v.findViewById(R.id.tvTenLoaiSp);
            tvTenLoaiSP.setText(item.getTenLoaiSanPham());
        }
        return v;
    }
}
