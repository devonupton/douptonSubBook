package com.doupton.douptonsubbook;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SubDetailsActivity extends AppCompatActivity {

    private Calendar startDate;
    private Subscription current;
    private SubscriptionManager manager;
    // Flag for whether delete was pressed
    private boolean canceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        canceled = false;
        initializeDatePicker();
        // TODO: Test if save file works
        this.manager = new SubscriptionManager(this);

        Intent intent = getIntent();
        int index = intent.getIntExtra(SubBookMainActivity.EXTRA_SUB_INDEX, -1);
        populateFields(index);
    }

    private void populateFields(int index) {

        // New subscription
        if (index < 0){
            // Just initialize the date
            this.current = null;
            this.startDate = Calendar.getInstance();
            updateDate();
        }
        else{
            this.current = MyApplication.subList.get(index);

            // Set name
            EditText nameEditText = findViewById(R.id.nameEditText);
            nameEditText.setText(current.getName());

            // Set date
            this.startDate = current.getStartDate();
            updateDate();

            // Set monthly charge
            // Source : https://stackoverflow.com/questions/10269045/
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            df.setGroupingUsed(false);
            EditText chargeEditText = findViewById(R.id.chargeEditText);
            chargeEditText.setText(df.format(current.getMonthlyCharge()));

            // Set comment
            EditText commentEditText = findViewById(R.id.commentEditText);
            commentEditText.setText(current.getComment());
        }
    }

    private void initializeDatePicker() {
        /* The following code is sourced form https://stackoverflow.com/questions/35604072 */

        EditText dateEditText= findViewById(R.id.dateEditText);
        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startDate.set(Calendar.YEAR, year);
                startDate.set(Calendar.MONTH, monthOfYear);
                startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };

        dateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(SubDetailsActivity.this, listener,
                        startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                        startDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        EditText dateEditText= findViewById(R.id.dateEditText);
        dateEditText.setText(format.format(startDate.getTime()));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Don't save anything if canceled
        if (canceled) return;

        // Attempt to save the current data
        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();

        if (name.length() > 0){
            EditText chargeEditText = findViewById(R.id.chargeEditText);
            EditText commentEditText = findViewById(R.id.commentEditText);

            String comment = commentEditText.getText().toString();
            String chargeString = chargeEditText.getText().toString();
            if (chargeString.length() == 0){
                chargeString = "0";
            }
            BigDecimal charge = new BigDecimal(chargeString);


            if (current == null){
                current = new Subscription(name, this.startDate, charge, comment);
                MyApplication.subList.add(current);
            }
            else{
                current.setName(name);
                current.setStartDate(this.startDate);
                current.setMonthlyCharge(charge);
                current.setComment(comment);
            }
            manager.saveToFile(MyApplication.subList);
        }
    }

    // Source: https://stackoverflow.com/questions/31387012/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteButton){
            if (this.current != null){
                MyApplication.subList.remove(current);
            }
            manager.saveToFile(MyApplication.subList);

            // TODO:  Nav back!!!
        }
        return super.onOptionsItemSelected(item);
    }
}
