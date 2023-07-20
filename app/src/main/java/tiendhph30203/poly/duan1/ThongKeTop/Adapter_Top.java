package tiendhph30203.poly.duan1.ThongKeTop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.duan1.R;
import tiendhph30203.poly.duan1.SanPham.SanPham;

public class Adapter_Top extends RecyclerView.Adapter<Adapter_Top.ViewHolder> {
    Context context;
    ArrayList<SanPham> list;

    public Adapter_Top(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycletop10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSanPhamTop.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMasanpham()));
        holder.txtTenSamPhamTop.setText("Tên sản phẩm: " + list.get(position).getTensanpham());
        holder.txtSoLuongMuonTop.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuongdamua()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSanPhamTop, txtTenSamPhamTop, txtSoLuongMuonTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSanPhamTop = itemView.findViewById(R.id.txtMaSanPhamTop);
            txtTenSamPhamTop = itemView.findViewById(R.id.txtTenSamPhamTop);
            txtSoLuongMuonTop = itemView.findViewById(R.id.txtSoLuongMuonTop);

        }
    }
}
