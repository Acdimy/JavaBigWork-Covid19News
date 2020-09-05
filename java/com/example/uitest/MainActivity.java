package com.example.uitest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import news.newsFragment;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,MainContract_interface.View{

    private Context context;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainContract_interface.Presenter mainPresenter;
    private Fragment newsfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.subscribe();
    }

    public void changeInfo(int id,String title)
    {
        this.mToolbar.setTitle(title);
        this.mNavigationView.setCheckedItem(id);
    }


    @Override
    public void switchToNews() {
        Log.i("MainActivity", "新闻");
        this.changeInfo(R.id.nav_news,"新闻");
        if (newsfragment == null) newsfragment = newsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,newsfragment).commit();
    }

    @Override
    public void switchToCovidData() {
        Log.i("MainActivity", "新冠数据");
        this.changeInfo(R.id.nav_coviddata,"新冠数据");
    }

    @Override
    public void switchToSettings() {
        Log.i("MainActivity", "设置");
        this.changeInfo(R.id.nav_settings,"设置");
    }

    @Override
    public void switchToAbout() {
        Log.i("MainActivity", "关于");
        this.changeInfo(R.id.nav_about,"关于");
    }

    @Override
    public void setPresenter(MainContract_interface.Presenter presenter) {
        this.mainPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void startAc(Intent intent, Bundle bundle) {
        startActivity(intent,bundle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mainPresenter.switchNavigation(item.getItemId());
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
