package com.my.app;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CatFragment catFragment=new CatFragment();
    DressFragment dressFragment=new DressFragment();
    BagFragment bagFragment=new BagFragment();
    ShosesFragment shosesFragment=new ShosesFragment();

    LinearLayout catLayout;
    LinearLayout dressLayout;
    LinearLayout bagLayout;
    LinearLayout shosesLayout;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        catLayout=findViewById(R.id.cat);
        dressLayout=findViewById(R.id.dress);
        bagLayout=findViewById(R.id.bag);
        shosesLayout=findViewById(R.id.shoses);
        catLayout.setOnClickListener(this);
        dressLayout.setOnClickListener(this);
        bagLayout.setOnClickListener(this);
        shosesLayout.setOnClickListener(this);
        replaceFragment(catFragment);
        catLayout.setSelected(true);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.fenpu);
        }
        navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.saihong);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        catLayout.setSelected(false);
        dressLayout.setSelected(false);
        bagLayout.setSelected(false);
        shosesLayout.setSelected(false);
        switch(v.getId()){
            case R.id.cat:
                replaceFragment(catFragment);
                catLayout.setSelected(true);
                break;
            case R.id.dress:
                replaceFragment(dressFragment);
                dressLayout.setSelected(true);
                break;
            case R.id.bag:
                replaceFragment(bagFragment);
                bagLayout.setSelected(true);
                break;
            case R.id.shoses:
                replaceFragment(shosesFragment);
                shosesLayout.setSelected(true);
                break;
            default:
                break;
        }
    }

    private void replaceFragment(BaseFragment baseFragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment, baseFragment);
        transaction.commit();
    }

}
