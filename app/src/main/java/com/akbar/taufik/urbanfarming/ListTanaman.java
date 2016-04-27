package com.akbar.taufik.urbanfarming;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListTanaman extends AppCompatActivity {

    public static List<Tanaman> arrayTanaman = new ArrayList<Tanaman>();

    AdapterTanaman adapterTanaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tanaman);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListTanaman.this);
                builder.setTitle("Add New Plant");
                builder.setItems(R.array.tanaman, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler dbH = new DatabaseHandler(ListTanaman.this);
                        dbH.addTanaman(new Tanaman(which+1));
                        ListTanaman.this.updateData();
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        DatabaseHandler dbH = new DatabaseHandler(this);
        Tanaman[] tanamans = dbH.readAllTanaman();
        if(tanamans.length == 0){
            TextView noPlant = (TextView) findViewById(R.id.noPlantText);
            noPlant.setVisibility(View.VISIBLE);
        }

        ListView listTanaman = (ListView) findViewById(R.id.listTanaman);
        adapterTanaman = new AdapterTanaman(this,R.layout.nama_tanaman, tanamans);
        listTanaman.setAdapter(adapterTanaman);

        listTanaman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTanaman.this, DetailTanaman.class);
                intent.putExtra("indeksTanaman",position);
                startActivity(intent);
            }
        });

        listTanaman.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListTanaman.this);
                builder.setTitle(arrayTanaman.get(position).namaTanaman + " " + arrayTanaman.get(position).tanggalTanaman);
                builder.setItems(R.array.contextMenu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0: {
                                Intent intent = new Intent(ListTanaman.this, DetailTanaman.class);
                                intent.putExtra("indeksTanaman",pos);
                                startActivity(intent);
                            }
                            break;
                            case 1: break;
                            case 2: {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(ListTanaman.this);
                                builder2.setTitle("Remove plant?");
                                builder2.setMessage("You will remove plant " + arrayTanaman.get(pos).namaTanaman +
                                        " " + arrayTanaman.get(pos).tanggalTanaman + ". This action cannot be undone");
                                builder2.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseHandler dbH = new DatabaseHandler(ListTanaman.this);
                                        dbH.deleteTanaman(ListTanaman.arrayTanaman.get(pos));
                                        updateData();
                                    }
                                });
                                builder2.setNegativeButton("Cancel",null);
                                builder2.create().show();
                            }
                            break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_tanaman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateData(){
        ListView listTanaman = (ListView) findViewById(R.id.listTanaman);
        listTanaman.invalidateViews();

        DatabaseHandler dbH = new DatabaseHandler(this);

        Tanaman[] tanamans = dbH.readAllTanaman();
        if(tanamans.length == 0){
            TextView noPlant = (TextView) findViewById(R.id.noPlantText);
            noPlant.setVisibility(View.VISIBLE);
        } else {
            TextView noPlant = (TextView) findViewById(R.id.noPlantText);
            noPlant.setVisibility(View.INVISIBLE);
        }
        adapterTanaman = new AdapterTanaman(this,R.layout.nama_tanaman, tanamans);
        listTanaman.setAdapter(adapterTanaman);
    }
}
