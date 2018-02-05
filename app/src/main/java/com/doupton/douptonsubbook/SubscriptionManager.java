/*
 * SubscriptionManager
 *
 * Version 1.0
 *
 * January 4, 2018
 *
 * Copyright (c) 2018.
 */
package com.doupton.douptonsubbook;

import android.content.Context;

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

/**
 * A manager for saving and loading Subscriptions
 */
public class SubscriptionManager {

    private static final String FILENAME = "subscriptions.sav";

    private Context context;

    /**
     * Construct a subscription manager
     * @param context context for saving and loading
     */
    public SubscriptionManager(Context context){
        this.context = context;
    }

    /**
     * Load the subscriptions
     * @return list of subscriptions
     */
    public ArrayList<Subscription> loadFromFile(){
        // Code modified from lonelytwitter lab
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

    /**
     * Save the subscription list to memory
     * @param subList subscription list
     */
    public void saveToFile(ArrayList<Subscription> subList){
        // Code modified from lonelytwitter lab
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
