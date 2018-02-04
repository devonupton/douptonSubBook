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
// TODO: Source https://stackoverflow.com/questions/15832335
/**
 * Created by devon on 03/02/18.
 */

public class SubscriptionArrayAdapter extends ArrayAdapter<Subscription> {

    private LayoutInflater inflater = null;

    public SubscriptionArrayAdapter(@NonNull Context context, int resource,
                                    @NonNull List<Subscription> objects) {
        super(context, resource, objects);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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
        // TODO: Put this code elsewhere??
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        dateTextView.setText(format1.format(sub.getStartDate()));

        TextView chargeTextView = row.findViewById(R.id.chargeTextView);
        chargeTextView.setText(NumberFormat.getCurrencyInstance().format(sub.getMonthlyCharge()));
        return row;
    }
}
