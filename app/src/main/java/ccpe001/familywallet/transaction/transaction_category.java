/*
package ccpe001.familywallet.transaction;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.nio.charset.Charset;

import ccpe001.familywallet.R;

public class transaction_category extends AppCompatActivity {

    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private MyPagerAdapter mAdapter;

    public transaction_category() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_category);
        mToolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolBar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }



    public static class MyFragment extends Fragment{
        public MyFragment(){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        TextView myText = new TextView(getActivity());
        myText.setText("fassafasf");
        myText.setGravity(Gravity.CENTER);
        return myText;
    }
}

class MyPagerAdapter extends FragmentStatePagerAdapter{
    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        ThirdActivity.MyFragment myFragment = new ThirdActivity.MyFragment();
        return myFragment;
    }

    @Override
    public int getCount(){
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return super.getPageTitle(position);
    }
}*/
