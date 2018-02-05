/*
 * SubscriptionArrayAdapter
 *
 * Version 1.0
 *
 * January 4, 2018
 *
 * Copyright (c) 2018.
 */
package com.doupton.douptonsubbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A custom array adapter for Subscription ListView
 */
public class SubscriptionArrayAdapter extends ArrayAdapter<Subscription> {

    // Code is modified from https://stackoverflow.com/questions/15832335

    private LayoutInflater inflater = null;

    /**
     * Construct the array adapter
     * @param context context
     * @param resource resource id
     * @param objects list of subscriptions
     */
    public SubscriptionArrayAdapter(@NonNull Context context, int resource,
                                    @NonNull List<Subscription> objects) {
        super(context, resource, objects);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Get the view for each subscription.
     * @param position position in array
     * @param convertView original view
     * @param parent parent view
     * @return row view for the subscription
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null){
            row = this.inflater.inflate(R.layout.row_subscription_list_view, parent, false);
        }
        Subscription sub = this.getItem(position);

        TextView nameTextView = row.findViewById(R.id.nameTextView);
        nameTextView.setText(sub.getName());

        TextView dateTextView = row.findViewById(R.id.dateTextView);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        dateTextView.setText(format1.format(sub.getStartDate().getTime()));

        TextView chargeTextView = row.findViewById(R.id.chargeTextView);
        chargeTextView.setText(NumberFormat.getCurrencyInstance().format(sub.getMonthlyCharge()));
        return row;
    }
}
