/*
 * MyApplication
 *
 * Version 1.0
 *
 * January 4, 2018
 *
 * Copyright (c) 2018.
 */
package com.doupton.douptonsubbook;

import android.app.Application;

import java.util.ArrayList;

/**
 * Derives application to keep track of the subscription list
 */

public class MyApplication extends Application {

    /**
     * The list of subscriptions currently loaded
     */
    public static ArrayList<Subscription> subList;
}
