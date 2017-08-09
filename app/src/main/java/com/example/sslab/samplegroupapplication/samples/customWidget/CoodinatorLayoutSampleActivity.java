package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CoodinatorLayoutSampleActivity extends AppCompatActivity {

    CoordinatorLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coodinateor_layout_sample);

        mainLayout = (CoordinatorLayout)findViewById( R.id.mainLayout );
        ViewGroup.LayoutParams params = mainLayout.getLayoutParams();

        if(params instanceof CoordinatorLayout.LayoutParams){
            Log.d("params","instanceof CoordinatorLayout");
            Log.d("params","instanceof FrameLayout");
        }else if( params instanceof FrameLayout.LayoutParams ){
            Log.d("params","instanceof FrameLayout");
        }else if( params instanceof ViewGroup.LayoutParams){
            Log.d("params","instanceof Viewgroup.");
        }

        ViewPager viewPager = ( ViewPager ) findViewById( R.id.viewpager);
        setupViewPager(viewPager);
//        setupViewPager

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(ListViewPageFragment.createInstance(), "List View");
        pagerAdapter.addFragment(GridViewPageFragment.createInstance(), "Grid View");
        viewPager.setAdapter(pagerAdapter);
    }

    static class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
