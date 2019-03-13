package com.adni_gumilang.catatanku;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DBHandler handler;
    EditText namaNote, tanggalNote, keterangan;
    Button tambahData;
    CheckBox keepData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DBHandler(this);

        namaNote = findViewById(R.id.editJudul);
        tanggalNote = findViewById(R.id.editTanggal);
        keterangan = findViewById(R.id.editKeterangan);

        tambahData = findViewById(R.id.btnTambah);

        keepData = findViewById(R.id.keepData);

        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaNotes = namaNote.getText().toString().trim();
                String tanggalNotes = tanggalNote.getText().toString().trim();
                String keteranganNotes = keterangan.getText().toString().trim();

                if (namaNotes.equals("") || tanggalNotes.equals("") || keteranganNotes.equals("")) {
                    Toast.makeText(MainActivity.this, "Pastikan semua form terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_nama_notes, namaNotes);
                    values.put(DBHandler.row_tanggal_notes, tanggalNotes);
                    values.put(DBHandler.row_keterangan, keteranganNotes);
                    handler.tambahData(values);
                    if (!keepData.isChecked()) {
                        namaNote.setText("");
                        tanggalNote.setText("");
                        keterangan.setText("");
                        namaNote.setText("");
                        namaNote.requestFocus();
                    }
                    Toast.makeText(MainActivity.this, "Berhasil input data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
