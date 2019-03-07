package com.adni_gumilang.catatanku;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Notes extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView ls;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_notes);

        FloatingActionButton btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, MainActivity.class);
                startActivity(intent);
            }
        });

        handler = new DBHandler(this);
        ls = findViewById(R.id.listNotes);
        ls.setOnItemClickListener(this);
        setUpListView();
    }

    private void setUpListView() {
        Cursor cursor = handler.semuaData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        ls.setAdapter(customCursorAdapter);
        ls.setDividerHeight(0);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
        builder.setMessage("Apakah anda ingin keluar ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long I) {
        TextView getid = findViewById(R.id.listID);
        final long id = Long.parseLong(getid.getText().toString());
        Cursor cur = handler.satuData(id);
        cur.moveToFirst();

        String ket = cur.getString(cur.getColumnIndex(DBHandler.row_keterangan));
        final String NamaNotes = cur.getString(cur.getColumnIndex(DBHandler.row_nama_notes));
        final AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
        builder.setTitle("Daftar Notes");
        builder.setMessage(ket);
        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogHapusItem(id, NamaNotes);
            }
        });

        builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent TanggalNotes = new Intent(Notes.this, EditNotes.class);
                TanggalNotes.putExtra(DBHandler.ROW_ID, id);
                startActivity(TanggalNotes);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void dialogHapusItem(final long id, String nama) {
        AlertDialog.Builder builderl = new AlertDialog.Builder(Notes.this);
        builderl.setTitle("Hapus data "+ nama);
        builderl.setMessage("Apakah anda yakin ingin menghapus data " + nama + " ?");
        builderl.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 handler.hapusData(id);
                setUpListView();
            }
        });
        builderl.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //CANCEL
            }
        });
        AlertDialog dialog = builderl.create();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
        builder.setTitle("Hapus Catatan ?");
        builder.setMessage("Apakah anda yakin ingin menghapus semua catatan ?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.hapusSemuaData();
                setUpListView();
            }
        });
        builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //CANCEL
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpListView();
    }
}
