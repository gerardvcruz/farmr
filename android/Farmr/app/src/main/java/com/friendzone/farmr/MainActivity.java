package com.friendzone.farmr;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.friendzone.farmr.buyers.MyOrdersFragment;
import com.friendzone.farmr.buyers.SuppliersFragment;
import com.friendzone.farmr.suppliers.OrdersFragment;
import com.friendzone.farmr.suppliers.StocksFragment;
import com.friendzone.farmr.utils.Constants;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends BaseActivity {

    FragmentPagerItemAdapter adapter;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check user type
        // Supplier = 1
        // Buyer = 2

//        if(shared.getBoolean(Constants.IS_LOGGED_IN, false) == true) {
//
//        }

        Log.e(TAG, "" + shared.getString(Constants.ACCESS_TOKEN, ""));

        int userType = getIntent().getIntExtra(Constants.USER_TYPE, 0);

        if(userType == Constants.SUPPLIER) {
            adapter = new FragmentPagerItemAdapter(
                    getSupportFragmentManager(), FragmentPagerItems.with(this)
                    .add("Orders", OrdersFragment.class)
                    .add("Stocks", StocksFragment.class)
                    .create());
        } else {
            adapter = new FragmentPagerItemAdapter(
                    getSupportFragmentManager(), FragmentPagerItems.with(this)
                    .add("My Orders", MyOrdersFragment.class)
                    .add("Suppliers", SuppliersFragment.class)
                    .create());
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}