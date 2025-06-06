package com.homework.donation.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.homework.donation.main.DonationApp;
import com.homework.donation.R;

public class Base extends AppCompatActivity {
    public DonationApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
        
        // Add network check
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No internet connection available", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menu_report);
        MenuItem donate = menu.findItem(R.id.menu_donation);
        MenuItem reset = menu.findItem(R.id.menu_reset);
        Log.v("data", String.valueOf(app.donations));

        if(app.donations.isEmpty()) {
            report.setEnabled(false);
            reset.setEnabled(false);
        } else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }

        if(this instanceof Donate){
            donate.setVisible(false);
            if(!app.donations.isEmpty()) {
                report.setVisible(true);
                reset.setEnabled(true);
            }
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.v("data3", String.valueOf(app.donations));
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_report) {
            report(item);
        } else if (id == R.id.menu_reset) {
            reset(item);
        } else if (id == R.id.menu_donation) {
            donate(item);
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset(MenuItem item)
    {
        Toast.makeText(this, "Reset selected", Toast.LENGTH_SHORT).show();
    }
    public void report(MenuItem item)
    {
        Toast.makeText(this, "Report selected", Toast.LENGTH_SHORT).show();
        startActivity (new Intent(this, Report.class));
    }
    public void donate(MenuItem item)
    {
        Toast.makeText(this, "Donate selected", Toast.LENGTH_SHORT).show();
        startActivity (new Intent(this, Donate.class));
    }
}
