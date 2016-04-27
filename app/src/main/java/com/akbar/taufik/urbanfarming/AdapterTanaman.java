package com.akbar.taufik.urbanfarming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Asus on 21/04/2016.
 */
public class AdapterTanaman extends ArrayAdapter {
    public AdapterTanaman(Context context, int resource, Tanaman[] tanaman){
        super(context, resource, tanaman);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.nama_tanaman, null);
        }

        Tanaman tanaman1 = (Tanaman)getItem(position);

        TextView nomorTanaman = (TextView) v.findViewById(R.id.nomorTanaman);
        String posisi = String.valueOf(position+1);
        nomorTanaman.setText(posisi);

        TextView detailTanaman = (TextView) v.findViewById(R.id.namaTanaman);
        String detail = tanaman1.namaTanaman;
        detailTanaman.setText(detail);

        TextView tanggalTanaman = (TextView) v.findViewById(R.id.tanggalTanaman);
        tanggalTanaman.setText(tanaman1.tanggalTanaman);

        ImageView gambarTanaman = (ImageView) v.findViewById(R.id.gambarTanaman);
        int gambar;
        switch(tanaman1.namaTanaman){
            case "LETTUCE" : gambar = R.drawable.lettuce; break;
            case "CABBAGE" : gambar = R.drawable.cabbage; break;
            case "BASELLA" : gambar = R.drawable.basella; break;
            default: gambar = R.drawable.cabbage;
        }
        gambarTanaman.setImageResource(gambar);

        return v;
    }

}
