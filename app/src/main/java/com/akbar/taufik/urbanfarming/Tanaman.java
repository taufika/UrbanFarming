package com.akbar.taufik.urbanfarming;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 21/04/2016.
 */
public class Tanaman {
    public String namaTanaman;
    public String tanggalTanaman;
    public int id;

    public Tanaman(int id, String namaTanaman, String tanggalTanaman){
        this.id = id;
        this.namaTanaman = namaTanaman;
        this.tanggalTanaman = tanggalTanaman;
    }

    public Tanaman(int tanaman){
        switch (tanaman){
            case 1 : namaTanaman = "LETTUCE"; break;
            case 2 : namaTanaman = "BASELLA"; break;
            case 3 : namaTanaman = "CABBAGE"; break;
            default: namaTanaman = "LETTUCE";
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.tanggalTanaman = format.format(new Date()).toString() ;
    }
}
