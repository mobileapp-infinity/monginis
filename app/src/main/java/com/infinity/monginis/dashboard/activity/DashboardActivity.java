package com.infinity.monginis.dashboard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewMediumFont;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.adapter.DashboardViewPagerAdapter;
import com.infinity.monginis.dashboard.fragments.CartFragment;
import com.infinity.monginis.dashboard.fragments.ExploreFragment;
import com.infinity.monginis.dashboard.fragments.ProfileFragment;
import com.infinity.monginis.dashboard.fragments.SearchFragment;
import com.infinity.monginis.utils.IntentConstants;

public class DashboardActivity extends AppCompatActivity {

    //    TextViewMediumFont tvStreetName;
//    TextViewRegularFont tvUserAddress;
    BottomNavigationView bottomNavigationView;
    public static ViewPager vpDashboard;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
//        tvStreetName.setText(getIntent().getStringExtra(IntentConstants.USER_CURRENT_STREET_NAME));
//        tvUserAddress.setText(getIntent().getStringExtra(IntentConstants.USER_CURRENT_ADDRESS));


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.explore:
                        vpDashboard.setCurrentItem(0);
                        InputMethodManager exploreInputManager = (InputMethodManager) DashboardActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        exploreInputManager.hideSoftInputFromWindow(bottomNavigationView.getWindowToken(), 0);
                        break;
                    case R.id.search:
                        vpDashboard.setCurrentItem(1);
                        InputMethodManager searchInputManger = (InputMethodManager) DashboardActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        searchInputManger.hideSoftInputFromWindow(bottomNavigationView.getWindowToken(), 0);
                        break;
                    case R.id.cart:
                        vpDashboard.setCurrentItem(2);
                        InputMethodManager cartInputManager = (InputMethodManager) DashboardActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        cartInputManager.hideSoftInputFromWindow(bottomNavigationView.getWindowToken(), 0);

                        break;
                    case R.id.profile:
                        vpDashboard.setCurrentItem(3);
                        InputMethodManager profileInputManager = (InputMethodManager) DashboardActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        profileInputManager.hideSoftInputFromWindow(bottomNavigationView.getWindowToken(), 0);
                        break;
                }
                return false;
            }
        });
    }

    private void setUpViewPager() {
        DashboardViewPagerAdapter dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        dashboardViewPagerAdapter.addFragment(ExploreFragment.getExploreFragment(), "");
        dashboardViewPagerAdapter.addFragment(SearchFragment.getSearchFragment(), "");
        dashboardViewPagerAdapter.addFragment(CartFragment.getCartFragment(), "");
        dashboardViewPagerAdapter.addFragment(ProfileFragment.getProfileFragment(), "");
        vpDashboard.setAdapter(dashboardViewPagerAdapter);
    }

    private void initView() {
        vpDashboard = findViewById(R.id.vpDashboard);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        tvStreetName = findViewById(R.id.tvStreetName);
//        tvUserAddress = findViewById(R.id.tvUserAddress);
        setUpViewPager();
        vpDashboard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}