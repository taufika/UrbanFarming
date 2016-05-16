package com.akbar.taufik.urbanfarming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Asus on 28/04/2016.
 */
public class AdapterInstruksi extends ArrayAdapter {
    public AdapterInstruksi(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listinstruksi, null);
        }

        String nama = (String) getItem(position);

        TextView namaTanaman = (TextView) v.findViewById(R.id.namaInstruksi);
        namaTanaman.setText(nama);

        ImageView gambarTanaman = (ImageView) v.findViewById(R.id.gambarInstruksi);
        int gambar;
        switch(nama){
            case "LETTUCE" : gambar = R.drawable.lettuce; break;
            case "CABBAGE" : gambar = R.drawable.cabbage; break;
            case "BASELLA" : gambar = R.drawable.basella; break;
            default: gambar = R.drawable.cabbage;
        }
        gambarTanaman.setImageResource(gambar);

        return v;
    }
}
