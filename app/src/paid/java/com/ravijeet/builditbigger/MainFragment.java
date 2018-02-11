package com.ravijeet.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ravijeet.jokedisplay.JokeDisplayActivity;

/**
 * Created by ravijeet on 11/02/18.
 */

public class MainFragment extends Fragment implements JokeReceiverAsyncTask.JokeListener {

    private static final String JOKE = "JOKE";

    private Button jokeButton;
    private ProgressBar progressBar;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = rootView.findViewById(R.id.tellJokeButton);
        progressBar = rootView.findViewById(R.id.progressBar);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jokeButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new JokeReceiverAsyncTask(MainFragment.this, getActivity()).execute();

            }
        });
        return rootView;
    }

    @Override
    public void onJokeReceived(String joke) {
        jokeButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JOKE, joke);
        startActivity(intent);
    }
}
