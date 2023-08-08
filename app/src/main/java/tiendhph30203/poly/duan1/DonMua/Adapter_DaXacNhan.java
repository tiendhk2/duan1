package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.HoaDonChiTietActivity;
import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_DaXacNhan  extends  RecyclerView.Adapter<Adapter_DaXacNhan.ViewHolder>{
    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_DaXacNhan(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = hoaDonDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_daxacnhan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtMaHoaDon.setText("" + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("" + list.get(position).getManguoidung());
        holder.txtTenNguoiDung.setText("" + list.get(position).getHoten());
        holder.txtNgayMua.setText("" + list.get(position).getNgaymua());
        holder.txtTongTien.setText("" + list.get(position).getTongtien());
        holder.txtTrangThai.setText("" + list.get(position).getTrangthai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HoaDonChiTietActivity.class);
                intent.putExtra("mahoadon", list.get(position).getMahoadon());
                context.startActivity(intent);

            }
        });

        int tinhTrang = list.get(position).getTrangthai();
        if (tinhTrang == 1) {
            holder.txtTrangThai.setText("Trạng thái: " + "Chờ xác nhận");
            holder.btnXacNhanDonHang.setVisibility(View.VISIBLE);
            holder.txtTrangThai.setTextColor(Color.YELLOW);
        } else if (tinhTrang == 2) {
            holder.txtTrangThai.setText("Trạng thái: " + "Đã xác nhận");
            holder.btnXacNhanDonHang.setVisibility(View.VISIBLE);
            holder.txtTrangThai.setTextColor(Color.YELLOW);
        }

        holder.btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                xacnhandonhang(context, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void xacnhandonhang(Context context, int i) {
        Button btnXacNhan;
        Button btnHuy;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_xacnhandonhang);
        dialog.show();
        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                HoaDon hoaDon = list.get(i);
                if (hoaDon.getTrangthai() == 0) {
                    hoaDon.setTrangthai(1);
                    hoaDonDAO.update(hoaDon);
                    list.remove(i);
                } else if (hoaDon.getTrangthai() == 1) {
                    hoaDon.setTrangthai(2);
                    hoaDonDAO.update(hoaDon);
                    list.remove(i);
                }
                else if (hoaDon.getTrangthai() == 2) {
                    hoaDon.setTrangthai(3);
                    hoaDonDAO.update(hoaDon);
                    list.remove(i);
                }  else if (hoaDon.getTrangthai() == 3) {
                    hoaDonDAO.delete(hoaDon);
                    list.remove(i);
                }
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaHoaDon, txtMaNguoiDung, txtMaSanPham, txtTenNguoiDung, txtNgayMua, txtTongTien, txtSoLuongDaMua, txtTrangThai;
        Button btnXacNhanDonHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDona);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDunga);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDunga);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMuaa);
            txtTongTien = itemView.findViewById(R.id.txtTongTiena);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThaia);
            btnXacNhanDonHang = itemView.findViewById(R.id.btnXacNhanDonHanga);


        }
    }
}
