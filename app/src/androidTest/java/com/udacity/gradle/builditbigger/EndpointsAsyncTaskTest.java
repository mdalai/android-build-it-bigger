package com.udacity.gradle.builditbigger;


import android.test.ActivityInstrumentationTestCase2;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.asyncTask.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.asyncTask.OnTaskCompleted;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;


/**
 * Created by minga on 8/10/2018.
 */
public class EndpointsAsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String TAG = "EndpointsAsyncTaskTest";
    String resJoke = null;
    CountDownLatch signal = null;


    public EndpointsAsyncTaskTest(){
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }


    @Test
    public void testAsynTask () throws InterruptedException  {
        MainActivity activity = getActivity ();
        EndpointsAsyncTask task = new EndpointsAsyncTask(activity);

        task.setListener (new OnTaskCompleted () {
            @Override
            public void OnTaskCompleted(String response) {
                resJoke = response;
                signal.countDown ();
            }
        }).execute ("paid");
        signal.await ();


        assertNotNull (resJoke);
        assertFalse(TextUtils.isEmpty(resJoke));
        //assertEquals ("Q: Why was the cat sitting on the computer?\n A: He was keeping an eye on the mouse!", resJoke);

    }

}