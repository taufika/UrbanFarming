package com.akbar.taufik.urbanfarming;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTanaman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public int pos;

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
        int indeksTanaman = intent.getIntExtra("indeksTanaman",0);
        pos = indeksTanaman;

        TextView namaTanaman = (TextView) findViewById(R.id.namaTanamanViewSpesifik);
        namaTanaman.setText(ListTanaman.arrayTanaman.get(indeksTanaman).namaTanaman);

        TextView tanggalTanaman = (TextView) findViewById(R.id.tanggalTanamanSpesifik);
        tanggalTanaman.setText(ListTanaman.arrayTanaman.get(indeksTanaman).tanggalTanaman);

        int gambar;
        switch(ListTanaman.arrayTanaman.get(indeksTanaman).namaTanaman){
            case "LETTUCE" : gambar = R.drawable.lettuce; break;
            case "CABBAGE" : gambar = R.drawable.cabbage; break;
            case "BASELLA" : gambar = R.drawable.basella; break;
            default: gambar = R.drawable.cabbage;
        }

        ImageView gambarTanaman = (ImageView) findViewById(R.id.gambarSpesifikTanaman);
        gambarTanaman.setImageResource(gambar);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int position = menuItem.getItemId();
        switch (position){
            case R.id.nav_latestPlan : {
                if(pos != ListTanaman.arrayTanaman.size() - 1){
                    Intent intent = new Intent(this, DetailTanaman.class);
                    intent.putExtra("indeksTanaman", ListTanaman.arrayTanaman.size()-1);
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
            case R.id.nav_instruction : break;
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
}
