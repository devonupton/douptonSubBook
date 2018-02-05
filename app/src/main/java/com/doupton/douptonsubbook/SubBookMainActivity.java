/*
 * SubBookMainActivity
 *
 * Version 1.0
 *
 * January 4, 2018
 *
 * Copyright (c) 2018.
 */
package com.doupton.douptonsubbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * Main activity for SubBook
 */
public class SubBookMainActivity extends AppCompatActivity {

    /**
     * Used to pass subscription index through an intent
     */
    public static final String EXTRA_SUB_INDEX = "com.doupton.douptonsubbook.EXTRA_SUB_INDEX";

    private SubscriptionArrayAdapter adapter;
    private ListView subListView;
    private SubscriptionManager manager;

    /**
     * Create the activity
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book_main);

        this.subListView = findViewById(R.id.subscriptionListView);
        this.manager = new SubscriptionManager(this);

        // Go to SubDetails when an item is clicked
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(SubBookMainActivity.this,
                        SubDetailsActivity.class);

                // Pass the list position to the activity
                intent.putExtra(SubBookMainActivity.EXTRA_SUB_INDEX, position);
                startActivity(intent);
            }
        });

        // Load subscriptions and assign the adapter
        if (MyApplication.subList == null){
            MyApplication.subList = manager.loadFromFile();
        }
        this.adapter = new SubscriptionArrayAdapter(this,
                R.layout.row_subscription_list_view, MyApplication.subList);
        subListView.setAdapter(this.adapter);
    }

    /**
     * On Resuming the activity
     * This refreshes the screen
     */
    @Override
    public void onResume(){
        super.onResume();
        refresh();
    }

    /**
     * Refresh the screen.
     */
    private void refresh(){
        // Update list
        this.adapter.notifyDataSetChanged();

        // Update total
        BigDecimal count = BigDecimal.ZERO;
        for (Subscription sub : MyApplication.subList){
            count = count.add(sub.getMonthlyCharge());
        }
        TextView totalTextView = this.findViewById(R.id.subTotalTextView);
        totalTextView.setText(NumberFormat.getCurrencyInstance().format(count));
    }


    /**
     * Create a custom menu with add button
     * @param menu menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Source: https://stackoverflow.com/questions/31387012/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Menu item selected
     * @param item menu item
     * @return super.OnOptionsItemSelected(item)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newButton){
            // Go create a new subscription
            Intent intent = new Intent(SubBookMainActivity.this,
                    SubDetailsActivity.class);

            // Pass -1 to indicate that this is a new subscription
            intent.putExtra(SubBookMainActivity.EXTRA_SUB_INDEX, -1);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
