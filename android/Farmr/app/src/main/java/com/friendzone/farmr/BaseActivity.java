package com.friendzone.farmr;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.friendzone.farmr.utils.Constants;

/**
 * Created by marylourdessabio on 08/08/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;
    Activity activity;
    SharedPreferences shared;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        activity = this;

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (!activity.getClass().getSimpleName().equals("LoginActivity")
                    && !activity.getClass().getSimpleName().equals("MainActivity")) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }

        shared = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        edit = shared.edit();

    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    protected void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayoutResource();


}