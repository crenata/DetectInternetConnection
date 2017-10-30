package com.hafiizh.detectinternetconnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int NOT_CONNECTED = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (getConnectivityStatus() == ConnectivityManager.TYPE_WIFI)
                    Toast.makeText(context, "Wifi type connection", Toast.LENGTH_SHORT).show();
                else if (getConnectivityStatus() == ConnectivityManager.TYPE_MOBILE)
                    Toast.makeText(context, "Mobile type connection", Toast.LENGTH_SHORT).show();
                else if (getConnectivityStatus() == NOT_CONNECTED)
                    Toast.makeText(context, "Not connected to internet", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private int getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null)
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
                return ConnectivityManager.TYPE_WIFI;
            else if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                return ConnectivityManager.TYPE_MOBILE;
        return NOT_CONNECTED;
    }
}
