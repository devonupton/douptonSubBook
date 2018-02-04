package com.doupton.douptonsubbook;

// TODO Used for load/saving

import android.content.Context;
import android.text.style.SubscriptSpan;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SubscriptionManager {
    private static final String FILENAME = "subscriptions.sav";

    private Context context;

    public SubscriptionManager(Context context){
        this.context = context;
    }

    // TODO: SOurce code from labs
    public ArrayList<Subscription> loadFromFile(){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            return gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
    public void saveToFile(ArrayList<Subscription> subList){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();
            // TODO: Better error handling here
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
