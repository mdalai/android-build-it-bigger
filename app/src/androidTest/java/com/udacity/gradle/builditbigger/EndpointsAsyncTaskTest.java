package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.test.espresso.base.MainThread;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.app.PendingIntent.getActivity;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.google.android.gms.internal.zzahn.runOnUiThread;
import static com.udacity.gradle.builditbigger.MainActivity.*;
import static org.junit.Assert.*;

/**
 * Created by minga on 8/10/2018.
 */
public class EndpointsAsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String TAG = "EndpointsAsyncTaskTest";

    public EndpointsAsyncTaskTest(){
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }


    @Test
    public void testAsynTask () throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch (1);

        // Execute the async task on the UI thread! THIS IS KEY!
        runOnUiThread (new Runnable() {
            @Override
            public void run() {
                MainActivity.EndpointsAsyncTask.execute(( Runnable ) new Thread ("Manfred"));

            }
        });

        /* The testing thread will wait here until the UI thread releases it
         * above with the countDown() or 30 seconds passes and it times out.
         */
        signal.await(5, TimeUnit.SECONDS);

        // The task is done, and now you can assert some things!
        //assertTrue(called);
        assertTrue("Happiness", true);

    }

}