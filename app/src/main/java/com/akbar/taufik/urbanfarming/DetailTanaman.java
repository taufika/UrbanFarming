package com.akbar.taufik.urbanfarming;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class DetailTanaman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public int pos;
    int panGel = 100;
    final int REQUEST_BT = 1;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanaman);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        toolbar.setTitle("My Plant");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerDetail);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.detail_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        final int indeksTanaman = intent.getIntExtra("indeksTanaman",0);
        pos = indeksTanaman;

        TextView namaTanaman = (TextView) findViewById(R.id.namaTanamanViewSpesifik);
        namaTanaman.setText(ListTanaman.arrayTanaman.get(indeksTanaman).namaTanaman);

        TextView tanggalTanaman = (TextView) findViewById(R.id.tanggalTanamanSpesifik);
        tanggalTanaman.setText(ListTanaman.arrayTanaman.get(indeksTanaman).tanggalTanaman);

        SensorRecord sensorRecord = new SensorRecord(this);
        String hasilBaca = sensorRecord.getReading(ListTanaman.arrayTanaman.get(pos).id);
        String[] readings = hasilBaca.split(";");
        String at = (readings[0]);
        String ah = (readings[1]);
        String st = (readings[2]);
        String sh = (readings[3]);

        TextView airT = (TextView) findViewById(R.id.airTemperature);
        airT.setText(at + " \u2103");
        TextView airH = (TextView) findViewById(R.id.airHumidity);
        airH.setText(ah + " %");
        TextView soilT = (TextView) findViewById(R.id.soilTemperature);
        soilT.setText(st + " \u2103");
        TextView soilH = (TextView) findViewById(R.id.soilHumidity);
        soilH.setText(sh + " %");

        FloatingActionButton fabSync = (FloatingActionButton) findViewById(R.id.fabsync);
        fabSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableBT();
            }
        });

        int gambar;
        switch(ListTanaman.arrayTanaman.get(indeksTanaman).namaTanaman){
            case "LETTUCE" : gambar = R.drawable.lettuce; break;
            case "CABBAGE" : gambar = R.drawable.cabbage; break;
            case "BASELLA" : gambar = R.drawable.basella; break;
            default: gambar = R.drawable.cabbage;
        }

        ImageView gambarTanaman = (ImageView) findViewById(R.id.gambarSpesifikTanaman);
        gambarTanaman.setImageResource(gambar);
        gambarTanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoInstruction = new Intent(DetailTanaman.this, InstruksiDetail.class);
                gotoInstruction.putExtra("namaTanaman", ListTanaman.arrayTanaman.get(indeksTanaman).namaTanaman.toLowerCase());
                startActivity(gotoInstruction);
            }
        });

        int panjangGelombang;
        switch(indeksTanaman){
            case 0: panjangGelombang = 661; break; //460
            case 1: panjangGelombang = 530; break;
            case 2: panjangGelombang = 660; break;
            default: panjangGelombang = 640;
        }

        panGel = panjangGelombang;
        TextView lampu = (TextView) findViewById(R.id.panjangGelombang);
        lampu.setText(panjangGelombang + "");

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int position = menuItem.getItemId();
        switch (position){
            case R.id.nav_latestPlan : {
                if(pos != ListTanaman.arrayTanaman.size() - 1) {
                    Intent intent = new Intent(this, DetailTanaman.class);
                    intent.putExtra("indeksTanaman", ListTanaman.arrayTanaman.size() - 1);
                    startActivity(intent);
                } else {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerDetail);
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
            break;
            case R.id.nav_list : {
                Intent intent = new Intent(this, ListTanaman.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            break;
            case R.id.nav_instruction : {
                Intent intent = new Intent(this, InstructionList.class);
                startActivity(intent);
            }
            break;
            case R.id.nav_settings : break;
        }
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        NavigationView navigationView = (NavigationView) findViewById(R.id.detail_nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerDetail);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void enableBT(){
        if(bluetoothAdapter == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth is not supported");
            builder.setMessage("Your device does not support bluetooth");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else {
            if (! bluetoothAdapter.isEnabled()){
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT, REQUEST_BT);
            } else {
                searchSensor();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                searchSensor();
            }
        }
    }

    public void searchSensor(){
        final ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
        ArrayList<String> namaDevices = new ArrayList<String>();

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            for (BluetoothDevice device : pairedDevices){
                devices.add(device);
                namaDevices.add(device.getName() + "(" + device.getAddress() + ")");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Paired Devices");
            builder.setItems(namaDevices.toArray(new String[namaDevices.size()]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    BluetoothDevice device = devices.get(which);
                    ConnectBT connectBT = new ConnectBT(DetailTanaman.this, device, panGel, pos);
                    connectBT.execute();
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Do not see your device?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent gotoBT = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivity(gotoBT);
                }
            });
            builder.create().show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Paired Devices Detected");
            builder.setMessage("Go to bluetooth settings to pair?");
            builder.setPositiveButton("GO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent gotoBT = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivity(gotoBT);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.create().show();
        }

    }
}
