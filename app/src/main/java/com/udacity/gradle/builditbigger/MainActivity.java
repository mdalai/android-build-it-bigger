package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidjokeslib.JokeActivity;
import com.udacity.gradle.builditbigger.asyncTask.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.asyncTask.OnTaskCompleted;



public class MainActivity extends AppCompatActivity implements OnTaskCompleted{
    String jokeStr;
    static String FLAVOR = "paid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeStr = new String ();
        EndpointsAsyncTask task = new EndpointsAsyncTask (MainActivity.this);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchJokeActivity(View view) {
        //Toast.makeText(this, myJoker.getJoke (), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (this, JokeActivity.class);
        intent.putExtra (JokeActivity.EXTRA_JOKE, jokeStr);
        startActivity (intent);
    }

    @Override
    public void OnTaskCompleted(String response) {
        jokeStr = response;
        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
    }



}
