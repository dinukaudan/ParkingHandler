package com.example.tharindu_prasad.parkinghandler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , BlankFragment9.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toast.makeText(this,"Show the Parking slots ",Toast.LENGTH_SHORT).show();
        BlankFragment6 blankFragment6=BlankFragment6.newInstance("some1","some2");
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment6,blankFragment6.getTag()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main4, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
           Toast.makeText(this,"View handler profile",Toast.LENGTH_SHORT).show();
            BlankFragment blankFragment=new BlankFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment,
                    blankFragment.getTag()).commit();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"Show the Parking slots ",Toast.LENGTH_SHORT).show();
            BlankFragment6 blankFragment6=BlankFragment6.newInstance("some1","some2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment6,blankFragment6.getTag()).commit();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this,"Extend if customer not com",Toast.LENGTH_SHORT).show();
            BlankFragment7 blankFragment7=BlankFragment7.newInstance("some1","some2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment7,blankFragment7.getTag()).commit();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,"payments onthespot",Toast.LENGTH_SHORT).show();
            BlankFragment8 blankFragment8=BlankFragment8.newInstance("some1","some2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment8,blankFragment8.getTag()).commit();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
            BlankFragment9 blankFragment9=BlankFragment9.newInstance(5);
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment9,blankFragment9.getTag()).commit();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this,"help",Toast.LENGTH_SHORT).show();
            BlankFragment10 blankFragment10=BlankFragment10.newInstance("some1","some2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,blankFragment10,blankFragment10.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String data) {
        Toast.makeText(this, data,Toast.LENGTH_SHORT).show();
    }
}
