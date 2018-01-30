package com.wordpress.ayo218.spacenews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String API_URL = "https://api.spacexdata.com/v2/launches";
    private static final String UPCOMING_LAUNCHES_URL = "https://api.spacexdata.com/v1/launches/upcoming";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RocketAdapter adapter;
    private ArrayList<RocketData> dataArrayList;

    TextView errorMessage;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_space);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //adapter
        adapter = new RocketAdapter(this, dataArrayList);
        recyclerView.setAdapter(adapter);

        errorMessage = (TextView)findViewById(R.id.tv_error_message);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        loadSpaceData();
    }

    private void loadSpaceData() {
        showSpaceData();

        new RocketAsyncTask().execute(API_URL);
    }

    private void showErrorMessage(){
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void showSpaceData(){
        recyclerView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }


    private class RocketAsyncTask extends AsyncTask<String, Void, ArrayList<RocketData>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<RocketData> doInBackground(String... urls) {
            String eachspace = urls[0];
            URL spaceUrl = QueryUtils.buildUrl(eachspace);

            try {
                String jsonSpaceRespocse = QueryUtils.getResponseFromHttpUrl(spaceUrl);
                Log.i(LOG_TAG, "It worked");

               ArrayList<RocketData> simpleJson = JsonHelperUtils.getSimpleSpaceJsonData(MainActivity.this, jsonSpaceRespocse);
                return simpleJson;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<RocketData> s) {
            progressBar.setVisibility(View.INVISIBLE);
            if (s != null){
                adapter.setRocketData(s);
                adapter.notifyDataSetChanged();
                showSpaceData();
                errorMessage.setText("hei");
            } else{
                showErrorMessage();
            }

        }
    }

}
