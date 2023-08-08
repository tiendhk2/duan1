package tiendhph30203.poly.duan1.LoaiSanPham;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_LoaiSanPham extends RecyclerView.Adapter<Adapter_LoaiSanPham.ViewHolder> {

    ArrayList<LoaiSanPham> list;
    Context context;
    private LoaiSanPhamDAO loaiSanPhamDAO;

    public Adapter_LoaiSanPham(ArrayList<LoaiSanPham> list, Context context, LoaiSanPhamDAO loaiSanPhamDAO) {
        this.list = list;
        this.context = context;
        this.loaiSanPhamDAO = loaiSanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycleloaisanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenLoaiSanPham.setText(list.get(position).getTenLoaiSanPham());
        holder.ibDeleteLoaiSanPham.setOnClickListener(new View.OnClickListener() {

            Button btnDialogYesLoaiSanPham, btnDialogNoLoaiSanPham;
            private LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_deleteloaisanpham, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                btnDialogYesLoaiSanPham = v.findViewById(R.id.btnDialogYesLoaiSanPham);
                btnDialogNoLoaiSanPham = v.findViewById(R.id.btnDialogNoLoaiSanPham);
                btnDialogNoLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnDialogYesLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSanPhamDAO loaiSanPhamDAO1 = new LoaiSanPhamDAO(context);
                        int check = loaiSanPhamDAO1.delete(list.get(position).getMaLoaiSanPham());
                        if (check > 0) {
                            list.clear();
                            list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(context, "Đã có sản phẩm, không thể xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        holder.ibUpdateLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            EditText edtDialogUpdateLoaiSanPham;
            Button btnDialogHuySuaLoaiSanPham, btnDialogSuaLoaiSanPham;
            private LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                view = inflater1.inflate(R.layout.item_updateloaisanpham, null);
                builder.setView(view);
                Dialog dialog = builder.create();

                edtDialogUpdateLoaiSanPham = view.findViewById(R.id.edtDialogUpdateLoaiSanPham);
                btnDialogHuySuaLoaiSanPham = view.findViewById(R.id.btnDialogHuySuaLoaiSanPham);
                btnDialogSuaLoaiSanPham = view.findViewById(R.id.btnDialogSuaLoaiSanPham);
                edtDialogUpdateLoaiSanPham.setText(list.get(position).getTenLoaiSanPham());

                btnDialogHuySuaLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogSuaLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSanPham loaiSanPham = list.get(position);
                        loaiSanPham.setTenLoaiSanPham(edtDialogUpdateLoaiSanPham.getText().toString());
                        if(loaiSanPhamDAO.update(loaiSanPham) >0){
                            list.clear();
                            list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiSanPham;
        ImageView ibUpdateLoaiSanPham, ibDeleteLoaiSanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiSanPham = itemView.findViewById(R.id.tvTenLoaiSanPham);
            ibUpdateLoaiSanPham = itemView.findViewById(R.id.ibUpdateLoaiSanPham);
            ibDeleteLoaiSanPham = itemView.findViewById(R.id.ibDeleteLoaiSanPham);


        }
    }
}
