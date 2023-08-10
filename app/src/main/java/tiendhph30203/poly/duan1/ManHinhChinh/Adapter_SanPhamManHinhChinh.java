package tiendhph30203.poly.duan1.ManHinhChinh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.duan1.SanPham.SanPham;
import tiendhph30203.poly.duan1.SanPham.SanPhamDAO;
import tiendhph30203.poly.duan1.showchonthongtindonhang;

public class Adapter_SanPhamManHinhChinh extends RecyclerView.Adapter<Adapter_SanPhamManHinhChinh.ViewHolder>{

    ArrayList<SanPham> list;
    Context context;
    private SanPhamDAO sanPhamDAO;

    public Adapter_SanPhamManHinhChinh(ArrayList<SanPham> list, Context context, SanPhamDAO sanPhamDAO) {
        this.list = list;
        this.context = context;
        this.sanPhamDAO = sanPhamDAO;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanphammanhinhchinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SanPham model = list.get(position);

        //
//        holder.tenSanPhamManHinhChinh.setText(list.get(position).getTensanpham());
//        holder.giaSanPhamManHinhChinh.setText(list.get(position).getGiasanpham());

        holder.tenSanPhamManHinhChinh.setText(model.getTensanpham());
        holder.giaSanPhamManHinhChinh.setText(model.getGiasanpham());

        SharedPreferences preferences = context.getSharedPreferences("thongtin",Context.MODE_PRIVATE);
        String quyen = (preferences.getString("loaitaikhoan",""));
        if((quyen.equals("admin"))){
            holder.imgCartManHinhChinh.setVisibility(View.INVISIBLE);
        }


        holder.imgCartManHinhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, showchonthongtindonhang.class);
                Bundle bundle = new Bundle();
                bundle.putString("linkanhsanpham",model.getLinkanhsanpham());
                bundle.putInt("masanpham",model.getMasanpham());
                bundle.putString("tensanpham",model.getTensanpham());
                bundle.putString("giasanpham",model.getGiasanpham());
                bundle.putInt("soluongtrongkho",model.getSoluongtrongkho());
                bundle.putString("ngaysanxuat",model.getNgaysanxuat());
                bundle.putString("hansudung",model.getHansudung());
                bundle.putString("hello","hello");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anhSanPhamManHinhChinh;
        TextView tenSanPhamManHinhChinh,giaSanPhamManHinhChinh;
        ImageView imgCartManHinhChinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPhamManHinhChinh = itemView.findViewById(R.id.tenSanPhamManHinhChinh);
            giaSanPhamManHinhChinh = itemView.findViewById(R.id.giaSanPhamManHinhChinh);
            anhSanPhamManHinhChinh = itemView.findViewById(R.id.anhSanPhamManHinhChinh);
            imgCartManHinhChinh = itemView.findViewById(R.id.imgCartManHinhChinh);


        }
    }
}
