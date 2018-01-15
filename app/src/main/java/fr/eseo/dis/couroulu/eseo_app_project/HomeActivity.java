package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private AllProjectsFragment allProjectsFragment;
    private MyPostersFragment myposterFragment;
    private MyJuriesFragment myJuriesFragment ;
    private HomeFragment homeFragment;
    private MonPagerAdapter pagerAdapter;
    private HomeActivity homeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        TextView loginTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.loginName);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        String login = intent.getStringExtra("Login");
        loginTextView.setText(login);

        this.homeFragment = new HomeFragment();
        this.myposterFragment = new MyPostersFragment();
        this.allProjectsFragment = AllProjectsFragment.newInstance(this);
        this.myJuriesFragment = MyJuriesFragment.newInstance(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new MonPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        ImageView imgEseoLink = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imgEseo);
        imgEseoLink.setOnClickListener(new View.OnClickListener() {
            // Fonction pour lorsque click imageView Eseo go to home Page
            public void onClick(View v) {

                        Intent appInfo = new Intent(v.getContext(), HomeActivity.class);
                        startActivity(appInfo);

            }
        });


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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deconnexion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_allprojects) {
                viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_myproject) {
                viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_jurys) {
                viewPager.setCurrentItem(3);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MonPagerAdapter extends FragmentStatePagerAdapter {

        public MonPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return homeFragment;
            }if (position == 1){
                return allProjectsFragment;
            }if (position == 2) {
                return myposterFragment ;
            }else{
                return myJuriesFragment;
                }

        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
