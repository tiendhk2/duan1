package tiendhph30203.poly.duan1.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamDAO;

public class Adapter_LoaiSanPhamManHinhChinh extends RecyclerView.Adapter<Adapter_LoaiSanPhamManHinhChinh.ViewHolder> {

    ArrayList<LoaiSanPham> list;
    Context context;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    SanPhamDAO sanPhamDAO;

    public Adapter_LoaiSanPhamManHinhChinh(ArrayList<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
        sanPhamDAO = new SanPhamDAO(context);
    }

    public Adapter_LoaiSanPhamManHinhChinh(ArrayList<LoaiSanPham> list, Context context, LoaiSanPhamDAO loaiSanPhamDAO) {
        this.list = list;
        this.context = context;
        this.loaiSanPhamDAO = loaiSanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanphammanhinhchinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        List<SanPham> sanPhams = sanPhamDAO.getAll();
//        String tensanpham = "";
//        for (SanPham sanPham : sanPhams) {
//            if (sanPham.getMaloai() == list.get(position).getMaLoaiSanPham()) {
//                tensanpham = sanPham.getTensanpham();
//
//            }
//        }

        holder.tvTenLoaiSanPhamManHinhChinh.setText(list.get(position).getTenLoaiSanPham());

        holder.tvTenLoaiSanPhamManHinhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + list.get(position).getTenLoaiSanPham(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiSanPhamManHinhChinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiSanPhamManHinhChinh = itemView.findViewById(R.id.tvTenLoaiSanPhamManHinhChinh);


        }
    }
}
