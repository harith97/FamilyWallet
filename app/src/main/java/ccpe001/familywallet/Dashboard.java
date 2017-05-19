package ccpe001.familywallet;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import at.markushi.ui.CircleButton;
import ccpe001.familywallet.budget.accUpdate;
import ccpe001.familywallet.budget.addAccount;
import ccpe001.familywallet.budget.budgetList;
import ccpe001.familywallet.summary.sumMain;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    Toolbar toolbar = null;
    NavigationView navigationView = null;
    DrawerLayout drawerLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //initialize dashboard fragment 1st
        /*android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        transactionMain transaction = new transactionMain();
        fragmentTransaction.replace(R.id.fragmentContainer1,transaction);
        fragmentTransaction.commit();*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_navigation_drawer);
        CircleButton img = (CircleButton)headerView.findViewById(R.id.loggedUsrImg);
        img.setOnClickListener(this);


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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

        if (id == R.id.transactionFrag) {
             /*android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
             android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             Dashboard dashboard = new Dashboard();
             fragmentTransaction.replace(R.id.fragmentContainer1,dashboard);
             fragmentTransaction.commit();*/
        } else if (id == R.id.reportsFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            sumMain summary = new sumMain();
            fragmentTransaction.replace(R.id.fragmentContainer1,summary);
            fragmentTransaction.commit();
        } else if (id == R.id.transferFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            accUpdate transmoney = new accUpdate();
            fragmentTransaction.replace(R.id.fragmentContainer1,transmoney);
            fragmentTransaction.commit();
        }else if (id == R.id.budgetFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            budgetList budget = new budgetList();
            fragmentTransaction.replace(R.id.fragmentContainer1,budget);
            fragmentTransaction.commit();
        }else if (id == R.id.walletFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            addAccount addwallet = new addAccount();
            fragmentTransaction.replace(R.id.fragmentContainer1,addwallet);
            fragmentTransaction.commit();
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.loggedUsrImg){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AddMember addmember = new AddMember();
            fragmentTransaction.replace(R.id.fragmentContainer1,addmember);
            fragmentTransaction.commit();
            //Close nav drawer here
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}