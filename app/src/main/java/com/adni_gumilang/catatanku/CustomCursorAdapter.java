package com.adni_gumilang.catatanku;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater ly;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        ly = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = ly.inflate(R.layout.row_notes,parent,false);
        MyHolder holder = new MyHolder();
        holder.listID = v.findViewById(R.id.listID);
        holder.listNama = v.findViewById(R.id.listNamaBarang);
        holder.listTanggal = v.findViewById(R.id.listJenisBarang);
        holder.listKeterangan = v.findViewById(R.id.listKeterangan);
        v.setTag(holder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        MyHolder holder = (MyHolder)view.getTag();

        holder.listID.setText(cursor.getString(cursor.getColumnIndex(DBHandler.ROW_ID)));
        holder.listNama.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_nama_notes)));
        holder.listTanggal.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_tanggal_notes)));
        holder.listKeterangan.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_keterangan)));

    }

    class MyHolder {
        TextView listID;
        TextView listNama;
        TextView listTanggal;
        TextView listKeterangan;
    }
}
