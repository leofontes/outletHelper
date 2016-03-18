package me.leofontes.outlethelper;

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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Discount extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText inputPrice;
    TextView seekBarValue;
    TextView finalPrice;
    SeekBar discountSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        inputPrice = (EditText) findViewById(R.id.originalPriceInput);
        seekBarValue = (TextView) findViewById(R.id.seekBarValue);
        finalPrice = (TextView) findViewById(R.id.finalPriceLabel);
        discountSeekBar = (SeekBar) findViewById(R.id.discountSeekBar);

        discountSeekBar.setMax(100);
        discountSeekBar.setProgress(10);

        discountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calcDiscount(progress);
                changeDiscountLabel(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    public void calcDiscount(int disc) {
        double input;
        if(inputPrice.getText().toString().equals("")) {
            input = 0.0;
        } else {
            input = Double.parseDouble(inputPrice.getText().toString());
        }
        double discount = disc / 100.0;

        double price = input * (1 - discount);
        DecimalFormat df = new DecimalFormat("#.00");
        String output;
        if(price == 0) {
            output = "$0.00";
        } else {
            output = "$" + df.format(price);
        }


        finalPrice.setText(output);
    }

    public void changeDiscountLabel(int disc) {
        String output = disc + "%";
        seekBarValue.setText(output);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.discount, menu);
        return true;
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
        int id = item.getItemId();

        if (id == R.id.nav_disc) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_sing) {
            Intent intent = new Intent(this, SinglePrice.class);
            startActivity(intent);
        } else if (id == R.id.nav_total) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
