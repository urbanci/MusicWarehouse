package com.example.urbankos.musicwarehouse;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.urbankos.musicwarehouse.dao.ItemDAO;
import com.example.urbankos.musicwarehouse.dao.WarehouseItemDAO;
import com.example.urbankos.musicwarehouse.objects.Item;
import com.example.urbankos.musicwarehouse.objects.WarehouseItem;
import com.example.urbankos.musicwarehouse.tabs.TabItem;
import com.example.urbankos.musicwarehouse.tabs.TabWarehouse;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    //  INTENT
    private Intent insertItemIntent;

    //  VARIABLES
    private String id_item;

    //  DAO
    private ItemDAO daoItem;
    private WarehouseItemDAO daoWarehouseItem;

    //  ITEM
    private Item item;
    private ArrayList<WarehouseItem> warehouseItem = new ArrayList<>();

    //  TABS
    private TabItem tab1;
    private TabWarehouse tab2;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //  Dao
        if(daoItem == null && daoWarehouseItem == null){
            daoItem = new ItemDAO(this);
            daoWarehouseItem = new WarehouseItemDAO(this);
        }

        //  Get extras
        getExtras();

        //  Set elements
        setElements();

        //  Get item
        item = getItem();
        warehouseItem = getWarehouseItem();

//      toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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

    private void getExtras(){
        insertItemIntent = getIntent();

        if(insertItemIntent != null && insertItemIntent.getStringExtra("id") != null) {
            id_item = insertItemIntent.getStringExtra("id");
        }
    }

    private void setElements(){

    }

    private Item getItem(){
        return daoItem.getItem(id_item);
    }

    private ArrayList<WarehouseItem> getWarehouseItem(){
        return daoWarehouseItem.getQuantityesInWarehouses(id_item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(tab1 == null){
                        tab1 = new TabItem(id_item);
                    }
                    return tab1;

                case 1:
                    if(tab2 == null){
                        tab2 = new TabWarehouse(warehouseItem);
                    }
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
