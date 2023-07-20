package tiendhph30203.poly.duan1.SanPham;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_SanPham extends RecyclerView.Adapter<Adapter_SanPham.ViewHolder> {

    List<SanPham> list;
    Context context;
    private SanPhamDAO sanPhamDAO;
    LoaiSanPhamDAO loaiSanPhamDAO;

    public Adapter_SanPham(Context context) {
        this.context = context;
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
    }

    public void setData(List<SanPham> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Adapter_SanPham(ArrayList<SanPham> list, Context context, SanPhamDAO sanPhamDAO) {
        this.list = list;
        this.context = context;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyclesanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        List<LoaiSanPham> loaiSanPham1 = loaiSanPhamDAO.getAll();
        String tenLoaiSanPham = "";
        for (LoaiSanPham loaiSanPham : loaiSanPham1) {
            if (loaiSanPham.getMaLoaiSanPham() == list.get(position).getMaloai()) {
                tenLoaiSanPham = loaiSanPham.getTenLoaiSanPham();
            }
        }
        holder.txtLoaiSanPham.setText(tenLoaiSanPham);
        holder.txtTenSanPham.setText(list.get(position).getTensanpham());
        holder.txtAnhSanPham.setText(list.get(position).getAnhsanpham());
        holder.txtLinkAnhSanPham.setText(list.get(position).getLinkanhsanpham());
        holder.txtGiaSanPham.setText(list.get(position).getGiasanpham());
        holder.txtGiamGia.setText(list.get(position).getGiamgia());
        holder.txtSoLuongTrongKho.setText(String.valueOf(list.get(position).getSoluongtrongkho()));
        holder.txtNgaySanXuat.setText(list.get(position).getNgaysanxuat());
        holder.txtHanSuDung.setText(list.get(position).getHansudung());

        holder.ibDeleteSanPham.setOnClickListener(new View.OnClickListener() {
            Button btnDiaLogXoaSanPham, btnDiaLogHuyXoaSanPham;
            private SanPhamDAO sanPhamDAO = new SanPhamDAO(context);

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_deleltesanpham, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                btnDiaLogHuyXoaSanPham = v.findViewById(R.id.btnDiaLogHuyXoaSanPham);
                btnDiaLogXoaSanPham = v.findViewById(R.id.btnDiaLogXoaSanPham);

                btnDiaLogHuyXoaSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnDiaLogXoaSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int check = sanPhamDAO.delete(list.get(position).getMasanpham());
                        switch (check) {
                            case 1:
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list = (ArrayList<SanPham>) sanPhamDAO.getAll();
                                notifyDataSetChanged();
                                dialog.dismiss();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;
                            case -1:
                                Toast.makeText(context, "Xóa không thành công vì đang có hóa đơn", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;

                        }
                    }
                });
                dialog.show();
            }
        });
        holder.ibUpdateSanPham.setOnClickListener(new View.OnClickListener() {

            EditText edtSuaTenSanPham, edtSuaAnhSanPham, edtSuaLinkAnhSanPham, edtSuaGiaSanPham, edtSuaGiamGia, edtSuaSoLuongTrongKho, edtSuaNgaySanXuat, edtSuaHanSuDung;
            Spinner spUpdateChonLoaiSanPham;
            Button btnDialodHuySuaSanPham, btnDialogSuaSanPham;
            SanPhamDAO sanPhamDAO = new SanPhamDAO(context);

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                v = inflater1.inflate(R.layout.item_updatesanpham, null);
                builder.setView(v);
                Dialog dialog = builder.create();
                edtSuaTenSanPham = v.findViewById(R.id.edtSuaTenSanPham);
                edtSuaAnhSanPham = v.findViewById(R.id.edtSuaAnhSanPham);
                edtSuaLinkAnhSanPham = v.findViewById(R.id.edtSuaLinkAnhSanPham);
                edtSuaGiaSanPham = v.findViewById(R.id.edtSuaGiaSanPham);
                edtSuaGiamGia = v.findViewById(R.id.edtSuaGiamGia);
                edtSuaSoLuongTrongKho = v.findViewById(R.id.edtSuaSoLuongTrongKho);
                edtSuaNgaySanXuat = v.findViewById(R.id.edtSuaNgaySanXuat);
                edtSuaHanSuDung = v.findViewById(R.id.edtSuaHanSuDung);

                btnDialodHuySuaSanPham = v.findViewById(R.id.btnDialodHuySuaSanPham);
                btnDialogSuaSanPham = v.findViewById(R.id.btnDialogSuaSanPham);
                spUpdateChonLoaiSanPham = v.findViewById(R.id.spUpdateChonLoaiSanPham);

                edtSuaTenSanPham.setText(list.get(position).getTensanpham());
                edtSuaAnhSanPham.setText(list.get(position).getAnhsanpham());
                edtSuaLinkAnhSanPham.setText(list.get(position).getLinkanhsanpham());
                edtSuaGiaSanPham.setText(list.get(position).getGiasanpham());
                edtSuaGiamGia.setText(list.get(position).getGiamgia());
                edtSuaSoLuongTrongKho.setText(String.valueOf(list.get(position).getSoluongtrongkho()));
                edtSuaNgaySanXuat.setText(list.get(position).getNgaysanxuat());
                edtSuaHanSuDung.setText(list.get(position).getHansudung());

                SanPham sanPham = new SanPham();
                List<String> lst = new ArrayList<>();
                List<LoaiSanPham> ok = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
                for (LoaiSanPham loaiSanPham : ok) {
                    lst.add(loaiSanPham.getTenLoaiSanPham());
                }
                spUpdateChonLoaiSanPham.setSelection(sanPham.getMaloai());

                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, lst);
                spUpdateChonLoaiSanPham.setAdapter(adapter);
                btnDialodHuySuaSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogSuaSanPham.setOnClickListener(new View.OnClickListener() {
                    private List<LoaiSanPham> ok = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();

                    @Override
                    public void onClick(View v) {
                        SanPham sanPham1 = list.get(position);
                        sanPham1.setTensanpham(edtSuaTenSanPham.getText().toString());
                        sanPham1.setAnhsanpham(edtSuaAnhSanPham.getText().toString());
                        sanPham1.setLinkanhsanpham(edtSuaLinkAnhSanPham.getText().toString());
                        sanPham1.setGiasanpham(edtSuaGiaSanPham.getText().toString());
                        sanPham1.setGiamgia(edtSuaGiamGia.getText().toString());
                        sanPham1.setSoluongtrongkho(Integer.parseInt(edtSuaSoLuongTrongKho.getText().toString()));
                        sanPham1.setNgaysanxuat(edtSuaNgaySanXuat.getText().toString());
                        sanPham1.setHansudung(edtSuaHanSuDung.getText().toString());

                        for (LoaiSanPham loaiSanPham : ok) {
                            if (loaiSanPham.getTenLoaiSanPham().equals(spUpdateChonLoaiSanPham.getSelectedItem().toString())) {
                                sanPham1.setMaloai(loaiSanPham.getMaLoaiSanPham());
                            }
                        }
                        if (sanPhamDAO.update(sanPham1) > 0) {
                            list.clear();
                            list = (ArrayList<SanPham>) sanPhamDAO.getAll();
                            setData(list);
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
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
        TextView txtLoaiSanPham, txtTenSanPham, txtAnhSanPham, txtLinkAnhSanPham,
                txtGiaSanPham, txtGiamGia, txtSoLuongTrongKho, txtNgaySanXuat, txtHanSuDung;
        ImageButton ibUpdateSanPham, ibDeleteSanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoaiSanPham = itemView.findViewById(R.id.txtLoaiSanPham);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtAnhSanPham = itemView.findViewById(R.id.txtAnhSanPham);
            txtLinkAnhSanPham = itemView.findViewById(R.id.txtLinkAnhSanPham);
            txtGiaSanPham = itemView.findViewById(R.id.txtGiaSanPham);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGia);
            txtSoLuongTrongKho = itemView.findViewById(R.id.txtSoLuongTrongKho);
            txtNgaySanXuat = itemView.findViewById(R.id.txtNgaySanXuat);
            txtHanSuDung = itemView.findViewById(R.id.txtHanSuDung);
            ibUpdateSanPham = itemView.findViewById(R.id.ibUpdateSanPham);
            ibDeleteSanPham = itemView.findViewById(R.id.ibDeleteSanPham);


        }
    }
}
