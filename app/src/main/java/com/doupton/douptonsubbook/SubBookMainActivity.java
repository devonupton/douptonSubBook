package com.doupton.douptonsubbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class SubBookMainActivity extends AppCompatActivity {

    private ArrayList<Subscription> subList;
    private SubscriptionArrayAdapter adapter;
    private ListView subListView;
    private SubscriptionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book_main);

        this.subListView = findViewById(R.id.subscriptionListView);
        this.manager = new SubscriptionManager(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Load subscriptions and assign the adapter
        this.subList = manager.loadFromFile();
        this.adapter = new SubscriptionArrayAdapter(this,
                R.layout.row_subscription_list_view, this.subList);
        subListView.setAdapter(this.adapter);

        // TODO: Testing
        subList.add(new Subscription("Netflix", new Date(), new BigDecimal(29.7)));
        subList.add(new Subscription("Cheese", new Date(), new BigDecimal(9.4)));
        subList.add(new Subscription("Dogs", new Date(), new BigDecimal(780.4)));
        subList.add(new Subscription("1", new Date(), new BigDecimal(29.489765)));
        subList.add(new Subscription("This is long name", new Date(), new BigDecimal(1525)));
        subList.add(new Subscription("3", new Date(), new BigDecimal(0)));

        // TODO: Need to always update the totalTextView!!!!
        BigDecimal count = BigDecimal.ZERO;
        for (Subscription sub : this.subList){
            count = count.add(sub.getMonthlyCharge());
        }
        TextView totalTextView = this.findViewById(R.id.subTotalTextView);
        totalTextView.setText(NumberFormat.getCurrencyInstance().format(count));
    }
    // TODO: Fix total bar
    // TODO: Fix row currency
// TODO Implement this
    private void itemClicked(){

    }

    // TODO: Test is disable rotations
    // TODO: Need to implement onitemclicklistener
}
