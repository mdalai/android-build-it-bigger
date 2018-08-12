package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidjokeslib.JokeActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.asyncTask.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.asyncTask.OnTaskCompleted;

public class FreeMainActivity extends AppCompatActivity implements OnTaskCompleted{
    String jokeStr;
    static String FLAVOR = "free";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_free_main);

        jokeStr = new String ();
        EndpointsAsyncTask task = new EndpointsAsyncTask (FreeMainActivity.this);
        task.execute (FLAVOR);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchJokeActivity(View view) {
        Intent intent = new Intent (this, JokeActivity.class);
        intent.putExtra (JokeActivity.EXTRA_JOKE, jokeStr);
        startActivity (intent);
    }

    @Override
    public void OnTaskCompleted(String response) {
        jokeStr = response;
    }

}
