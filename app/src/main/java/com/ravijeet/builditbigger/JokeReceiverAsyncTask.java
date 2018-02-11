package com.ravijeet.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ravijeet.backend.myApi.MyApi;
import java.io.IOException;

/**
 * Created by ravijeet on 11/02/18.
 */

public class JokeReceiverAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = JokeReceiverAsyncTask.class.getSimpleName();


    private MyApi jokeApiService = null;
    private JokeListener jokeListener;
    private Context context;

    interface JokeListener {
        void onJokeReceived(String joke);
    }

    public JokeReceiverAsyncTask(JokeListener jokeListener, Context context){
        this.jokeListener = jokeListener;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {

        if(jokeApiService == null){

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.app_engine_url))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeApiService = builder.build();
        }

        try{
            return jokeApiService.tellJoke().execute().getData();
        } catch (Exception e){
            Log.e(TAG, e.getMessage(), e);
            return "";
        }

    }

    @Override
    protected void onPostExecute(String response) {
        jokeListener.onJokeReceived(response);
    }
}
