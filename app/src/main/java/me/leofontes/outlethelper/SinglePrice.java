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
import android.widget.TextView;

import java.text.DecimalFormat;

public class SinglePrice extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText inputBuy;
    EditText inputFree;
    EditText inputBasePrice;
    TextView outputPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_price);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        inputBuy = (EditText) findViewById(R.id.inputBuy);
        inputFree = (EditText) findViewById(R.id.inputFree);
        inputBasePrice = (EditText) findViewById(R.id.inputBasePrice);
        outputPrice = (TextView) findViewById(R.id.outputSinglePrice);
    }

    public void calcSinglePrice(View view) {
        int numBuy;
        int numFree;
        double basePrice;

        if(inputBuy.getText().toString().equals("")) {
            numBuy = 0;
        } else {
            numBuy = Integer.parseInt(inputBuy.getText().toString());
        }

        if(inputFree.getText().toString().equals("")) {
            numFree = 0;
        } else {
            numFree = Integer.parseInt(inputFree.getText().toString());
        }

        if(inputBasePrice.getText().toString().equals("")) {
            basePrice = 0.0;
        } else {
            basePrice = Double.parseDouble(inputBasePrice.getText().toString());
        }

        double price = basePrice * numBuy / (numBuy + numFree);
        DecimalFormat df = new DecimalFormat("#.00");
        String output;
        if(price == 0) {
            output = "$0.00";
        } else {
            output = "$" + df.format(price);
        }

        outputPrice.setText(output);
        inputFree.setText("");
        inputBuy.setText("");
        inputBasePrice.setText("");
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
        getMenuInflater().inflate(R.menu.single_price, menu);
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
            Intent intent = new Intent(this, Discount.class);
            startActivity(intent);
        } else if (id == R.id.nav_sing) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_total) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
