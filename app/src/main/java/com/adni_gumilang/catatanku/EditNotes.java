package com.adni_gumilang.catatanku;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNotes extends AppCompatActivity {

    DBHandler handler;
    EditText namaNote, tanggalNote, keterangan;
    Button simpan;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_notes);

        handler = new DBHandler(this);

        id = getIntent().getLongExtra(DBHandler.ROW_ID, 0);

        namaNote = findViewById(R.id.editJudul);
        tanggalNote = findViewById(R.id.editTanggal);
        keterangan = findViewById(R.id.editKeterangan);

        getData();

        simpan = findViewById(R.id.btnSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaNotes = namaNote.getText().toString().trim();
                String tanggalNotes = tanggalNote.getText().toString().trim();
                String keteranganNotes = keterangan.getText().toString().trim();

                if (namaNotes.equals("") || tanggalNotes.equals("") || keteranganNotes.equals("")) {
                    Toast.makeText(EditNotes.this, "Pastikan semua form terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_nama_notes, namaNotes);
                    values.put(DBHandler.row_tanggal_notes, tanggalNotes);
                    values.put(DBHandler.row_keterangan, keteranganNotes);
                    handler.editData(values, id);
                    finish();
                }
            }
        });
    }

    private void getData() {
        Cursor cur = handler.satuData(id);
        if (cur.moveToFirst()) {
            String NamaNotes = cur.getString(cur.getColumnIndex(DBHandler.row_nama_notes));
            String TanggalNotes = cur.getString(cur.getColumnIndex(DBHandler.row_tanggal_notes));
            String KeteranganNotes = cur.getString(cur.getColumnIndex(DBHandler.row_keterangan));
            namaNote.setText(NamaNotes);
            tanggalNote.setText(TanggalNotes);
            keterangan.setText(KeteranganNotes);

        }
    }


}
