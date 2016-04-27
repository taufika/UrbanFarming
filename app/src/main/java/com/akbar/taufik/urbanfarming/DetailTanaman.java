package com.akbar.taufik.urbanfarming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTanaman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanaman);

        Intent intent = getIntent();
        int indeksTanaman = intent.getIntExtra("indeksTanaman",0);

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
}
