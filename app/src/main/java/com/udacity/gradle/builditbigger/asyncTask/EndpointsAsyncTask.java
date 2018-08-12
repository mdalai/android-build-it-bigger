package com.udacity.gradle.builditbigger.asyncTask;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by minga on 8/12/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    String jokeStr ="";
    private OnTaskCompleted taskCompleted;

    public EndpointsAsyncTask(OnTaskCompleted activityContext){
        this.taskCompleted = activityContext;
    }

    public EndpointsAsyncTask setListener(OnTaskCompleted listener) {
        this.taskCompleted = listener;
        return this;
    }


    @Override
    protected String doInBackground(String... strings) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory (), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer () {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        String name = strings[0];

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        jokeStr = result;
        taskCompleted.OnTaskCompleted (jokeStr);
    }
}
