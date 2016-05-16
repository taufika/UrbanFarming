package com.akbar.taufik.urbanfarming;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.ParcelUuid;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 28/04/2016.
 */
public class ConnectBT extends AsyncTask {

    ProgressDialog progressDialog;
    Context context;
    BluetoothDevice device;
    InputStream inputStream;
    OutputStream outputStream;
    String message = "";
    int panGel;
    int pos;

    public ConnectBT(Context context, BluetoothDevice device, int panGel, int pos){
        this.context = context;
        this.device = device;
        this.panGel = panGel;
        this.pos = pos;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Connecting to sensor");
        progressDialog.setMessage("Please wait while connecting to bluetooth sensor");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        ParcelUuid[] uuids = device.getUuids();
        try {
            BluetoothSocket btSocket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
            btSocket.connect();
            inputStream = btSocket.getInputStream();
            outputStream = btSocket.getOutputStream();
            outputStream.write(("m" + panGel + "i").getBytes());

            String msgPart = "";

            while(!msgPart.contains("~")) {
                int bufferSize = 8;
                byte[] buffer = new byte[bufferSize];
                int bytes = inputStream.read(buffer);
                msgPart = new String(buffer, 0, bytes);
                message = message + msgPart;
            }

            btSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        Toast.makeText(context, "SENSOR READING: " + message, Toast.LENGTH_LONG).show();

        String[] sensorRead = message.split("#");

        String airTemp = sensorRead[1];
        String soilTemp = sensorRead[4];
        String airHumidity = sensorRead[2];
        String soilHumidity = sensorRead[3];
        int nyalaLampu = Integer.parseInt(sensorRead[5].split("~")[0]);

        TextView airT = (TextView) ((Activity)context).findViewById(R.id.airTemperature);
        airT.setText(airTemp + " \u2103");
        TextView airH = (TextView) ((Activity)context).findViewById(R.id.airHumidity);
        airH.setText(airHumidity + " %");
        TextView soilT = (TextView) ((Activity)context).findViewById(R.id.soilTemperature);
        soilT.setText(soilTemp + " \u2103");
        TextView soilH = (TextView) ((Activity)context).findViewById(R.id.soilHumidity);
        soilH.setText(soilHumidity + " %");
        TextView onOff = (TextView) ((Activity)context).findViewById(R.id.onOffLampu);
        if(nyalaLampu == 1) {
            onOff.setText("ON");
            onOff.setTextColor(Color.parseColor("#00FF00"));
        } else {
            onOff.setText("OFF");
            onOff.setTextColor(Color.parseColor("#FF0000"));
        }
        float at, ah, st, sh;
        if(airTemp.equalsIgnoreCase("nan"))
            at = -1;
        else
            at = Float.parseFloat(airTemp);
        if(airHumidity.equalsIgnoreCase("nan"))
            ah = -1;
        else
            ah = Float.parseFloat(airHumidity);
        if(soilTemp.equalsIgnoreCase("nan"))
            st = -1;
        else
            st = Float.parseFloat(soilTemp);
        if(soilHumidity.equalsIgnoreCase("nan"))
            sh = -1;
        else
            sh = Float.parseFloat(soilHumidity);
        SensorRecord sensorRecord = new SensorRecord(context);
        sensorRecord.addRecord(ListTanaman.arrayTanaman.get(pos).id,at,ah,st,sh);
    }
}
