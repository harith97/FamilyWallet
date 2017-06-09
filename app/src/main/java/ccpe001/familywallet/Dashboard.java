package ccpe001.familywallet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.TextView;
import ccpe001.familywallet.budget.BudgetHandling;
import ccpe001.familywallet.budget.accUpdate;
import ccpe001.familywallet.budget.addAccount;
import ccpe001.familywallet.budget.budgetList;
import ccpe001.familywallet.summary.sumMain;
import ccpe001.familywallet.transaction.Transaction_main;
import com.facebook.Profile;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.io.IOException;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private Toolbar toolbar = null;
    private NavigationView navigationView = null;
    private DrawerLayout drawerLayout = null;
    private FloatingActionButton circleButton;
    private ShowcaseView showcaseView;
    private TextView navUserDetTxt;

    int count;
    private Target t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().setTitle("Dashboard");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //initialize dashboard fragment 1st
        //CoMMENT CUZ java.lang.NumberFormatException: Invalid int: "null" ERROR
        /*android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Transaction_main transaction = new Transaction_main();
        fragmentTransaction.replace(R.id.fragmentContainer1,transaction);
        fragmentTransaction.commit();*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Intent signUpIntent = getIntent();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_navigation_drawer);
        navUserDetTxt = (TextView) headerView.findViewById(R.id.navUserDet);
        circleButton = (FloatingActionButton) headerView.findViewById(R.id.loggedUsrImg);
        circleButton.setOnClickListener(this);

        navUserDetTxt.setText(signUpIntent.getStringExtra("firstname")+" "+
                signUpIntent.getStringExtra("lastname"));


        try{

            RoundedBitmapDrawable round = null;
            if (signUpIntent.getIntExtra("selectOpt",1)==1) {

                try {
                    round = RoundedBitmapDrawableFactory.create(getResources(),
                            MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                    Uri.parse(signUpIntent.getStringExtra("profilepic"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(signUpIntent.getIntExtra("selectOpt",-1)==0) {

                round = RoundedBitmapDrawableFactory.create(getResources(),
                        (Bitmap) signUpIntent.getParcelableExtra("profilepic"));

            }


            round.setCircular(true);
            circleButton.setImageDrawable(round);

        }catch (Exception e){

        }

        /*Point ts = ;
        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new Target(headerView.findViewById(R.id.loggedUsrImg)).getPoint())
                .setContentTitle("Help Menu")
                .setContentText("descdf")
                .hideOnTouchOutside()
                .build();
        showcaseView.setButtonText("Next");

        //t1 =headerView.findViewById(R.id.loggedUsrImg)*/
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
             android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
             android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Transaction_main dashboard = new Transaction_main();
             fragmentTransaction.replace(R.id.fragmentContainer1,dashboard);
             fragmentTransaction.commit();
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
        }else if (id == R.id.settingFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Settings setting = new Settings();
            fragmentTransaction.replace(R.id.fragmentContainer1,setting);
            fragmentTransaction.commit();
        }else if (id == R.id.backupFrag) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ExportData backup = new ExportData();
            fragmentTransaction.replace(R.id.fragmentContainer1,backup);
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