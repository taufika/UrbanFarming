package com.akbar.taufik.urbanfarming;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class InstructionList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarInstruction);
        toolbar.setTitle("Instructions");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerInstruction);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.instruction_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView listInstruksi = (ListView) findViewById(R.id.listInstruksi);
        AdapterInstruksi adapterInstruksi = new AdapterInstruksi(this, R.layout.listinstruksi, getResources().getStringArray(R.array.tanaman));
        listInstruksi.setAdapter(adapterInstruksi);

        listInstruksi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InstructionList.this, InstruksiDetail.class);
                intent.putExtra("namaTanaman",getResources().getStringArray(R.array.tanaman)[position]);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerInstruction);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int position = item.getItemId();
        switch (position){
            case R.id.nav_latestPlan : {
                Intent intent = new Intent(this, DetailTanaman.class);
                intent.putExtra("indeksTanaman", ListTanaman.arrayTanaman.size() - 1);
                startActivity(intent);
            }
            break;
            case R.id.nav_list : {
                Intent intent = new Intent(this, ListTanaman.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            break;
            case R.id.nav_instruction :{
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerInstruction);
                drawer.closeDrawer(GravityCompat.START);
            }
            break;
            case R.id.nav_settings : break;
        }

        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        NavigationView navigationView = (NavigationView) findViewById(R.id.instruction_nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}
